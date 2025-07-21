package med.voll.api.application.services;

import jakarta.validation.ValidationException;
import med.voll.api.domain.models.Consulta;
import med.voll.api.domain.models.Medico;
import med.voll.api.domain.models.Paciente;
import med.voll.api.domain.dtos.consulta.DadosAgendamentoConsulta;
import med.voll.api.domain.dtos.consulta.DadosDetalhamentoConsulta;
import med.voll.api.infra.repositories.ConsultaRepository;
import med.voll.api.infra.repositories.MedicoRepository;
import med.voll.api.infra.repositories.PacienteRepository;
import med.voll.api.infra.validations.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsultaService {

    @Autowired
    private ConsultaRepository repository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;
    @Autowired
    private List<Validation> validators;

    public DadosDetalhamentoConsulta agendarConsulta(DadosAgendamentoConsulta dadosConsulta) {

        if (!pacienteRepository.existsById(dadosConsulta.idPaciente())) {
            throw new ValidationException("Paciente informado não existe");
        }

        validators.forEach(v -> v.validate(dadosConsulta));

        Medico medico = escolherMedico(dadosConsulta);

        Paciente paciente = pacienteRepository.getReferenceById(dadosConsulta.idPaciente());

        Consulta consulta = new Consulta(medico, paciente, dadosConsulta.data());

        repository.save(consulta);

        return new DadosDetalhamentoConsulta(consulta);

    }

    public Page<DadosDetalhamentoConsulta> findAll(Long idPaciente,Pageable paginacao ) {

        if (idPaciente != null) {
            return repository.findByPacienteId(idPaciente, paginacao).map(DadosDetalhamentoConsulta::new);
        }

        return repository.findAll(paginacao).map(DadosDetalhamentoConsulta::new);

    }

    public DadosDetalhamentoConsulta findConsultaById(Long id) {
        Consulta consulta = repository.getReferenceById(id);

        return new DadosDetalhamentoConsulta(consulta);
    }


    public void deleteConsulta(Long id) {

        Consulta consulta = repository.getReferenceById(id);

        repository.delete(consulta);
    }

    private Medico escolherMedico(DadosAgendamentoConsulta dadosConsulta) {


        if (dadosConsulta.idMedico() != null) {
            return medicoRepository.getReferenceById(dadosConsulta.idMedico());
        }

        if (dadosConsulta.especialidade() == null) {
            throw new ValidationException("Especialidade é obrigatória quando médico não for escolhido");
        }

        Medico medico = medicoRepository.MedicoAleatorio(dadosConsulta.especialidade(), dadosConsulta.data());

        if (medico == null) {
            throw new ValidationException("Especialidade não disponível nessa data");
        }

        return medico;
    }

}

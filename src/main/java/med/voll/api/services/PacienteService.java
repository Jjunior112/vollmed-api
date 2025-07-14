package med.voll.api.services;

import med.voll.api.domain.Paciente;
import med.voll.api.dtos.DadosCadastroPaciente;
import med.voll.api.dtos.DadosListagemPaciente;
import med.voll.api.dtos.UpdatePaciente;
import med.voll.api.repositories.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository repository;


    public Paciente createPaciente(DadosCadastroPaciente dados) {

        Paciente paciente = new Paciente(dados);

        repository.save(paciente);

        return paciente;

    }

    public Page<DadosListagemPaciente> findAllPacientes(Pageable paginacao) {
        return repository.findAllByIsActiveTrue(paginacao).map(DadosListagemPaciente::new);
    }

    public DadosListagemPaciente findById(long id) {
        try {
            Paciente paciente = repository.getReferenceById(id);

            return new DadosListagemPaciente(paciente);

        } catch (Exception ex) {
            throw new NullPointerException("Paciente não encontrado!");
        }
    }

    public DadosListagemPaciente updatePaciente(long id, UpdatePaciente update) {
        try {
            Paciente paciente = repository.getReferenceById(id);

            paciente.atualizarInformacoes(update);

            return new DadosListagemPaciente(paciente);


        } catch (Exception ex) {
            throw new NullPointerException("Paciente não encontrado!");
        }

    }

    public void deletePaciente(long id) {

            Paciente paciente = repository.getReferenceById(id);

            paciente.deletePaciente();

    }
}

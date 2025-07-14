package med.voll.api.medico.service;


import jakarta.persistence.EntityNotFoundException;
import med.voll.api.medico.domain.Medico;
import med.voll.api.medico.dto.DadosCadastroMedico;
import med.voll.api.medico.dto.DadosListagemMedico;
import med.voll.api.medico.dto.DeleteMedico;
import med.voll.api.medico.dto.UpdateMedico;
import med.voll.api.medico.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MedicoService {

    @Autowired
    private MedicoRepository repository;

    public Medico createMedico(DadosCadastroMedico data) {
        Medico medico = new Medico(data);

        repository.save(medico);

        return medico;

    }

    public Page<DadosListagemMedico> findAllMedicos(Pageable paginacao) {
        return repository.findAllByIsActiveTrue(paginacao).map(DadosListagemMedico::new);
    }

    public Optional<DadosListagemMedico> findMedicoById(long id) {

        Optional<Medico> optionalMedico = repository.findById(id);

        return optionalMedico.map(DadosListagemMedico::new);

    }

    public DadosListagemMedico updateMedico(long id, UpdateMedico update) {

        try {
            Medico medico = repository.getReferenceById(id);
            medico.atualizarInformacoes(update);
            return new DadosListagemMedico(medico);
        } catch (EntityNotFoundException ex) {

            throw new NullPointerException( "Médico não encontrado!");
        }

    }

    public DeleteMedico deleteMedico(long id) {
        try {
            Medico medico = repository.getReferenceById(id);
            medico.deleteMedico();
            return new DeleteMedico(true, "Médico inativado!");
        } catch (EntityNotFoundException ex) {
            return new DeleteMedico(false, "Médico não encontrado!");
        }

    }


}

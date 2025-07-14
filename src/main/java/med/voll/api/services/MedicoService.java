package med.voll.api.services;


import jakarta.persistence.EntityNotFoundException;
import med.voll.api.domain.Medico;
import med.voll.api.dtos.DadosCadastroMedico;
import med.voll.api.dtos.DadosListagemMedico;
import med.voll.api.dtos.UpdateMedico;
import med.voll.api.repositories.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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

            throw new NullPointerException("Médico não encontrado!");
        }

    }

    public void deleteMedico(long id) {

        Medico medico = repository.getReferenceById(id);
        medico.deleteMedico();


    }


}

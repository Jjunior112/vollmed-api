package med.voll.api.medico.service;


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
        Medico medico = repository.getReferenceById(id);

        if(medico==null)
        {
            return null;
        }

        medico.atualizarInformacoes(update);

        return new DadosListagemMedico(medico);

    }

    public DeleteMedico deleteMedico(long id) {
        Medico medico = repository.getReferenceById(id);

        if (medico == null) {
            return new DeleteMedico(false, "Medico não encontrado!");
        }

        medico.deleteMedico();


        return new DeleteMedico(true, "Medico inativo!");

    }


}

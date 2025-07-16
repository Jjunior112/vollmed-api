package med.voll.api.services;


import med.voll.api.domain.Medico;
import med.voll.api.dtos.medico.DadosCadastroMedico;
import med.voll.api.dtos.medico.DadosListagemMedico;
import med.voll.api.dtos.medico.UpdateMedico;
import med.voll.api.repositories.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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

    public Medico findMedicoById(long id) {

        Medico medico = repository.getReferenceById(id);

        return medico;

    }

    public DadosListagemMedico updateMedico(long id, UpdateMedico update) {

        Medico medico = repository.getReferenceById(id);
        medico.atualizarInformacoes(update);
        return new DadosListagemMedico(medico);


    }

    public void deleteMedico(long id) {

        Medico medico = repository.getReferenceById(id);
        medico.deleteMedico();


    }


}

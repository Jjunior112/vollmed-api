package med.voll.api.medico.service;


import med.voll.api.medico.domain.Medico;
import med.voll.api.medico.dto.DadosCadastroMedico;
import med.voll.api.medico.dto.DadosListagemMedico;
import med.voll.api.medico.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicoService {

    @Autowired
    private MedicoRepository repository;

    public Medico createMedico(DadosCadastroMedico data)
    {
        Medico medico = new Medico(data);

        repository.save(medico);

        return medico;

    }

    public Page<DadosListagemMedico> findAllMedicos(Pageable paginacao)
    {
        return repository.findAll(paginacao).map(DadosListagemMedico::new);
    }


}

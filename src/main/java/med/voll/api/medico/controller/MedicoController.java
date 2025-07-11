package med.voll.api.medico.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.medico.domain.Medico;
import med.voll.api.medico.dto.DadosCadastroMedico;
import med.voll.api.medico.dto.DadosListagemMedico;
import med.voll.api.medico.service.MedicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private MedicoService medicoService;

    @PostMapping
    @Transactional
    public ResponseEntity<Medico> cadastrar(@RequestBody @Valid DadosCadastroMedico dados) {
        Medico response = this.medicoService.createMedico(dados);

        return new ResponseEntity<>(response, HttpStatus.CREATED);

    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemMedico>> GetAllMedicos( @PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {

        Page<DadosListagemMedico> medicos = medicoService.findAllMedicos(paginacao);

        return new ResponseEntity<>(medicos, HttpStatus.OK);
    }


}

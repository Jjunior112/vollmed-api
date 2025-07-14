package med.voll.api.medico.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.medico.domain.Medico;
import med.voll.api.medico.dto.DadosCadastroMedico;
import med.voll.api.medico.dto.DadosListagemMedico;
import med.voll.api.medico.dto.DeleteMedico;
import med.voll.api.medico.dto.UpdateMedico;
import med.voll.api.medico.service.MedicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private MedicoService medicoService;

    @PostMapping
    @Transactional
    public ResponseEntity<DadosListagemMedico> cadastrar(@RequestBody @Valid DadosCadastroMedico dados) {
        Medico response = this.medicoService.createMedico(dados);

        return new ResponseEntity<>(new DadosListagemMedico(response), HttpStatus.CREATED);

    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemMedico>> GetAllMedicos(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {

        Page<DadosListagemMedico> medicos = medicoService.findAllMedicos(paginacao);

        return new ResponseEntity<>(medicos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<DadosListagemMedico>> getById(@PathVariable long id) {
        Optional<DadosListagemMedico> medico = this.medicoService.findMedicoById(id);

        return new ResponseEntity<>(medico, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DadosListagemMedico> updateMedico(@PathVariable long id, @RequestBody UpdateMedico update) {
        DadosListagemMedico updatedMedico = this.medicoService.updateMedico(id, update);

        if (updatedMedico == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(updatedMedico, HttpStatus.OK);


    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<String> deleteMedico(@PathVariable long id) {
        DeleteMedico response = this.medicoService.deleteMedico(id);

        if (response.success()) {
            return new ResponseEntity<>(response.message(), HttpStatus.OK);
        }

        return new ResponseEntity<>(response.message(), HttpStatus.NOT_FOUND);
    }
}

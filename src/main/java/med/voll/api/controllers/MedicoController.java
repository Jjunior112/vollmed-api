package med.voll.api.controllers;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.Medico;
import med.voll.api.dtos.medico.DadosCadastroMedico;
import med.voll.api.dtos.medico.DadosListagemMedico;
import med.voll.api.dtos.medico.UpdateMedico;
import med.voll.api.services.MedicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private MedicoService medicoService;

    @PostMapping
    @Transactional
    public ResponseEntity<DadosListagemMedico> cadastrar(@RequestBody @Valid DadosCadastroMedico dados, UriComponentsBuilder uriBuilder) {
        Medico response = this.medicoService.createMedico(dados);

        var uri = uriBuilder.path("/medicos/{id}").buildAndExpand(response.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosListagemMedico(response));

    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemMedico>> GetAllMedicos(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {

        Page<DadosListagemMedico> medicos = medicoService.findAllMedicos(paginacao);

        return new ResponseEntity<>(medicos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosListagemMedico> getById(@PathVariable long id) {
        Medico medico = this.medicoService.findMedicoById(id);

        return new ResponseEntity<>(new DadosListagemMedico(medico), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DadosListagemMedico> updateMedico(@PathVariable long id, @RequestBody UpdateMedico update) {
        DadosListagemMedico updatedMedico = this.medicoService.updateMedico(id, update);

        return new ResponseEntity<>(updatedMedico, HttpStatus.OK);

    }
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deleteMedico(@PathVariable long id) {

        this.medicoService.deleteMedico(id);

        return ResponseEntity.noContent().build();
    }
}

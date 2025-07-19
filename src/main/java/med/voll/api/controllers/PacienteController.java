package med.voll.api.controllers;

import jakarta.transaction.Transactional;
import med.voll.api.domain.models.Paciente;
import med.voll.api.domain.dtos.paciente.DadosCadastroPaciente;
import med.voll.api.domain.dtos.paciente.DadosListagemPaciente;
import med.voll.api.domain.dtos.paciente.UpdatePaciente;
import med.voll.api.application.services.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    private PacienteService service;


    @PostMapping
    @Transactional
    public ResponseEntity<DadosListagemPaciente> create(@RequestBody DadosCadastroPaciente dados, UriComponentsBuilder uriBuilder) {

        Paciente response = service.createPaciente(dados);

        var uri = uriBuilder.path("pacientes/{id}").buildAndExpand(response.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosListagemPaciente(response));
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemPaciente>> getAllPacientes(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {

        Page<DadosListagemPaciente> pacientes = service.findAllPacientes(paginacao);

        return new ResponseEntity<>(pacientes, HttpStatus.OK);

    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosListagemPaciente> getById(@PathVariable long id) {

        Paciente paciente = service.findById(id);



        return new ResponseEntity<>(new DadosListagemPaciente(paciente), HttpStatus.OK);

    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DadosListagemPaciente> updatePaciente(@PathVariable long id, @RequestBody UpdatePaciente update) {

        DadosListagemPaciente paciente = service.updatePaciente(id, update);

        return new ResponseEntity<>(paciente, HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<String> deletePaciente(@PathVariable long id) {

        service.deletePaciente(id);

        return ResponseEntity.noContent().build();
    }


}

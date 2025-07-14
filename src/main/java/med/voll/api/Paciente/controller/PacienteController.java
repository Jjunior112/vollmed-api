package med.voll.api.Paciente.controller;

import jakarta.transaction.Transactional;
import med.voll.api.Paciente.domain.Paciente;
import med.voll.api.Paciente.dto.DadosCadastroPaciente;
import med.voll.api.Paciente.dto.DadosListagemPaciente;
import med.voll.api.Paciente.dto.DeletePaciente;
import med.voll.api.Paciente.dto.UpdatePaciente;
import med.voll.api.Paciente.service.PacienteService;
import med.voll.api.medico.dto.DadosListagemMedico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriBuilder;
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

        DadosListagemPaciente paciente = service.findById(id);

        return new ResponseEntity<>(paciente, HttpStatus.OK);

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
        DeletePaciente response = service.deletePaciente(id);

        if (response.success()) {
            return new ResponseEntity<>(response.message(), HttpStatus.OK);
        }

        return new ResponseEntity<>(response.message(), HttpStatus.NOT_FOUND);
    }


}

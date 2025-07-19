package med.voll.api.controllers;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.dtos.consulta.DadosAgendamentoConsulta;
import med.voll.api.domain.dtos.consulta.DadosDetalhamentoConsulta;
import med.voll.api.application.services.ConsultaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/consultas")
public class ConsultaController {

    @Autowired
    private ConsultaService consultaService;

    @PostMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoConsulta> AgendarConsulta(@RequestBody @Valid DadosAgendamentoConsulta dadosConsulta, UriComponentsBuilder uriBuilder) {

        var response = consultaService.agendarConsulta(dadosConsulta);

        var uri = uriBuilder.path("/consultas/{id}").buildAndExpand(response.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoConsulta(response));

    }

    @GetMapping
    public ResponseEntity<Page<DadosDetalhamentoConsulta>> getAllConsultas(@PageableDefault(size = 10, sort = {"data"}) Pageable paginacao, @RequestParam(name = "paciente", required = false) Long idPaciente) {

        Page<DadosDetalhamentoConsulta> consultas = consultaService.findAll(idPaciente, paginacao);

        return new ResponseEntity<>(consultas, HttpStatus.OK);
    }

    @GetMapping("/{id}")

    public ResponseEntity<DadosDetalhamentoConsulta> getById(@PathVariable Long id) {

        DadosDetalhamentoConsulta response = consultaService.findConsultaById(id);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @DeleteMapping("{id}")
    @Transactional

    public ResponseEntity deleteConsulta(@PathVariable Long id) {
        consultaService.deleteConsulta(id);

        return ResponseEntity.noContent().build();
    }

}

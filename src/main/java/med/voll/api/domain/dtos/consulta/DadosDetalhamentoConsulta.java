package med.voll.api.domain.dtos.consulta;

import med.voll.api.domain.models.Consulta;

import java.time.LocalDateTime;

public record DadosDetalhamentoConsulta(Long id, Long idMedico, Long idPaciente, LocalDateTime data) {
    public DadosDetalhamentoConsulta(Consulta consulta) {

        this(consulta.getId(), consulta.getMedico().getId(), consulta.getPaciente().getId(), consulta.getData());
    }
}

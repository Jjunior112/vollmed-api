package med.voll.api.domain.dtos.consulta;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.enums.Especialidade;

import java.time.LocalDateTime;

public record DadosAgendamentoConsulta(
        Long idMedico,
        @NotNull
        Long idPaciente

        ,
        @NotNull
        @Future
        LocalDateTime data,

        Especialidade especialidade

) {
}

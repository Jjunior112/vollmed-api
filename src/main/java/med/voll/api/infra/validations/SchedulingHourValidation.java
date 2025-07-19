package med.voll.api.infra.validations;

import jakarta.validation.ValidationException;
import med.voll.api.domain.dtos.consulta.DadosAgendamentoConsulta;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class SchedulingHourValidation implements Validation{

    public void validate(DadosAgendamentoConsulta consulta) {
        var dataConsulta = consulta.data();

        var now = LocalDateTime.now();

        var diference = Duration.between(now, dataConsulta).toMinutes();

        if (diference < 30) {
            throw new ValidationException("A consulta deve ser agendada com 30 minutos de antecedência!");
        }


    }
}

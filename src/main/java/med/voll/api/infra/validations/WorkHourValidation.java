package med.voll.api.infra.validations;

import jakarta.validation.ValidationException;
import med.voll.api.domain.dtos.consulta.DadosAgendamentoConsulta;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Component
public class WorkHourValidation implements Validation {

    public void validate(DadosAgendamentoConsulta consulta) {
        var dataConsulta = consulta.data();

        var sunday = dataConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY);

        var beforeHour = dataConsulta.getHour() < 7;

        var afterHour = dataConsulta.getHour() > 18;

        if (sunday || beforeHour || afterHour) {
            throw new ValidationException("Consulta fora do horário de funcionamento da clinica!");
        }


    }
}

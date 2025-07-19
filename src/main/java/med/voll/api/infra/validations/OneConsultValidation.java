package med.voll.api.infra.validations;

import jakarta.validation.ValidationException;
import med.voll.api.domain.dtos.consulta.DadosAgendamentoConsulta;
import med.voll.api.infra.repositories.ConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OneConsultValidation implements Validation{

    @Autowired
    private ConsultaRepository consultaRepository;

    public void validate(DadosAgendamentoConsulta consulta) {
       var firstHour = consulta.data().withHour(7);

       var lastHour = consulta.data().withHour(18);

       var isOneConsult = consultaRepository.existsByPacienteIdAndDataBetween(consulta.idPaciente(),firstHour,lastHour);

       if(isOneConsult)
       {
           throw new ValidationException("Paciente já possui consulta agendada nesse dia!");
       }
    }

}

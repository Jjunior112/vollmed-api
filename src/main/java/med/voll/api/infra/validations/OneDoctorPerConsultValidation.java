package med.voll.api.infra.validations;

import jakarta.validation.ValidationException;
import med.voll.api.domain.dtos.consulta.DadosAgendamentoConsulta;
import med.voll.api.infra.repositories.ConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OneDoctorPerConsultValidation implements Validation {

    @Autowired
    private ConsultaRepository consultaRepository;

    public void validate(DadosAgendamentoConsulta consulta) {
        var validateConsult = consultaRepository.existsByMedicoIdAndData(consulta.idMedico(), consulta.data());

        if (validateConsult) {
            throw new ValidationException("Medico informado ja possui consulta agendada nesse mesmo horario!");
        }
    }
}

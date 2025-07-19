package med.voll.api.infra.validations;

import jakarta.validation.ValidationException;
import med.voll.api.domain.dtos.consulta.DadosAgendamentoConsulta;
import med.voll.api.infra.repositories.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InactivePatientValidation implements Validation {
    @Autowired
    private PacienteRepository pacienteRepository;

    public void validate(DadosAgendamentoConsulta consulta) {


        if (consulta.idMedico() == null) {
            return;
        }

        var isActivePatient = pacienteRepository.findIsActiveById(consulta.idMedico());

        if (isActivePatient == null || !isActivePatient) {
            throw new ValidationException("Não foi possivel agendar consulta, médico informado está inativo!");
        }


    }
}

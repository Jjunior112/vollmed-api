package med.voll.api.infra.validations;


import jakarta.validation.ValidationException;
import med.voll.api.domain.dtos.consulta.DadosAgendamentoConsulta;
import med.voll.api.infra.repositories.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InactiveDoctorValidation implements Validation {
    @Autowired
    private MedicoRepository medicoRepository;

    public void validate(DadosAgendamentoConsulta consulta) {

        if (consulta.idMedico() == null) {
            return;
        }

        var isActiveDoctor = medicoRepository.findIsActiveById(consulta.idMedico());


        if ( isActiveDoctor == null||!isActiveDoctor ) {
            throw new ValidationException("Não foi possivel agendar consulta, médico informado não existe ou está inativo!");
        }


    }

}

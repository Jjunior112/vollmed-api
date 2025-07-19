package med.voll.api.infra.validations;

import med.voll.api.domain.dtos.consulta.DadosAgendamentoConsulta;

public interface Validation {

    void validate(DadosAgendamentoConsulta consulta);
}

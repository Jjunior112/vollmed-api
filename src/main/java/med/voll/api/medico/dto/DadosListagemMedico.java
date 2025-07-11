package med.voll.api.medico.dto;

import med.voll.api.medico.domain.Medico;
import med.voll.api.medico.enums.Especialidade;

public record DadosListagemMedico(String nome, String email, String crm, Especialidade especialidade) {
    public DadosListagemMedico(Medico medico)
    {
        this(medico.getNome(),medico.getEmail(), medico.getCrm(), medico.getEspecialidade());
    }
}

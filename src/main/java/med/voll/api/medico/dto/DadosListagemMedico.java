package med.voll.api.medico.dto;

import med.voll.api.medico.domain.Medico;
import med.voll.api.medico.enums.Especialidade;

public record DadosListagemMedico(long id, String nome, String email, String crm, Especialidade especialidade, boolean isActive) {
    public DadosListagemMedico(Medico medico)
    {
        this(medico.getId(), medico.getNome(),medico.getEmail(), medico.getCrm(), medico.getEspecialidade(), medico.isActive());
    }
}

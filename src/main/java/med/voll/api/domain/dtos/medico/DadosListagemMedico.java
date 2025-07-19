package med.voll.api.domain.dtos.medico;

import med.voll.api.domain.models.Medico;
import med.voll.api.domain.enums.Especialidade;

public record DadosListagemMedico(long id, String nome, String email, String crm, Especialidade especialidade, boolean isActive) {
    public DadosListagemMedico(Medico medico)
    {
        this(medico.getId(), medico.getNome(),medico.getEmail(), medico.getCrm(), medico.getEspecialidade(), medico.isActive());
    }
}

package med.voll.api.domain.dtos.medico;


import med.voll.api.domain.dtos.endereco.DadosEndereco;

public record UpdateMedico(String nome, String telefone, DadosEndereco endereco, boolean isActive) {
}

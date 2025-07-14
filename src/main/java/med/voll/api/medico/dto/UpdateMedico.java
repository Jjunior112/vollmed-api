package med.voll.api.medico.dto;


import med.voll.api.endereco.dto.DadosEndereco;

public record UpdateMedico(String nome, String telefone, DadosEndereco endereco, boolean isActive) {
}

package med.voll.api.dtos;

import med.voll.api.endereco.dto.DadosEndereco;

public record UpdatePaciente(String nome, String telefone, DadosEndereco endereco, boolean isActive) {
}

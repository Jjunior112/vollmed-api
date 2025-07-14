package med.voll.api.Paciente.dto;

import med.voll.api.endereco.dto.DadosEndereco;

public record UpdatePaciente(String nome, String telefone, DadosEndereco endereco, boolean isActive) {
}

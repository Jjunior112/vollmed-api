package med.voll.api.domain.dtos.paciente;

import med.voll.api.domain.dtos.endereco.DadosEndereco;

public record UpdatePaciente(String nome, String telefone, DadosEndereco endereco, boolean isActive) {
}

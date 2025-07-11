package med.voll.api.endereco.domain;

import jakarta.persistence.Embeddable;
import lombok.*;
import med.voll.api.endereco.dto.DadosEndereco;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Endereco {

    private String logradouro;
    private String bairro;
    private String cep;
    private String cidade;
    private String uf;
    private String complemento;
    private String numero;

    public Endereco(DadosEndereco data) {
        this.logradouro = data.logradouro();
        this.bairro = data.bairro();
        this.numero = data.numero();
        this.complemento = data.complemento();
        this.cidade = data.cidade();
        this.cep = data.cep();
        this.uf = data.uf();
    }
}

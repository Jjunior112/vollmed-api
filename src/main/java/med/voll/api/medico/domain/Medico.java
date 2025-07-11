package med.voll.api.medico.domain;

import jakarta.persistence.*;
import lombok.*;
import med.voll.api.endereco.domain.Endereco;
import med.voll.api.medico.dto.DadosCadastroMedico;
import med.voll.api.medico.enums.Especialidade;


@Entity
@Table(name="medico")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")

public class Medico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Column(unique = true)
    private String email;


    private String telefone;

    @Column(unique = true)
    private String crm;

    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;

    @Embedded
    private Endereco endereco;

    public Medico(DadosCadastroMedico data)
    {
        this.nome = data.nome();
        this.email = data.email();
        this.telefone = data.telefone();
        this.crm = data.crm();
        this.especialidade = data.especialidade();
        this.endereco = new Endereco(data.endereco());
    }
}

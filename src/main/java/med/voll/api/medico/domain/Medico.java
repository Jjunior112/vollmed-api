package med.voll.api.medico.domain;

import jakarta.persistence.*;
import lombok.*;
import med.voll.api.endereco.DadosEndereco;
import med.voll.api.endereco.domain.Endereco;
import med.voll.api.medico.enums.Especialidade;


@Entity(name="medicos")
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

    private String email;

    private String crm;

    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;

    @Embedded
    private Endereco endereco;
}

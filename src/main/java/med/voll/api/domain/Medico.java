package med.voll.api.domain;

import jakarta.persistence.*;
import lombok.*;
import med.voll.api.endereco.domain.Endereco;
import med.voll.api.dtos.DadosCadastroMedico;
import med.voll.api.dtos.UpdateMedico;
import med.voll.api.enums.Especialidade;


@Entity
@Table(name = "medico")
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

    private boolean isActive;

    public Medico(DadosCadastroMedico data) {
        this.nome = data.nome();
        this.email = data.email();
        this.telefone = data.telefone();
        this.crm = data.crm();
        this.isActive = true;
        this.especialidade = data.especialidade();
        this.endereco = new Endereco(data.endereco());

    }

    public void atualizarInformacoes(UpdateMedico update) {
        if (update.nome() != null) {
            this.nome = update.nome();
        }

        if (update.endereco() != null) {
            this.endereco.atualizarInformacoes(update.endereco());
        }

        if (update.telefone() != null) {
            this.telefone = update.telefone();
        }
        if (update.isActive()) {
            this.isActive = update.isActive();
        }
    }

    public void deleteMedico() {
        this.isActive = false;
    }
}

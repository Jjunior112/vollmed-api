package med.voll.api.domain;


import jakarta.persistence.*;
import lombok.*;
import med.voll.api.dtos.paciente.DadosCadastroPaciente;
import med.voll.api.dtos.paciente.UpdatePaciente;
import med.voll.api.endereco.domain.Endereco;

@Entity
@Table(name = "pacientes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")

public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Column(unique = true, nullable = false)

    private String email;

    private String telefone;

    @Column(unique = true, nullable = false)
    private String cpf;

    @Embedded
    private Endereco endereco;

    private boolean isActive;

    public Paciente(DadosCadastroPaciente dados) {
        this.nome = dados.nome();
        this.email = dados.email();
        this.telefone = dados.telefone();
        this.cpf = dados.cpf();
        this.endereco = new Endereco(dados.endereco());
        this.isActive = true;

    }

    public void atualizarInformacoes(UpdatePaciente update) {
        if (update.nome() != null) {
            this.nome = update.nome();
        }
        if (update.telefone() != null) {
            this.telefone = update.telefone();
        }
        if (update.endereco() != null) {
            this.endereco.atualizarInformacoes(update.endereco());
        }
        if (update.isActive()) {
            this.isActive = update.isActive();
        }
    }

    public void deletePaciente()
    {
        this.isActive = false;
    }


}

package med.voll.api.infra.repositories;

import jakarta.persistence.EntityManager;
import med.voll.api.domain.dtos.endereco.DadosEndereco;
import med.voll.api.domain.dtos.medico.DadosCadastroMedico;
import med.voll.api.domain.dtos.paciente.DadosCadastroPaciente;
import med.voll.api.domain.enums.Especialidade;
import med.voll.api.domain.models.Consulta;
import med.voll.api.domain.models.Medico;
import med.voll.api.domain.models.Paciente;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class MedicoRepositoryTest {

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    EntityManager em;

    @Test
    @DisplayName("Deveria devolver null quando unico medico cadastrado não está disponivel")
    void medicoAleatorioCenario1() {

        //given ou arrange

        var nextMonday = LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .atTime(10, 0);

        var medico = cadastrarMedico("medico", "medico@medico.com", "123456", Especialidade.CARDIOLOGIA);
        var paciente = cadastrarPaciente("paciente","paciente@paciente.com","12345678900");

        cadastrarConsulta(medico, paciente, nextMonday);

        //when ou act

        var medicoLive = medicoRepository.MedicoAleatorio(Especialidade.CARDIOLOGIA, nextMonday);

        //then ou assert

        assertThat(medicoLive).isNull();
    }
    @Test
    @DisplayName("Deveria devolver medico quando ele estiver disponivel na data")
    void medicoAleatorioCenario2() {

        //given ou arrange

        var nextMonday = LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .atTime(10, 0);

        var medico = cadastrarMedico("medico", "medico@medico.com", "123456", Especialidade.CARDIOLOGIA);

        //when ou act

        var medicoLive = medicoRepository.MedicoAleatorio(Especialidade.CARDIOLOGIA, nextMonday);

        //then ou assert

        assertThat(medicoLive).isEqualTo(medico);
    }

    private void cadastrarConsulta(Medico medico, Paciente paciente, LocalDateTime data) {
        em.persist(new Consulta(medico, paciente, data));
    }

    private Medico cadastrarMedico(String nome, String email, String crm, Especialidade especialidade) {
        var medico = new Medico(DadosMedico(nome, email, crm, especialidade));
        em.persist(medico);
        return medico;
    }

    private Paciente cadastrarPaciente(String nome, String email, String cpf) {
        var paciente = new Paciente(dadosPaciente(nome, email, cpf));
        em.persist(paciente);
        return paciente;
    }

    private DadosCadastroMedico DadosMedico(String nome, String email, String crm, Especialidade especialidade) {
        return new DadosCadastroMedico(
                nome,
                email,
                "1234567890",
                crm,
                especialidade,
                dadosEndereco()
        );
    }

    private DadosCadastroPaciente dadosPaciente(String nome, String email, String cpf) {
        return new DadosCadastroPaciente(
                nome,
                email,
                "121234567809",
                cpf,
                dadosEndereco()
        );
    }

    private DadosEndereco dadosEndereco() {
        return new DadosEndereco(
                "rua a",
                "bairro",
                "00000000",
                "cidade",
                "uf",
                null,
                null
        );
    }
}
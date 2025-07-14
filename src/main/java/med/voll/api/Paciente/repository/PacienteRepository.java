package med.voll.api.Paciente.repository;

import med.voll.api.Paciente.domain.Paciente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PacienteRepository extends JpaRepository<Paciente,Long> {
    Page<Paciente> findAllByIsActiveTrue(Pageable paginacao);
}

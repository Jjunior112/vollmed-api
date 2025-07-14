package med.voll.api.medico.repository;

import med.voll.api.medico.domain.Medico;
import med.voll.api.medico.dto.UpdateMedico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MedicoRepository extends JpaRepository<Medico,Long> {


    Page<Medico> findAllByIsActiveTrue(Pageable paginacao);
}

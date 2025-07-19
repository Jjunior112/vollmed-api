package med.voll.api.infra.repositories;

import med.voll.api.domain.models.Paciente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PacienteRepository extends JpaRepository<Paciente,Long> {
    Page<Paciente> findAllByIsActiveTrue(Pageable paginacao);


    @Query(
            """
                    select p.isActive from Paciente p 
                    where p.id = :id                
                    """
    )
    Boolean findIsActiveById(Long id);
}

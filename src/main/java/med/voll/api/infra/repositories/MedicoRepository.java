package med.voll.api.infra.repositories;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.models.Medico;
import med.voll.api.domain.enums.Especialidade;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface MedicoRepository extends JpaRepository<Medico, Long> {


    Page<Medico> findAllByIsActiveTrue(Pageable paginacao);

    @Query("""
            SELECT m FROM Medico m 
            WHERE 
            m.isActive = true
            AND 
            m.especialidade = :especialidade
            AND
            
            m.id not in(
            
            SELECT c.medico.id FROM consulta c 
            WHERE c.data = :data
            
            )
            
            order by rand()
            limit 1
            
            """)
    Medico MedicoAleatorio(Especialidade especialidade, @NotNull @Future LocalDateTime data);

    @Query(
            """
                    select m.isActive from Medico m 
                    where m.id = :id                
                    """
    )
    Boolean findIsActiveById(Long id);
}

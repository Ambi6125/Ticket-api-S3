package sem3.project.individual.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import sem3.project.individual.persistence.DTO.EventTicketCountDTO;
import sem3.project.individual.persistence.entity.TicketEntity;

import java.util.List;


public interface TicketRepository extends JpaRepository<TicketEntity, Long>
{
    @Query("SELECT new sem3.project.individual.persistence.DTO.EventTicketCountDTO(t.event.title, COUNT(t)) FROM TicketEntity t WHERE t.account.id = :accountId GROUP BY t.event.title")
    List<EventTicketCountDTO> findUserTicketsWithCount(@Param("accountId") Long accountId);

}

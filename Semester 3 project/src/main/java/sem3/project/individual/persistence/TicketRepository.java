package sem3.project.individual.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import sem3.project.individual.persistence.entity.TicketEntity;

public interface TicketRepository extends JpaRepository<TicketEntity, Long>
{

}

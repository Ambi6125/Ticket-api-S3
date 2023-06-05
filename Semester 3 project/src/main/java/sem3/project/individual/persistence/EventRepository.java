package sem3.project.individual.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import sem3.project.individual.persistence.entity.EventEntity;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

public interface EventRepository extends JpaRepository<EventEntity, Long>
{
    boolean existsByLocationIgnoreCaseAndMoment(String location, Instant moment);
    Optional<EventEntity> findByTitleIgnoreCase(String title);
    List<EventEntity> findAllByTitleContainingIgnoreCaseOrLocationContainingIgnoreCase(String query);
}

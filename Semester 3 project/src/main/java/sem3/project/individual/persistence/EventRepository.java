package sem3.project.individual.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import sem3.project.individual.persistence.entity.EventEntity;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

public interface EventRepository extends JpaRepository<EventEntity, Long>
{
    boolean existsByLocationIgnoreCaseAndMoment(String location, Instant moment);
    Optional<EventEntity> findByTitleIgnoreCase(String title);

    /**
     * Finds all events whose title or location matches the query provided.
     * @param query Search term.
     * @return A List containing all qualifying entities.
     */
    @Query("SELECT e FROM EventEntity e WHERE LOWER(e.title) LIKE LOWER(CONCAT('%', :query, '%')) OR LOWER(e.location) LIKE LOWER(CONCAT('%', :query, '%'))")
    List<EventEntity> findBySearchQuery(@Param("query") String query);

    @Query("SELECT e FROM EventEntity e WHERE e.id = :id")
    EventEntity fetchById(@Param("id") Long id);
}

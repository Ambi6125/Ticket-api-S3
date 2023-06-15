package sem3.project.individual.persistence;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import sem3.project.individual.persistence.entity.EventEntity;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

public interface EventRepository extends JpaRepository<EventEntity, Long> {
    boolean existsByLocationIgnoreCaseAndMoment(String location, Instant moment);

    Optional<EventEntity> findByTitleIgnoreCase(String title);

    @Query("SELECT e FROM EventEntity e WHERE LOWER(e.title) LIKE LOWER(CONCAT('%', :query, '%')) OR LOWER(e.location) LIKE LOWER(CONCAT('%', :query, '%'))")
    List<EventEntity> findBySearchQuery(@Param("query") String query);

    @Query("SELECT e FROM EventEntity e WHERE e.id = :id")
    EventEntity fetchById(@Param("id") Long id);

    @Query("SELECT e FROM EventEntity e ORDER BY FUNCTION('RAND')")
    List<EventEntity> getRandomEvents(Pageable pageable);

}
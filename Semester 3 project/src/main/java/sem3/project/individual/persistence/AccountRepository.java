package sem3.project.individual.persistence;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import sem3.project.individual.persistence.entity.AccountEntity;

public interface AccountRepository extends JpaRepository<AccountEntity, Long>
{
    boolean existsByUsername(String username);
    AccountEntity findByUsername(String username);

    @Query("SELECT e FROM AccountEntity e WHERE e.id = :id")
    AccountEntity fetchById(@Param("id") Long id);
}

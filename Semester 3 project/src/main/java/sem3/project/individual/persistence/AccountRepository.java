package sem3.project.individual.persistence;


import org.springframework.data.jpa.repository.JpaRepository;
import sem3.project.individual.persistence.entity.AccountEntity;

public interface AccountRepository extends JpaRepository<AccountEntity, Long>
{
    boolean existsByUsername(String username);
    AccountEntity findByUsername(String username);
}

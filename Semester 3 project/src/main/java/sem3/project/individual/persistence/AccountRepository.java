package sem3.project.individual.persistence;


import org.springframework.data.jpa.repository.JpaRepository;
import sem3.project.individual.domain.accounts.UpdateAccountResponse;
import sem3.project.individual.misc.UnexpectedResultException;
import sem3.project.individual.persistence.entity.AccountEntity;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<AccountEntity, Long>
{
    AccountEntity findByUsername(String username);
    AccountEntity findByEmailContainingIgnoreCase(String email);
}

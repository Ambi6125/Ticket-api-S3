package sem3.project.individual.persistence;


import sem3.project.individual.domain.accounts.UpdateAccountRequest;
import sem3.project.individual.domain.accounts.UpdateAccountResponse;
import sem3.project.individual.misc.UnexpectedResultException;
import sem3.project.individual.persistence.entity.AccountEntity;

import java.util.List;
import java.util.Optional;

public interface AccountRepository
{
    boolean idExists(int id);
    boolean usernameExists(String username);
    AccountEntity create(AccountEntity account);
    List<AccountEntity> getAll();
    void delete(int id);
    UpdateAccountResponse update(AccountEntity account);
    AccountEntity get(String username) throws UnexpectedResultException;

    Optional<AccountEntity> getById(int id);
}

package sem3.project.individual.persistence;


import sem3.project.individual.domain.accounts.Account;
import sem3.project.individual.persistence.entity.AccountDTO;

import java.util.List;

public interface AccountRepository
{
    boolean idExists(int id);
    boolean usernameExists(String username);
    AccountDTO create(AccountDTO account);
    void update(AccountDTO account);
    List<AccountDTO> getAll();
    void delete(int id);
}
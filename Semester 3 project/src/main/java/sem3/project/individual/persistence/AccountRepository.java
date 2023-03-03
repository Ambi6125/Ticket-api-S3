package sem3.project.individual.persistence;


import sem3.project.individual.persistence.entity.AccountDTO;

public interface AccountRepository
{
    boolean idExists(int id);
    boolean usernameExists(String username);
    AccountDTO create(AccountDTO account);
    void update(AccountDTO account);

}

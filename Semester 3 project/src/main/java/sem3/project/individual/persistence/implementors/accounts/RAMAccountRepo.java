package sem3.project.individual.persistence.implementors.accounts;

import org.springframework.stereotype.Repository;
import sem3.project.individual.domain.accounts.Account;
import sem3.project.individual.domain.accounts.UpdateAccountRequest;
import sem3.project.individual.domain.accounts.UpdateAccountResponse;
import sem3.project.individual.misc.UnexpectedResultException;
import sem3.project.individual.persistence.AccountRepository;
import sem3.project.individual.persistence.entity.AccountEntity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Repository
public class RAMAccountRepo implements AccountRepository {

    private static int NEXT_ID = 1;
    private final List<AccountEntity> allAccounts;

    public RAMAccountRepo()
    {
        allAccounts = new ArrayList<>();
    }

    @Override
    public boolean idExists(int id)
    {
        return allAccounts.stream().anyMatch(x -> x.getId() == id);
    }

    @Override
    public boolean usernameExists(String username)
    {
        return allAccounts.stream().anyMatch(x -> x.getUsername().equals(username));
    }

    @Override
    public AccountEntity create(AccountEntity account)
    {
        if(allAccounts.stream().anyMatch(x -> x.getId() == account.getId()))
        {
            throw new IllegalArgumentException("An object with this id already exists.");
        }
        account.setId(NEXT_ID);
        NEXT_ID++;
        allAccounts.add(account);
        return account;
    }


    @Override
    public UpdateAccountResponse update(AccountEntity account)
    {
       AccountEntity target;

       try
       {
           target = allAccounts.stream()
                   .filter(x -> x.getId() == account.getId())
                   .findFirst()
                   .orElseThrow(NoSuchElementException::new);

       }
       catch (NoSuchElementException notFound)
       {
           return new UpdateAccountResponse(false, "Account not found.");
       }

       int valuesChanged = 0;

       if(!account.getUsername().equals(target.getUsername()))
       {
           target.setUsername(account.getUsername());
           valuesChanged++;
       }
       if(!account.getPassword().equals(target.getPassword()))
       {
           target.setPassword(account.getPassword());
           valuesChanged++;
       }
       if(!account.getEmail().equals(target.getEmail()))
       {
           target.setEmail(account.getEmail());
           valuesChanged++;
       }

       return new UpdateAccountResponse(true, "Account updated");
    }

    @Override
    public List<AccountEntity> getAll()
    {
        return Collections.unmodifiableList(allAccounts);
    }

    public AccountEntity getById(int id)
    {
        return allAccounts.stream().filter(x -> x.getId() == id).findFirst().orElse(null);
    }
    @Override
    public void delete(int id)
    {
        allAccounts.removeIf(x -> x.getId() == id);
    }

    @Override
    public AccountEntity get(String username) throws UnexpectedResultException {
        var searchResult = allAccounts.stream().filter(a -> a.getUsername().equals(username)).toList();

        if(searchResult.size() > 1)
        {
            throw new UnexpectedResultException("Only one result expected");
        }

        return searchResult.stream().findFirst().orElse(null);
    }

}

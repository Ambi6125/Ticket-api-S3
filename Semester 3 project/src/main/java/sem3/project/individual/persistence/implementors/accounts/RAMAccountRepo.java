package sem3.project.individual.persistence.implementors.accounts;

import lombok.SneakyThrows;
import org.springframework.stereotype.Repository;
import sem3.project.individual.persistence.AccountRepository;
import sem3.project.individual.persistence.entity.AccountDTO;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

@Repository
public class RAMAccountRepo implements AccountRepository {

    private static int NEXT_ID = 1;
    private final List<AccountDTO> allAccounts;

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
    public AccountDTO create(AccountDTO account)
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


    @Override @SneakyThrows
    public void update(AccountDTO account)
    {
        //Check if account does not exist
        var searchresult = allAccounts.stream().filter(x -> x.getId() == account.getId());

        if(searchresult.count() < 1)
        {
            throw new NoSuchElementException(String.format("No element with ID %s exists", account.getId()));
        }
        else if(searchresult.count() > 1)
        {
            throw new IllegalArgumentException("Multiple such accounts exist.");
        }


        //Replace at index the instance resides
        int index = allAccounts.indexOf(searchresult.findFirst().get());
        allAccounts.set(index, account);
    }

    @Override
    public List<AccountDTO> getAll()
    {
        return Collections.unmodifiableList(allAccounts);
    }

    public AccountDTO getById(int id)
    {
        return allAccounts.stream().filter(x -> x.getId() == id).findFirst().orElse(null);
    }
    @Override
    public void delete(int id)
    {
        allAccounts.removeIf(x -> x.getId() == id);
    }
}

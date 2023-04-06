package sem3.project.individual.business.implementors;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;
import sem3.project.individual.business.GetAccountsFunctionality;
import sem3.project.individual.domain.accounts.Account;
import sem3.project.individual.domain.accounts.GetAccountResponse;
import sem3.project.individual.domain.accounts.GetAllAccountsResponse;
import sem3.project.individual.misc.NotImplementedException;
import sem3.project.individual.misc.UnexpectedResultException;
import sem3.project.individual.persistence.AccountRepository;
import sem3.project.individual.persistence.entity.AccountEntity;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service @AllArgsConstructor
public class GetAccountsDefaultImplementation implements GetAccountsFunctionality//How else would I implement this lmao
{
    private final AccountRepository repo;

    @Override
    public GetAllAccountsResponse getAllAccounts()
    {
        var result = repo.getAll();
        final GetAllAccountsResponse responseAccounts = new GetAllAccountsResponse();
        List<Account> accounts = new ArrayList<>();
        for(var v : result)
        {
            int id = v.getId();
            String username = v.getUsername();
            String email = v.getEmail();
            String password = v.getPassword();
            LocalDateTime time = v.getTimeCreated();

            accounts.add(new Account(id, username, email, time));
        }

        responseAccounts.setAccounts(accounts);
        return responseAccounts;
    }

    @Override @SneakyThrows
    public GetAccountResponse get(String username)
    {
        AccountEntity response;
        try
        {
            response = repo.get(username);
        }
        catch (NoSuchElementException notFound)
        {
            return null;
        }

        int id = response.getId();
        String email = response.getEmail();
        String password = response.getPassword();
        LocalDateTime dt = response.getTimeCreated();
        Account result = new Account(id, username, email, dt);

        return new GetAccountResponse(result);
    }
}

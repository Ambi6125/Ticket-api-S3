package sem3.project.individual.business.implementors;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import sem3.project.individual.business.GetMultipleAccountsFunctionality;
import sem3.project.individual.domain.accounts.Account;
import sem3.project.individual.domain.accounts.GetAllAccountsResponse;
import sem3.project.individual.persistence.AccountRepository;
import sem3.project.individual.persistence.entity.AccountDTO;

import java.sql.Array;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service @AllArgsConstructor
public class GetAllAccountsImplementation implements GetMultipleAccountsFunctionality//How else would I implement this lmao
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
            Instant time = v.getTimeCreated();

            accounts.add(new Account(id, username, email, password, time));
        }

        responseAccounts.setAccounts(accounts);
        return responseAccounts;
    }
}

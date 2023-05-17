package sem3.project.individual.business.implementors.accounts;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;
import sem3.project.individual.business.GetAccountsFunctionality;
import sem3.project.individual.domain.accounts.Account;
import sem3.project.individual.domain.accounts.AccountConverter;
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
        List<AccountEntity> result = repo.findAll();
        final GetAllAccountsResponse responseAccounts = new GetAllAccountsResponse();

        //Convert all persistence entities to domain instances
        List<Account> accounts = result.stream().map(AccountConverter::toDomain).toList();

        responseAccounts.setAccounts(accounts);
        return responseAccounts;
    }

    @Override @SneakyThrows
    public GetAccountResponse getByUsername(String username)
    {
        AccountEntity response;
        try
        {
            response = repo.findByUsername(username);
        }
        catch (NoSuchElementException notFound)
        {
            return null;
        }


        Account result = AccountConverter.toDomain(response);

        return new GetAccountResponse(result);
    }
}

package sem3.project.individual.business.implementors.accounts;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import sem3.project.individual.business.GetAccountsFunctionality;
import sem3.project.individual.business.exceptions.InvalidCredentialsException;
import sem3.project.individual.business.exceptions.InvalidTokenException;
import sem3.project.individual.domain.accounts.*;
import sem3.project.individual.domain.login.tokens.AccessToken;
import sem3.project.individual.misc.MapObject;
import sem3.project.individual.persistence.AccountRepository;
import sem3.project.individual.persistence.entity.AccountEntity;

import javax.annotation.security.RolesAllowed;
import javax.persistence.EntityNotFoundException;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service @AllArgsConstructor
public class GetAccountsDefaultImplementation implements GetAccountsFunctionality//How else would I implement this lmao
{
    private final AccountRepository repo;
    private AccessToken token;


    @Override
    public GetAccountResponse getById(Long id)
    {
        AccountEntity response;

        try
        {
            response = repo.getById(id);
        }
        catch (EntityNotFoundException)
        {
            return null;
        }

        Account result = MapObject.transform(response, AccountConverter::toDomain);

        return new GetAccountResponse(result);
    }



    @Override @SneakyThrows
    public GetAccountResponse getByUsername(String username) throws InvalidTokenException
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


        Account result = MapObject.transform(response, AccountConverter::toDomain);

        if(token.hasRole(AccountRole.ADMIN.name())) //TODO: If debugging; subject might not actually be username
        {
            if (token.getSubject() != username)
            {
                throw new InvalidTokenException("Not same user.");
            }
        }

        return new GetAccountResponse(result);
    }

    @Override @RolesAllowed({"ROLE_ADMIN"})
    public GetAllAccountsResponse getAllAccounts()
    {
        List<AccountEntity> result = repo.findAll();
        final GetAllAccountsResponse responseAccounts = new GetAllAccountsResponse();

        //Convert all persistence entities to domain instances
        List<Account> accounts = result.stream().map(AccountConverter::toDomain).toList();

        responseAccounts.setAccounts(accounts);
        return responseAccounts;
    }
}

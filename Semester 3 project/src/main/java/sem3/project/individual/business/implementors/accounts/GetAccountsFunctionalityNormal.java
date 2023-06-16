package sem3.project.individual.business.implementors.accounts;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import sem3.project.individual.business.GetAccountsFunctionality;
import sem3.project.individual.business.exceptions.InvalidTokenException;
import sem3.project.individual.domain.accounts.*;
import sem3.project.individual.domain.login.tokens.AccessToken;
import sem3.project.individual.misc.MapObject;
import sem3.project.individual.persistence.AccountRepository;
import sem3.project.individual.persistence.DTO.AccountTicketCountDTO;
import sem3.project.individual.persistence.entity.AccountEntity;

import javax.annotation.security.RolesAllowed;
import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service @AllArgsConstructor
public class GetAccountsFunctionalityNormal implements GetAccountsFunctionality//How else would I implement this lmao
{
    private final AccountRepository repo;
    private AccessToken token;


    @Override
    public Optional<GetAccountResponse> getById(Long id)
    {
        AccountEntity response = repo.fetchById(id);

        if(response == null)
        {
            return Optional.empty();
        }

        Account result = AccountConverter.toDomain(response);

        return Optional.of(new GetAccountResponse(result));
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


        Account result = AccountConverter.toDomain(response);

        if(!token.hasRole(AccountRole.ADMIN.name())) //TODO: If debugging; subject might not actually be username
        {
            if (!token.getSubject().equals(username))
            {
                throw new InvalidTokenException("Not same user.");
            }
        }

        return new GetAccountResponse(result);
    }

    @Override @RolesAllowed({"ROLE_ADMIN"})
    public GetMultipleAccountsResponse getAllAccounts()
    {
        List<AccountEntity> result = repo.findAll();
        final GetMultipleAccountsResponse responseAccounts = new GetMultipleAccountsResponse();

        //Convert all persistence entities to domain instances
        List<Account> accounts = result.stream().map(AccountConverter::toDomain).toList();

        responseAccounts.setAccounts(accounts);
        return responseAccounts;
    }

    /**
     * Gets a list of accounts sorted by the amount of tickets bought. Also includes the count of tickets.
     *
     * @param threshold Accounts who have bought fewer tickets than this amount are omitted.
     * @return
     */
    public AccountRankingResponse<AccountTicketCountDTO> getAccountsByTicketsBought(int threshold)
    {
        long longThreshold = threshold;
        var response = repo.getUsersByTicketCount(longThreshold);
        return new AccountRankingResponse<>(response);
    }
}

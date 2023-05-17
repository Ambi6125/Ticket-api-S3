package sem3.project.individual.business.implementors.accounts;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import sem3.project.individual.business.CreateAccountFunctionality;
import sem3.project.individual.business.exceptions.UsernameTakenException;
import sem3.project.individual.domain.accounts.CreateAccountRequest;
import sem3.project.individual.domain.accounts.CreateAccountResponse;
import sem3.project.individual.persistence.AccountRepository;
import sem3.project.individual.persistence.entity.AccountEntity;

@Service @AllArgsConstructor
public class CreateAccountFunctionalityNormal implements CreateAccountFunctionality
{
    private final AccountRepository repo;

    @Override
    public CreateAccountResponse createAccount(CreateAccountRequest request)
    {
        if(repo.existsByUsername(request.getUsername()))
        {
            throw new UsernameTakenException();
        }

        var responseAccount = saveAccount(request);
        return new CreateAccountResponse(responseAccount.getId());
    }

    private AccountEntity saveAccount(CreateAccountRequest request)
    {
        AccountEntity newAcc = AccountEntity.builder()
                .username(request.getUsername())
                .password(request.getPassword())
                .email(request.getEmail())
                .build();
        return repo.save(newAcc);
    }
}

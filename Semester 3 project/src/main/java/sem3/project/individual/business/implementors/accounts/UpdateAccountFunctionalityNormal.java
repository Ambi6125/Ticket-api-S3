package sem3.project.individual.business.implementors.accounts;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import sem3.project.individual.business.UpdateAccountFunctionality;
import sem3.project.individual.domain.accounts.Account;
import sem3.project.individual.domain.accounts.UpdateAccountRequest;
import sem3.project.individual.domain.accounts.UpdateAccountResponse;
import sem3.project.individual.misc.NotImplementedException;
import sem3.project.individual.persistence.AccountRepository;
import sem3.project.individual.persistence.entity.AccountEntity;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service @AllArgsConstructor
public class UpdateAccountFunctionalityNormal implements UpdateAccountFunctionality
{

    private final AccountRepository repo;
    @Override
    public UpdateAccountResponse update(UpdateAccountRequest request)
    {
        AccountEntity accountData = AccountEntity.builder()
                .id(request.getTargetId())
                .username(request.getUsername())
                .password(request.getPassword())
                .email(request.getEmail())
                .build();
        return saveChanges(accountData);
    }

    @SneakyThrows
    private UpdateAccountResponse saveChanges(AccountEntity account)
    {
        //TODO: Update implementation lacking
        throw new NotImplementedException("Unsure how to update");
    }
}

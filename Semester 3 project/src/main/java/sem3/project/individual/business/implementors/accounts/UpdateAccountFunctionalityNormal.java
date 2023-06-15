package sem3.project.individual.business.implementors.accounts;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
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
    public void update(UpdateAccountRequest request)
    {
        AccountEntity accountData = repo.fetchById(request.getTargetId());

        if(accountData == null)
        {
            throw new NoSuchElementException("Not found.");
        }

        accountData.setUsername(request.getUsername());
        accountData.setEmail(request.getEmail());
        accountData.setPassword(request.getPassword());

        repo.save(accountData);
    }
}

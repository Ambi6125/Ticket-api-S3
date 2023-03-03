package sem3.project.individual.business.implementors;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import sem3.project.individual.business.DeleteAccountsFunctionality;
import sem3.project.individual.persistence.AccountRepository;

@Service @AllArgsConstructor
public class DeleteAccountFunctionalityNormal implements DeleteAccountsFunctionality
{
    private final AccountRepository repo;

    @Override
    public void deleteAccount(int id)
    {
        repo.delete(id);
    }
}

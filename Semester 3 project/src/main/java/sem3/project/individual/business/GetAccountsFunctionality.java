package sem3.project.individual.business;


import sem3.project.individual.domain.accounts.GetAccountResponse;
import sem3.project.individual.domain.accounts.GetAllAccountsResponse;

import java.util.Optional;

public interface GetAccountsFunctionality
{
    GetAllAccountsResponse getAllAccounts();
    GetAccountResponse getByUsername(String username);
    Optional<GetAccountResponse> getById(Long id);
}

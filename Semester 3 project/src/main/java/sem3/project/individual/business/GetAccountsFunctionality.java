package sem3.project.individual.business;


import sem3.project.individual.domain.accounts.AccountRankingResponse;
import sem3.project.individual.domain.accounts.GetAccountResponse;
import sem3.project.individual.domain.accounts.GetMultipleAccountsResponse;
import sem3.project.individual.persistence.DTO.AccountTicketCountDTO;

import java.util.Optional;

public interface GetAccountsFunctionality
{
    GetMultipleAccountsResponse getAllAccounts();
    GetAccountResponse getByUsername(String username);
    Optional<GetAccountResponse> getById(Long id);

    AccountRankingResponse<AccountTicketCountDTO> getAccountsByTicketsBought(int threshold);
}

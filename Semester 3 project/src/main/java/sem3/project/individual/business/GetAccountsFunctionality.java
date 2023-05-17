package sem3.project.individual.business;


import sem3.project.individual.domain.accounts.GetAccountResponse;
import sem3.project.individual.domain.accounts.GetAllAccountsResponse;

public interface GetAccountsFunctionality
{
    GetAllAccountsResponse getAllAccounts();
    GetAccountResponse getByUsername(String username);
}

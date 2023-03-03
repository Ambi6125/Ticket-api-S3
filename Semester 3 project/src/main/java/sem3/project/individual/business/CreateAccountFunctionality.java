package sem3.project.individual.business;

import sem3.project.individual.domain.accounts.CreateAccountRequest;
import sem3.project.individual.domain.accounts.CreateAccountResponse;

public interface CreateAccountFunctionality
{
    CreateAccountResponse createAccount(CreateAccountRequest request);
}

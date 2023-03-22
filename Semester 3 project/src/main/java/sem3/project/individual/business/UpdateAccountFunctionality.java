package sem3.project.individual.business;


import sem3.project.individual.domain.accounts.UpdateAccountRequest;
import sem3.project.individual.domain.accounts.UpdateAccountResponse;

public interface UpdateAccountFunctionality
{
    UpdateAccountResponse update(UpdateAccountRequest request);
}

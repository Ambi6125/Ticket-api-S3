package sem3.project.individual.business;

import sem3.project.individual.domain.accounts.Account;

import java.util.Optional;

public interface GetAccountFunctionality
{
    Optional<Account> getAccountById();
}

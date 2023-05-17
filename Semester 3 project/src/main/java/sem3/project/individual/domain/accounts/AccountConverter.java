package sem3.project.individual.domain.accounts;

import sem3.project.individual.persistence.entity.AccountEntity;

public final class AccountConverter
{
    private AccountConverter() {}

    public static Account toDomain(AccountEntity entity)
    {
        return new Account(entity.getId(), entity.getUsername(), entity.getPassword(), entity.getEmail());
    }

    public static Account toDomainOmitPassword(AccountEntity entity)
    {
        return new Account(entity.getId(), entity.getUsername(), null, entity.getEmail());
    }
}

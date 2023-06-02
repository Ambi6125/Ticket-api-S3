package sem3.project.individual.domain.accounts;

import sem3.project.individual.persistence.entity.AccountEntity;

import java.util.HashSet;
import java.util.Set;

public final class AccountConverter
{
    private AccountConverter() {}

    public static Account toDomain(AccountEntity entity)
    {
        Set<AccountRole> roles = new HashSet<>();

        for(var v : entity.getRoles())
        {
            roles.add(v.getRole());
        }

        return new Account(entity.getId(), entity.getUsername(), entity.getPassword(), entity.getEmail(), roles);
    }

    public static Account toDomainOmitPassword(AccountEntity entity)
    {
        Set<AccountRole> roles = new HashSet<>();

        for(var v : entity.getRoles())
        {
            roles.add(v.getRole());
        }
        return new Account(entity.getId(), entity.getUsername(), null, entity.getEmail(), roles);
    }
}

package sem3.project.individual.domain.accounts;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;

@NoArgsConstructor
@AllArgsConstructor @Data
public class GetAllAccountsResponse
{
    private List<Account> accounts;
    public List<Account> getAccounts()
    {
        return Collections.unmodifiableList(accounts);
    }
}

package sem3.project.individual.domain.accounts;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor @Data
public class GetAllAccountsResponse
{
    private List<Account> accounts;
}

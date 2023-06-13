package sem3.project.individual.domain.accounts;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class AccountRankingResponse<T>
{
    private List<T> rankings;
}

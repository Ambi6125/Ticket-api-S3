package sem3.project.individual.domain.stats;

import lombok.AllArgsConstructor;
import sem3.project.individual.persistence.DTO.AccountTicketCountDTO;

import java.util.List;

@AllArgsConstructor
public class UserTicketsResponse
{
    private List<AccountTicketCountDTO> rankings;
}

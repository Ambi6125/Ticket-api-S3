package sem3.project.individual.business.implementors.tickets;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import sem3.project.individual.business.GetTicketsFunctionality;
import sem3.project.individual.domain.tickets.GetTicketCountResponse;
import sem3.project.individual.persistence.DTO.EventTicketCountDTO;
import sem3.project.individual.persistence.TicketRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class GetTicketsFunctionalityNormal implements GetTicketsFunctionality
{
    private TicketRepository repo;
    @Override
    public GetTicketCountResponse getEventTicketCountByOwner(Long accountId)
    {
        return new GetTicketCountResponse(repo.findUserTicketsWithCount(accountId));
    }
}

package sem3.project.individual.business;

import sem3.project.individual.domain.tickets.GetTicketCountResponse;
import sem3.project.individual.persistence.DTO.EventTicketCountDTO;

import java.util.List;

public interface GetTicketsFunctionality
{
    GetTicketCountResponse getEventTicketCountByOwner(Long accountId);
}

package sem3.project.individual.domain.tickets;

import lombok.AllArgsConstructor;
import lombok.Data;
import sem3.project.individual.persistence.DTO.EventTicketCountDTO;

import java.util.List;

@AllArgsConstructor @Data
public class GetTicketCountResponse
{
    private List<EventTicketCountDTO> results;
}

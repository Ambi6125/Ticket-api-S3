package sem3.project.individual.persistence.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;


/**
 * Represents an event and an amount of tickets.
 */
@Getter
@AllArgsConstructor
public class EventTicketCountDTO
{
    private String eventTitle;
    private Long ticketCount;
}

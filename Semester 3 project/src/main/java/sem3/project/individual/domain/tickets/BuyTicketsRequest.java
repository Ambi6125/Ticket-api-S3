package sem3.project.individual.domain.tickets;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BuyTicketsRequest
{
    private Long buyerId;
    private Long eventId;

    private int amount;
}

package sem3.project.individual.domain.events;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.time.LocalDateTime;

@Data
@Builder
public class Event
{
    private Long id;
    private String title;
    private String location;
    private Instant moment;
    private int totalTickets;
    private int remainingTickets;

    public void purchaseTickets(int amount) throws IllegalArgumentException
    {
        if(amount < 1 || amount > remainingTickets)
        {
            throw new IllegalArgumentException("Amount less than 1 or more than left.");
        }

        remainingTickets -= amount;
    }
}

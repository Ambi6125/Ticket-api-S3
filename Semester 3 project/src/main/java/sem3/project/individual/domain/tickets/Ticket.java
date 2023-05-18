package sem3.project.individual.domain.tickets;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import sem3.project.individual.domain.accounts.Account;
import sem3.project.individual.domain.events.Event;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@Setter
public class Ticket
{
    private int id;
    private Event event;
}

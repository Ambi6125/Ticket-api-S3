package sem3.project.individual.business.implementors.tickets;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sem3.project.individual.business.BuyTicketFunctionality;
import sem3.project.individual.domain.events.Event;
import sem3.project.individual.domain.events.EventConverter;
import sem3.project.individual.domain.tickets.BuyTicketsResponse;
import sem3.project.individual.misc.MapObject;
import sem3.project.individual.persistence.AccountRepository;
import sem3.project.individual.persistence.EventRepository;
import sem3.project.individual.persistence.TicketRepository;
import sem3.project.individual.persistence.entity.AccountEntity;
import sem3.project.individual.persistence.entity.EventEntity;
import sem3.project.individual.persistence.entity.TicketEntity;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class BuyTicketsFunctionalityNormal implements BuyTicketFunctionality
{
    private TicketRepository ticketRepo;
    private EventRepository eventRepo;
    private AccountRepository accountRepo;
    @Override
    @Transactional
    public BuyTicketsResponse buy(Long accountId, Long eventId, int amount) {
        var updatedEntity = removeTicketCount(eventId, amount);
        updatedEntity = eventRepo.save(updatedEntity);

        List<Long> newIds = new ArrayList<>();

        for (int i = 0; i < amount; i++) {
            var ticket = buildTicket(accountId, updatedEntity);
            ticket = ticketRepo.save(ticket);
            newIds.add(ticket.getId());
        }

        return new BuyTicketsResponse(newIds);
    }

    /**
     * Sets up a proxy and overwrites ticket amount on the entity. This entity is not yet committed to the datasource.
     * @param eventId The id of the target event.
     * @param amount The amount of tickets purchased.
     * @return An object ready to be saved by the caller.
     */
    private EventEntity removeTicketCount(Long eventId, int amount) {
        EventEntity target = eventRepo.fetchById(eventId);
        Event proxy = EventConverter.toDomain(target);

        proxy.purchaseTickets(amount);

        target.setRemainingTickets(proxy.getRemainingTickets());

        return target;
    }

    private TicketEntity buildTicket(Long accountId, EventEntity event) {
        AccountEntity foundAccount = accountRepo.fetchById(accountId);
        TicketEntity ticket = new TicketEntity();
        ticket.setEvent(event);
        ticket.setAccount(foundAccount);

        return ticket;
    }
}

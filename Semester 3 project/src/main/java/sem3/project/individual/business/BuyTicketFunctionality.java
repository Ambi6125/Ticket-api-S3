package sem3.project.individual.business;

import sem3.project.individual.domain.tickets.BuyTicketsResponse;

public interface BuyTicketFunctionality
{
    BuyTicketsResponse buy(Long accountId, Long eventId, int amount);
}

package sem3.project.individual.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import sem3.project.individual.business.BuyTicketFunctionality;
import sem3.project.individual.business.UpdateEventFunctionality;
import sem3.project.individual.domain.tickets.BuyTicketsRequest;
import sem3.project.individual.domain.tickets.BuyTicketsResponse;

@RestController
@AllArgsConstructor
public class TicketController
{
    private BuyTicketFunctionality ticketBuyer;
    @PostMapping("/buytickets")
    public ResponseEntity<BuyTicketsResponse> buyTickets(@RequestBody BuyTicketsRequest request)
    {
        var response = ticketBuyer.buy(request.getBuyerId(), request.getEventId(), request.getAmount());
        return ResponseEntity.ok(response);
    }
}

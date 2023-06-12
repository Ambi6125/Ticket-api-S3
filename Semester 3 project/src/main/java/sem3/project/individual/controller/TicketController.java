package sem3.project.individual.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sem3.project.individual.business.BuyTicketFunctionality;
import sem3.project.individual.business.UpdateEventFunctionality;
import sem3.project.individual.configuration.security.auth.RequireAuthentication;
import sem3.project.individual.domain.tickets.BuyTicketsRequest;
import sem3.project.individual.domain.tickets.BuyTicketsResponse;

import javax.annotation.security.RolesAllowed;

@RestController
@AllArgsConstructor
@RequestMapping("/tickets")
@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:3000"}, allowedHeaders = {"Accept", "Content-Type", "Authorization"})
public class TicketController
{
    private BuyTicketFunctionality ticketBuyer;
    @PostMapping("/buy") @RequireAuthentication @RolesAllowed({"ROLE_USER", "ROLE_ADMIN"})
    public ResponseEntity<BuyTicketsResponse> buyTickets(@RequestBody BuyTicketsRequest request)
    {
        var response = ticketBuyer.buy(request.getBuyerId(), request.getEventId(), request.getAmount());
        return ResponseEntity.ok(response);
    }
}

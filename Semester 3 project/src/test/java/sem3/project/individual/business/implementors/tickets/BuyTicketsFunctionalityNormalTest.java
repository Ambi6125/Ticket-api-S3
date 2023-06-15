package sem3.project.individual.business.implementors.tickets;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import sem3.project.individual.domain.tickets.BuyTicketsResponse;
import sem3.project.individual.persistence.AccountRepository;
import sem3.project.individual.persistence.EventRepository;
import sem3.project.individual.persistence.TicketRepository;
import sem3.project.individual.persistence.entity.AccountEntity;
import sem3.project.individual.persistence.entity.EventEntity;
import sem3.project.individual.persistence.entity.TicketEntity;

import java.time.Instant;
import java.util.List;
import java.util.NoSuchElementException;

@ExtendWith(MockitoExtension.class)
class BuyTicketsFunctionalityNormalTest {

    @Mock
    private TicketRepository ticketRepo;
    @Mock
    private EventRepository eventRepo;
    @Mock
    private AccountRepository accountRepo;
    @InjectMocks
    private BuyTicketsFunctionalityNormal useCase;

    @Test
    void buy_ValidParameters_ReturnsBuyTicketsResponse() {
        Long accountId = 1L;
        Long eventId = 2L;
        int amount = 1;

        EventEntity eventEntity = EventEntity.builder()
                .id(eventId)
                .title("Event")
                .moment(Instant.EPOCH)
                .location("Here")
                .totalTickets(50)
                .remainingTickets(10)
                .build();

        EventEntity updatedEntity = EventEntity.builder()
                .id(eventId)
                .title("Event")
                .moment(Instant.EPOCH)
                .location("Here")
                .totalTickets(50)
                .remainingTickets(9)
                .build();


        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setId(accountId);

        when(eventRepo.fetchById(eventId)).thenReturn(eventEntity);
        when(eventRepo.save(updatedEntity)).thenReturn(updatedEntity);
        when(accountRepo.fetchById(accountId)).thenReturn(accountEntity);

        TicketEntity ticket = TicketEntity.builder()
                .account(accountEntity)
                .event(updatedEntity)
                .build();

        TicketEntity generatedTicket = TicketEntity.builder()
                .id(1L)
                .build();

        when(ticketRepo.save(ticket)).thenReturn(generatedTicket);


        BuyTicketsResponse actualResponse = useCase.buy(accountId, eventId, amount);
        BuyTicketsResponse expectedResponse = BuyTicketsResponse.builder()
                .ids(List.of(generatedTicket.getId()))
                .build();



        assertEquals(actualResponse, expectedResponse);
        verify(eventRepo).fetchById(eventId);
        verify(accountRepo).fetchById(accountId);
        verify(eventRepo).save(updatedEntity);
        verify(ticketRepo).save(ticket);
    }


}
package sem3.project.individual.business.implementors.tickets;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import sem3.project.individual.domain.tickets.GetTicketCountResponse;
import sem3.project.individual.persistence.DTO.EventTicketCountDTO;
import sem3.project.individual.persistence.TicketRepository;
import sem3.project.individual.persistence.entity.AccountEntity;
import sem3.project.individual.persistence.entity.EventEntity;
import sem3.project.individual.persistence.entity.TicketEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetTicketsFunctionalityNormalTest {

    @Mock
    private TicketRepository repo;
    @InjectMocks
    private GetTicketsFunctionalityNormal useCase;
    @Test
    void getEventTicketCountByOwner()
    {
        Long accountId = 1L;


        EventTicketCountDTO firstResult = new EventTicketCountDTO("One event", 1L);
        EventTicketCountDTO secondResult = new EventTicketCountDTO("A second event", 1L);

        GetTicketCountResponse expected = new GetTicketCountResponse(List.of(firstResult, secondResult));

        when(repo.findUserTicketsWithCount(accountId)).thenReturn(List.of(firstResult, secondResult));
        GetTicketCountResponse actual = useCase.getEventTicketCountByOwner(accountId);

        assertEquals(expected, actual);
        verify(repo).findUserTicketsWithCount(accountId);
    }
}
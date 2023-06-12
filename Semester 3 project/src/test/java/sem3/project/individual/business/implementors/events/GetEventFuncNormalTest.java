package sem3.project.individual.business.implementors.events;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import sem3.project.individual.domain.events.Event;
import sem3.project.individual.domain.events.GetMultipleEventsResponse;
import sem3.project.individual.persistence.EventRepository;
import sem3.project.individual.persistence.entity.AccountEntity;
import sem3.project.individual.persistence.entity.EventEntity;

import java.time.Instant;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class GetEventFuncNormalTest {

    @Mock
    private EventRepository mockRepo;
    @InjectMocks
    private GetEventFuncNormal useCase;
    @Test
    void getByTitle() {
    }

    @Test
    void getByTitleContains() {
    }

    @Test
    void getByLocation() {
    }

    @Test
    void getByStringSearch() {
    }

    @Test
    void getById() {
    }

    @Test
    void getAll() {
        EventEntity entity = EventEntity.builder()
                .id(1L)
                .title("Music event")
                .moment(Instant.EPOCH)
                .location("Here")
                .totalTickets(100)
                .remainingTickets(69)
                .build();

        EventEntity entity2 = EventEntity.builder()
                .id(2L)
                .title("Music event 2")
                .moment(Instant.EPOCH)
                .location("Here again")
                .totalTickets(101)
                .remainingTickets(69)
                .build();

        when(mockRepo.findAll()).thenReturn(List.of(entity, entity2));

        GetMultipleEventsResponse response = useCase.getAll();

        Event event = Event.builder()
                .id(1L)
                .title("Music event")
                .moment(Instant.EPOCH)
                .location("Here")
                .totalTickets(100)
                .remainingTickets(69)
                .build();
        Event event2 = Event.builder()
                .id(2L)
                .title("Music event 2")
                .moment(Instant.EPOCH)
                .location("Here again")
                .totalTickets(101)
                .remainingTickets(69)
                .build();

        GetMultipleEventsResponse expectedResponse = GetMultipleEventsResponse.builder()
                .events(List.of(event, event2))
                .build();

        assertEquals(response, expectedResponse);
        verify(mockRepo).findAll();
    }
}
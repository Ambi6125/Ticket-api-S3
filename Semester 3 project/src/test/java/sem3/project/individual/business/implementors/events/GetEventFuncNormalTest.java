package sem3.project.individual.business.implementors.events;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import sem3.project.individual.domain.events.Event;
import sem3.project.individual.domain.events.EventConverter;
import sem3.project.individual.domain.events.GetEventResponse;
import sem3.project.individual.domain.events.GetMultipleEventsResponse;
import sem3.project.individual.persistence.EventRepository;
import sem3.project.individual.persistence.entity.AccountEntity;
import sem3.project.individual.persistence.entity.EventEntity;

import java.time.Instant;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

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
    void getByTitle()
    {
        String title = "Your mother";

        EventEntity e1 = EventEntity.builder()
                .id(1L)
                .build();



        when(mockRepo.findByTitleIgnoreCase(title)).thenReturn(Optional.of(e1));

        Optional<GetEventResponse> expected = Optional.of(new GetEventResponse(EventConverter.toDomain(e1)));

        Optional<GetEventResponse> actual = useCase.getByTitle(title);

        assertEquals(expected, actual);

        verify(mockRepo).findByTitleIgnoreCase(title);
    }

    @Test
    void getByStringSearch()
    {
        String query = "ton";

        EventEntity e1 = EventEntity.builder().id(1L).build();
        Event domainE1 = EventConverter.toDomain(e1);
        when(mockRepo.findBySearchQuery(query)).thenReturn(List.of(e1));

        Optional<GetMultipleEventsResponse> expected = Optional.of(GetMultipleEventsResponse.builder()
                .events(List.of(domainE1))
                .build());

        Optional<GetMultipleEventsResponse> actual = useCase.getByStringSearch(query);

        assertEquals(expected, actual);
        verify(mockRepo).findBySearchQuery(query);
    }

    @Test
    void getByStringSearch_NoValueFound_ReturnsEmptyOptional()
    {
        String query = "on";

        when(mockRepo.findBySearchQuery(query)).thenReturn(Collections.emptyList());

        Optional<GetMultipleEventsResponse> expected = Optional.empty();

        Optional<GetMultipleEventsResponse> actual = useCase.getByStringSearch(query);

        assertEquals(expected, actual);
        verify(mockRepo).findBySearchQuery(query);
    }

    @Test
    void getById()
    {
        Long id = 1L;

        Optional<EventEntity> e1 = Optional.of(EventEntity.builder().id(1L).build());

        when(mockRepo.findById(id)).thenReturn(e1);

        Optional<GetEventResponse> expected = Optional.of(new GetEventResponse(EventConverter.toDomain(e1.get())));

        Optional<GetEventResponse> actual = useCase.getById(id);

        assertEquals(expected, actual);

        verify(mockRepo).findById(id);
    }

    @Test
    void getById_NotFound_ReturnsEmptyOptional()
    {
        Long id = 1L;

        when(mockRepo.findById(id)).thenReturn(Optional.empty());

        Optional<GetEventResponse> expected = Optional.empty();
        Optional<GetEventResponse> actual = useCase.getById(id);

        assertEquals(expected, actual);
        verify(mockRepo).findById(id);
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

    @Test
    void getRandom_ReturnsSixResults()
    {
        Pageable sixEntries = PageRequest.of(0, 6);
        EventEntity e1 = EventEntity.builder()
                .id(1L)
                .build();
        EventEntity e2 = EventEntity.builder()
                .id(2L)
                .build();
        EventEntity e3 = EventEntity.builder()
                .id(3L)
                .build();
        EventEntity e4 = EventEntity.builder()
                .id(4L)
                .build();
        EventEntity e5 = EventEntity.builder()
                .id(5L)
                .build();
        EventEntity e6 = EventEntity.builder()
                .id(6L)
                .build();

        List<EventEntity> expectedResult = List.of(e1, e2, e3, e4, e5, e6);
        List<Event> expectedResultMapped = expectedResult.stream().map(EventConverter::toDomain).toList();
        GetMultipleEventsResponse expected = new GetMultipleEventsResponse(expectedResultMapped);

        when(mockRepo.getRandomEvents(sixEntries)).thenReturn(expectedResult);

        GetMultipleEventsResponse actual = useCase.getRandom();

        assertEquals(expected, actual);
    }
}
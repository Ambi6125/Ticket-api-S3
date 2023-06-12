package sem3.project.individual.business.implementors.events;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import sem3.project.individual.business.exceptions.TimeLocationOverlapException;
import sem3.project.individual.domain.events.CreateEventRequest;
import sem3.project.individual.domain.events.CreateEventResponse;
import sem3.project.individual.domain.events.EventConverter;
import sem3.project.individual.misc.MapObject;
import sem3.project.individual.persistence.EventRepository;
import sem3.project.individual.persistence.entity.EventEntity;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreateEventFuncNormalTest {

    @Mock
    private EventRepository mockEventRepo;
    @InjectMocks
    private CreateEventFuncNormal useCase;
    @Test
    void create() throws TimeLocationOverlapException {
        CreateEventRequest request = CreateEventRequest.builder()
                .title("Test event")
                .moment(Instant.EPOCH)
                .location("Here")
                .totalTickets(50)
                .build();

        when(mockEventRepo.existsByLocationIgnoreCaseAndMoment(request.getLocation(), request.getMoment()))
                .thenReturn(false);

        EventEntity entity = EventConverter.creationToEntity(request);
        EventEntity returnValues = EventEntity.builder()
                .id(1L)
                .title("Test event")
                .moment(Instant.EPOCH)
                .location("Here")
                .totalTickets(50)
                .build();


        when(mockEventRepo.save(entity)).thenReturn(returnValues);

        CreateEventResponse response = useCase.create(request);

        CreateEventResponse expectedResponse = CreateEventResponse.builder()
                .id(1L)
                .build();

        assertEquals(response, expectedResponse);
        verify(mockEventRepo).save(entity);
    }

    @Test
    void createFailsWhenDataIsTaken()
    {
        CreateEventRequest request = CreateEventRequest.builder()
                .title("Test event")
                .moment(Instant.EPOCH)
                .location("Here")
                .totalTickets(50)
                .build();

        when(mockEventRepo.existsByLocationIgnoreCaseAndMoment(request.getLocation(), request.getMoment()))
                .thenReturn(true);

        assertThrows(TimeLocationOverlapException.class, () -> {
            useCase.create(request);
        });

        verify(mockEventRepo).existsByLocationIgnoreCaseAndMoment(request.getLocation(), request.getMoment());
    }
}
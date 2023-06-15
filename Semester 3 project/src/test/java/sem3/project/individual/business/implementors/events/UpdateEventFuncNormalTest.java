package sem3.project.individual.business.implementors.events;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import sem3.project.individual.domain.events.UpdateEventRequest;
import sem3.project.individual.persistence.EventRepository;
import sem3.project.individual.persistence.entity.EventEntity;

import java.time.Instant;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UpdateEventFuncNormalTest
{
    @Mock
    private EventRepository repo;

    @InjectMocks
    private UpdateEventFuncNormal useCase;

    @Test
    void updateEntity()
    {
        EventEntity entity = EventEntity.builder()
                .id(1L)
                .title("Wrong name")
                .moment(Instant.EPOCH)
                .totalTickets(50)
                .remainingTickets(50)
                .location("Wrong location")
                .build();

        UpdateEventRequest request = UpdateEventRequest.builder()
                .id(1L)
                .title("Right name")
                .moment(Instant.EPOCH)
                .totalTickets(50)
                .location("Right location")
                .build();

        when(repo.fetchById(request.getId())).thenReturn(entity);

        EventEntity updatedEntity = EventEntity.builder()
                .id(1L)
                .title("Right name")
                .moment(Instant.EPOCH)
                .totalTickets(50)
                .remainingTickets(50)
                .location("Right location")
                .build();

        //TODO: If statements

        when(repo.save(entity)).thenReturn(updatedEntity);

        useCase.updateEntity(request);

        verify(repo).fetchById(request.getId());
        verify(repo).save(entity);
    }
}
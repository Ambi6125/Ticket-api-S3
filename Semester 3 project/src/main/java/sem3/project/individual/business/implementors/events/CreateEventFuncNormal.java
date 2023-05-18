package sem3.project.individual.business.implementors.events;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import sem3.project.individual.business.CreateEventFunctionality;
import sem3.project.individual.business.exceptions.TimeLocationOverlapException;
import sem3.project.individual.domain.events.CreateEventRequest;
import sem3.project.individual.domain.events.CreateEventResponse;
import sem3.project.individual.domain.events.EventConverter;
import sem3.project.individual.persistence.EventRepository;
import sem3.project.individual.persistence.entity.EventEntity;

@Service @AllArgsConstructor
public class CreateEventFuncNormal implements CreateEventFunctionality
{
    private final EventRepository repo;

    private EventEntity saveEvent(CreateEventRequest request)
    {
        EventEntity entity = EventConverter.creationToEntity(request);

        return repo.save(entity);
    }

    @Override
    public CreateEventResponse create(CreateEventRequest request) throws TimeLocationOverlapException
    {
        if(repo.existsByLocationIgnoreCaseAndLocation(request.getLocation(), request.getMoment()))
        {
            throw new TimeLocationOverlapException();
        }

        var response = saveEvent(request);

        return new CreateEventResponse(response.getId());
    }
}

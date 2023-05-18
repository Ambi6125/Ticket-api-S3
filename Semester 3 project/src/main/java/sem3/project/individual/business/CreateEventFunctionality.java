package sem3.project.individual.business;

import lombok.AllArgsConstructor;
import sem3.project.individual.business.exceptions.TimeLocationOverlapException;
import sem3.project.individual.domain.events.CreateEventRequest;
import sem3.project.individual.domain.events.CreateEventResponse;

public interface CreateEventFunctionality
{
    CreateEventResponse create(CreateEventRequest request) throws TimeLocationOverlapException;
}

package sem3.project.individual.business;

import sem3.project.individual.domain.events.Event;
import sem3.project.individual.domain.events.GetEventResponse;

import java.util.Optional;

public interface GetEventFunctionality
{
    Optional<GetEventResponse> getByTitle(String title);
    Optional<GetEventResponse> getByTitleContains(String criteria);
    Optional<GetEventResponse> getByLocation(String location);

    Optional<GetEventResponse> getById(Long id);
}

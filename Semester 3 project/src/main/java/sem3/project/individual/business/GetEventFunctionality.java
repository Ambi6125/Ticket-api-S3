package sem3.project.individual.business;

import sem3.project.individual.domain.events.GetEventResponse;
import sem3.project.individual.domain.events.GetMultipleEventsResponse;

import java.util.Optional;

public interface GetEventFunctionality
{
    Optional<GetEventResponse> getByTitle(String title);

    /**
     * Checks the data source for any event that either
     * matches its title by containing the query,
     * or matches its location by containing the query.
     * @param query The search query to match against.
     * @return An optional containing all found events, or nothing if none are found.
     */
    Optional<GetMultipleEventsResponse> getByStringSearch(String query);
    Optional<GetEventResponse> getById(Long id);

    GetMultipleEventsResponse getAll();

    GetMultipleEventsResponse getRandom();
}

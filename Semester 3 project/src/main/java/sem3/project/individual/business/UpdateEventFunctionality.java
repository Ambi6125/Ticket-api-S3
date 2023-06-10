package sem3.project.individual.business;

import sem3.project.individual.business.exceptions.TimeLocationOverlapException;
import sem3.project.individual.domain.events.UpdateEventRequest;

import java.util.NoSuchElementException;


public interface UpdateEventFunctionality
{
    void UpdateEntity(UpdateEventRequest request) throws NoSuchElementException, TimeLocationOverlapException;

    void purchaseTickets(Long targetId, int amount) throws IllegalArgumentException;
}

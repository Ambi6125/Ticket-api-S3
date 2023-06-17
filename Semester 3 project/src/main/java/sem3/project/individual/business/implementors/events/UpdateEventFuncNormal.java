package sem3.project.individual.business.implementors.events;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import sem3.project.individual.business.UpdateEventFunctionality;
import sem3.project.individual.business.exceptions.TimeLocationOverlapException;
import sem3.project.individual.domain.events.Event;
import sem3.project.individual.domain.events.EventConverter;
import sem3.project.individual.domain.events.UpdateEventRequest;
import sem3.project.individual.misc.MapObject;
import sem3.project.individual.persistence.EventRepository;
import sem3.project.individual.persistence.entity.EventEntity;

import java.util.NoSuchElementException;

@Service @AllArgsConstructor
public class UpdateEventFuncNormal implements UpdateEventFunctionality
{
    private EventRepository repo;
    @Override
    public void updateEntity(UpdateEventRequest request)
    {
        EventEntity target = repo.fetchById(request.getId());

        if(target == null)
        {
            throw new NoSuchElementException();
        }
        target.setTitle(request.getTitle());
        target.setLocation(request.getLocation());
        target.setMoment(request.getMoment());

        repo.save(target);
    }


}

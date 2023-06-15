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

        if(target.getTitle() != request.getTitle())
        {
            target.setTitle(request.getTitle());
        }
        if(target.getLocation() != request.getLocation())
        {
            target.setLocation(request.getLocation());
        }
        if(!target.getMoment().equals(request.getMoment()))
        {
            target.setMoment(request.getMoment());
        }

        repo.save(target);
    }


}

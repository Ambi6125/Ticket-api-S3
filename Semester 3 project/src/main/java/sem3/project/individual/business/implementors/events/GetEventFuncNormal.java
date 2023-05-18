package sem3.project.individual.business.implementors.events;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import sem3.project.individual.business.GetEventFunctionality;
import sem3.project.individual.domain.events.Event;
import sem3.project.individual.domain.events.EventConverter;
import sem3.project.individual.domain.events.GetEventResponse;
import sem3.project.individual.misc.NotImplementedException;
import sem3.project.individual.persistence.EventRepository;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service  @AllArgsConstructor
public class GetEventFuncNormal implements GetEventFunctionality
{
    private EventRepository repo;


    @Override
    public Optional<GetEventResponse> getByTitle(String title)
    {
        return repo.findByTitleIgnoreCase(title)
                .map(response -> new GetEventResponse(EventConverter.toDomain(response)));
    }

    @Override
    public Optional<GetEventResponse> getByTitleContains(String criteria) {
        throw new NotImplementedException();
    }

    @Override
    public Optional<GetEventResponse> getByLocation(String location) {
        throw new NotImplementedException();
    }

    @Override
    public Optional<GetEventResponse> getById(Long id)
    {
        var response = repo.findById(id);

        if(response.isPresent())
        {
            Event event = EventConverter.toDomain(response.get());
            return Optional.of(new GetEventResponse(event));
        }
        else
        {
            return Optional.empty();
        }
    }
}

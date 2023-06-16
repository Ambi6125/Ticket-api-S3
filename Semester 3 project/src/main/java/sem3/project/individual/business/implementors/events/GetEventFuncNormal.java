package sem3.project.individual.business.implementors.events;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import sem3.project.individual.business.GetEventFunctionality;
import sem3.project.individual.domain.events.Event;
import sem3.project.individual.domain.events.EventConverter;
import sem3.project.individual.domain.events.GetEventResponse;
import sem3.project.individual.domain.events.GetMultipleEventsResponse;
import sem3.project.individual.misc.NotImplementedException;
import sem3.project.individual.persistence.EventRepository;

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
    public Optional<GetMultipleEventsResponse> getByStringSearch(String query)
    {
        var response = repo.findBySearchQuery(query);
        if(response.size() == 0)
        {
            return Optional.empty();
        }

        return Optional.of(new GetMultipleEventsResponse(response.stream().map(EventConverter::toDomain).toList()));
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

    @Override
    public GetMultipleEventsResponse getAll()
    {
        var response = repo.findAll();
        return new GetMultipleEventsResponse(response.stream().map(EventConverter::toDomain).toList());
    }

    @Override
    public GetMultipleEventsResponse getRandom()
    {
        Pageable pageable = PageRequest.of(0, 6);
        var response = repo.getRandomEvents(pageable);
        var mappedResponse = response.stream().map(EventConverter::toDomain).toList();
        return new GetMultipleEventsResponse(mappedResponse);
    }
}

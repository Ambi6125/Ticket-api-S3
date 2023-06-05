package sem3.project.individual.domain.events;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class GetMultipleEventsResponse
{
    private List<Event> events;
}

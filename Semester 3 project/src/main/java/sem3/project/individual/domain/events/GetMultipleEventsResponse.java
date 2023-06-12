package sem3.project.individual.domain.events;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
@Builder
@Data
public class GetMultipleEventsResponse
{
    private List<Event> events;
}

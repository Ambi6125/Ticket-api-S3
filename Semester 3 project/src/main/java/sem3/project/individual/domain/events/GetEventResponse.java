package sem3.project.individual.domain.events;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@AllArgsConstructor
@Data
public class GetEventResponse
{
    private Event event;
}

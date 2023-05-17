package sem3.project.individual.business;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.Instant;

@Getter
@AllArgsConstructor
public class CreateEventRequest
{
    private String title;
    private String location;
    private Instant moment;
}

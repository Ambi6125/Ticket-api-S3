package sem3.project.individual.domain.events;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import sem3.project.individual.misc.InstantDeserializer;

import java.time.Instant;

@Builder
@Getter
@AllArgsConstructor
public class CreateEventRequest
{
    private String title;
    private String location;

    @JsonDeserialize(using = InstantDeserializer.class)
    private Instant moment;
    private int totalTickets;
}

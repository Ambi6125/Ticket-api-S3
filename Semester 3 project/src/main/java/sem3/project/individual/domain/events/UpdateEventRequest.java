package sem3.project.individual.domain.events;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.commons.lang3.builder.HashCodeExclude;
import org.apache.commons.lang3.builder.ToStringExclude;
import sem3.project.individual.misc.InstantDeserializer;

import java.time.Instant;

@AllArgsConstructor @Data
public class UpdateEventRequest
{
    private Long id;

    private String title;
    private String location;

    @JsonDeserialize(using = InstantDeserializer.class)
    @ToStringExclude @HashCodeExclude
    private Instant moment;
    private int totalTickets;
}

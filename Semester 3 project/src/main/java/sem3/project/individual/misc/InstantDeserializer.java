package sem3.project.individual.misc;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;
import java.time.Instant;

public class InstantDeserializer extends JsonDeserializer<Instant> {
    @Override
    public Instant deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException
    {
        String timestampString = jsonParser.readValueAs(String.class);
        return Instant.parse(timestampString);
    }
}

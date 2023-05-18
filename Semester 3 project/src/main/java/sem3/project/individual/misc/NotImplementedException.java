package sem3.project.individual.misc;

import lombok.NoArgsConstructor;

import javax.swing.text.html.Option;
import java.util.Optional;


public class NotImplementedException extends RuntimeException
{
    private Optional<String> message;

    public NotImplementedException()
    {
        message = Optional.empty();
    }

    public NotImplementedException(String message)
    {
        this.message = Optional.of(message);
    }
}

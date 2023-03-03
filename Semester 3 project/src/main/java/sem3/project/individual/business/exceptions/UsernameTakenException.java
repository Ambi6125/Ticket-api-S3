package sem3.project.individual.business.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class UsernameTakenException extends ResponseStatusException
{
    public UsernameTakenException()
    {
        super(HttpStatus.BAD_REQUEST, "USERNAME_TAKEN");
    }
}

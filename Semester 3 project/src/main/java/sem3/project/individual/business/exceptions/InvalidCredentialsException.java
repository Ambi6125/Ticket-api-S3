package sem3.project.individual.business.exceptions;

public class InvalidCredentialsException extends ExceptionWithMessage
{
    public InvalidCredentialsException()
    {
        super("Username or password incorrect.");
    }
    public InvalidCredentialsException(String message)
    {
        super(message);
    }
}

package sem3.project.individual.business.exceptions;

public class TimeLocationOverlapException extends ExceptionWithMessage
{

    public TimeLocationOverlapException()
    {
        super("An event already exists at this time and place.");
    }
}

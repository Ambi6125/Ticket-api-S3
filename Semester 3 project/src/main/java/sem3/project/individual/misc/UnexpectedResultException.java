package sem3.project.individual.misc;

import lombok.Getter;

public class UnexpectedResultException extends Exception
{
    @Getter
    private final String reason;
    public UnexpectedResultException(String explanation)
    {
        reason = explanation;
    }
}

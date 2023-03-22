package sem3.project.individual.domain.accounts;


import lombok.AllArgsConstructor;
import lombok.Getter;
import sem3.project.individual.misc.MessageArgBuilder;

@Getter @AllArgsConstructor
public class UpdateAccountResponse
{
    private boolean success;
    private String message;

    public UpdateAccountResponse(boolean success, MessageArgBuilder message)
    {
        this.success = success;
        this.message = message.construct();
    }
}

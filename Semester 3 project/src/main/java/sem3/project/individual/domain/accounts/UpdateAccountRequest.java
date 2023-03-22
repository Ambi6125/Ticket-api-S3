package sem3.project.individual.domain.accounts;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter @AllArgsConstructor
public class UpdateAccountRequest
{
    private final int targetId;
    private String username;
    private String password;
    private String email;
}
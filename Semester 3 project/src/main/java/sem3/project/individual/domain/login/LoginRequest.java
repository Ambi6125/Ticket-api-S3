package sem3.project.individual.domain.login;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class LoginRequest
{
    private String username;
    private String password;
}

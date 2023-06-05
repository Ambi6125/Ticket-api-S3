package sem3.project.individual.domain.login;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@Data
public class LoginRequest
{
    @NotBlank
    private String username;
    @NotBlank
    private String password;
}

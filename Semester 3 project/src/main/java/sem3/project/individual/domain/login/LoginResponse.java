package sem3.project.individual.domain.login;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor @Builder
@Data
public class LoginResponse
{
    private String accessToken;
}

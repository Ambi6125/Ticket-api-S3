package sem3.project.individual.domain.accounts;

import lombok.*;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateAccountRequest
{
    private String username;
    private String password;
    private String email;
}

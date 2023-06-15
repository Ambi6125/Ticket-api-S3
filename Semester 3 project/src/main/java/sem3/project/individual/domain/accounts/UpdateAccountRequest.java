package sem3.project.individual.domain.accounts;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Data
@AllArgsConstructor @Builder
public class UpdateAccountRequest
{
    private final Long targetId;
    private String username;
    private String password;
    private String email;
}

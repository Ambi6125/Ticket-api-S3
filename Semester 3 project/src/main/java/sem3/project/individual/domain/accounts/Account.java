package sem3.project.individual.domain.accounts;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;


@AllArgsConstructor
@Data
public class Account
{
    private final Long id;

    private String username;
    private String password;
    private String email;

    private Set<AccountRole> roles;
}

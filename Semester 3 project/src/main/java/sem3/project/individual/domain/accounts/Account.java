package sem3.project.individual.domain.accounts;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;

@AllArgsConstructor
@Getter
public class Account
{
    private final int id;
    private String username;
    private String email;
    private String password;
    private final LocalDateTime timeCreated;
}

package sem3.project.individual.persistence.entity;


import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data @Builder
public class AccountDTO
{
    private int id;
    private String username;
    private String email;
    private String password;
    private final Instant timeCreated;
}

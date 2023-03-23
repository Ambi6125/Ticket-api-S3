package sem3.project.individual.persistence.entity;


import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data @Builder
public class AccountEntity
{
    private int id;
    private String username;
    private String email;
    private String password;
    private final LocalDateTime timeCreated;
}

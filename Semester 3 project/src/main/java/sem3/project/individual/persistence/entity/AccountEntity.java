package sem3.project.individual.persistence.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "accounts")
public class AccountEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank
    @Min(4) @Max(20)
    private String username;

    @Pattern(regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")
    @Min(5) @Max(60)
    private String email;

    @NotNull
    @NotBlank
    @Min(8) @Max(200)
    private String password;

    @OneToMany
    @JoinColumn(name = "tickets_id")
    private List<TicketEntity> purchasedTickets;
}

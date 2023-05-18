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
    @Column(name = "id")
    private Long id;

    @NotNull
    @NotBlank
    @Min(4) @Max(50)
    @Column(name = "username")
    private String username;

    @Pattern(regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")
    @Min(5) @Max(60)
    @Column(name = "email")
    private String email;

    @NotNull
    @NotBlank
    @Min(8) @Max(200)
    @Column(name = "password")
    private String password;

    @OneToMany(mappedBy = "account")
    private List<TicketEntity> purchasedTickets;
}

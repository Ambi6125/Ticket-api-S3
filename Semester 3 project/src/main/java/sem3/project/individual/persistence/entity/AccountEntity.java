package sem3.project.individual.persistence.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import sem3.project.individual.domain.accounts.AccountRole;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

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
    @Length(min = 4, max = 50)
    @Column(name = "username")
    private String username;

    @Pattern(regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")
    @Length(min = 5, max = 30)
    @Column(name = "email")
    private String email;

    @NotNull
    @NotBlank
    @Length(min = 8, max = 255)
    @Column(name = "password")
    private String password;

    @OneToMany(mappedBy = "account")
    private List<TicketEntity> purchasedTickets;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "account_id")
    private Set<AccountRoleEntity> roles;
}

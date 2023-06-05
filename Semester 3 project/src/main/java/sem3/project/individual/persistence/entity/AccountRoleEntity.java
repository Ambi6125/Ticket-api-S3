package sem3.project.individual.persistence.entity;

import lombok.*;
import sem3.project.individual.domain.accounts.AccountRole;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "user_role")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AccountRoleEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "account_id")
    @ManyToOne
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private AccountEntity account;


    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "role_name")
    @Getter
    private AccountRole role;
}

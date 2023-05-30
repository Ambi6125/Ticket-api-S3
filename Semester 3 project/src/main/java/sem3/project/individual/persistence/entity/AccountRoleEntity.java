package sem3.project.individual.persistence.entity;

import javax.persistence.*;

@Entity
@Table(name = "user_role")
public class AccountRoleEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}

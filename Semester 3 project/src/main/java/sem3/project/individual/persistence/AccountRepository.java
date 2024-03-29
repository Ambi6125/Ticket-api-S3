package sem3.project.individual.persistence;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import sem3.project.individual.persistence.DTO.AccountTicketCountDTO;
import sem3.project.individual.persistence.entity.AccountEntity;

import java.util.List;

public interface AccountRepository extends JpaRepository<AccountEntity, Long>
{
    boolean existsByUsername(String username);
    AccountEntity findByUsername(String username);

    @Query("SELECT e FROM AccountEntity e WHERE e.id = :id")
    AccountEntity fetchById(@Param("id") Long id);

    @Query("SELECT new sem3.project.individual.persistence.DTO.AccountTicketCountDTO(a.username, COUNT(t)) " +
            "FROM AccountEntity a " +
            "JOIN a.purchasedTickets t " +
            "GROUP BY a.id " +
            "HAVING COUNT(t) >= :minAmount " +
            "ORDER BY COUNT(t) DESC")
    List<AccountTicketCountDTO> getUsersByTicketCount(@Param("minAmount") long minAmount);

}

package sem3.project.individual.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import sem3.project.individual.domain.tickets.Ticket;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.time.LocalDateTime;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "events")
public class EventEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @NotBlank
    @Min(2) @Max(50)
    @Column(name = "title")
    private String title;

    @NotNull
    @Column(name = "moment")
    private Instant moment;

    @NotNull
    @NotBlank
    @Min(4) @Max(60)
    @Column(name = "location")
    private String location;

    @Min(50)
    private int totalTickets;

    @Min(0)
    private int remainingTickets;

}

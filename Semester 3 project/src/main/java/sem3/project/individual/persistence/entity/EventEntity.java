package sem3.project.individual.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

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
    @Length(min = 2, max = 50)
    @Column(name = "title")
    private String title;

    @NotNull
    @Column(name = "moment")
    private Instant moment;

    @NotNull
    @NotBlank
    @Length(min = 4, max = 150)
    @Column(name = "location")
    private String location;

    @Min(1)
    @Column(name = "totalTickets")
    private int totalTickets;

    @Min(1)
    @Column(name = "remainingTickets")
    private int remainingTickets;

}

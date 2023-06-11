package sem3.project.individual.domain.tickets;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class BuyTicketsResponse
{
    private List<Long> ids;
}

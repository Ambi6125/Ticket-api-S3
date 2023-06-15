package sem3.project.individual.domain.tickets;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Collection;
import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class BuyTicketsResponse
{
    private List<Long> ids;

}

package sem3.project.individual.domain.artists;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder
public class Artist
{
    private final int id;
    private String name;

    @Setter
    private String description;


    public void setName(String newName) {
        if (newName.isEmpty() || newName.isBlank())
        {
            throw new IllegalArgumentException("New name contains no characters.");
        }
        name = newName;
    }
}

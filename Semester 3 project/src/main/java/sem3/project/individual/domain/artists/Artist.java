package sem3.project.individual.domain.artists;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Collections;
import java.util.List;

@Getter
@Builder
public class Artist
{
    private final Long id;
    private String name;

    @Setter
    private String description;

    private List<String> genres;


    public void setName(String newName) {
        if (newName.isEmpty() || newName.isBlank())
        {
            throw new IllegalArgumentException("New name contains no characters.");
        }
        name = newName;
    }

    public List<String> getGenres()
    {
        return Collections.unmodifiableList(genres);
    }
}

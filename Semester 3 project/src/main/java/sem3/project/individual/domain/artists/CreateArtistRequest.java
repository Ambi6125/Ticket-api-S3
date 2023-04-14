package sem3.project.individual.domain.artists;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import sem3.project.individual.domain.accounts.CreateAccountRequest;

import java.util.List;

@AllArgsConstructor
@Getter
public class CreateArtistRequest
{
    private String name;
    private String description;
    private List<String> genres;
}
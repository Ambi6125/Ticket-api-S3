package sem3.project.individual.domain.artists;

import lombok.AllArgsConstructor;
import lombok.Getter;
import sem3.project.individual.domain.accounts.GetAllAccountsResponse;
import sem3.project.individual.persistence.entity.ArtistEntity;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class GetAllArtistsResponse
{
    private List<Artist> artists;

    public List<Artist> getArtists()
    {
        return Collections.unmodifiableList(artists);
    }


}

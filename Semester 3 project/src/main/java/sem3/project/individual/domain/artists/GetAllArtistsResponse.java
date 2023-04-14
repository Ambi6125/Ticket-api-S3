package sem3.project.individual.domain.artists;

import lombok.AllArgsConstructor;
import lombok.Getter;
import sem3.project.individual.domain.accounts.GetAllAccountsResponse;
import sem3.project.individual.persistence.entity.ArtistEntity;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class GetAllArtistsResponse
{
    private List<Artist> artists;

    public List<Artist> getArtists()
    {
        return Collections.unmodifiableList(artists);
    }

    public GetAllArtistsResponse(List<ArtistEntity> artists)
    {
        this.artists = artists.stream().map(artistEntity -> {
                    return new Artist(artistEntity.getId(),
                            artistEntity.getName(),
                            artistEntity.getDescription(),
                            artistEntity.getGenres());
        })
                .collect(Collectors.toList());
    }
}

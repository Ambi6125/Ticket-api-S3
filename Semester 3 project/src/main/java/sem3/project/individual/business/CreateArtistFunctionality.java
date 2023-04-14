package sem3.project.individual.business;

import sem3.project.individual.domain.artists.CreateArtistRequest;
import sem3.project.individual.domain.artists.CreateArtistResponse;

public interface CreateArtistFunctionality
{
    CreateArtistResponse create(CreateArtistRequest request);
}

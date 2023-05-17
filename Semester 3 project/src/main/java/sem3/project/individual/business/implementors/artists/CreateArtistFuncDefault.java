package sem3.project.individual.business.implementors.artists;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import sem3.project.individual.business.CreateArtistFunctionality;
import sem3.project.individual.domain.artists.CreateArtistRequest;
import sem3.project.individual.domain.artists.CreateArtistResponse;
import sem3.project.individual.misc.NotImplementedException;
import sem3.project.individual.persistence.ArtistRepository;
import sem3.project.individual.persistence.entity.ArtistEntity;

@Service
@AllArgsConstructor
public class CreateArtistFuncDefault implements CreateArtistFunctionality
{
    private ArtistRepository repo;

    private ArtistEntity convertToEntity(Object request)
    {
        if(request instanceof CreateArtistRequest createRequest)
        {
            return ArtistEntity.builder()
                    .name(createRequest.getName())
                    .description(createRequest.getDescription())
                    .build();
        }

        else
        {
            throw new IllegalArgumentException("Not a proper request.");
        }
    }

    @Override
    public CreateArtistResponse create(CreateArtistRequest request)
    {
        var conversion = convertToEntity(request);
        return new CreateArtistResponse(repo.create(conversion).getId());
    }
}

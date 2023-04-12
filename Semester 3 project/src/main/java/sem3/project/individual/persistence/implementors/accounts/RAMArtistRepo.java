package sem3.project.individual.persistence.implementors.accounts;

import sem3.project.individual.persistence.ArtistRepository;
import sem3.project.individual.persistence.entity.ArtistEntity;

import java.util.ArrayList;
import java.util.List;

public class RAMArtistRepo implements ArtistRepository
{
    private int idSeeder = 1;
    private List<ArtistEntity> artists = new ArrayList<>();

    @Override
    public ArtistEntity create(ArtistEntity artist)
    {
        if(artists.stream().anyMatch(a -> a.getName() == artist.getName()))
        {
            throw new IllegalArgumentException("Name taken.");
        }
        ArtistEntity newEntity = ArtistEntity.builder()
                                    .id(idSeeder++)
                                    .name(artist.getName())
                                    .description(artist.getDescription())
                                    .genres(artist.getGenres())
                                    .build();
        artists.add(newEntity);
        return newEntity;
    }

    @Override
    public List<ArtistEntity> getAll() {
        return null;
    }

    @Override
    public ArtistEntity getByName(String name) {
        return null;
    }

    @Override
    public ArtistEntity getById(int id) {
        return null;
    }
}

package sem3.project.individual.persistence.implementors.accounts;

import org.springframework.stereotype.Repository;
import sem3.project.individual.persistence.ArtistRepository;
import sem3.project.individual.persistence.entity.ArtistEntity;

import java.util.*;

@Repository
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
        return Collections.unmodifiableList(artists);
    }

    @Override
    public Optional<ArtistEntity> getByName(String name)
    {
        return artists.stream()
                .filter(x -> x.getName()
                        .toLowerCase()
                        .equals(name.toLowerCase()))
                .findFirst();
    }

    @Override
    public ArtistEntity getById(int id)
    {
        return artists.stream()
                .filter(x -> x.getId() == id)
                .findFirst()
                .orElseThrow(NoSuchElementException::new);
    }

    @Override
    public void update(ArtistEntity artist)
    {
        for(int i = 0; i < artists.size(); i++)
        {
            if(artists.get(i).equals(artist))
            {
                artists.set(i, artist);
                break;
            }
        }
    }

    @Override
    public boolean delete(int id)
    {
        return artists.removeIf(x -> x.getId() == id);
    }
}

package sem3.project.individual.persistence;


import sem3.project.individual.domain.artists.Artist;
import sem3.project.individual.persistence.entity.ArtistEntity;

import java.util.List;
import java.util.Optional;

public interface ArtistRepository
{
    ArtistEntity create(ArtistEntity artist);
    List<ArtistEntity> getAll();
    Optional<ArtistEntity> getByName(String name);
    ArtistEntity getById(int id);

    void update(ArtistEntity artist);
    boolean delete(int id);
}
package sem3.project.individual.persistence;


import sem3.project.individual.domain.artists.Artist;
import sem3.project.individual.persistence.entity.ArtistEntity;

import java.util.List;

public interface ArtistRepository
{
    ArtistEntity create(ArtistEntity artist);
    List<ArtistEntity> getAll();
    ArtistEntity getByName(String name);
    ArtistEntity getById(int id);
}
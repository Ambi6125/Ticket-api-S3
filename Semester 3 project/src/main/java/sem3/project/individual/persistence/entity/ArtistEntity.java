package sem3.project.individual.persistence.entity;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder @Getter
public class ArtistEntity
{
    private int id;
    private String name;
    private String description;
    private List<String> genres;

    @Override
    public boolean equals(Object other)
    {
        if(other instanceof ArtistEntity artist)
        {
            return id == artist.getId();
        }

        return super.equals(other);
    }
}

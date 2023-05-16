package sem3.project.individual.persistence.entity;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.util.List;

@Builder
@Data
public class ArtistEntity
{
    private int id;
    private String name;
    private String description;


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

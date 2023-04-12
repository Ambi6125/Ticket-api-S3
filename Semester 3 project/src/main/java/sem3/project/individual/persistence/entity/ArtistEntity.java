package sem3.project.individual.persistence.entity;

import lombok.Builder;
import lombok.Getter;

@Builder @Getter
public class ArtistEntity
{
    private int id;
    private String name;
    private String description;
    private String[] genres;
}

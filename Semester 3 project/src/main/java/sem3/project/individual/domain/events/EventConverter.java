package sem3.project.individual.domain.events;


import sem3.project.individual.persistence.entity.EventEntity;

public final class EventConverter
{
    private EventConverter() {}

    public static <T extends EventEntity> Event toDomain(T entity)
    {
        return Event.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .moment(entity.getMoment())
                .location(entity.getLocation())
                .build();
    }


}

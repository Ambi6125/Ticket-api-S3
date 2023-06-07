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
                .totalTickets(entity.getTotalTickets())
                .remainingTickets(entity.getRemainingTickets())
                .location(entity.getLocation())
                .build();
    }

    public static <T extends CreateEventRequest> EventEntity creationToEntity(T request)
    {
        int ticketCap = request.getTotalTickets();
        return EventEntity.builder()
                .title(request.getTitle())
                .location(request.getLocation())
                .moment(request.getMoment())
                .totalTickets(ticketCap)
                .remainingTickets(ticketCap)
                .build();
    }
}

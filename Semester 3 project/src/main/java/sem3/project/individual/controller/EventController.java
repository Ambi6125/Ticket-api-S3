package sem3.project.individual.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sem3.project.individual.business.CreateEventFunctionality;
import sem3.project.individual.business.GetEventFunctionality;
import sem3.project.individual.business.exceptions.TimeLocationOverlapException;
import sem3.project.individual.domain.events.CreateEventRequest;
import sem3.project.individual.domain.events.GetEventResponse;

@RestController
@AllArgsConstructor
@RequestMapping("/events")
public class EventController
{
    private CreateEventFunctionality eventCreator;
    private GetEventFunctionality eventGetter;

    @PostMapping
    public ResponseEntity<Object> createEvent(CreateEventRequest request)
    {
        try
        {
            var response = eventCreator.create(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        }
        catch (TimeLocationOverlapException e)
        {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Data taken");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetEventResponse> getEventById(@PathVariable Long id)
    {
            var response = eventGetter.getById(id);

            return response.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
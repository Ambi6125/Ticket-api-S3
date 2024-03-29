package sem3.project.individual.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sem3.project.individual.business.CreateEventFunctionality;
import sem3.project.individual.business.GetEventFunctionality;
import sem3.project.individual.business.UpdateEventFunctionality;
import sem3.project.individual.business.exceptions.TimeLocationOverlapException;
import sem3.project.individual.configuration.security.auth.RequireAuthentication;
import sem3.project.individual.domain.events.*;
import sem3.project.individual.misc.NotImplementedException;

import javax.annotation.security.RolesAllowed;
import java.util.Collections;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/events")
@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:3000"}, allowedHeaders = {"Accept", "Content-Type", "Authorization"})
public class EventController
{
    private CreateEventFunctionality eventCreator;
    private GetEventFunctionality eventGetter;

    private UpdateEventFunctionality eventUpdater;

    @PostMapping
    public ResponseEntity<CreateEventResponse> createEvent(@RequestBody CreateEventRequest request)
    {
        try
        {
            var response = eventCreator.create(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        }
        catch (TimeLocationOverlapException e)
        {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }


    @RequireAuthentication @RolesAllowed({"ROLE_ADMIN"})
    @GetMapping
    public ResponseEntity<GetMultipleEventsResponse> getAll()
    {
        var response = eventGetter.getAll();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetEventResponse> getEventById(@PathVariable Long id)
    {
            var response = eventGetter.getById(id);

            return response.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<GetEventResponse> getEventByName(@PathVariable String name)
    {
        var response = eventGetter.getByTitle(name);
        return response.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/location/{location}")
    public ResponseEntity<GetMultipleEventsResponse> getEventByLocation(@PathVariable String location)
    {
        throw new NotImplementedException();
    }

    @GetMapping("/search")
    public ResponseEntity<GetMultipleEventsResponse> getEventsByNameSearch(@RequestParam String searchQuery)
    {
        Optional<GetMultipleEventsResponse> response = eventGetter.getByStringSearch(searchQuery);

        if(response.isPresent())
        {
            return  ResponseEntity.ok(response.get());
        }
        else return ResponseEntity.ok(new GetMultipleEventsResponse(Collections.emptyList()));
    }

    @PutMapping
    public ResponseEntity updateEvent(@RequestBody UpdateEventRequest request)
    {

        eventUpdater.updateEntity(request);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/random")
    public ResponseEntity<GetMultipleEventsResponse> getRandom()
    {
        var response = eventGetter.getRandom();
        return ResponseEntity.ok(response);
    }
}
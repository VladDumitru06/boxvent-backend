package com.boxvent.boxventwebsite.controller;

import com.boxvent.boxventwebsite.business.*;
import com.boxvent.boxventwebsite.domain.*;
import com.boxvent.boxventwebsite.presistence.Impl.entity.EventEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping("/events")
@CrossOrigin(origins ={"http://https://boxvent-frontend.herokuapp.com:3000"})
@RequiredArgsConstructor
public class EventController {
    private final CreateEventUseCase createEventUseCase;
    private final GetEventUseCase getEventUseCase;
    private final GetEventsUseCase getEventsUseCase;
    private final CreateFightCardUseCase createFightCardUseCase ;
    private final CreateFightUseCase createFightUseCase;
    private final UpdateEventUseCase updateEventUseCase;
    private final DeleteEventUseCase deleteEventUseCase;
    @GetMapping
    @RolesAllowed({"ROLE_ADMIN","ROLE_CLIENT","ROLE_GUEST"})
    public ResponseEntity<GetAllEventsResponse> getAllEvents() {
        GetAllEventsResponse response = getEventsUseCase.getEvents();
        return ResponseEntity.ok(response);
    }
    @GetMapping("/{eventId}")
    @RolesAllowed({"ROLE_ADMIN","ROLE_CLIENT","ROLE_GUEST"})
    public ResponseEntity<GetEventResponse> getEvent(@PathVariable(value="eventId") Long eventId) {
        GetEventResponse response = getEventUseCase.getEvent(eventId);
        return ResponseEntity.ok(response);
    }
    @PostMapping
    @RolesAllowed({"ROLE_ADMIN"})
    public ResponseEntity<CreateEventResponse> createEvent(@RequestBody @Valid CreateEventRequest request) {

        CreateEventResponse createEventResponse = createEventUseCase.createEvent(request);
        return ResponseEntity.ok(createEventResponse);
    }
    @PostMapping("/fightcard")
    @RolesAllowed({"ROLE_ADMIN"})
    public ResponseEntity<CreateFightCardResponse> createFightCard(@RequestBody @Valid CreateFightCardRequest request) {
        CreateFightCardResponse createFightCardResponse = createFightCardUseCase.createFightCard(request);
        return ResponseEntity.ok(createFightCardResponse);
    }
    @PostMapping("/fight")
    @RolesAllowed({"ROLE_ADMIN"})
    public ResponseEntity<CreateFightResponse> createFight(@RequestBody @Valid CreateFightRequest request) {
        CreateFightResponse createFightResponse = createFightUseCase.createFight(request);
        return ResponseEntity.ok(createFightResponse);
    }
    @GetMapping("/{eventId}/eventPic")
    @RolesAllowed({"ROLE_ADMIN","ROLE_CLIENT","ROLE_GUEST"})
    public ResponseEntity<Resource> getEventImage(@PathVariable(value="eventId") Long eventId) {
        try {
// Load the image file from the specified location
            File image = new File("src/main/java/com/boxvent/boxventwebsite/presistence/events/" + eventId + ".jpg");
            InputStream inputStream = new FileInputStream(image);
            // Return the image file as a response
            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_JPEG)
                    .body(new InputStreamResource(inputStream));
        } catch (IOException ex) {
            // If the image file is not found, return a 404 response
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping("/{eventId}")
    @RolesAllowed({"ROLE_ADMIN"})
    public ResponseEntity<Event> updateEvent(@PathVariable(value="eventId") Long eventId,@RequestBody UpdateEventRequest request)
    {
        Event updatedEvent = updateEventUseCase.UpdateEvent(request);
        return ResponseEntity.ok(updatedEvent);
    }

    @DeleteMapping("/{eventId}")
    @RolesAllowed({"ROLE_ADMIN"})
    public ResponseEntity deleteEvent(@PathVariable(value="eventId") Long eventId)
    {
        deleteEventUseCase.deleteEvent(eventId);
        return ResponseEntity.ok().build();
    }


}

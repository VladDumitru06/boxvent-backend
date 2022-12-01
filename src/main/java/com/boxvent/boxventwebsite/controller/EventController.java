package com.boxvent.boxventwebsite.controller;

import com.boxvent.boxventwebsite.business.*;
import com.boxvent.boxventwebsite.domain.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;

@RestController
@RequestMapping("/events")
@CrossOrigin(origins ={"http://localhost:3000"})
@RequiredArgsConstructor
public class EventController {
    private final CreateEventUseCase createEventUseCase;
    private final GetEventsUseCase getEventsUseCase;
    private final CreateFightCardUseCase createFightCardUseCase ;
    private final CreateFightUseCase createFightUseCase;
    @GetMapping
    @Secured({"ROLE_ADMIN","ROLE_CLIENT"})
    public ResponseEntity<GetAllEventsResponse> getAllEvents() {
        GetAllEventsResponse response = getEventsUseCase.getEvents();
        return ResponseEntity.ok(response);
    }

    @PostMapping

    public ResponseEntity<CreateEventResponse> createEvent(@RequestBody @Valid CreateEventRequest request) {
        CreateEventResponse createEventResponse = createEventUseCase.createEvent(request);
        return ResponseEntity.ok(createEventResponse);
    }
    @PostMapping("/fightcard")
    public ResponseEntity<CreateFightCardResponse> createFightCard(@RequestBody @Valid CreateFightCardRequest request) {
        CreateFightCardResponse createFightCardResponse = createFightCardUseCase.createFightCard(request);
        return ResponseEntity.ok(createFightCardResponse);
    }
    @PostMapping("/fight")
    public ResponseEntity<CreateFightResponse> createFight(@RequestBody @Valid CreateFightRequest request) {
        CreateFightResponse createFightResponse = createFightUseCase.createFight(request);
        return ResponseEntity.ok(createFightResponse);
    }
}

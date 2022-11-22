package com.boxvent.boxventwebsite.controller;


import com.boxvent.boxventwebsite.business.CreateTicketUseCase;
import com.boxvent.boxventwebsite.domain.CreateTicketRequest;
import com.boxvent.boxventwebsite.domain.CreateTicketResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/tickets")
@CrossOrigin(origins ={"http://localhost:3000"})
@RequiredArgsConstructor
public class TicketController {
    private final CreateTicketUseCase createTicketUseCase;

    @PostMapping
    public ResponseEntity<CreateTicketResponse> createCountry(@RequestBody @Valid CreateTicketRequest request) {
        CreateTicketResponse createTicketResponse = createTicketUseCase.createTicket(request);
        return ResponseEntity.ok(createTicketResponse);
    }
}

package com.boxvent.boxventwebsite.controller;


import com.boxvent.boxventwebsite.business.CreateTicketUseCase;
import com.boxvent.boxventwebsite.business.GetTicketsUseCase;
import com.boxvent.boxventwebsite.domain.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;

@RestController
@RequestMapping("/tickets")
@CrossOrigin(origins ={"http://localhost:3000"})
@RequiredArgsConstructor
public class TicketController {
    private final CreateTicketUseCase createTicketUseCase;
    private final GetTicketsUseCase getTicketsUseCase;
    @PostMapping
    public ResponseEntity<CreateTicketResponse> createTicket(@RequestBody @Valid CreateTicketRequest request) {
        CreateTicketResponse createTicketResponse = createTicketUseCase.createTicket(request);
        return ResponseEntity.ok(createTicketResponse);
    }
    @GetMapping
    public ResponseEntity<GetTicketsByUserResponse> getTickets(@RequestParam(value="id") final Long id){
        GetTicketsByUserResponse response = getTicketsUseCase.getTickets(id);
        return ResponseEntity.ok(response);
    }
}

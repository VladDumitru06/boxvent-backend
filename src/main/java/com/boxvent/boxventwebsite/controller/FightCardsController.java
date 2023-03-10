package com.boxvent.boxventwebsite.controller;

import com.boxvent.boxventwebsite.business.*;
import com.boxvent.boxventwebsite.domain.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.io.Console;

@RestController
@RequestMapping("/fightcards")
@CrossOrigin(origins ={"https://boxvent-frontend.herokuapp.com/"})
@RequiredArgsConstructor
public class FightCardsController {
    private final GetFightCardsUseCase getFightCardsUseCase;
    @GetMapping
    @RolesAllowed({"ROLE_ADMIN","ROLE_CLIENT","ROLE_GUEST"})
    public ResponseEntity<GetFightCardsByEventResponse> getFightCardsByEvent(@RequestParam(value="id") final Long id) {
        System.out.println(id);

        GetFightCardsByEventResponse response = getFightCardsUseCase.getFightCards(id);
        return ResponseEntity.ok(response);
    }



}

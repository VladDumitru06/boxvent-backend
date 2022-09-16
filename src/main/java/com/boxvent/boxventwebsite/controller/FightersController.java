package com.boxvent.boxventwebsite.controller;

import com.boxvent.boxventwebsite.business.GetFighterUseCase;
import com.boxvent.boxventwebsite.domain.Fighter;
import lombok.AllArgsConstructor;
import com.boxvent.boxventwebsite.business.CreateFighterUseCase;
import com.boxvent.boxventwebsite.business.GetFightersUseCase;
import com.boxvent.boxventwebsite.domain.CreateFighterRequest;
import com.boxvent.boxventwebsite.domain.CreateFighterResponse;
import com.boxvent.boxventwebsite.domain.GetAllFightersResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("/fighters")
public class FightersController {
    private final CreateFighterUseCase createFighterUseCase;
    private final GetFightersUseCase getFightersUseCase;
    private final GetFighterUseCase getFighterUseCase;
    @GetMapping
    public ResponseEntity<GetAllFightersResponse> getAllFighters() {
        GetAllFightersResponse response = getFightersUseCase.getFighters();
        return ResponseEntity.ok(response);
    }
    @GetMapping("{id}")
    public ResponseEntity<Fighter> getCountry(@PathVariable(value="id") final long id){
        final Fighter fighter = getFighterUseCase.getFighter(id);
        if(fighter == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(fighter);
    }
    @PostMapping()
    public ResponseEntity<CreateFighterResponse> createFighter(@RequestBody @Valid CreateFighterRequest request) {
        CreateFighterResponse response = createFighterUseCase.createFighter(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}

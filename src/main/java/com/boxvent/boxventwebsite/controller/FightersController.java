package com.boxvent.boxventwebsite.controller;

import com.boxvent.boxventwebsite.business.*;
import com.boxvent.boxventwebsite.domain.*;
import lombok.AllArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@RestController
@CrossOrigin(origins ={"http://localhost:3000"})
@AllArgsConstructor
@RequestMapping("/fighters")
public class FightersController {
    private final CreateFighterUseCase createFighterUseCase;
    private final GetFightersUseCase getFightersUseCase;
    private final GetFighterUseCase getFighterUseCase;
    private  final UpdateFighterUseCase updateFighterUseCase;
    private final DeleteFighterUseCase deleteFighterUseCase;
    @GetMapping
    @RolesAllowed({"ROLE_ADMIN","ROLE_CLIENT","ROLE_GUEST"})
    public ResponseEntity<GetAllFightersResponse> getAllFighters() {
        GetAllFightersResponse response = getFightersUseCase.getFighters();
        return ResponseEntity.ok(response);
    }
    @GetMapping("{id}")
    @RolesAllowed({"ROLE_ADMIN","ROLE_CLIENT","ROLE_GUEST"})
    public ResponseEntity<Fighter> getFighter(@PathVariable(value="id") final long id){
        final Fighter fighter = getFighterUseCase.getFighter(id);
        if(fighter == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(fighter);
    }
    @PostMapping()
    @RolesAllowed("ROLE_ADMIN")
    public ResponseEntity<CreateFighterResponse> createFighter(@RequestBody @Valid CreateFighterRequest request) {
        CreateFighterResponse response = createFighterUseCase.createFighter(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @GetMapping("/{fighterId}/profilePic")
    @RolesAllowed({"ROLE_ADMIN","ROLE_CLIENT","ROLE_GUEST"})
    public ResponseEntity<Resource> getImage(@PathVariable(value="fighterId") Long fighterId) {
        try {
            // Load the image file from the specified location
            File image = new File("src/main/java/com/boxvent/boxventwebsite/presistence/fighters/" + fighterId + ".jpg");
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
    @PutMapping("/{fighterId}")
    @RolesAllowed({"ROLE_ADMIN"})
    public ResponseEntity<Fighter> updateEvent(@PathVariable(value="fighterId") Long fighterId, @RequestBody UpdateFighterRequest request)
    {
        Fighter updatedFighter = updateFighterUseCase.UpdateFighter(request);
        return ResponseEntity.ok(updatedFighter);
    }
    @DeleteMapping("/{fighterId}")
    @RolesAllowed({"ROLE_ADMIN"})
    public ResponseEntity deleteEvent(@PathVariable(value="fighterId") Long fighterId)
    {
        deleteFighterUseCase.deleteFighter(fighterId);
        return ResponseEntity.ok().build();
    }

}

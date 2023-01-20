package com.boxvent.boxventwebsite.controller;

import com.boxvent.boxventwebsite.business.LoginUseCase;
import com.boxvent.boxventwebsite.domain.LoginRequest;
import com.boxvent.boxventwebsite.domain.LoginResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;

@RestController
@RequestMapping("/login")
@CrossOrigin(origins ={"http://localhost:3000"})
@RequiredArgsConstructor
public class LoginController {
    private final LoginUseCase loginUseCase;

    @PostMapping
    public ResponseEntity<LoginResponse> login(@RequestBody @Valid LoginRequest loginRequest) {
        LoginResponse loginResponse = loginUseCase.login(loginRequest);
        return ResponseEntity.ok(loginResponse);
    }
}

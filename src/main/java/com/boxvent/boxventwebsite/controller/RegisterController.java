package com.boxvent.boxventwebsite.controller;

import com.boxvent.boxventwebsite.business.RegisterUserUseCase;
import com.boxvent.boxventwebsite.domain.LoginRequest;
import com.boxvent.boxventwebsite.domain.LoginResponse;
import com.boxvent.boxventwebsite.domain.RegisterRequest;
import com.boxvent.boxventwebsite.domain.RegisterResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/register")
@RequiredArgsConstructor
public class RegisterController {
    private final RegisterUserUseCase registerUserUseCase;

    @PostMapping
    public ResponseEntity<RegisterResponse> register(@RequestBody @Valid RegisterRequest request) {
        RegisterResponse loginResponse = registerUserUseCase.createNewUser(request);
        return ResponseEntity.ok(loginResponse);
    }
}

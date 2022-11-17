package com.boxvent.boxventwebsite.business.impl;

import com.boxvent.boxventwebsite.business.AccessTokenEncoder;
import com.boxvent.boxventwebsite.business.LoginUseCase;
import com.boxvent.boxventwebsite.business.exception.InvalidCredentialsException;
import com.boxvent.boxventwebsite.domain.AccessToken;
import com.boxvent.boxventwebsite.domain.LoginRequest;
import com.boxvent.boxventwebsite.domain.LoginResponse;
import com.boxvent.boxventwebsite.presistence.UserRepository;
import com.boxvent.boxventwebsite.presistence.Impl.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LoginUseCaseImpl implements LoginUseCase {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AccessTokenEncoder accessTokenEncoder;

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        UserEntity user = userRepository.findByUsername(loginRequest.getUsername());
        if (user == null) {
            throw new InvalidCredentialsException();
        }

        if (!matchesPassword(loginRequest.getPassword(), user.getPassword())) {
            throw new InvalidCredentialsException();
        }

        String accessToken = generateAccessToken(user);
        return LoginResponse.builder().accessToken(accessToken).build();
    }

    private boolean matchesPassword(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword,encodedPassword);
    }

    private String generateAccessToken(UserEntity user) {
        List<String> roles = user.getUserRoles().stream()
                .map(userRole -> userRole.getRole().toString())
                .toList();

        return accessTokenEncoder.encode(
                AccessToken.builder()
                        .subject(user.getUsername())
                        .roles(roles)
                        .build());
    }

}

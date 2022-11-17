package com.boxvent.boxventwebsite.business;

import com.boxvent.boxventwebsite.domain.LoginRequest;
import com.boxvent.boxventwebsite.domain.LoginResponse;

public interface LoginUseCase {
    LoginResponse login(LoginRequest loginRequest);
}

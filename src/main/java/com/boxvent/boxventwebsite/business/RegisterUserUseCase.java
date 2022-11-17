package com.boxvent.boxventwebsite.business;

import com.boxvent.boxventwebsite.domain.RegisterRequest;
import com.boxvent.boxventwebsite.domain.RegisterResponse;

public interface RegisterUserUseCase {
     RegisterResponse createNewUser(RegisterRequest request);
}

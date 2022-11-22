package com.boxvent.boxventwebsite.business;

import com.boxvent.boxventwebsite.domain.CreateLocationRequest;
import com.boxvent.boxventwebsite.domain.CreateLocationResponse;

public interface CreateLocationUseCase {
    CreateLocationResponse createLocation (CreateLocationRequest request);
}

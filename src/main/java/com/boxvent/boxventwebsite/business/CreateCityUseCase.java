package com.boxvent.boxventwebsite.business;

import com.boxvent.boxventwebsite.domain.CreateCityRequest;
import com.boxvent.boxventwebsite.domain.CreateCityResponse;

public interface CreateCityUseCase {
    CreateCityResponse createCity(CreateCityRequest request);
}

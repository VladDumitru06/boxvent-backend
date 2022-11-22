package com.boxvent.boxventwebsite.business;

import com.boxvent.boxventwebsite.domain.CreateCountryRequest;
import com.boxvent.boxventwebsite.domain.CreateCountryResponse;

public interface CreateCountryUseCase {
    CreateCountryResponse createCountry(CreateCountryRequest request);
}

package com.boxvent.boxventwebsite.business;

import com.boxvent.boxventwebsite.domain.GetAllCitiesResponse;
import com.boxvent.boxventwebsite.domain.GetAllCountriesResponse;

public interface GetCountriesUseCase {
    GetAllCountriesResponse getCountries();
}

package com.boxvent.boxventwebsite.controller;


import com.boxvent.boxventwebsite.business.*;
import com.boxvent.boxventwebsite.domain.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/locations")
@CrossOrigin(origins ={"http://localhost:3000"})
@RequiredArgsConstructor
public class LocationController {
    private final CreateLocationUseCase createLocationUseCase;
    private final CreateCountryUseCase createCountryUseCase;
    private final CreateCityUseCase createCityUseCase;
    private final GetCitiesUseCase getCitiesUseCase;
    private final GetCountriesUseCase getCountriesUseCase;
    @PostMapping
    public ResponseEntity<CreateLocationResponse> createLocation(@RequestBody @Valid CreateLocationRequest request) {
        CreateLocationResponse createLocationResponse = createLocationUseCase.createLocation(request);
        return ResponseEntity.ok(createLocationResponse);
    }
    @PostMapping("/city")
    public ResponseEntity<CreateCityResponse> createCity(@RequestBody @Valid CreateCityRequest request) {
        CreateCityResponse createCountryResponse = createCityUseCase.createCity(request);
        return ResponseEntity.ok(createCountryResponse);
    }
    @PostMapping("/country")
    public ResponseEntity<CreateCountryResponse> createCity(@RequestBody @Valid CreateCountryRequest request) {
        CreateCountryResponse createCountryResponse = createCountryUseCase.createCountry(request);
        return ResponseEntity.ok(createCountryResponse);
    }

    @GetMapping("/cities")
    public ResponseEntity<GetAllCitiesResponse> getAllCities() {
        GetAllCitiesResponse response = getCitiesUseCase.getCities();
        return ResponseEntity.ok(response);
    }
    @GetMapping("/countries")
    public ResponseEntity<GetAllCountriesResponse> getAllCountries() {
        GetAllCountriesResponse response = getCountriesUseCase.getCountries();
        return ResponseEntity.ok(response);
    }
}

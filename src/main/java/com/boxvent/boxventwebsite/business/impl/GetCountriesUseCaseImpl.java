package com.boxvent.boxventwebsite.business.impl;

import com.boxvent.boxventwebsite.business.GetCitiesUseCase;
import com.boxvent.boxventwebsite.business.GetCountriesUseCase;
import com.boxvent.boxventwebsite.domain.City;
import com.boxvent.boxventwebsite.domain.Country;
import com.boxvent.boxventwebsite.domain.GetAllCitiesResponse;
import com.boxvent.boxventwebsite.domain.GetAllCountriesResponse;
import com.boxvent.boxventwebsite.presistence.CityRepository;
import com.boxvent.boxventwebsite.presistence.CountryRepository;
import com.boxvent.boxventwebsite.presistence.Impl.entity.CityEntity;
import com.boxvent.boxventwebsite.presistence.Impl.entity.CountryEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class GetCountriesUseCaseImpl implements GetCountriesUseCase {
    private CountryRepository countryRepository;
    private CountryConverter countryConverter;

    @Transactional
    @Override
    public GetAllCountriesResponse getCountries() {
        List<Country> countries = new ArrayList<>();
        for(CountryEntity countryEntity : countryRepository.findAll())
        {
            countries.add(countryConverter.convert(countryEntity));
        }
        return GetAllCountriesResponse.builder().countries(countries).build();
    }
}

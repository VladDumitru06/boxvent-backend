package com.boxvent.boxventwebsite.business.impl;

import com.boxvent.boxventwebsite.business.CreateCityUseCase;
import com.boxvent.boxventwebsite.business.exception.CityNameAlreadyExistsException;
import com.boxvent.boxventwebsite.business.exception.InvalidCountryException;
import com.boxvent.boxventwebsite.domain.CreateCityRequest;
import com.boxvent.boxventwebsite.domain.CreateCityResponse;
import com.boxvent.boxventwebsite.presistence.CityRepository;
import com.boxvent.boxventwebsite.presistence.CountryRepository;
import com.boxvent.boxventwebsite.presistence.Impl.entity.CityEntity;
import com.boxvent.boxventwebsite.presistence.Impl.entity.CountryEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
@Service
@AllArgsConstructor
public class CreateCityUseCaseImpl implements CreateCityUseCase {

    private final CityRepository cityRepository;
    private final CountryRepository countryRepository;
    @Override
    @Transactional
    public CreateCityResponse createCity(CreateCityRequest request) {
        if(cityRepository.existsByCityName(request.getCityName())){
            throw new CityNameAlreadyExistsException();
        }
        CityEntity savedCity = saveNewCity(request);
        return CreateCityResponse.builder().cityId(savedCity.getId()).build();

    }
    private CityEntity saveNewCity(CreateCityRequest request)
    {
        CountryEntity country = countryRepository.findByCountryCode(request.getCountryCode())
                .orElseThrow(InvalidCountryException::new);
        CityEntity city = CityEntity.builder().cityName(request.getCityName()).country(country).build();
        return cityRepository.save(city);
    }
}

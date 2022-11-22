package com.boxvent.boxventwebsite.business.impl;

import com.boxvent.boxventwebsite.business.CreateLocationUseCase;
import com.boxvent.boxventwebsite.business.exception.InvalidCountryException;
import com.boxvent.boxventwebsite.business.exception.LocationAlreadyExistsException;
import com.boxvent.boxventwebsite.domain.CreateLocationRequest;
import com.boxvent.boxventwebsite.domain.CreateLocationResponse;
import com.boxvent.boxventwebsite.presistence.CityRepository;
import com.boxvent.boxventwebsite.presistence.CountryRepository;
import com.boxvent.boxventwebsite.presistence.Impl.entity.CityEntity;
import com.boxvent.boxventwebsite.presistence.Impl.entity.CountryEntity;
import com.boxvent.boxventwebsite.presistence.Impl.entity.FightEntity;
import com.boxvent.boxventwebsite.presistence.Impl.entity.LocationEntity;
import com.boxvent.boxventwebsite.presistence.LocationRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
@Service
@AllArgsConstructor
public class CreateLocationUseCaseImpl implements CreateLocationUseCase {
    private final LocationRepository locationRepository;
    private final CityRepository cityRepository;
    private  final CountryRepository countryRepository;
    @Override
    @Transactional
    public CreateLocationResponse createLocation(CreateLocationRequest request)
    {
        CountryEntity country = countryRepository.findByCountryCode(request.getCountryCode())
                .orElseThrow(InvalidCountryException::new);
        CityEntity city = cityRepository.findByCityName(request.getCityName())
                .orElseThrow(InvalidCountryException::new);
        Example<LocationEntity> exampleLocationEntity = Example.of(LocationEntity.builder()
                .city(city)
                .country(country)
                .build());
        if(locationRepository.exists(exampleLocationEntity))
        {
            throw new LocationAlreadyExistsException();
        }
        LocationEntity savedLocation = saveLocation(country,city);
        return CreateLocationResponse.builder().locationId(savedLocation.getId()).build();
    }
    private LocationEntity saveLocation(CountryEntity country, CityEntity city)
    {

        LocationEntity newLocation = LocationEntity.builder().country(country).city(city).build();
        return locationRepository.save(newLocation);
    }
}

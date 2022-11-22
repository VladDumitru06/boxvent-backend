package com.boxvent.boxventwebsite.business.impl;

import com.boxvent.boxventwebsite.business.CreateCountryUseCase;
import com.boxvent.boxventwebsite.business.exception.CountryCodeAlreadyExistsException;
import com.boxvent.boxventwebsite.domain.CreateCountryRequest;
import com.boxvent.boxventwebsite.domain.CreateCountryResponse;
import com.boxvent.boxventwebsite.presistence.CountryRepository;
import com.boxvent.boxventwebsite.presistence.Impl.entity.CountryEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
@Service
@AllArgsConstructor
public class CreateCountryUseCaseImpl implements CreateCountryUseCase {
    private final CountryRepository countryRepository;
    @Override
    @Transactional
    public CreateCountryResponse createCountry(CreateCountryRequest request)
    {
        if(countryRepository.existsByCountryCode(request.getCountryCode()))
        {
            throw new CountryCodeAlreadyExistsException();
        }
        CountryEntity country = saveCountry(request);
        return CreateCountryResponse.builder().countryId(country.getId()).build();
    }
    private CountryEntity saveCountry(CreateCountryRequest request)
    {
        CountryEntity newCountry = CountryEntity.builder().countryCode(request.getCountryCode()).countryName(request.getCountryName()).build();
        return countryRepository.save(newCountry);

    }
}

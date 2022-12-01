package com.boxvent.boxventwebsite.business.impl;

import com.boxvent.boxventwebsite.domain.City;
import com.boxvent.boxventwebsite.domain.Country;
import com.boxvent.boxventwebsite.presistence.Impl.entity.CityEntity;
import com.boxvent.boxventwebsite.presistence.Impl.entity.CountryEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

@Data
@AllArgsConstructor
@Service
final class CountryConverter {
    public Country convert(CountryEntity country) {
        return Country.builder()
                .id(country.getId())
                .country_code(country.getCountryCode())
                .country_name(country.getCountryName())
                .build();
    }

}

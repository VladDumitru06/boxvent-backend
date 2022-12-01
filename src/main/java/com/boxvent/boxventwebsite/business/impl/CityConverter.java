package com.boxvent.boxventwebsite.business.impl;

import com.boxvent.boxventwebsite.domain.City;
import com.boxvent.boxventwebsite.domain.Location;
import com.boxvent.boxventwebsite.presistence.Impl.entity.CityEntity;
import com.boxvent.boxventwebsite.presistence.Impl.entity.LocationEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

@Data
@AllArgsConstructor
@Service
final class CityConverter {
    public City convert(CityEntity city) {
        return City.builder()
                .id(city.getId())
                .city_name(city.getCityName())
                .country_code(city.getCountry().getCountryCode())
                .build();
    }

}

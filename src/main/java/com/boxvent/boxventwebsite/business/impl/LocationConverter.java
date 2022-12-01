package com.boxvent.boxventwebsite.business.impl;

import com.boxvent.boxventwebsite.domain.Fighter;
import com.boxvent.boxventwebsite.domain.Location;
import com.boxvent.boxventwebsite.presistence.Impl.entity.FighterEntity;
import com.boxvent.boxventwebsite.presistence.Impl.entity.LocationEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

@Data
@AllArgsConstructor
@Service
final class LocationConverter {
    private final CityConverter cityConverter;
    private final CountryConverter countryConverter;
    public Location convert(LocationEntity location) {
        return Location.builder()
                .id(location.getId())
                .city(cityConverter.convert(location.getCity()) )
                .country(countryConverter.convert(location.getCountry()))
                .build();
    }

}

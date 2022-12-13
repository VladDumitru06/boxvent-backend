package com.boxvent.boxventwebsite.business.impl;

import com.boxvent.boxventwebsite.business.GetCitiesUseCase;
import com.boxvent.boxventwebsite.business.GetFighterUseCase;
import com.boxvent.boxventwebsite.business.exception.InvalidFighterException;
import com.boxvent.boxventwebsite.domain.City;
import com.boxvent.boxventwebsite.domain.Fighter;
import com.boxvent.boxventwebsite.domain.GetAllCitiesResponse;
import com.boxvent.boxventwebsite.domain.GetAllFightersResponse;
import com.boxvent.boxventwebsite.presistence.CityRepository;
import com.boxvent.boxventwebsite.presistence.FighterRepository;
import com.boxvent.boxventwebsite.presistence.Impl.entity.CityEntity;
import com.boxvent.boxventwebsite.presistence.Impl.entity.FighterEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class GetCitiesUseCaseImpl implements GetCitiesUseCase {
    private CityRepository cityRepository;
    private CityConverter cityConverter;

    @Transactional
    @Override
    public GetAllCitiesResponse getCities() {
        List<City> cities = new ArrayList<>();
        for(CityEntity cityEntity : cityRepository.findAll())
        {
            cities.add(cityConverter.convert(cityEntity));
        }
        return GetAllCitiesResponse.builder().cities(cities).build();
    }
}

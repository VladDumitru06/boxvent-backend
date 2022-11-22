package com.boxvent.boxventwebsite.presistence;

import com.boxvent.boxventwebsite.presistence.Impl.entity.CityEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CityRepository extends JpaRepository<CityEntity,Long> {
    Boolean existsByCityName(String cityName);
    Optional<CityEntity> findByCityName(String cityName);

}

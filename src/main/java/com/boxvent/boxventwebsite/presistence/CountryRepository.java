package com.boxvent.boxventwebsite.presistence;

import com.boxvent.boxventwebsite.presistence.Impl.entity.CountryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CountryRepository extends JpaRepository<CountryEntity,Long> {
    Optional<CountryEntity> findByCountryCode(String countryCode);
    Boolean existsByCountryCode(String countryCode);


}

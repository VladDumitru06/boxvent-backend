package com.boxvent.boxventwebsite.presistence;

import com.boxvent.boxventwebsite.presistence.Impl.entity.LocationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<LocationEntity,Long> {

}

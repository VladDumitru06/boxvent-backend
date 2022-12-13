package com.boxvent.boxventwebsite.presistence;

import com.boxvent.boxventwebsite.presistence.Impl.entity.CityEntity;
import com.boxvent.boxventwebsite.presistence.Impl.entity.FightCardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Set;

public interface FightCardRepository extends JpaRepository<FightCardEntity,Long> {

    Optional<Set<FightCardEntity>> findByEventId(Long eventId);
}

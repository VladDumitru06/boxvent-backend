package com.boxvent.boxventwebsite.presistence;

import com.boxvent.boxventwebsite.presistence.Impl.entity.FightEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FightRepository extends JpaRepository<FightEntity,Long> {

}

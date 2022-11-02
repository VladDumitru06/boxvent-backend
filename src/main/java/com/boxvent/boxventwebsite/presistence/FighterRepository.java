package com.boxvent.boxventwebsite.presistence;

import com.boxvent.boxventwebsite.presistence.Impl.entity.FighterEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FighterRepository extends JpaRepository<FighterEntity,Long> {
    boolean existsByName(String name);
    FighterEntity findByName(String name);
}

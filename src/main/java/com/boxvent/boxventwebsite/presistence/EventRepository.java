package com.boxvent.boxventwebsite.presistence;

import com.boxvent.boxventwebsite.presistence.Impl.entity.EventEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<EventEntity,Long> {
    boolean existsByEventName(String name);
}

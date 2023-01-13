package com.boxvent.boxventwebsite.presistence;

import com.boxvent.boxventwebsite.presistence.Impl.entity.TicketEntity;
import com.boxvent.boxventwebsite.presistence.Impl.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface TicketRepository extends JpaRepository<TicketEntity,Long> {
    Set<TicketEntity> findByUserId(Long id);

}

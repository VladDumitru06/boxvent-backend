package com.boxvent.boxventwebsite.presistence;

import com.boxvent.boxventwebsite.presistence.Impl.entity.TicketEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<TicketEntity,Long> {

}

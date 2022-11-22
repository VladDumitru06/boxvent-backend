package com.boxvent.boxventwebsite.presistence.Impl.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "event")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EventEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "event_name")
    private String eventName;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "location_id", referencedColumnName = "id")
    private LocationEntity location;

    @NotNull
    @Column(name = "sold_tickets",columnDefinition = "integer default 0")
    private Long soldTickets;

    @NotNull
    @Column(name = "available_tickets")
    private Long availableTickets;

    @NotNull
    @Column(name = "ticket_price")
    private Double ticketPrice;

    @NotNull
    @Column(name="date_time")
    private LocalDateTime dateTime;
}

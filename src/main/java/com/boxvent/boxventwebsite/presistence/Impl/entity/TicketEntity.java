package com.boxvent.boxventwebsite.presistence.Impl.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "ticket")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class TicketEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @OneToOne
    @JoinColumn(name = "client_id",referencedColumnName = "id")
    private ClientEntity client;

    @NotNull
    @OneToOne
    @JoinColumn(name = "event_id",referencedColumnName = "id")
    private EventEntity event;
}

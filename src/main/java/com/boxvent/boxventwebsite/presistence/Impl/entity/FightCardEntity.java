package com.boxvent.boxventwebsite.presistence.Impl.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "fight_card")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FightCardEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name="order_nr")
    private Long order_nr;

    @NotNull
    @OneToOne
    @JoinColumn(name = "fight_id", referencedColumnName = "id")
    private FightEntity fight;

    @OneToOne
    @JoinColumn(name = "event_id")
    private EventEntity event;
}

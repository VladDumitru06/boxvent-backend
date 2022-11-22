package com.boxvent.boxventwebsite.presistence.Impl.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "fight")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FightEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "challenger_id", referencedColumnName = "id")
    private FighterEntity challengerFighter;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "challenged_id", referencedColumnName = "id")
    private FighterEntity challengedFighter;

    @NotNull
    @Column(name="rounds")
    private Long rounds;
}

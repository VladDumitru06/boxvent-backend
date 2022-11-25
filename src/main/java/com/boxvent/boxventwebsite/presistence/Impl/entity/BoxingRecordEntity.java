package com.boxvent.boxventwebsite.presistence.Impl.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "boxing_record")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoxingRecordEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "fighterid", referencedColumnName = "id")
    private FighterEntity fighter;

    @NotNull
    @Column(name = "wins")
    private Long wins;

    @NotNull
    @Column(name = "draws")
    private Long draws;

    @NotNull
    @Column(name = "loses")
    private Long loses;

}

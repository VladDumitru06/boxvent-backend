package com.boxvent.boxventwebsite.presistence.Impl.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Builder
@Entity
@Table(name = "fighter")
@AllArgsConstructor
@NoArgsConstructor
public class FighterEntity {
    /*
    CREATE TABLE fighter
(
    id   int     NOT NULL AUTO_INCREMENT,
    name varchar(50) NOT NULL,
    PRIMARY KEY (id)
);
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "name")
    private String name;

}

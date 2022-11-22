package com.boxvent.boxventwebsite.presistence.Impl.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "country")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CountryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Length(min = 2, max = 50)
    @Column(name = "country_name")
    private String countryName;

    @NotNull
    @Length(min = 2, max = 2)
    @Column(name = "country_code")
    private String countryCode;
}
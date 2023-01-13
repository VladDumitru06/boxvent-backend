package com.boxvent.boxventwebsite.presistence.Impl.entity;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Entity
@Table(name = "user")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @NotBlank
    @Length(min = 2, max = 20)
    @Column(name = "username", unique = true)
    private String username;

    @NotBlank
    @Column(name="email", unique = true)
    private String email;
    @NotBlank
    @Column(name = "password")
    @Length(max = 100)
    private String password;


    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private Set<UserRoleEntity> userRoles;
}

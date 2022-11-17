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
    @Column(name = "username")
    private String username;

    @Column(name = "password")
    @Length(max = 100)
    private String password;

    @OneToOne(optional = true,cascade = CascadeType.ALL)
    @JoinColumn(name = "client_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private ClientEntity client;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private Set<UserRoleEntity> userRoles;
}

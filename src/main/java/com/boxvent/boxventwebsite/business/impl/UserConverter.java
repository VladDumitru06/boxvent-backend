package com.boxvent.boxventwebsite.business.impl;

import com.boxvent.boxventwebsite.domain.User;
import com.boxvent.boxventwebsite.domain.UserRole;
import com.boxvent.boxventwebsite.presistence.Impl.entity.UserEntity;
import com.boxvent.boxventwebsite.presistence.Impl.entity.UserRoleEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
@Data
final class UserConverter {
    public User convert(UserEntity userEntity) {
        return User.builder()
                .id(userEntity.getId())
                .username(userEntity.getUsername())
                .email(userEntity.getEmail())
                .password(userEntity.getPassword())
                .build();
    }

}
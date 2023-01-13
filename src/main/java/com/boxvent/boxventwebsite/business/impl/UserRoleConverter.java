package com.boxvent.boxventwebsite.business.impl;

import com.boxvent.boxventwebsite.domain.UserRole;
import com.boxvent.boxventwebsite.presistence.Impl.entity.UserRoleEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

@Service
@Data
@AllArgsConstructor
final class UserRoleConverter {

    private final UserConverter userConverter;
    public UserRole convert(UserRoleEntity userRoleEntity) {
        return UserRole.builder()
                .id(userRoleEntity.getId())
                .role(userRoleEntity.getRole())
                .user(userConverter.convert(userRoleEntity.getUser()))
                .build();
    }
}

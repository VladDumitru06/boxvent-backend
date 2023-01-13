package com.boxvent.boxventwebsite.business.impl;

import com.boxvent.boxventwebsite.business.RegisterUserUseCase;
import com.boxvent.boxventwebsite.business.exception.UsernameAlreadyExistsException;
import com.boxvent.boxventwebsite.domain.RegisterRequest;
import com.boxvent.boxventwebsite.domain.RegisterResponse;
import com.boxvent.boxventwebsite.presistence.Impl.entity.RoleEnum;
import com.boxvent.boxventwebsite.presistence.Impl.entity.UserEntity;
import com.boxvent.boxventwebsite.presistence.Impl.entity.UserRoleEntity;
import com.boxvent.boxventwebsite.presistence.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class RegisterUserUseCaseImpl implements RegisterUserUseCase {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    @Override
    @Transactional
    public RegisterResponse createNewUser(RegisterRequest request) {
    if(userRepository.existsByUsername(request.getUsername()))
    {
        throw new UsernameAlreadyExistsException() ;
    }
            String encodedPassword = passwordEncoder.encode(request.getPassword());

            UserEntity newUser = UserEntity.builder()
                    .username(request.getUsername())
                    .email(request.getEmail())
                    .password(encodedPassword)
                    .build();
            newUser.setUserRoles(Set.of(
                    UserRoleEntity.builder()
                            .user(newUser)
                            .role(RoleEnum.CLIENT)
                            .build()));

            UserEntity savedUser = userRepository.save(newUser);
            return RegisterResponse.builder().userid(savedUser.getId()).build();
    }
}

package com.boxvent.boxventwebsite.business.impl;

import com.boxvent.boxventwebsite.business.RegisterUserUseCase;
import com.boxvent.boxventwebsite.domain.RegisterRequest;
import com.boxvent.boxventwebsite.domain.RegisterResponse;
import com.boxvent.boxventwebsite.presistence.Impl.entity.ClientEntity;
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

            String encodedPassword = passwordEncoder.encode(request.getPassword());
            ClientEntity newClient = ClientEntity.builder().build();
            UserEntity newUser = UserEntity.builder()
                    .username(request.getUsername())
                    .password(encodedPassword)
                    .client(newClient)
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

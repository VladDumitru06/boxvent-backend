package com.boxvent.boxventwebsite.business.impl;

import com.boxvent.boxventwebsite.business.GetFighterUseCase;
import com.boxvent.boxventwebsite.business.GetUserUseCase;
import com.boxvent.boxventwebsite.business.exception.InvalidFighterException;
import com.boxvent.boxventwebsite.business.exception.InvalidUserException;
import com.boxvent.boxventwebsite.domain.Fighter;
import com.boxvent.boxventwebsite.domain.User;
import com.boxvent.boxventwebsite.presistence.FighterRepository;
import com.boxvent.boxventwebsite.presistence.Impl.entity.FighterEntity;
import com.boxvent.boxventwebsite.presistence.Impl.entity.UserEntity;
import com.boxvent.boxventwebsite.presistence.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@AllArgsConstructor
public class GetUserUseCaseImpl implements GetUserUseCase {
    private UserRepository userRepository;
    private UserConverter userConverter;

    @Override
    @Transactional
    public User getUser(long id) {
        UserEntity userEntity = userRepository.findById(id)
                .orElseThrow(InvalidUserException::new);
        return userConverter.convert(userEntity);
    }
}

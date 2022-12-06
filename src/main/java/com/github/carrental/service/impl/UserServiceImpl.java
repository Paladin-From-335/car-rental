package com.github.carrental.service.impl;

import com.github.carrental.model.dto.UserDto;
import com.github.carrental.model.entity.User;
import com.github.carrental.repo.UserRepo;
import com.github.carrental.service.UserService;
import com.github.carrental.util.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final Mapper mapper;
    private final UserRepo userRepo;

    @Override
    public UserDto saveUser(UserDto dto) {
        User user = mapper.dtoToEntity(dto);
        return mapper.entityToDto(userRepo.save(user));
    }
}

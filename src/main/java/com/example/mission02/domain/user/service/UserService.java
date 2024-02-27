package com.example.mission02.domain.user.service;

import com.example.mission02.domain.user.dto.UserRequestDto.CreateUserRequestDto;
import com.example.mission02.domain.user.dto.UserResponseDto.CreateUserResponseDto;
import com.example.mission02.domain.user.entity.User;
import com.example.mission02.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    public CreateUserResponseDto CreateUser(CreateUserRequestDto RequestDto){
        User user = userRepository.save(RequestDto.toEntity());
        return new CreateUserResponseDto(user);
    }
}

package com.example.mission02.domain.user.service;

import com.example.mission02.domain.user.dto.UserRequestDto.CreateUserRequestDto;
import com.example.mission02.domain.user.dto.UserResponseDto.CreateUserResponseDto;
import com.example.mission02.domain.user.entity.User;
import com.example.mission02.domain.user.repository.UserRepository;
import com.example.mission02.global.handler.exception.CustomApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    public CreateUserResponseDto CreateUser(CreateUserRequestDto requestDto){
        if(userRepository.existsByPhone(requestDto.getPhone()) || userRepository.existsByIdentification(requestDto.getIdentification())){
            throw new CustomApiException("이미 등록된 회원입니다.");
        }
        User user = userRepository.save(requestDto.toEntity());
        return new CreateUserResponseDto(user);
    }
}

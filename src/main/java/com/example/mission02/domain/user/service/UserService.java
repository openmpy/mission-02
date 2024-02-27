package com.example.mission02.domain.user.service;

import com.example.mission02.domain.user.dto.UserRequestDto.CreateUserRequestDto;
import com.example.mission02.domain.user.dto.UserResponseDto.CreateUserResponseDto;
import com.example.mission02.domain.user.entity.User;
import com.example.mission02.domain.user.repository.UserRepository;
import com.example.mission02.global.handler.exception.CustomApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.example.mission02.global.handler.exception.ErrorCode.ALREADY_REGISTERED_IDENTIFICATION;
import static com.example.mission02.global.handler.exception.ErrorCode.ALREADY_REGISTERED_PHONE;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public CreateUserResponseDto createUser(CreateUserRequestDto requestDto) {
        if (userRepository.existsByPhone(requestDto.getPhone())) {
            throw new CustomApiException(ALREADY_REGISTERED_PHONE.getMessage());
        }
        if (userRepository.existsByIdentification(requestDto.getIdentification())) {
            throw new CustomApiException(ALREADY_REGISTERED_IDENTIFICATION.getMessage());
        }

        User user = userRepository.save(requestDto.toEntity());
        return new CreateUserResponseDto(user);
    }
}

package com.example.mission02.domain.user.controller;

import com.example.mission02.domain.user.dto.UserRequestDto.CreateUserRequestDto;
import com.example.mission02.domain.user.dto.UserResponseDto.CreateUserResponseDto;
import com.example.mission02.domain.user.service.UserService;
import com.example.mission02.global.dto.ResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/users")
@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody @Valid CreateUserRequestDto requestDto) {
        CreateUserResponseDto responseDto = userService.CreateUser(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                new ResponseDto<>(true, "회원 등록", responseDto)
        );
    }
}

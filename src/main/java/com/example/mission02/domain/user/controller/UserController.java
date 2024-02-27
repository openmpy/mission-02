package com.example.mission02.domain.user.controller;

import com.example.mission02.domain.user.dto.UserRequestDto.CreateUserRequestDto;
import com.example.mission02.domain.user.dto.UserResponseDto.CreateUserResponseDto;
import com.example.mission02.domain.user.service.UserService;
import com.example.mission02.global.dto.ResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "User API", description = "도서관 회원과 관련된 API 정보를 담고 있습니다.")
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
@RestController
public class UserController {
    private final UserService userService;

    @Operation(summary = "도서관 회원 등록 기능", description = "도서관 회원을 등록할 수 있는 API")
    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody @Valid CreateUserRequestDto requestDto, BindingResult bindingResult) {
        CreateUserResponseDto responseDto = userService.createUser(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                new ResponseDto<>(true, "회원 등록", responseDto)
        );
    }
}

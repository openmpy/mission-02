package com.example.mission02.domain.user.service;

import com.example.mission02.domain.book.dto.BookResponseDto.GetBookResponseDto;
import com.example.mission02.domain.book.entity.Book;
import com.example.mission02.domain.user.dto.UserRequestDto.CreateUserRequestDto;
import com.example.mission02.domain.user.dto.UserResponseDto.CreateUserResponseDto;
import com.example.mission02.domain.user.entity.GenderType;
import com.example.mission02.domain.user.entity.User;
import com.example.mission02.domain.user.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.LongStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Test
    @DisplayName("성공 - 회원을 등록한다.")
    void createUser() throws Exception {
        CreateUserRequestDto requestDto = CreateUserRequestDto.builder()
                .name("name")
                .gender(GenderType.FEMALE)
                .phone("01000000000")
                .identification("123")
                .address("")
                .build();

        User user = User.builder()
                .id(1L)
                .name("name")
                .gender(GenderType.FEMALE)
                .phone("01000000000")
                .identification("123")
                .address("")
                .build();

        when(userRepository.save(any(User.class))).thenReturn(user);
        CreateUserResponseDto responseDto = userService.CreateUser(requestDto);

        assertEquals(user.getAddress(), responseDto.getAddress());
        assertEquals(user.getIdentification(),responseDto.getIdentification());

    }

}
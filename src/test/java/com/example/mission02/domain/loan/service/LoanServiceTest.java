package com.example.mission02.domain.loan.service;

import com.example.mission02.domain.book.entity.Book;
import com.example.mission02.domain.book.repository.BookRepository;
import com.example.mission02.domain.loan.dto.LoanRequestDto.CreateLoanRequestDto;
import com.example.mission02.domain.loan.dto.LoanResponseDto.CreateLoanResponseDto;
import com.example.mission02.domain.loan.repository.LoanRepository;
import com.example.mission02.domain.user.entity.User;
import com.example.mission02.domain.user.repository.UserRepository;
import com.example.mission02.global.handler.exception.CustomApiException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LoanServiceTest {

    @InjectMocks
    private LoanService loanService;

    @Mock
    private LoanRepository loanRepository;

    @Mock
    private BookRepository bookRepository;

    @Mock
    private UserRepository userRepository;

    @Test
    @DisplayName("성공 - 도서 대출을 성공한다.")
    void create_01() throws Exception {
        // given
        Book book = Book.builder()
                .id(1L)
                .build();

        User user = User.builder()
                .id(1L)
                .build();

        CreateLoanRequestDto requestDto = new CreateLoanRequestDto(book.getId(), user.getId());

        // stub
        when(bookRepository.findById(anyLong())).thenReturn(Optional.of(book));
        when(userRepository.existsById(anyLong())).thenReturn(true);
        when(loanRepository.existsByUserIdAndIsReturnedFalse(anyLong())).thenReturn(false);
        when(loanRepository.save(any())).thenReturn(requestDto.toEntity());

        // when
        CreateLoanResponseDto responseDto = loanService.create(requestDto);

        // then
        Assertions.assertEquals(1L, responseDto.getBookId());
        Assertions.assertEquals(1L, responseDto.getUserId());
        Assertions.assertTrue(book.isLoaned());
    }

    @Test
    @DisplayName("실패 - 찾을 수 없는 도서 번호로 대출에 실패한다.")
    void create_02() throws Exception {
        // given
        User user = User.builder()
                .id(1L)
                .build();

        CreateLoanRequestDto requestDto = new CreateLoanRequestDto(1L, user.getId());

        // when & then
        CustomApiException exception = Assertions.assertThrows(CustomApiException.class, () ->
                loanService.create(requestDto)
        );
        System.out.println("exception = " + exception);
    }

    @Test
    @DisplayName("실패 - 이미 대출 상태의 도서로 대출에 실패한다.")
    void create_03() throws Exception {
        // given
        Book book = Book.builder()
                .id(1L)
                .isLoaned(true)
                .build();

        User user = User.builder()
                .id(1L)
                .build();

        CreateLoanRequestDto requestDto = new CreateLoanRequestDto(book.getId(), user.getId());

        // stub
        when(bookRepository.findById(anyLong())).thenReturn(Optional.of(book));

        // when & then
        CustomApiException exception = Assertions.assertThrows(CustomApiException.class, () ->
                loanService.create(requestDto)
        );
        System.out.println("exception = " + exception);
    }

    @Test
    @DisplayName("실패 - 찾을 수 없는 회원 번호로 대출에 실패한다.")
    void create_04() throws Exception {
        // given
        Book book = Book.builder()
                .id(1L)
                .build();

        CreateLoanRequestDto requestDto = new CreateLoanRequestDto(book.getId(), 1L);

        // stub
        when(bookRepository.findById(anyLong())).thenReturn(Optional.of(book));

        // when & then
        CustomApiException exception = Assertions.assertThrows(CustomApiException.class, () ->
                loanService.create(requestDto)
        );
        System.out.println("exception = " + exception);
    }

    @Test
    @DisplayName("실패 - 반납하지 않은 도서가 존재해서 대출에 실패한다.")
    void create_05() throws Exception {
        // given
        Book book = Book.builder()
                .id(1L)
                .build();

        User user = User.builder()
                .id(1L)
                .build();

        CreateLoanRequestDto requestDto = new CreateLoanRequestDto(book.getId(), user.getId());

        // stub
        when(bookRepository.findById(anyLong())).thenReturn(Optional.of(book));
        when(userRepository.existsById(anyLong())).thenReturn(true);
        when(loanRepository.existsByUserIdAndIsReturnedFalse(anyLong())).thenReturn(true);

        // when & then
        CustomApiException exception = Assertions.assertThrows(CustomApiException.class, () ->
                loanService.create(requestDto)
        );
        System.out.println("exception = " + exception);
    }
}
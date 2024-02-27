package com.example.mission02.domain.loan.service;

import com.example.mission02.domain.book.entity.Book;
import com.example.mission02.domain.book.repository.BookRepository;
import com.example.mission02.domain.loan.dto.LoanRequestDto.CreateLoanRequestDto;
import com.example.mission02.domain.loan.dto.LoanRequestDto.ReturnedLoanRequestDto;
import com.example.mission02.domain.loan.dto.LoanResponseDto.CreateLoanResponseDto;
import com.example.mission02.domain.loan.dto.LoanResponseDto.GetLoanResponseDto;
import com.example.mission02.domain.loan.dto.LoanResponseDto.ReturnedLoanResponseDto;
import com.example.mission02.domain.loan.entity.Loan;
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

import java.time.LocalDateTime;
import java.util.List;
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
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));
        when(loanRepository.existsByUserAndIsReturnedFalse(any())).thenReturn(false);
        when(loanRepository.save(any())).thenReturn(requestDto.toEntity(book, user));

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
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));
        when(loanRepository.existsByUserAndIsReturnedFalse(any())).thenReturn(true);

        // when & then
        CustomApiException exception = Assertions.assertThrows(CustomApiException.class, () ->
                loanService.create(requestDto)
        );
        System.out.println("exception = " + exception);
    }

    @Test
    @DisplayName("성공 - 선택한 도서 반납에 성공한다.")
    void returned_01() throws Exception {
        // given
        Book book = Book.builder()
                .id(1L)
                .isLoaned(true)
                .build();

        User user = User.builder()
                .id(1L)
                .build();

        Loan loan = Loan.builder()
                .id(1L)
                .book(book)
                .user(user)
                .isReturned(false)
                .loanedAt(LocalDateTime.now())
                .returnedAt(null)
                .build();

        ReturnedLoanRequestDto requestDto = new ReturnedLoanRequestDto(book.getId(), user.getId());

        // stub
        when(bookRepository.findById(anyLong())).thenReturn(Optional.of(book));
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));
        when(loanRepository.findByBookAndUserAndIsReturnedFalse(any(), any())).thenReturn(Optional.of(loan));

        // when
        ReturnedLoanResponseDto responseDto = loanService.returned(requestDto);

        // then
        Assertions.assertTrue(responseDto.isReturned());
        Assertions.assertNotNull(responseDto.getReturnedAt());
        Assertions.assertFalse(book.isLoaned());
    }

    @Test
    @DisplayName("실패 - 찾을 수 없는 도서 번호로 도서 반납에 실패한다.")
    void returned_02() throws Exception {
        // given
        User user = User.builder()
                .id(1L)
                .build();

        ReturnedLoanRequestDto requestDto = new ReturnedLoanRequestDto(1L, user.getId());

        // when & then
        CustomApiException exception = Assertions.assertThrows(CustomApiException.class, () ->
                loanService.returned(requestDto)
        );
        System.out.println("exception = " + exception);
    }

    @Test
    @DisplayName("실패 - 찾을 수 없는 회원 번호로 도서 반납에 실패한다.")
    void returned_03() throws Exception {
        // given
        Book book = Book.builder()
                .id(1L)
                .build();

        ReturnedLoanRequestDto requestDto = new ReturnedLoanRequestDto(book.getId(), 1L);

        // stub
        when(bookRepository.findById(anyLong())).thenReturn(Optional.of(book));

        // when & then
        CustomApiException exception = Assertions.assertThrows(CustomApiException.class, () ->
                loanService.returned(requestDto)
        );
        System.out.println("exception = " + exception);
    }

    @Test
    @DisplayName("실패 - 찾을 수 없는 대출 정보로 도서 반납에 실패한다.")
    void returned_04() throws Exception {
        // given
        Book book = Book.builder()
                .id(1L)
                .build();

        User user = User.builder()
                .id(1L)
                .build();

        ReturnedLoanRequestDto requestDto = new ReturnedLoanRequestDto(book.getId(), user.getId());

        // stub
        when(bookRepository.findById(anyLong())).thenReturn(Optional.of(book));
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));
        when(loanRepository.findByBookAndUserAndIsReturnedFalse(any(), any())).thenReturn(Optional.empty());

        // when & then
        CustomApiException exception = Assertions.assertThrows(CustomApiException.class, () ->
                loanService.returned(requestDto)
        );
        System.out.println("exception = " + exception);
    }

    @Test
    @DisplayName("성공 - 회원 번호로 도서 대출 내역 목록을 조회한다.")
    void getListForUser_01() throws Exception {
        // given
        Book book = Book.builder()
                .id(1L)
                .title("어린왕자")
                .build();

        User user = User.builder()
                .id(1L)
                .name("손흥민")
                .build();

        Loan loan1 = Loan.builder()
                .id(1L)
                .book(book)
                .user(user)
                .isReturned(true)
                .build();

        Loan loan2 = Loan.builder()
                .id(2L)
                .book(book)
                .user(user)
                .isReturned(false)
                .build();

        List<Loan> loanList = List.of(loan1, loan2);

        // stub
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));
        when(loanRepository.findByUserOrderByLoanedAt(any())).thenReturn(loanList);

        // when
        List<GetLoanResponseDto> responseDtoList = loanService.getListForUser(1L);

        // then
        Assertions.assertEquals(2, responseDtoList.size());
        Assertions.assertTrue(responseDtoList.get(0).isReturned());
        Assertions.assertFalse(responseDtoList.get(1).isReturned());
    }

    @Test
    @DisplayName("실패 - 찾을 수 없는 회원 번호로 도서 대출 내역 목록 조회에 실패한다.")
    void getListForUser_02() throws Exception {
        // when & then
        CustomApiException exception = Assertions.assertThrows(CustomApiException.class, () ->
                loanService.getListForUser(1L)
        );
        System.out.println("exception = " + exception);
    }
}
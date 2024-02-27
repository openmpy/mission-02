package com.example.mission02.domain.loan.dto;

import com.example.mission02.domain.book.entity.Book;
import com.example.mission02.domain.loan.entity.Loan;
import com.example.mission02.domain.user.entity.User;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

public class LoanRequestDto {

    @AllArgsConstructor
    @Getter
    public static class CreateLoanRequestDto {

        @NotNull(message = "도서 번호를 입력해주세요.")
        private Long bookId;

        @NotNull(message = "회원 번호를 입력해주세요.")
        private Long userId;

        public Loan toEntity(Book book, User user) {
            return Loan.builder()
                    .book(book)
                    .user(user)
                    .loanedAt(LocalDateTime.now())
                    .build();
        }
    }

    @AllArgsConstructor
    @Getter
    public static class ReturnedLoanRequestDto {

        @NotNull(message = "도서 번호를 입력해주세요.")
        private Long bookId;

        @NotNull(message = "회원 번호를 입력해주세요.")
        private Long userId;
    }
}

package com.example.mission02.domain.loan.dto;

import com.example.mission02.domain.loan.entity.Loan;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

import java.time.LocalDateTime;

public class LoanResponseDto {

    @Getter
    public static class CreateLoanResponseDto {

        private final Long id;
        private final Long bookId;
        private final Long userId;
        private final boolean isReturned;

        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
        private final LocalDateTime loanedAt;

        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
        private final LocalDateTime returnedAt;

        public CreateLoanResponseDto(Loan loan) {
            this.id = loan.getId();
            this.bookId = loan.getBook().getId();
            this.userId = loan.getUser().getId();
            this.isReturned = loan.isReturned();
            this.loanedAt = loan.getLoanedAt();
            this.returnedAt = loan.getReturnedAt();
        }
    }

    @Getter
    public static class ReturnedLoanResponseDto {

        private final Long id;
        private final Long bookId;
        private final Long userId;
        private final boolean isReturned;

        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
        private final LocalDateTime loanedAt;

        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
        private final LocalDateTime returnedAt;

        public ReturnedLoanResponseDto(Loan loan) {
            this.id = loan.getId();
            this.bookId = loan.getBook().getId();
            this.userId = loan.getUser().getId();
            this.isReturned = loan.isReturned();
            this.loanedAt = loan.getLoanedAt();
            this.returnedAt = loan.getReturnedAt();
        }
    }

    @Getter
    public static class GetLoanResponseDto {

        private final Long id;
        private final String title;
        private final String author;
        private final String name;
        private final String phone;
        private final boolean isReturned;

        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
        private final LocalDateTime loanedAt;

        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
        private final LocalDateTime returnedAt;

        public GetLoanResponseDto(Loan loan) {
            this.id = loan.getId();
            this.title = loan.getBook().getTitle();
            this.author = loan.getBook().getAuthor();
            this.name = loan.getUser().getName();
            this.phone = loan.getUser().getPhone();
            this.isReturned = loan.isReturned();
            this.loanedAt = loan.getLoanedAt();
            this.returnedAt = loan.getReturnedAt();
        }
    }
}

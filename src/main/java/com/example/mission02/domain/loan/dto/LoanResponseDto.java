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
            this.bookId = loan.getBookId();
            this.userId = loan.getUserId();
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
            this.bookId = loan.getBookId();
            this.userId = loan.getUserId();
            this.isReturned = loan.isReturned();
            this.loanedAt = loan.getLoanedAt();
            this.returnedAt = loan.getReturnedAt();
        }
    }
}

package com.example.mission02.domain.loan.dto;

import com.example.mission02.domain.loan.entity.Loan;
import lombok.Getter;

import java.time.LocalDateTime;

public class LoanResponseDto {

    @Getter
    public static class CreateLoanResponseDto {

        private final Long id;
        private final Long bookId;
        private final Long userId;
        private final boolean isReturned;
        private final LocalDateTime loanedAt;
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
}

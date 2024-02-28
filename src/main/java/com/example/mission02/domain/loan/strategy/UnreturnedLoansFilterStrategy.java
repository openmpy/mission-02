package com.example.mission02.domain.loan.strategy;

import com.example.mission02.domain.loan.entity.Loan;

public class UnreturnedLoansFilterStrategy implements LoanFilterStrategy {

    @Override
    public boolean filterLoan(Loan loan, boolean isAll) {
        return !loan.isReturned();
    }
}

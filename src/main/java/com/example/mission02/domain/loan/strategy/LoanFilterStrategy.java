package com.example.mission02.domain.loan.strategy;

import com.example.mission02.domain.loan.entity.Loan;

public interface LoanFilterStrategy {

    boolean filterLoan(Loan loan, boolean isAll);
}

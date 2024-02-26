package com.example.mission02.domain.loan.repository;

import com.example.mission02.domain.loan.entity.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LoanRepository extends JpaRepository<Loan, Long> {

    boolean existsByUserIdAndIsReturnedFalse(Long userId);

    Optional<Loan> findByBookIdAndUserIdAndIsReturnedFalse(Long bookId, Long userId);
}

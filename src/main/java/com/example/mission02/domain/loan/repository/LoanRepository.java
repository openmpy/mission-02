package com.example.mission02.domain.loan.repository;

import com.example.mission02.domain.book.entity.Book;
import com.example.mission02.domain.loan.entity.Loan;
import com.example.mission02.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LoanRepository extends JpaRepository<Loan, Long> {

    boolean existsByUserAndIsReturnedFalse(User user);

    Optional<Loan> findByBookAndUserAndIsReturnedFalse(Book book, User user);

    List<Loan> findByUserOrderByLoanedAt(User user);
}

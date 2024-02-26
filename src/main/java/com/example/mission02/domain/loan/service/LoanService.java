package com.example.mission02.domain.loan.service;

import com.example.mission02.domain.book.entity.Book;
import com.example.mission02.domain.book.repository.BookRepository;
import com.example.mission02.domain.loan.dto.LoanRequestDto.CreateLoanRequestDto;
import com.example.mission02.domain.loan.dto.LoanRequestDto.ReturnedLoanRequestDto;
import com.example.mission02.domain.loan.dto.LoanResponseDto.CreateLoanResponseDto;
import com.example.mission02.domain.loan.dto.LoanResponseDto.ReturnedLoanResponseDto;
import com.example.mission02.domain.loan.entity.Loan;
import com.example.mission02.domain.loan.repository.LoanRepository;
import com.example.mission02.domain.user.repository.UserRepository;
import com.example.mission02.global.handler.exception.CustomApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class LoanService {

    private final LoanRepository loanRepository;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    @Transactional
    public CreateLoanResponseDto create(CreateLoanRequestDto requestDto) {
        // 도서 예외 처리
        Book book = bookRepository.findById(requestDto.getBookId()).orElseThrow(() ->
                new CustomApiException("찾을 수 없는 도서 번호입니다.")
        );
        if (book.isLoaned()) {
            throw new CustomApiException("이미 대출 상태의 도서입니다.");
        }

        // 회원 예외 처리
        if (!userRepository.existsById(requestDto.getUserId())) {
            throw new CustomApiException("찾을 수 없는 회원 번호입니다.");
        }

        // 대출 예외 처리
        if (loanRepository.existsByUserIdAndIsReturnedFalse(requestDto.getUserId())) {
            throw new CustomApiException("반납하지 않은 도서가 존재합니다.");
        }

        Loan loan = loanRepository.save(requestDto.toEntity());
        book.updateLoaned(true);
        return new CreateLoanResponseDto(loan);
    }

    @Transactional
    public ReturnedLoanResponseDto returned(ReturnedLoanRequestDto requestDto) {
        // 도서 예외 처리
        Book book = bookRepository.findById(requestDto.getBookId()).orElseThrow(() ->
                new CustomApiException("찾을 수 없는 도서 번호입니다.")
        );

        // 회원 예외 처리
        if (!userRepository.existsById(requestDto.getUserId())) {
            throw new CustomApiException("찾을 수 없는 회원 번호입니다.");
        }

        // 대출 예외 처리
        Loan loan = loanRepository.findByBookIdAndUserIdAndIsReturnedFalse(requestDto.getBookId(), requestDto.getUserId())
                .orElseThrow(() -> new CustomApiException("찾을 수 없는 대출 정보입니다."));

        loan.returned(LocalDateTime.now());
        book.updateLoaned(false);
        return new ReturnedLoanResponseDto(loan);
    }
}

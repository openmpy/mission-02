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
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class LoanService {

    private static final int PENALTY_DAYS = 7;
    private static final int NO_LOAN_DAYS = 14;

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
        User user = userRepository.findById(requestDto.getUserId()).orElseThrow(() ->
                new CustomApiException("찾을 수 없는 회원 번호입니다.")
        );

        // 패널티 체크
        checkPenalty(user);

        // 대출 예외 처리
        if (loanRepository.existsByUserAndIsReturnedFalse(user)) {
            throw new CustomApiException("반납하지 않은 도서가 존재합니다.");
        }

        Loan loan = loanRepository.save(requestDto.toEntity(book, user));
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
        User user = userRepository.findById(requestDto.getUserId()).orElseThrow(() ->
                new CustomApiException("찾을 수 없는 회원 번호입니다.")
        );

        // 대출 예외 처리
        Loan loan = loanRepository.findByBookAndUserAndIsReturnedFalse(book, user)
                .orElseThrow(() -> new CustomApiException("찾을 수 없는 대출 정보입니다."));

        // 패널티 기능
        setPenalty(user, loan);

        loan.returned(LocalDateTime.now());
        book.updateLoaned(false);
        return new ReturnedLoanResponseDto(loan);
    }

    @Transactional(readOnly = true)
    public List<GetLoanResponseDto> getListForUser(Long userId, boolean isAll) {
        User user = userRepository.findById(userId).orElseThrow(() ->
                new CustomApiException("찾을 수 없는 회원 번호입니다.")
        );

        return loanRepository.findByUserOrderByLoanedAt(user)
                .stream()
                .filter(loan -> isAll || !loan.isReturned())
                .map(GetLoanResponseDto::new)
                .toList();
    }

    // 패널티 검사
    private static void checkPenalty(User user) {
        LocalDateTime now = LocalDateTime.now();
        if (user.getPenalizedAt() != null && now.isBefore(user.getPenalizedAt())) {
            throw new CustomApiException("패널티로 인해 도서를 대출 받을 수 없습니다.");
        }
    }

    // 패널티 주기
    private static void setPenalty(User user, Loan loan) {
        LocalDateTime now = LocalDateTime.now();
        if (now.isAfter(loan.getLoanedAt().plusDays(PENALTY_DAYS))) {
            user.updatePenalizedAt(LocalDateTime.now().plusDays(NO_LOAN_DAYS));
        }
    }
}

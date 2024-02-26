package com.example.mission02.domain.loan.controller;

import com.example.mission02.domain.loan.dto.LoanRequestDto.CreateLoanRequestDto;
import com.example.mission02.domain.loan.dto.LoanResponseDto.CreateLoanResponseDto;
import com.example.mission02.domain.loan.service.LoanService;
import com.example.mission02.global.dto.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/v1/loans")
@RestController
public class LoanController {

    private final LoanService loanService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody CreateLoanRequestDto requestDto) {
        CreateLoanResponseDto responseDto = loanService.create(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                new ResponseDto<>(true, "선택한 도서 대출 기능", responseDto)
        );
    }
}

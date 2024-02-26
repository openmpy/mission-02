package com.example.mission02.domain.loan.controller;

import com.example.mission02.domain.loan.dto.LoanRequestDto.CreateLoanRequestDto;
import com.example.mission02.domain.loan.dto.LoanRequestDto.ReturnedLoanRequestDto;
import com.example.mission02.domain.loan.dto.LoanResponseDto.CreateLoanResponseDto;
import com.example.mission02.domain.loan.dto.LoanResponseDto.ReturnedLoanResponseDto;
import com.example.mission02.domain.loan.service.LoanService;
import com.example.mission02.global.dto.ResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/v1/loans")
@RestController
public class LoanController {

    private final LoanService loanService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid CreateLoanRequestDto requestDto, BindingResult bindingResult) {
        CreateLoanResponseDto responseDto = loanService.create(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                new ResponseDto<>(true, "선택한 도서 대출 기능", responseDto)
        );
    }

    @PutMapping("/return")
    public ResponseEntity<?> returned(@RequestBody @Valid ReturnedLoanRequestDto requestDto, BindingResult bindingResult) {
        ReturnedLoanResponseDto responseDto = loanService.returned(requestDto);
        return ResponseEntity.ok().body(
                new ResponseDto<>(true, "선택한 도서 반납 기능", responseDto)
        );
    }
}

package com.example.mission02.domain.loan.controller;

import com.example.mission02.domain.loan.dto.LoanRequestDto.CreateLoanRequestDto;
import com.example.mission02.domain.loan.dto.LoanRequestDto.ReturnedLoanRequestDto;
import com.example.mission02.domain.loan.dto.LoanResponseDto.CreateLoanResponseDto;
import com.example.mission02.domain.loan.dto.LoanResponseDto.GetLoanResponseDto;
import com.example.mission02.domain.loan.dto.LoanResponseDto.ReturnedLoanResponseDto;
import com.example.mission02.domain.loan.service.LoanService;
import com.example.mission02.global.dto.ResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Loan API", description = "도서 대출과 관련된 API 정보를 담고 있습니다.")
@RequiredArgsConstructor
@RequestMapping("/api/v1/loans")
@RestController
public class LoanController {

    private final LoanService loanService;

    @Operation(summary = "선택한 도서 대출 기능", description = "선택한 도서를 대출할 수 있는 API")
    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid CreateLoanRequestDto requestDto, BindingResult bindingResult) {
        CreateLoanResponseDto responseDto = loanService.create(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                new ResponseDto<>(true, "선택한 도서 대출 기능", responseDto)
        );
    }

    @Operation(summary = "선택한 도서 반납 기능", description = "선택한 도서를 반납할 수 있는 API")
    @PutMapping("/return")
    public ResponseEntity<?> returned(@RequestBody @Valid ReturnedLoanRequestDto requestDto, BindingResult bindingResult) {
        ReturnedLoanResponseDto responseDto = loanService.returned(requestDto);
        return ResponseEntity.ok().body(
                new ResponseDto<>(true, "선택한 도서 반납 기능", responseDto)
        );
    }

    @Operation(summary = "대출 내역 조회 기능", description = "대출한 도서 내역을 조회할 수 있는 API")
    @GetMapping("/users/{userId}")
    public ResponseEntity<?> getListForUser(@PathVariable Long userId, @RequestParam(defaultValue = "true") boolean isAll) {
        List<GetLoanResponseDto> responseDtoList = loanService.getListForUser(userId, isAll);
        return ResponseEntity.ok().body(
                new ResponseDto<>(true, "대출 내역 조회 기능", responseDtoList)
        );
    }
}

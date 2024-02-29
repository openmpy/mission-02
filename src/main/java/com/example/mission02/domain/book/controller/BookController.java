package com.example.mission02.domain.book.controller;

import com.example.mission02.domain.book.dto.BookRequestDto.CreateBookRequestDto;
import com.example.mission02.domain.book.dto.BookResponseDto.CreateBookResponseDto;
import com.example.mission02.domain.book.dto.BookResponseDto.GetBookResponseDto;
import com.example.mission02.domain.book.service.BookService;
import com.example.mission02.global.dto.ResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Book API", description = "도서와 관련된 API 정보를 담고 있습니다.")
@RequiredArgsConstructor
@RequestMapping("/api/v1/books")
@RestController
public class BookController {

    private final BookService bookService;

    @Operation(summary = "도서 등록 기능", description = "도서를 등록할 수 있는 API")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ResponseDto<CreateBookResponseDto> createBook(@RequestBody @Valid CreateBookRequestDto requestDto, BindingResult bindingResult) {
        CreateBookResponseDto responseDto = bookService.createBook(requestDto);
        return ResponseDto.success("게시글 작성", responseDto);
    }

    @Operation(summary = "도서 목록 조회 기능", description = "등록된 모든 도서를 조회할 수 있는 API")
    @GetMapping
    public ResponseDto<List<GetBookResponseDto>> getBookList() {
        List<GetBookResponseDto> responseDtoList = bookService.getBookList();
        return ResponseDto.success("도서 목록 조회", responseDtoList);
    }

    @Operation(summary = "선택한 도서 정보 조회 기능", description = "선택한 도서 정보를 조회할 수 있는 API")
    @GetMapping("/{id}")
    public ResponseDto<GetBookResponseDto> getList(@PathVariable Long id) {
        GetBookResponseDto responseDto = bookService.getBook(id);
        return ResponseDto.success("특정 도서 조회", responseDto);
    }
}

package com.example.mission02.domain.book.controller;

import com.example.mission02.domain.book.dto.BookRequestDto.CreateBookRequestDto;
import com.example.mission02.domain.book.dto.BookResponseDto;
import com.example.mission02.domain.book.dto.BookResponseDto.CreateBookResponseDto;
import com.example.mission02.domain.book.service.BookService;
import com.example.mission02.global.dto.ResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/v1/books")
@RestController
public class BookController {
    private final BookService bookService;

    @PostMapping
    public ResponseEntity<?> createBook(@RequestBody @Valid CreateBookRequestDto requestDto, BindingResult bindingResult) {
        CreateBookResponseDto responseDto = bookService.createBook(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                new ResponseDto<>(true, "게시글 작성", responseDto)
        );
    }
  
    @GetMapping
    public ResponseEntity<?> getBookList() {
        List<BookResponseDto.GetBookResponseDto> bookList = bookService.getBookList();
        return ResponseEntity.status(HttpStatus.CREATED).body(
                new ResponseDto<>(true, "게시글 작성", bookList)
        );
    }
}

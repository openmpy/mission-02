package com.example.mission02.domain.book.service;

import com.example.mission02.domain.book.dto.BookRequestDto.CreateBookRequestDto;
import com.example.mission02.domain.book.dto.BookResponseDto.CreateBookResponseDto;
import com.example.mission02.domain.book.entity.Book;
import com.example.mission02.domain.book.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
@RequiredArgsConstructor
@Service
public class BookService {
    private final BookRepository bookRepository;

    public CreateBookResponseDto createBook(CreateBookRequestDto requestDto) {
        Book book = bookRepository.save(requestDto.toEntity());
        return new CreateBookResponseDto(book);
    }
}

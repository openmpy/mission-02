package com.example.mission02.domain.book.service.service;

import com.example.mission02.domain.book.service.dto.BookRequestDto.CreateBookRequestDto;
import com.example.mission02.domain.book.service.dto.BookResponseDto.CreateBookResponseDto;
import com.example.mission02.domain.book.service.entity.Book;
import com.example.mission02.domain.book.service.repository.BookRepository;
import org.springframework.stereotype.Service;

@Service
public class BookService {
    private final BookRepository bookRepository;
    public BookService(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    public CreateBookResponseDto createBook(CreateBookRequestDto requestDto){
        Book book = new Book(requestDto);
        bookRepository.save(book);
        return new CreateBookResponseDto(book);
    }
}

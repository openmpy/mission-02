package com.example.mission02.domain.book.service;

import com.example.mission02.domain.book.dto.BookRequestDto.CreateBookRequestDto;
import com.example.mission02.domain.book.dto.BookResponseDto.CreateBookResponseDto;
import com.example.mission02.domain.book.entity.Book;
import com.example.mission02.domain.book.repository.BookRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class BookServiceTest {
    @InjectMocks
    private BookService bookService;

    @Mock
    private BookRepository bookRepository;

    @Test
    @DisplayName("성공 - 책을 등록한다")
    void createBook() throws Exception {
        CreateBookRequestDto requestDto = CreateBookRequestDto.builder()
                .title("title")
                .author("author")
                .language("English")
                .publisher("pub")
                .build();

        Book book = Book.builder()
                .id(1L)
                .title(requestDto.getTitle())
                .author(requestDto.getAuthor())
                .language(requestDto.getLanguage())
                .publisher(requestDto.getPublisher())
                .build();

        when(bookRepository.save(any(Book.class))).thenReturn(book);

        CreateBookResponseDto responseDto = bookService.createBook(requestDto);

        assertEquals(book.getId(), responseDto.getId());
        assertEquals(book.getTitle(), responseDto.getTitle());
        assertEquals(book.getAuthor(), responseDto.getAuthor());
        assertEquals(book.getLanguage(), responseDto.getLanguage());
        assertEquals(book.getPublisher(), responseDto.getPublisher());
    }

}
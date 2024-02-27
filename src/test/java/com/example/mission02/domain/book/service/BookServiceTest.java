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
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class BookServiceTest {
    @InjectMocks
    private BookService bookService;

    @Mock
    private BookRepository bookRepository;

    @Test
    @DisplayName("성공 - 책을 등록한다")
    void createBook() throws Exception {
        CreateBookRequestDto requestDto = new CreateBookRequestDto("Book Title", "Author Name", "English", "Publisher Name");
        Book book = new Book(requestDto);

        when(bookRepository.save(any(Book.class))).thenReturn(book);

        CreateBookResponseDto responseDto = bookService.createBook(requestDto);

        assertEquals(requestDto.getTitle(), responseDto.getTitle());
        assertEquals(requestDto.getAuthor(), responseDto.getAuthor());
        assertEquals(requestDto.getLanguage(), responseDto.getLanguage());
        assertEquals(requestDto.getPublisher(), responseDto.getPublisher());
        assertFalse(responseDto.isLoaned());
    }

}
package com.example.mission02.domain.book.service;

import com.example.mission02.domain.book.dto.BookRequestDto.CreateBookRequestDto;
import com.example.mission02.domain.book.dto.BookResponseDto;
import com.example.mission02.domain.book.dto.BookResponseDto.GetBookResponseDto;
import com.example.mission02.domain.book.dto.BookResponseDto.CreateBookResponseDto;
import com.example.mission02.domain.book.entity.Book;
import com.example.mission02.domain.book.repository.BookRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

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
    @DisplayName("성공 - 책을 등록한다.")
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

    @Test
    @DisplayName("성공 - 도서 목록을 조회한다.")
    void getBookLst() throws Exception {
        Book book1 = Book.builder()
                .id(1L)
                .title("Book 1")
                .author("Author 1")
                .language("English")
                .publisher("Publisher 1")
                .build();

        Book book2 = Book.builder()
                .id(2L)
                .title("Book 2")
                .author("Author 2")
                .language("French")
                .publisher("Publisher 2")
                .build();

        Book book3 = Book.builder()
                .id(3L)
                .title("Book 3")
                .author("Author 3")
                .language("Spanish")
                .publisher("Publisher 3")
                .build();

        List<Book> bookList = List.of(book1, book2, book3);
        when(bookRepository.findAll()).thenReturn(bookList);
        List<GetBookResponseDto> serviceBookList = bookService.getBookList();

        assertEquals(bookList.size(), serviceBookList.size());
        for (int i = 0; i < bookList.size(); i++) {
            assertEquals(bookList.get(i).getId(), serviceBookList.get(i).getId());
            assertEquals(bookList.get(i).getTitle(), serviceBookList.get(i).getTitle());
            assertEquals(bookList.get(i).getAuthor(), serviceBookList.get(i).getAuthor());
            assertEquals(bookList.get(i).getLanguage(), serviceBookList.get(i).getLanguage());
            assertEquals(bookList.get(i).getPublisher(), serviceBookList.get(i).getPublisher());
        }
    }
}
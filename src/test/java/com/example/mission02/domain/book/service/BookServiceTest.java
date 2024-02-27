package com.example.mission02.domain.book.service;

import com.example.mission02.domain.book.dto.BookRequestDto.CreateBookRequestDto;
import com.example.mission02.domain.book.dto.BookResponseDto.CreateBookResponseDto;
import com.example.mission02.domain.book.dto.BookResponseDto.GetBookResponseDto;
import com.example.mission02.domain.book.entity.Book;
import com.example.mission02.domain.book.repository.BookRepository;
import com.example.mission02.domain.loan.dto.LoanRequestDto.CreateLoanRequestDto;
import com.example.mission02.domain.loan.dto.LoanResponseDto;
import com.example.mission02.domain.loan.entity.Loan;
import com.example.mission02.domain.loan.repository.LoanRepository;
import com.example.mission02.domain.loan.service.LoanService;
import com.example.mission02.domain.user.entity.GenderType;
import com.example.mission02.domain.user.entity.User;
import com.example.mission02.domain.user.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.LongStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class BookServiceTest {
    @InjectMocks
    private BookService bookService;

    @InjectMocks
    private LoanService loanService;

    @Mock
    private BookRepository bookRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private LoanRepository loanRepository;

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
    void getBookList() throws Exception {
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
        when(bookRepository.findAllByOrderByCreatedAtAsc()).thenReturn(bookList);
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

    @Test
    @DisplayName("성공 - 선택한 도서를 조회한다.")
    void getBook() throws Exception {
        List<Book> bookList = new ArrayList<>();
        LongStream.range(0, 3).forEach(i -> {
            Book book = Book.builder()
                    .id(i)
                    .title("title" + i)
                    .publisher("" + i)
                    .author("" + i)
                    .language("eng")
                    .build();
            bookList.add(book);
        });

        when(bookRepository.findAllByOrderByCreatedAtAsc()).thenReturn(bookList);
        List<GetBookResponseDto> responseDtoList = bookService.getBookList();
        Assertions.assertEquals(3, responseDtoList.size());
        Assertions.assertEquals("2", responseDtoList.get(2).getAuthor());
    }

    @Test
    @DisplayName("성공 - 도서 대출 가능 여부를 확인한다.")
    void checkIsLoaned() throws Exception {
        Book book = Book.builder()
                .id(1L)
                .title("Book 1")
                .author("Author 1")
                .language("English")
                .publisher("Publisher 1")
                .build();

        User user = User.builder()
                .id(1L)
                .name("name")
                .gender(GenderType.FEMALE)
                .phone("01000000000")
                .identification("123")
                .address("")
                .build();

        Loan loan = Loan.builder()
                .id(1L)
                .book(book)
                .user(user)
                .build();

        CreateLoanRequestDto requestDto = new CreateLoanRequestDto(book.getId(), user.getId());
        when(bookRepository.findById(anyLong())).thenReturn(Optional.of(book));
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));
        when(loanRepository.existsByUserAndIsReturnedFalse(any())).thenReturn(false);
        when(loanRepository.save(any())).thenReturn(requestDto.toEntity(book, user));

        LoanResponseDto.CreateLoanResponseDto responseDto = loanService.create(requestDto);
        GetBookResponseDto bookResponseDto = bookService.getBook(book.getId());

        Assertions.assertTrue(bookResponseDto.isLoaned());
    }
}
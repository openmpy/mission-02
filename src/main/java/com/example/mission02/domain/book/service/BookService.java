package com.example.mission02.domain.book.service;

import com.example.mission02.domain.book.dto.BookRequestDto.CreateBookRequestDto;
import com.example.mission02.domain.book.dto.BookResponseDto.CreateBookResponseDto;
import com.example.mission02.domain.book.dto.BookResponseDto.GetBookResponseDto;
import com.example.mission02.domain.book.entity.Book;
import com.example.mission02.domain.book.repository.BookRepository;
import com.example.mission02.global.handler.exception.CustomApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BookService {
    private final BookRepository bookRepository;

    public CreateBookResponseDto createBook(CreateBookRequestDto requestDto) {
        Book book = bookRepository.save(requestDto.toEntity());
        return new CreateBookResponseDto(book);
    }
    @Transactional(readOnly = true)
    public List<GetBookResponseDto> getBookList(){
        return bookRepository.findAllByOrderByCreatedAtAsc()
                .stream()
                .map(GetBookResponseDto::new)
                .toList();
    }
    @Transactional(readOnly = true)
    public GetBookResponseDto getBook(Long id){
        Book book= bookRepository.findById(id).orElseThrow(()->
            new CustomApiException("찾는 책이 없습니다.")
        );
        return new GetBookResponseDto(book);
    }
}

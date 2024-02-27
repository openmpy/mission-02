package com.example.mission02.domain.book.entity;

import com.example.mission02.domain.book.dto.BookRequestDto;
import com.example.mission02.global.entity.Timestamped;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor
public class Book extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String author;

    @Column(nullable = false)
    private String language;

    @Column(nullable = false)
    private String publisher;

    @Column(nullable = false)
    private boolean isLoaned;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    public Book(BookRequestDto.CreateBookRequestDto book) {
        this.title = book.getTitle();
        this.author = book.getAuthor();
        this.language = book.getLanguage();
        this.publisher = book.getPublisher();
    }
}

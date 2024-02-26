package com.example.mission02.domain.book.service.dto;

import com.example.mission02.domain.book.service.entity.Book;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class BookResponseDto {
    @AllArgsConstructor
    @Getter
    @NoArgsConstructor
    public static class CreateBookResponseDto{
        private Long id;
        private String title;
        private String author;
        private String language;
        private String publisher;
        private boolean isLoaned;
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
        private LocalDateTime createdAt;

        public CreateBookResponseDto(Book book){
            this.id=book.getId();
            this.title=book.getTitle();
            this.author=book.getAuthor();
            this.language=book.getLanguage();
            this.publisher=book.getPublisher();
            this.createdAt=book.getCreatedAt();
        }
    }
}

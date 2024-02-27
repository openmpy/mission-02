package com.example.mission02.domain.book.dto;

import com.example.mission02.domain.book.entity.Book;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class BookRequestDto {
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    public static class CreateBookRequestDto {

        @NotNull(message = "책 제목을 입력해주세요.")
        private String title;

        @NotNull(message = "책 저자를 입력해주세요.")
        private String author;

        @NotNull(message = "책 언어를 입력해주세요.")
        private String language;

        @NotNull(message = "출판사를 입력해주세요.")
        private String publisher;

        public Book toEntity() {
            return Book.builder()
                    .title(this.title)
                    .author(this.author)
                    .language(this.language)
                    .publisher(this.publisher)
                    .build();
        }
    }
}

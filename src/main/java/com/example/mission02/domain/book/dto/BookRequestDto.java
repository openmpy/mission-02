package com.example.mission02.domain.book.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

public class BookRequestDto {
    @Getter
    public static class CreateBookRequestDto {

        @NotNull(message = "책 제목을 입력해주세요.")
        private final String title;

        @NotNull(message = "책 저자를 입력해주세요.")
        private final String author;

        @NotNull(message = "책 언어를 입력해주세요.")
        private final String language;

        @NotNull(message = "출판사를 입력해주세요.")
        private final String publisher;

        private final boolean isLoaned = false;

        public CreateBookRequestDto(String title, String author, String language, String publisher) {
            this.title = title;
            this.author = author;
            this.language = language;
            this.publisher = publisher;
        }
    }
}

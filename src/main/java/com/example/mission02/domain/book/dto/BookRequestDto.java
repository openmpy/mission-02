package com.example.mission02.domain.book.dto;

import com.example.mission02.domain.book.entity.Book;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class BookRequestDto {

    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @Getter
    public static class CreateBookRequestDto {

        @Schema(description = "제목", example = "어린왕자")
        @NotBlank(message = "책 제목을 입력해주세요.")
        private String title;

        @Schema(description = "저자", example = "앙투안 드 생텍쥐페리")
        @NotBlank(message = "책 저자를 입력해주세요.")
        private String author;

        @Schema(description = "언어", example = "한국어")
        @NotBlank(message = "책 언어를 입력해주세요.")
        private String language;

        @Schema(description = "출판사", example = "항해99")
        @NotBlank(message = "출판사를 입력해주세요.")
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

package com.example.mission02.global.handler;

import com.example.mission02.global.dto.ResponseDto;
import com.example.mission02.global.handler.exception.CustomApiException;
import com.example.mission02.global.handler.exception.CustomValidationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(CustomApiException.class)
    public ResponseEntity<?> handleCustomApiException(CustomApiException e) {
        return ResponseEntity.badRequest().body(new ResponseDto<>(false, e.getMessage(), null));
    }

    @ExceptionHandler(CustomValidationException.class)
    public ResponseEntity<?> handleValidationApiException(CustomValidationException e) {
        return ResponseEntity.badRequest().body(new ResponseDto<>(false, e.getMessage(), e.getErrorMap()));
    }
}

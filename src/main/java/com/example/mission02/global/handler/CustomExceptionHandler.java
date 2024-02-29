package com.example.mission02.global.handler;

import com.example.mission02.global.dto.ResponseDto;
import com.example.mission02.global.handler.exception.CustomApiException;
import com.example.mission02.global.handler.exception.CustomValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(CustomApiException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseDto<?> handleCustomApiException(CustomApiException e) {
        return ResponseDto.fail(e.getMessage(), null);
    }

    @ExceptionHandler(CustomValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseDto<?> handleValidationApiException(CustomValidationException e) {
        return ResponseDto.fail(e.getMessage(), e.getErrorMap());
    }
}

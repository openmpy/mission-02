package com.example.mission02.global.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ResponseDto<T> {

    private boolean status;
    private String message;
    private T data;
}

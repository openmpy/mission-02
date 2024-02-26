package com.example.mission02.domain.user.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum GenderType {

    MALE("남자"), FEMALE("여자");

    private final String value;
}
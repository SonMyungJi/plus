package com.sparta.plus.dto;

import lombok.Getter;

@Getter
public class ApiResponseDto {
    private final String msg;
    private final int statusCode;

    public ApiResponseDto(String msg, int statusCode) {
        this.msg = msg;
        this.statusCode = statusCode;
    }
}

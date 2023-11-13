package com.cdcm.apirestpsql.exception.custom;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class CustomErrorException extends RuntimeException{

    private final HttpStatus status;
    public CustomErrorException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }
}

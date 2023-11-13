package com.cdcm.apirestpsql.exception.custom;

import org.springframework.http.HttpStatus;

public class ItemNotFoundException extends CustomErrorException{

    public ItemNotFoundException(String message, HttpStatus status) {
        super(message, status);
    }
}

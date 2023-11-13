package com.cdcm.apirestpsql.exception.custom;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class ItemNotFoundException extends CustomErrorException{

    public ItemNotFoundException(String message, HttpStatus status) {
        super(message, status);
    }
}

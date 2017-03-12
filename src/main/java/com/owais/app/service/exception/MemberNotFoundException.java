package com.owais.app.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class MemberNotFoundException extends Exception {

    public MemberNotFoundException(String message) {
        super(message);
    }

}

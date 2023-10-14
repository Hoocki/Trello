package com.example.trello.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class BoardNotFoundException extends RuntimeException {

     public BoardNotFoundException() {
        super("Board doesn't exist");
    }

}
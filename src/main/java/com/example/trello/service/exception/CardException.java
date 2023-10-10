package com.example.trello.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class CardException extends RuntimeException {

    public CardException() {
        super("Card doesn't exist");
    }

}

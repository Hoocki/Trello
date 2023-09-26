package com.example.trello.service.exception;

public class CardException extends RuntimeException {

    public CardException() {
        super("Card doesn't exist");
    }

    public CardException(final String errorMessage) {
        super(errorMessage);
    }
}

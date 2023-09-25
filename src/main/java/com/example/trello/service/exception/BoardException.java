package com.example.trello.service.exception;

public class BoardException extends RuntimeException {

     public BoardException() {
        super("Board doesn't exist");
    }

    public BoardException(final String errorMessage) {
        super(errorMessage);
    }

}

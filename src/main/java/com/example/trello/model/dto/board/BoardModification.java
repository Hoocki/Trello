package com.example.trello.model.dto.board;

public class BoardModification {

    private final String name;

    private final String description;

    public BoardModification(final String name, final String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

}

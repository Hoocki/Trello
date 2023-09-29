package com.example.trello.model.dto.card;

public class CardModification {

    private final String name;

    private final String description;

    public CardModification(final String name, final String description) {
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

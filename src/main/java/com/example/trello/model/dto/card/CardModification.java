package com.example.trello.model.dto.card;

import jakarta.validation.constraints.Pattern;

public class CardModification {

    @Pattern(regexp = "^[A-Za-z][A-Za-z0-9]*$", message = "The name must begin with a symbol of the English alphabet and do not have special characters")
    private final String name;

    @Pattern(regexp = "^[A-Za-z][A-Za-z0-9]*$", message = "The description must begin with a symbol of the English alphabet and do not have special characters")
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

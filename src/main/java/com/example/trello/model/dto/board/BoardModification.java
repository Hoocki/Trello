package com.example.trello.model.dto.board;

import jakarta.validation.constraints.Pattern;
import lombok.Builder;

@Builder
public record BoardModification(
        @Pattern(regexp = "^[A-Za-z][A-Za-z0-9]*$", message = "The name must begin with a symbol of the English alphabet and do not have special characters") String name,
        @Pattern(regexp = "^[A-Za-z][A-Za-z0-9]*$", message = "The description must begin with a symbol of the English alphabet and do not have special characters") String description)
{}

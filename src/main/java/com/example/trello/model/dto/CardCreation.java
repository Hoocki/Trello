package com.example.trello.model.dto;

import com.example.trello.model.entity.BoardEntity;

public class CardCreation {

    private String name;

    private String description;

    private BoardEntity boardId;

    public CardCreation() {
    }

    public CardCreation(final String name, final String description, final BoardEntity boardId) {
        this.name = name;
        this.description = description;
        this.boardId = boardId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public BoardEntity getBoardId() {
        return boardId;
    }

}

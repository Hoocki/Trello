package com.example.trello.model.dto;

import com.example.trello.model.entity.BoardEntity;
import java.time.LocalDate;

public class CardCreation {

    private long id;

    private String name;

    private String description;

    private LocalDate localDate;

    private BoardEntity boardEntity;


    public CardCreation() {
    }

    public CardCreation(long id, String name, String description, LocalDate localDate, BoardEntity boardEntity) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.localDate = localDate;
        this.boardEntity = boardEntity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }

    public BoardEntity getBoardEntity() {
        return boardEntity;
    }

    public void setBoardEntity(BoardEntity boardEntity) {
        this.boardEntity = boardEntity;
    }

}

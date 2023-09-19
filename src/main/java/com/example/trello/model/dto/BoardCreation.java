package com.example.trello.model.dto;

public class BoardCreation {
    private Long id;

    private String name;

    private String description;


    public BoardCreation() {
    }

    public BoardCreation(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
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

}

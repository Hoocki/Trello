package com.example.trello.model.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "card")
public class CardEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private String description;

    private LocalDate localDate;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "board_id")
    private BoardEntity boardEntity;

    public CardEntity() {
    }

    public CardEntity(String name, String description, LocalDate localDate) {
        this.name = name;
        this.description = description;
        this.localDate = localDate;
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
}

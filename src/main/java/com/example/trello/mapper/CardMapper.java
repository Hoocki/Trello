package com.example.trello.mapper;

import com.example.trello.model.dto.card.Card;
import com.example.trello.model.dto.card.CardModification;
import com.example.trello.model.entity.card.CardEntity;
import org.springframework.stereotype.Component;

@Component
public class CardMapper {

    public CardEntity map(final CardModification cardModification) {
       return CardEntity.builder()
                .name(cardModification.name())
                .description(cardModification.description())
                .build();
    }

    public Card map(final CardEntity cardEntity) {
        return Card.builder()
                .id(cardEntity.getId())
                .name(cardEntity.getName())
                .description(cardEntity.getDescription())
                .createdAt(cardEntity.getCreatedAt())
                .updatedAt(cardEntity.getUpdatedAt())
                .build();
    }

    public CardEntity map(final Card card) {
        return CardEntity.builder()
                .name(card.getName())
                .description(card.getDescription())
                .createdAt(card.getCreatedAt())
                .updatedAt(card.getUpdatedAt())
                .build();
    }

}
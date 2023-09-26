package com.example.trello.mapper;

import com.example.trello.model.dto.card.Card;
import com.example.trello.model.dto.card.CardModification;
import com.example.trello.model.entity.card.CardEntity;
import org.springframework.stereotype.Component;

@Component
public class CardMapper {

    public CardEntity map(final CardModification cardModification) {
        return new CardEntity(cardModification.getName(), cardModification.getDescription());
    }

    public Card map(final CardEntity cardEntity) {
        return new Card(
                cardEntity.getId(),
                cardEntity.getName(),
                cardEntity.getDescription(),
                cardEntity.getCreatedAt(),
                cardEntity.getUpdatedAt());
    }

    public CardEntity map(final Card card) {
        return new CardEntity(
                card.getName(),
                card.getDescription(),
                card.getCreatedAt(),
                card.getUpdatedAt());
    }

}
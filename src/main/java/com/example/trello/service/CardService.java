package com.example.trello.service;

import com.example.trello.model.dto.card.Card;
import com.example.trello.model.dto.card.CardModification;
import java.util.List;

public interface CardService {

    List<Card> getCards(Long boardId);

    Card getCardById(Long cardId);

    Card addCard(Long bordId, CardModification card);

    Card updateCard(Long cardId, CardModification card);

    void deleteCard(Long cardId);

}

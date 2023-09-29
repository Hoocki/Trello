package com.example.trello.controller;

import com.example.trello.model.dto.card.Card;
import com.example.trello.model.dto.card.CardModification;
import com.example.trello.service.CardService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("api/v1/boards/{boardId}/cards")
public class CardController {

    private final CardService cardService;

    public CardController(final CardService cardService) {
        this.cardService = cardService;
    }

    @GetMapping
    public List<Card> getCardsByBoardId(@PathVariable final Long boardId) {
        return cardService.getCards(boardId);
    }

    @GetMapping("{cardId}")
    public Card getCard(@PathVariable final Long cardId) {
        return cardService.getCardById(cardId);
    }

    @PostMapping
    public Card createCard(@PathVariable final Long boardId, @RequestBody final CardModification cardModification) {
        return cardService.addCard(boardId, cardModification);
    }

    @PutMapping("{cardId}")
    public Card updateCard(@PathVariable final Long cardId, @RequestBody final CardModification cardModification) {
        return cardService.updateCard(cardId, cardModification);
    }

    @DeleteMapping("{cardId}")
    public void deleteCard(@PathVariable final Long cardId) {
        cardService.deleteCard(cardId);
    }

}

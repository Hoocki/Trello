package com.example.trello.controller;

import com.example.trello.model.dto.card.Card;
import com.example.trello.model.dto.card.CardModification;
import com.example.trello.service.CardService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class CardControllerTest {

    @InjectMocks
    private CardController cardController;

    @Mock
    private CardService cardService;

    @Test
    void should_returnCardsByBoardId() {
        // given
        var boardId = 1L;
        var card1 = Card.builder()
                .id(1L)
                .name("cardName1")
                .description("cardDesc1")
                .build();

        var card2 = Card.builder()
                .id(1L)
                .name("cardName2")
                .description("cardDesc2")
                .build();

        var expectedCards = List.of(card1, card2);

        given(cardService.getCards(boardId)).willReturn(expectedCards);

        // when
        var result = cardController.getCardsByBoardId(boardId);

        // then
        assertThat(result)
                .containsExactlyElementsOf(expectedCards);

    }

    @Test
    void should_returnCard() {
        // given
        var cardId = 1L;
        var card1 = Card.builder()
                .id(1L)
                .name("cardName1")
                .description("cardDesc1")
                .build();

        given(cardService.getCardById(cardId)).willReturn(card1);

        // when
        var result = cardController.getCard(cardId);

        // then
        assertThat(result).isEqualTo(card1);
    }

    @Test
    void should_addCard() {
        // given
        var boardId = 1L;
        var cardModification = CardModification.builder()
                .name("newCardName")
                .description("newCardDesc")
                .build();

        var card1 = Card.builder()
                .id(1L)
                .name("cardName1")
                .description("cardDesc1")
                .build();

        given(cardService.addCard(boardId, cardModification)).willReturn(card1);

        // when
        var result = cardController.createCard(boardId, cardModification);

        // then
        assertThat(result).isEqualTo(card1);
    }

    @Test
    void should_updateCard() {
        // given
        var cardId = 1L;
        var cardModification = CardModification.builder()
                .name("newCardName")
                .description("newCardDesc")
                .build();

        var card1 = Card.builder()
                .id(1L)
                .name("cardName1")
                .description("cardDesc1")
                .build();

        given(cardService.updateCard(cardId, cardModification)).willReturn(card1);

        // when
        var result = cardController.updateCard(cardId, cardModification);

        // then
        assertThat(result).isEqualTo(card1);
    }

    @Test
    void should_deleteCard() {
        // given
        var cardId = 1L;

        // when
        cardController.deleteCard(cardId);

        // then
        then(cardService).should().deleteCard(cardId);
    }

}
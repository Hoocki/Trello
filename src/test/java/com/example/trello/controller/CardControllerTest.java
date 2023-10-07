package com.example.trello.controller;

import com.example.trello.model.dto.card.Card;
import com.example.trello.model.dto.card.CardModification;
import com.example.trello.service.CardService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Arrays;
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
        long boardId = 1L;
        Card card1 = new Card(1L,"cardName1", "cardDesc1", null, null);
        Card card2 = new Card(2L, "cardName2", "cardDesc2", null, null);
        List<Card> expectedCards = Arrays.asList(card1, card2);

        given(cardService.getCards(boardId)).willReturn(expectedCards);

        // when
        List<Card> result = cardController.getCardsByBoardId(boardId);

        // then
        assertThat(result)
                .containsExactlyElementsOf(expectedCards);

    }

    @Test
    void should_returnCard() {
        // given
        long cardId = 1L;
        Card card1 = new Card(1L,"cardName1", "cardDesc1", null, null);

        given(cardService.getCardById(cardId)).willReturn(card1);

        // when
        Card result = cardController.getCard(cardId);

        // then
        assertThat(result).isEqualTo(card1);
    }

    @Test
    void should_addCard() {
        // given
        long boardId = 1L;
        Card card1 = new Card(1L,"cardName1", "cardDesc1", null, null);
        CardModification cardModification = new CardModification("newCardName", "newCardDesc");

        given(cardService.addCard(boardId, cardModification)).willReturn(card1);

        // when
        Card result = cardController.createCard(boardId, cardModification);

        // then
        assertThat(result).isEqualTo(card1);
    }

    @Test
    void should_updateCard() {
        // given
        long cardId = 1L;
        Card card1 = new Card(1L,"cardName1", "cardDesc1", null, null);
        CardModification cardModification = new CardModification("newCardName", "newCardDesc");

        given(cardService.updateCard(cardId, cardModification)).willReturn(card1);

        // when
        Card result = cardController.updateCard(cardId, cardModification);

        // then
        assertThat(result).isEqualTo(card1);
    }

    @Test
    void should_deleteCard() {
        // given
        long cardId = 1L;

        // when
        cardController.deleteCard(cardId);

        // then
        then(cardService).should().deleteCard(cardId);
    }

}
package com.example.trello.service.impl;

import com.example.trello.mapper.CardMapper;
import com.example.trello.model.dto.card.Card;
import com.example.trello.model.dto.card.CardModification;
import com.example.trello.model.entity.board.BoardEntity;
import com.example.trello.model.entity.card.CardEntity;
import com.example.trello.repository.CardRepository;
import com.example.trello.service.BoardService;
import com.example.trello.service.exception.CardException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.BDDAssertions.catchThrowable;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class CardServiceImplTest {

    @InjectMocks
    private CardServiceImpl cardService;

    @Mock
    private CardRepository cardRepository;

    @Mock
    private BoardService boardService;

    @Mock
    private CardMapper cardMapper;

    @Test
    void should_returnCard_whenCardExists() {
        // given
        long cardId = 1L;
        Card card1 = new Card(1L,"cardName1", "cardDesc1", null, null);
        CardEntity cardEntity1 = new CardEntity("cardName1", "cardDesc1");

        given(cardRepository.findById(cardId)).willReturn(Optional.of(cardEntity1));
        given(cardMapper.map(cardEntity1)).willReturn(card1);

        // when
        Card result = cardService.getCardById(cardId);

        //then
        assertThat(result).isEqualTo(card1);

    }

    @Test
    void should_throwCardException_whenCardDoNotExist() {
        // given
        long cardId = 1L;

        given(cardRepository.findById(cardId)).willReturn(Optional.empty());

        // when
        Throwable throwable = catchThrowable(() -> cardService.getCardById(cardId));

        //then
        assertThat(throwable)
                .isInstanceOf(CardException.class)
                .hasMessageContaining("Card doesn't exist");
    }

    @Test
    void should_returnUpdatedCard_whenCardExists() {
        //given
        long cardId = 1L;
        Card card1 = new Card(1L,"newCardName", "newCardDesc", null, null);
        CardEntity cardEntity1 = new CardEntity("oldCardName", "oldCardDesc");
        CardEntity cardEntity2 = new CardEntity("newCardName", "newCardDesc");
        CardModification cardModification = new CardModification("newCardName", "newCardDesc");

        given(cardRepository.findById(cardId)).willReturn(Optional.of(cardEntity1));
        given(cardRepository.save(cardEntity2)).willReturn(cardEntity2);
        given(cardMapper.map(cardEntity2)).willReturn(card1);

        //when
        Card result = cardService.updateCard(cardId, cardModification);

        //then
        assertThat(result).isEqualTo(card1);

    }

    @Test
    void should_returnCards_whenCardsExist() {
        //given
        long boardId = 1L;
        Card card1 = new Card(1L,"cardName1", "cardDesc1", null, null);
        Card card2 = new Card(2L, "cardName2", "cardDesc2", null, null);
        List<Card> expectedCards = Arrays.asList(card1, card2);

        CardEntity cardEntity1 = new CardEntity("cardName1", "cardDesc1");
        CardEntity cardEntity2 = new CardEntity("cardName2", "cardDesc2");
        List<CardEntity> cardEntities = Arrays.asList(cardEntity1, cardEntity2);

        given(cardRepository.findAllByBoardEntityIdOrderByCreatedAtDesc(boardId)).willReturn(cardEntities);
        given(cardMapper.map(cardEntity1)).willReturn(card1);
        given(cardMapper.map(cardEntity2)).willReturn(card2);

        //when
        List<Card> result = cardService.getCards(boardId);

        //then
        assertThat(result)
                .containsExactlyElementsOf(expectedCards);
    }

    @Test
    void should_returnEmptyCards_whenCardsDoNotExist() {
        //given
        long boardId = 1L;
        List<CardEntity> cardEntities = List.of();

        given(cardRepository.findAllByBoardEntityIdOrderByCreatedAtDesc(boardId)).willReturn(cardEntities);

        //when
        List<Card> result = cardService.getCards(boardId);

        //then
        assertThat(result).isEmpty();
    }

    @Test
    void should_addCard_whenBoardExist() {
        // given
        long boardId = 1L;
        BoardEntity boardEntity1 = new BoardEntity("name1", "desc1");
        Card card1 = new Card(1L,"cardName1", "cardDesc1", null, null);
        CardEntity cardEntity1 = new CardEntity("cardName1", "cardDesc1");
        CardModification cardModification = new CardModification("newCardName", "newCardDesc");

        given(boardService.getBoardById(boardId)).willReturn(boardEntity1);
        given(cardMapper.map(cardModification)).willReturn(cardEntity1);
        given(cardRepository.save(cardEntity1)).willReturn(cardEntity1);
        given(cardMapper.map(cardEntity1)).willReturn(card1);

        //when
        Card result = cardService.addCard(boardId, cardModification);

        //then
        assertThat(result).isEqualTo(card1);
    }

    @Test
    void should_deleteCard() {
        // given
        long cardId = 1L;

        // when
        cardService.deleteCard(cardId);

        // then
        then(cardRepository).should().deleteById(cardId);
    }

}
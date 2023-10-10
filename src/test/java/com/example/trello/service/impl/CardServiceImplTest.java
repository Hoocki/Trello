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
    void should_returnCard_when_cardExists() {
        // given
        var cardId = 1L;
        var cardEntity1 = CardEntity.builder()
                .name("cardName1")
                .description("cardDesc1")
                .build();

        var card1 = Card.builder()
                .id(1L)
                .name("cardName1")
                .description("cardDesc1")
                .build();

        given(cardRepository.findById(cardId)).willReturn(Optional.of(cardEntity1));
        given(cardMapper.map(cardEntity1)).willReturn(card1);

        // when
        var result = cardService.getCardById(cardId);

        //then
        assertThat(result).isEqualTo(card1);

    }

    @Test
    void should_throwCardException_when_cardDoesNotExist() {
        // given
        var cardId = 1L;

        given(cardRepository.findById(cardId)).willReturn(Optional.empty());

        // when
        Throwable throwable = catchThrowable(() -> cardService.getCardById(cardId));

        //then
        assertThat(throwable)
                .isInstanceOf(CardException.class)
                .hasMessageContaining("Card doesn't exist");
    }

    @Test
    void should_returnUpdatedCard_when_cardExists() {
        //given
        var cardId = 1L;
        var cardEntity1 = CardEntity.builder()
                .name("oldCardName")
                .description("oldCardDesc")
                .build();

        var cardEntity2 = CardEntity.builder()
                .name("newCardName")
                .description("newCardDesc")
                .build();

        var cardModification = CardModification.builder()
                .name("newCardName")
                .description("newCardDesc")
                .build();

        var card1 = Card.builder()
                .id(1L)
                .name("newCardName")
                .description("newCardDesc")
                .build();

        given(cardRepository.findById(cardId)).willReturn(Optional.of(cardEntity1));
        given(cardRepository.save(cardEntity2)).willReturn(cardEntity2);
        given(cardMapper.map(cardEntity2)).willReturn(card1);

        //when
        var result = cardService.updateCard(cardId, cardModification);

        //then
        assertThat(result).isEqualTo(card1);

    }

    @Test
    void should_returnCards_when_cardsExist() {
        //given
        var boardId = 1L;
        var card1 = Card.builder()
                .id(1L)
                .name("cardName1")
                .description("cardDesc1")
                .build();

        var card2 = Card.builder()
                .id(2L)
                .name("cardName1")
                .description("cardDesc1")
                .build();

        var cardEntity1 = CardEntity.builder()
                .name("cardName1")
                .description("cardDesc1")
                .build();

        var cardEntity2 = CardEntity.builder()
                .name("cardName2")
                .description("cardDesc2")
                .build();

        var cardEntities = Arrays.asList(cardEntity1, cardEntity2);

        given(cardRepository.findAllByBoardEntityIdOrderByCreatedAtDesc(boardId)).willReturn(cardEntities);
        given(cardMapper.map(cardEntity1)).willReturn(card1);
        given(cardMapper.map(cardEntity2)).willReturn(card2);

        //when
        var result = cardService.getCards(boardId);

        //then
        var expectedCards = Arrays.asList(card1, card2);
        assertThat(result)
                .containsExactlyElementsOf(expectedCards);
    }

    @Test
    void should_returnEmptyCards_when_cardsDoNotExist() {
        //given
        var boardId = 1L;
        List<CardEntity> cardEntities = List.of();

        given(cardRepository.findAllByBoardEntityIdOrderByCreatedAtDesc(boardId)).willReturn(cardEntities);

        //when
        List<Card> result = cardService.getCards(boardId);

        //then
        assertThat(result).isEmpty();
    }

    @Test
    void should_addCard_when_boardExists() {
        // given
        var boardId = 1L;
        var boardEntity1 = BoardEntity.builder()
                .name("name1")
                .description("desc1")
                .build();

        var cardEntity1 = CardEntity.builder()
                .name("cardName1")
                .description("cardDesc1")
                .build();

        var cardModification = CardModification.builder()
                .name("cardName1")
                .description("cardDesc1")
                .build();

        var card1 = Card.builder()
                .id(1L)
                .name("cardName1")
                .description("cardDesc1")
                .build();

        given(boardService.getBoardById(boardId)).willReturn(boardEntity1);
        given(cardMapper.map(cardModification)).willReturn(cardEntity1);
        given(cardRepository.save(cardEntity1)).willReturn(cardEntity1);
        given(cardMapper.map(cardEntity1)).willReturn(card1);

        //when
        var result = cardService.addCard(boardId, cardModification);

        //then
        assertThat(result).isEqualTo(card1);
    }

    @Test
    void should_deleteCard() {
        // given
        var cardId = 1L;

        // when
        cardService.deleteCard(cardId);

        // then
        then(cardRepository).should().deleteById(cardId);
    }

}
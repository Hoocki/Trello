package com.example.trello.mapper;

import com.example.trello.model.dto.card.Card;
import com.example.trello.model.dto.card.CardModification;
import com.example.trello.model.entity.card.CardEntity;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class CardMapperTest {

    private final CardMapper CARD_MAPPER = new CardMapper();

    @Test
    void should_returnCardEntity_when_givenCardModification() {
        // given
        var cardModification = CardModification.builder()
                .name("newCardName")
                .description("newCardDesc")
                .build();

        // when
        var result = CARD_MAPPER.map(cardModification);

        // then
        var expectedCardEntity = CardEntity.builder()
                .name("newCardName")
                .description("newCardDesc")
                .build();

        assertThat(result).isEqualTo(expectedCardEntity);
    }

    @Test
    void should_returnCard_when_givenCardEntity() {
        // given
        var cardEntity = CardEntity.builder()
                .id(1L)
                .name("newCardName")
                .description("newCardDesc")
                .build();

        // when
        var result = CARD_MAPPER.map(cardEntity);

        // then
        var expectedCard = Card.builder()
                .id(1L)
                .name("newCardName")
                .description("newCardDesc")
                .build();

        assertThat(result).isEqualTo(expectedCard);
    }

    @Test
    void should_returnCardEntity_when_givenCard() {
        // given
        var card = Card.builder()
                .id(1L)
                .name("cardName1")
                .description("cardDesc1")
                .build();

        // when
        var result = CARD_MAPPER.map(card);

        // then
        var expectedCardEntity = CardEntity.builder()
                .name("cardName1")
                .description("cardDesc1")
                .build();

        assertThat(result).isEqualTo(expectedCardEntity);
    }

}
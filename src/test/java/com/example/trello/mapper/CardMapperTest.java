package com.example.trello.mapper;

import com.example.trello.model.dto.card.Card;
import com.example.trello.model.dto.card.CardModification;
import com.example.trello.model.entity.card.CardEntity;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class CardMapperTest {

    private final CardMapper CARD_MAPPER = new CardMapper();

    @Test
    void should_returnCardEntity_whenGivenCardModification() {
        // given
        final CardModification cardModification = new CardModification("newCardName", "newCardDesc");

        // when
        CardEntity result = CARD_MAPPER.map(cardModification);

        // then
        CardEntity expectedCardEntity = new CardEntity("newCardName", "newCardDesc");
        assertThat(result).isEqualTo(expectedCardEntity);
    }

    @Test
    void should_returnCard_whenGivenCardEntity() {
        // given
        final CardEntity cardEntity = new CardEntity(1L, "newCardName", "newCardDesc",null,null);
        // when
        Card result = CARD_MAPPER.map(cardEntity);

        // then
        Card expectedCard = new Card(1L, "newCardName", "newCardDesc",null,null);
        assertThat(result).isEqualTo(expectedCard);
    }

    @Test
    void should_returnCardEntity_whenGivenCard() {
        // given
        final Card card = new Card(1L,"cardName1", "cardDesc1", null,null);
        // when
        CardEntity result = CARD_MAPPER.map(card);

        // then
        CardEntity expectedCardEntity = new CardEntity("cardName1", "cardDesc1");
        assertThat(result).isEqualTo(expectedCardEntity);
    }

}
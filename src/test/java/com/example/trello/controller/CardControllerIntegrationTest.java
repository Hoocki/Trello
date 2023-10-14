package com.example.trello.controller;

import com.example.trello.model.dto.card.Card;
import com.example.trello.model.dto.card.CardModification;
import com.example.trello.service.exception.CardNotFoundException;
import com.example.trello.service.impl.CardServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@WebMvcTest(controllers = CardController.class)
class CardControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private CardServiceImpl cardService;

    private final Card CARD = Card.builder().name("cardName1").description("cardDesc1").build();

    private final CardModification CARD_MODIFICATION = CardModification.builder().name("cardName1").description("cardDesc1").build();

    @Test
    void should_returnCards() throws Exception {
        //given
        var boardId = 1L;
        var card2 = Card.builder()
                .name("cardName2")
                .description("cardDesc2")
                .build();

        var expectedCards = List.of(CARD, card2);

        given(cardService.getCards(boardId)).willReturn(expectedCards);

        //when
        var responseBody = mockMvc
                .perform(MockMvcRequestBuilders.get("/api/v1/boards/{boardId}/cards", boardId))
                .andReturn()
                .getResponse()
                .getContentAsString();

        //then
        var expectedResponseBody = mapper.writeValueAsString(expectedCards);
        assertThat(responseBody).isEqualTo(expectedResponseBody);
    }

    @Test
    void should_returnCard_whenCardExists() throws Exception {
        //given
        var boardId = 1L;
        var cardId = 1L;
        given(cardService.getCardById(cardId)).willReturn(CARD);

        //when
        var responseBody = mockMvc
                .perform(MockMvcRequestBuilders.get("/api/v1/boards/{boardId}/cards/{cardId}", boardId, cardId))
                .andReturn()
                .getResponse()
                .getContentAsString();

        //then
        var expectedResponseBody = mapper.writeValueAsString(CARD);
        assertThat(responseBody).isEqualTo(expectedResponseBody);
    }

    @Test
    void should_returnException_whenCardDoesNotExist() throws Exception {
        //given
        var boardId = 1L;
        var cardId = 999L;
        given(cardService.getCardById(cardId)).willThrow(new CardNotFoundException());

        //when
        var status = mockMvc
                .perform(MockMvcRequestBuilders.get("/api/v1/boards/{boardId}/cards/{cardId}", boardId, cardId))
                .andReturn()
                .getResponse()
                .getStatus();

        //then
        assertThat(status).isEqualTo(404);
    }

    @Test
    void should_returnCard_when_cardAdded() throws Exception {
        //given
        var boardId = 1L;
        given(cardService.addCard(boardId, CARD_MODIFICATION)).willReturn(CARD);

        //when
        var responseBody = mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/api/v1/boards/{boardId}/cards", boardId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(CARD_MODIFICATION)))
                .andReturn()
                .getResponse()
                .getContentAsString();

        //then
        var expectedResponseBody = mapper.writeValueAsString(CARD);
        assertThat(responseBody).isEqualTo(expectedResponseBody);
    }

    @Test
    void should_returnException_when_addCardInputInvalid() throws Exception {
        //given
        var boardId = 1L;
        var cardModification = CardModification.builder()
                .name("1asd")
                .description("")
                .build();

        //when
        var status = mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/api/v1/boards/{boardId}/cards", boardId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(cardModification)))
                .andReturn()
                .getResponse()
                .getStatus();

        //then
        assertThat(status).isEqualTo(400);
    }

    @Test
    void should_returnCard_when_cardUpdated() throws Exception {
        //given
        var boardId = 1L;
        var cardId = 1L;
        given(cardService.updateCard(cardId, CARD_MODIFICATION)).willReturn(CARD);

        //when
        var responseBody = mockMvc
                .perform(MockMvcRequestBuilders
                        .put("/api/v1/boards/{boardId}/cards/{cardId}", boardId, cardId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(CARD_MODIFICATION)))
                .andReturn()
                .getResponse()
                .getContentAsString();

        //then
        var expectedResponseBody = mapper.writeValueAsString(CARD);
        assertThat(responseBody).isEqualTo(expectedResponseBody);
    }

    @Test
    void should_returnException_when_updateCardInputInvalid() throws Exception {
        //given
        var boardId = 1L;
        var cardId = 1L;
        var cardModification = CardModification.builder()
                .name("1asd")
                .description("")
                .build();

        //when
        var status = mockMvc
                .perform(MockMvcRequestBuilders
                        .put("/api/v1/boards/{boardId}/cards/{cardId}", boardId, cardId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(cardModification)))
                .andReturn()
                .getResponse()
                .getStatus();

        //then
        assertThat(status).isEqualTo(400);
    }

    @Test
    void should_returnException_when_updateCardDoesNotExist() throws Exception {
        //given
        var boardId = 1L;
        var cardId = 999L;
        given(cardService.updateCard(cardId, CARD_MODIFICATION)).willThrow(new CardNotFoundException());

        //when
        var status = mockMvc
                .perform(MockMvcRequestBuilders
                        .put("/api/v1/boards/{boardId}/cards/{cardId}", boardId, cardId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(CARD_MODIFICATION)))
                .andReturn()
                .getResponse()
                .getStatus();

        //then
        assertThat(status).isEqualTo(404);
    }

}
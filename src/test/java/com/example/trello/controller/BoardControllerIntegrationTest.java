package com.example.trello.controller;

import com.example.trello.model.dto.board.BoardModification;
import com.example.trello.model.entity.board.BoardEntity;
import com.example.trello.service.exception.BoardNotFoundException;
import com.example.trello.service.impl.BoardServiceImpl;
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

@WebMvcTest(controllers = BoardController.class)
class BoardControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private BoardServiceImpl boardService;

    private final BoardEntity BOARD_ENTITY = BoardEntity.builder().name("boardName1").description("boardDesc1").build();

    private final BoardModification BOARD_MODIFICATION = BoardModification.builder().name("boardName1").description("boardDesc1").build();

    @Test
    void should_returnBoards() throws Exception {
        // given
        var boardEntity2 = BoardEntity.builder()
                .name("boardName2")
                .description("boardDesc2")
                .build();

        var boardEntities = List.of(BOARD_ENTITY, boardEntity2);
        given(boardService.getBoards()).willReturn(boardEntities);

        // when
        var responseBody = mockMvc
                .perform(MockMvcRequestBuilders.get("/api/v1/boards"))
                .andReturn()
                .getResponse()
                .getContentAsString();

        // then
        var expectedResponseBody = mapper.writeValueAsString(boardEntities);
        assertThat(responseBody).isEqualTo(expectedResponseBody);
    }

    @Test
    void should_returnBoard_whenBoardExists() throws Exception {
        // given
        var boardId = 1L;

        given(boardService.getBoardById(boardId)).willReturn(BOARD_ENTITY);

        // when
        var responseBody = mockMvc
                .perform(MockMvcRequestBuilders.get("/api/v1/boards/{boardId}", boardId))
                .andReturn()
                .getResponse()
                .getContentAsString();

        // then
        var expectedResponseBody = mapper.writeValueAsString(BOARD_ENTITY);
        assertThat(responseBody).isEqualTo(expectedResponseBody);
    }

    @Test
    void should_returnException_whenBoardDoesNotExist() throws Exception {
        // given
        var boardId = 999L;
        given(boardService.getBoardById(boardId)).willThrow(new BoardNotFoundException());

        // when
        var status = mockMvc
                .perform(MockMvcRequestBuilders.get("/api/v1/boards/{boardId}", boardId))
                .andReturn()
                .getResponse()
                .getStatus();

        // then
        assertThat(status).isEqualTo(404);
    }

    @Test
    void should_returnBoard_when_boardAdded() throws Exception {
        //given
        given(boardService.addBoard(BOARD_MODIFICATION)).willReturn(BOARD_ENTITY);

        //when
        var responseBody = mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/api/v1/boards")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(BOARD_MODIFICATION)))
                .andReturn()
                .getResponse()
                .getContentAsString();

        //then
        var expectedResponseBody = mapper.writeValueAsString(BOARD_ENTITY);
        assertThat(responseBody).isEqualTo(expectedResponseBody);
    }

    @Test
    void should_returnException_when_addBoardInputInvalid() throws Exception {
        //given
        var invalidBoardModification = BoardModification.builder()
                .name("")
                .description("1")
                .build();

        //when
        var status = mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/api/v1/boards")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(invalidBoardModification)))
                .andReturn()
                .getResponse()
                .getStatus();

        //then
        assertThat(status).isEqualTo(400);
    }

    @Test
    void should_returnBoard_when_boardUpdated() throws Exception {
        //given
        var boardId = 1L;
        given(boardService.updateBoard(boardId, BOARD_MODIFICATION)).willReturn(BOARD_ENTITY);

        //when
        var responseBody = mockMvc
                .perform(MockMvcRequestBuilders
                        .put("/api/v1/boards/{boardId}", boardId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(BOARD_MODIFICATION)))
                .andReturn()
                .getResponse()
                .getContentAsString();

        //then
        var expectedResponseBody = mapper.writeValueAsString(BOARD_ENTITY);
        assertThat(responseBody).isEqualTo(expectedResponseBody);
    }

    @Test
    void should_returnException_when_updateBoardInputInvalid() throws Exception {
        //given
        var boardId = 1L;
        var invalidBoardModification = BoardModification.builder()
                .name("")
                .description("")
                .build();

        //when
        var status = mockMvc
                .perform(MockMvcRequestBuilders
                        .put("/api/v1/boards/{boardId}", boardId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(invalidBoardModification)))
                .andReturn()
                .getResponse()
                .getStatus();

        //then
        assertThat(status).isEqualTo(400);
    }

    @Test
    void should_returnException_when_updateBoardDoesNotExist() throws Exception {
        //given
        var boardId = 999L;
        given(boardService.updateBoard(boardId, BOARD_MODIFICATION)).willThrow(new BoardNotFoundException());

        //when
        var status = mockMvc
                .perform(MockMvcRequestBuilders
                        .put("/api/v1/boards/{boardId}", boardId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(BOARD_MODIFICATION)))
                .andReturn()
                .getResponse()
                .getStatus();

        //then
        assertThat(status).isEqualTo(404);
    }

}
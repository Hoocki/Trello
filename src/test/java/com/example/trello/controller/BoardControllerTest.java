package com.example.trello.controller;

import com.example.trello.model.dto.board.BoardModification;
import com.example.trello.model.entity.board.BoardEntity;
import com.example.trello.service.BoardService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Arrays;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class BoardControllerTest {

    @InjectMocks
    private BoardController boardController;

    @Mock
    private BoardService boardService;

    @Test
    void should_returnBoards() {
        // given
        var boardEntity1 = BoardEntity.builder()
                .name("name1")
                .description("desc1")
                .build();

        var boardEntity2 = BoardEntity.builder()
                .name("name2")
                .description("desc2")
                .build();

        var expectedBoards = Arrays.asList(boardEntity1, boardEntity2);

        given(boardService.getBoards()).willReturn(expectedBoards);

        // when
        var result = boardController.getBoards();

        // then
        assertThat(result)
                .containsExactlyElementsOf(expectedBoards);

    }

    @Test
    void should_returnBoard() {
        // given
        var boardId = 1L;
        var boardEntity1 = BoardEntity.builder()
                .name("name1")
                .description("desc1")
                .build();

        given(boardService.getBoardById(boardId)).willReturn(boardEntity1);

        // when
        var result = boardController.getBoardById(boardId);

        // then
        assertThat(result).isEqualTo(boardEntity1);
    }

    @Test
    void should_addBoard() {
        // given
        var boardModification = BoardModification.builder()
                .name("name1")
                .description("desc1")
                .build();

        var boardEntity1 = BoardEntity.builder()
                .name("name1")
                .description("desc1")
                .build();

        given(boardService.addBoard(boardModification)).willReturn(boardEntity1);

        // when
        var result = boardController.createBoard(boardModification);

        // then
        assertThat(result).isEqualTo(boardEntity1);
    }

    @Test
    void should_updateBoard() {
        // given
        var boardId = 1L;
        var boardModification = BoardModification.builder()
                .name("name1")
                .description("desc1")
                .build();

        var boardEntity1 = BoardEntity.builder()
                .name("name1")
                .description("desc1")
                .build();

        given(boardService.updateBoard(boardId, boardModification)).willReturn(boardEntity1);

        // when
        var result = boardController.updateBoard(boardId, boardModification);

        // then
        assertThat(result).isEqualTo(boardEntity1);
    }

    @Test
    void should_deleteBoard() {
        // given
        var boardId = 1L;

        // when
        boardController.deleteBoard(boardId);

        // then
       then(boardService).should().deleteBoard(boardId);
    }

}
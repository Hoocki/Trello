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
import java.util.List;
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
        BoardEntity boardEntity1 = new BoardEntity("name1", "desc1");
        BoardEntity boardEntity2 = new BoardEntity("name2", "desc2");
        List<BoardEntity> expectedBoards = Arrays.asList(boardEntity1, boardEntity2);

        given(boardService.getBoards()).willReturn(expectedBoards);

        // when
        List<BoardEntity> result = boardController.getBoards();

        // then
        assertThat(result)
                .containsExactlyElementsOf(expectedBoards);

    }

    @Test
    void should_returnBoard() {
        // given
        long boardId = 1L;
        BoardEntity boardEntity1 = new BoardEntity("name1", "desc1");

        given(boardService.getBoardById(boardId)).willReturn(boardEntity1);

        // when
        BoardEntity result = boardController.getBoardById(boardId);

        // then
        assertThat(result).isEqualTo(boardEntity1);
    }

    @Test
    void should_addBoard() {
        // given
        BoardEntity boardEntity1 = new BoardEntity("name1", "desc1");
        BoardModification boardModification = new BoardModification("name1", "desc1");

        given(boardService.addBoard(boardModification)).willReturn(boardEntity1);

        // when
        BoardEntity result = boardController.createBoard(boardModification);

        // then
        assertThat(result).isEqualTo(boardEntity1);
    }

    @Test
    void should_updateBoard() {
        // given
        long boardId = 1L;
        BoardEntity boardEntity1 = new BoardEntity("name1", "desc1");
        BoardModification boardModification = new BoardModification("name1", "desc1");

        given(boardService.updateBoard(boardId, boardModification)).willReturn(boardEntity1);

        // when
        BoardEntity result = boardController.updateBoard(boardId, boardModification);

        // then
        assertThat(result).isEqualTo(boardEntity1);
    }

    @Test
    void should_deleteBoard() {
        // given
        long boardId = 1L;

        // when
        boardController.deleteBoard(boardId);

        // then
       then(boardService).should().deleteBoard(boardId);
    }

}
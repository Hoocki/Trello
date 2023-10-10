package com.example.trello.service.impl;

import com.example.trello.mapper.BoardMapper;
import com.example.trello.model.dto.board.BoardModification;
import com.example.trello.model.entity.board.BoardEntity;
import com.example.trello.repository.BoardRepository;
import com.example.trello.service.exception.BoardException;
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
class BoardServiceImplTest {

    @InjectMocks
    private BoardServiceImpl boardService;

    @Mock
    private BoardRepository boardRepository;

    @Mock
    private BoardMapper boardMapper;

    @Test
    void should_returnBoards_whenBoardsExist() {
        // given
        BoardEntity boardEntity1 = new BoardEntity("name1", "desc1");
        BoardEntity boardEntity2 = new BoardEntity("name2", "desc2");
        List<BoardEntity> expectedBoards = Arrays.asList(boardEntity1, boardEntity2);

        given(boardRepository.findByOrderByNameAsc()).willReturn(expectedBoards);

        //when
        List<BoardEntity> result = boardService.getBoards();

        //then
        assertThat(result)
                .containsExactlyElementsOf(expectedBoards);

    }

    @Test
    void should_returnEmptyBoards_whenBoardsDoNotExist() {
        // given
        List<BoardEntity> expectedBoards = List.of();

        given(boardRepository.findByOrderByNameAsc()).willReturn(expectedBoards);

        //when
        List<BoardEntity> result = boardService.getBoards();

        //then
        assertThat(result).isEmpty();
    }

    @Test
    void should_returnBoardById_whenBoardExist() {
        // given
        long boardId = 1L;
        BoardEntity boardEntity1 = new BoardEntity("name1", "desc1");

        given(boardRepository.findById(boardId)).willReturn(Optional.of(boardEntity1));

        // when
        BoardEntity result = boardService.getBoardById(boardId);

        // then
        assertThat(result).isEqualTo(boardEntity1);
    }

    @Test
    void should_throwBoardException_whenBoardNotFound() {
        // given
        long boardId = 1L;

        given(boardRepository.findById(boardId)).willReturn(Optional.empty());

        // when
        Throwable thrown = catchThrowable(() -> boardService.getBoardById(boardId));

        // then
        assertThat(thrown)
                .isInstanceOf(BoardException.class)
                .hasMessageContaining("Board doesn't exist");

    }

    @Test
    void should_addBoard() {
        //given
        BoardEntity boardEntity1 = new BoardEntity("name1", "desc1");
        BoardModification boardModification = new BoardModification("name1", "desc1");

        given(boardMapper.map(boardModification)).willReturn(boardEntity1);
        given(boardRepository.save(boardEntity1)).willReturn(boardEntity1);

        //when
        BoardEntity result = boardService.addBoard(boardModification);

        //then
        assertThat(result).isEqualTo(boardEntity1);
    }

    @Test
    void should_deleteBoard() {
        // given
        long boardId = 1L;

        // when
        boardService.deleteBoard(boardId);

        // then
        then(boardRepository).should().deleteById(boardId);
    }

    @Test
    void should_updateBoard() {
        // given
        long boardId = 1L;
        BoardEntity boardEntity1 = new BoardEntity("name1", "desc1");
        BoardEntity boardEntity2 = new BoardEntity("name2", "desc2");
        BoardModification boardModification = new BoardModification("name1", "desc1");

        given(boardRepository.findById(boardId)).willReturn(Optional.of(boardEntity2));
        given(boardRepository.save(boardEntity1)).willReturn(boardEntity1);
        given(boardRepository.findById(boardId)).willReturn(Optional.of(boardEntity1));

        // when
        BoardEntity result = boardService.updateBoard(boardId, boardModification);

        // then
        assertThat(result).isEqualTo(boardEntity1);
    }

}
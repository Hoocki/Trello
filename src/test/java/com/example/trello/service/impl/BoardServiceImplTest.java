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
    void should_returnBoards_when_boardsExist() {
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

        given(boardRepository.findByOrderByNameAsc()).willReturn(expectedBoards);

        //when
        var result = boardService.getBoards();

        //then
        assertThat(result)
                .containsExactlyElementsOf(expectedBoards);

    }

    @Test
    void should_returnEmptyBoards_when_boardsDoNotExist() {
        // given
        List<BoardEntity> expectedBoards = List.of();

        given(boardRepository.findByOrderByNameAsc()).willReturn(expectedBoards);

        //when
        var result = boardService.getBoards();

        //then
        assertThat(result).isEmpty();
    }

    @Test
    void should_returnBoardById_when_boardExists() {
        // given
        var boardId = 1L;
        var boardEntity1 = BoardEntity.builder()
                .name("name1")
                .description("desc1")
                .build();

        given(boardRepository.findById(boardId)).willReturn(Optional.of(boardEntity1));

        // when
        var result = boardService.getBoardById(boardId);

        // then
        assertThat(result).isEqualTo(boardEntity1);
    }

    @Test
    void should_throwBoardException_when_boardNotFound() {
        // given
        var boardId = 1L;

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
        var boardModification = BoardModification.builder()
                .name("name1")
                .description("desc1")
                .build();

        var boardEntity1 = BoardEntity.builder()
                .name("name1")
                .description("desc1")
                .build();

        given(boardMapper.map(boardModification)).willReturn(boardEntity1);
        given(boardRepository.save(boardEntity1)).willReturn(boardEntity1);

        //when
        var result = boardService.addBoard(boardModification);

        //then
        assertThat(result).isEqualTo(boardEntity1);
    }

    @Test
    void should_deleteBoard() {
        // given
        var boardId = 1L;

        // when
        boardService.deleteBoard(boardId);

        // then
        then(boardRepository).should().deleteById(boardId);
    }

    @Test
    void should_updateBoard() {
        // given
        var boardId = 1L;
        var boardEntity2 = BoardEntity.builder()
                .name("name2")
                .description("desc2")
                .build();

        var boardModification = BoardModification.builder()
                .name("name1")
                .description("desc1")
                .build();

        var boardEntity1 = BoardEntity.builder()
                .name("name1")
                .description("desc1")
                .build();

        given(boardRepository.findById(boardId)).willReturn(Optional.of(boardEntity2));
        given(boardRepository.save(boardEntity1)).willReturn(boardEntity1);
        given(boardRepository.findById(boardId)).willReturn(Optional.of(boardEntity1));

        // when
        var result = boardService.updateBoard(boardId, boardModification);

        // then
        assertThat(result).isEqualTo(boardEntity1);
    }

}
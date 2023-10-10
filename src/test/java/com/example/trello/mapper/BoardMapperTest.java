package com.example.trello.mapper;

import com.example.trello.model.dto.board.BoardModification;
import com.example.trello.model.entity.board.BoardEntity;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class BoardMapperTest {

    private final BoardMapper BOARD_MAPPER = new BoardMapper();

    @Test
    void should_returnBoardEntity_when_givenBoardModification() {
        // given
        final BoardModification boardModification = new BoardModification("name1", "desc1");

        //when
        BoardEntity result = BOARD_MAPPER.map(boardModification);

        //then
        BoardEntity expectedBoardEntity = new BoardEntity("name1", "desc1");
        assertThat(result).isEqualTo(expectedBoardEntity);
    }

}
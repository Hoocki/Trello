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
        var boardModification = BoardModification.builder()
                .name("name1")
                .description("desc1")
                .build();

        //when
        var result = BOARD_MAPPER.map(boardModification);

        //then
        var expectedBoardEntity = BoardEntity.builder()
                .name("name1")
                .description("desc1")
                .build();

        assertThat(result).isEqualTo(expectedBoardEntity);
    }

}
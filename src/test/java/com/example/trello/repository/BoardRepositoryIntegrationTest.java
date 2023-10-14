package com.example.trello.repository;

import com.example.trello.model.entity.board.BoardEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class BoardRepositoryIntegrationTest {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    void should_returnBoardsOrderedByNameAsc() {
        // given
        var board1 = testEntityManager.persist(
                BoardEntity.builder()
                        .name("name1")
                        .description("desc1")
                        .build());

        var board2 = testEntityManager.persist(
                BoardEntity.builder()
                        .name("name2")
                        .description("desc3")
                        .build());

        var board3 = testEntityManager.persist(
                BoardEntity.builder()
                        .name("name3")
                        .description("desc3")
                        .build());

        // when
        var boardEntities = boardRepository.findByOrderByNameAsc();

        // then
        assertThat(boardEntities).containsExactly(board1, board2, board3);
    }

}
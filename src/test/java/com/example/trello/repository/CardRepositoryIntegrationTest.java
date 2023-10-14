package com.example.trello.repository;

import com.example.trello.model.entity.board.BoardEntity;
import com.example.trello.model.entity.card.CardEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CardRepositoryIntegrationTest {

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    void should_returnAllCardsByBoardIdOrderedByCreatedAtDesc() {
        // given
        var boardEntity = testEntityManager.persistAndFlush(BoardEntity.builder()
                .name("boardName1")
                .description("desc1")
                .build());

        var cardEntity1 = testEntityManager.persistAndFlush(CardEntity.builder()
                .name("card1")
                .description("desc1")
                .boardEntity(boardEntity)
                .build());

        var cardEntity2 = testEntityManager.persistAndFlush(CardEntity.builder()
                .name("card2")
                .description("desc2")
                .boardEntity(boardEntity)
                .build());


        // when
        var cardEntities = cardRepository.findAllByBoardEntityIdOrderByCreatedAtDesc(boardEntity.getId());

        // then
        assertThat(cardEntities).containsExactly(cardEntity2, cardEntity1);
    }

}
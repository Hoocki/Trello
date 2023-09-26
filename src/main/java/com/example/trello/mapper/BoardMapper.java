package com.example.trello.mapper;

import com.example.trello.model.dto.board.BoardModification;
import com.example.trello.model.entity.board.BoardEntity;
import org.springframework.stereotype.Component;

@Component
public class BoardMapper {

    public BoardEntity map(final BoardModification boardModification) {
        return new BoardEntity(
                boardModification.getName(),
                boardModification.getDescription()
        );
    }

}

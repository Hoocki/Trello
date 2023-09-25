package com.example.trello.mapper;

import com.example.trello.model.dto.BoardModification;
import com.example.trello.model.entity.BoardEntity;
import org.springframework.stereotype.Component;

@Component
public class BoardMapper {

    public BoardEntity mapToBoardEntity(final BoardModification boardModification) {
        return new BoardEntity(
                boardModification.getName(),
                boardModification.getDescription()
        );
    }

}

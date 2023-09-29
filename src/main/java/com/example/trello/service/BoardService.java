package com.example.trello.service;

import com.example.trello.model.dto.board.BoardModification;
import com.example.trello.model.entity.board.BoardEntity;
import java.util.List;

public interface BoardService {

    List<BoardEntity> getBoards();

    BoardEntity getBoardById(Long boardId);

    BoardEntity addBoard(BoardModification board);

    BoardEntity updateBoard(Long boardId, BoardModification board);

    void deleteBoard(Long boardId);

}

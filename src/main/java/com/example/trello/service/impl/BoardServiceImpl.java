package com.example.trello.service.impl;

import com.example.trello.service.exception.BoardNotFoundException;
import com.example.trello.mapper.BoardMapper;
import com.example.trello.model.dto.board.BoardModification;
import com.example.trello.model.entity.board.BoardEntity;
import com.example.trello.repository.BoardRepository;
import com.example.trello.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;

    private final BoardMapper boardMapper;

    public List<BoardEntity> getBoards() {
        return boardRepository.findByOrderByNameAsc();
    }

    public BoardEntity getBoardById(final Long boardId) {
        return boardRepository.findById(boardId).orElseThrow(BoardNotFoundException::new);
    }

    public BoardEntity addBoard(final BoardModification boardModification) {
        final var boardEntity = boardMapper.map(boardModification);
        return boardRepository.save(boardEntity);
    }

    public void deleteBoard(final Long boardId) {
        boardRepository.deleteById(boardId);
    }

    public BoardEntity updateBoard(final Long boardId, final BoardModification boardModification) {
        final var boardEntity = getBoardById(boardId);
        boardEntity.setName(boardModification.name());
        boardEntity.setDescription(boardModification.description());
        return boardRepository.save(boardEntity);
    }

}

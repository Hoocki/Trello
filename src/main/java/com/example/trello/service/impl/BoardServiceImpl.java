package com.example.trello.service.impl;

import com.example.trello.service.exception.BoardException;
import com.example.trello.mapper.BoardMapper;
import com.example.trello.model.dto.BoardModification;
import com.example.trello.model.entity.BoardEntity;
import com.example.trello.repository.BoardRepository;
import com.example.trello.service.BoardService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;

    private final BoardMapper boardMapper;

    public BoardServiceImpl(final BoardRepository boardRepository, final BoardMapper boardMapper) {
        this.boardRepository = boardRepository;
        this.boardMapper = boardMapper;
    }

    public List<BoardEntity> getBoards() {
        return boardRepository.findByOrderByNameAsc();
    }

    public BoardEntity getBoardById(final Long boardId) {
        return boardRepository.findById(boardId).orElseThrow(BoardException::new);
    }

    public BoardEntity addBoard(final BoardModification boardModification) {
        final BoardEntity boardEntity = boardMapper.mapToBoardEntity(boardModification);
        return boardRepository.save(boardEntity);
    }

    public void deleteBoard(final Long boardId) {
        boardRepository.deleteById(boardId);
    }

    public BoardEntity updateBoard(final Long boardId, final BoardModification boardModification) {
        final BoardEntity boardEntity = getBoardById(boardId);
        boardEntity.setName(boardModification.getName());
        boardEntity.setDescription(boardModification.getDescription());
        return boardRepository.save(boardEntity);
    }

}

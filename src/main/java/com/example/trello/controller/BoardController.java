package com.example.trello.controller;

import com.example.trello.model.dto.board.BoardModification;
import com.example.trello.model.entity.board.BoardEntity;
import com.example.trello.service.BoardService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "api/v1/boards")
public class BoardController {

    private final BoardService boardService;

    @GetMapping
    public List<BoardEntity> getBoards() {
        return boardService.getBoards();
    }

    @GetMapping("{boardId}")
    public BoardEntity getBoardById(@PathVariable final Long boardId) {
        return boardService.getBoardById(boardId);
    }

    @PostMapping
    public BoardEntity createBoard(@Valid @RequestBody final BoardModification boardModification) {
        return boardService.addBoard(boardModification);
    }

    @PutMapping("{boardId}")
    public BoardEntity updateBoard(@PathVariable("boardId") final Long boardId,
                                                        @Valid @RequestBody final BoardModification boardModification) {
        return boardService.updateBoard(boardId, boardModification);
    }

    @DeleteMapping("{boardId}")
    public void deleteBoard(@PathVariable("boardId") final Long boardId) {
        boardService.deleteBoard(boardId);
    }

}
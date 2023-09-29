package com.example.trello.service.impl;

import com.example.trello.mapper.CardMapper;
import com.example.trello.model.dto.card.Card;
import com.example.trello.model.dto.card.CardModification;
import com.example.trello.model.entity.board.BoardEntity;
import com.example.trello.model.entity.card.CardEntity;
import com.example.trello.repository.CardRepository;
import com.example.trello.service.BoardService;
import com.example.trello.service.CardService;
import com.example.trello.service.exception.CardException;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CardServiceImpl implements CardService {

    private final CardRepository cardRepository;

    private final BoardService boardService;

    private final CardMapper cardMapper;

    public CardServiceImpl(final CardRepository cardRepository, final BoardService boardService, final CardMapper cardMapper) {
        this.cardRepository = cardRepository;
        this.boardService = boardService;
        this.cardMapper = cardMapper;
    }

    public Card getCardById(final Long cardId) {
        return cardMapper.map(getCardEntityById(cardId));
    }

    public Card updateCard(final Long cardId, final CardModification cardModification) {
        CardEntity cardEntity = getCardEntityById(cardId);
        cardEntity.setName(cardModification.getName());
        cardEntity.setDescription(cardModification.getDescription());
        cardEntity = cardRepository.save(cardEntity);
        return cardMapper.map(cardEntity);
    }

    private CardEntity getCardEntityById(final Long cardId) {
        return cardRepository.findById(cardId).orElseThrow(CardException::new);
    }

    public List<Card> getCards(final Long boardId) {
        return cardRepository.findAllByBoardEntityIdOrderByCreatedAtDesc(boardId)
                .stream()
                .map(cardMapper::map)
                .collect(Collectors.toList());
    }

    public Card addCard(final Long boardId, final CardModification cardModification) {
        final BoardEntity boardEntity = boardService.getBoardById(boardId);
        CardEntity cardEntity = cardMapper.map(cardModification);
        cardEntity.setBoardEntity(boardEntity);
        cardEntity = cardRepository.save(cardEntity);
        return cardMapper.map(cardEntity);
    }

    public void deleteCard(final Long cardId) {
        cardRepository.deleteById(cardId);
    }

}
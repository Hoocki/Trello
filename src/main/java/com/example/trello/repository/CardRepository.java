package com.example.trello.repository;

import com.example.trello.model.entity.card.CardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CardRepository extends JpaRepository<CardEntity, Long> {

    List<CardEntity> findAllByBoardEntityIdOrderByCreatedAtDesc(Long boardId);

}

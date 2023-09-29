package com.example.trello.repository;

import com.example.trello.model.entity.board.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<BoardEntity, Long> {

    List<BoardEntity> findByOrderByNameAsc();

}

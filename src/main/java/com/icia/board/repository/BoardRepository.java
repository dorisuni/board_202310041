package com.icia.board.repository;

import com.icia.board.entity.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface BoardRepository extends JpaRepository<BoardEntity,Long> {

    @Modifying
    @Query("UPDATE BoardEntity b SET b.boardHits = b.boardHits + 1 WHERE b.id = :id")
    int incrementBoardHitsById(@Param("id") Long id);
//    @Query(value = "update board_table set board_hits=board_hits+1 where id=:id",nativeQuery = true)

}

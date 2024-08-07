package com.hackathon.sprout.domain.board.repository;

import com.hackathon.sprout.domain.board.domain.Board;
import com.hackathon.sprout.domain.board.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByBoardId(Board board);
    int countByBoardId(Board boardId);
}

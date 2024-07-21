package com.hackathon.sprout.domain.board.controller;

import com.hackathon.sprout.domain.board.dto.BoardRequest;
import com.hackathon.sprout.domain.board.dto.BoardResponse;
import com.hackathon.sprout.domain.board.dto.CommentRequest;
import com.hackathon.sprout.domain.board.dto.CommentUpdateRequest;
import com.hackathon.sprout.domain.board.service.BoardService;
import com.hackathon.sprout.domain.board.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/boards")
public class BoardController {
    private final BoardService boardService;
    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<Void> createBoard(Authentication authentication, @RequestBody BoardRequest boardRequest) {
        boardService.createBoard(authentication, boardRequest);
        return ResponseEntity.status(201).build();
    }

    @PatchMapping("/{boardId}")
    public ResponseEntity<Void> updateBoard(Authentication authentication, @PathVariable("boardId") Long boardId, @RequestBody BoardRequest boardRequest) {
        boardService.updateBoard(authentication, boardId, boardRequest);
        return ResponseEntity.status(200).build();
    }

    @DeleteMapping("/{boardId}")
    public ResponseEntity<Void> deleteBoard(Authentication authentication, @PathVariable("boardId") Long boardId) {
        boardService.deleteBoard(authentication, boardId);
        return ResponseEntity.status(204).build();
    }

    @PostMapping("/comments")
    public ResponseEntity<Void> createComment(Authentication authentication, @RequestBody CommentRequest commentRequest) {
        commentService.createComment(authentication, commentRequest);
        return ResponseEntity.status(201).build();
    }

    @PatchMapping("/comments/{commentId}")
    public ResponseEntity<Void> updateComment(Authentication authentication, @PathVariable("commentId") Long commentId, @RequestBody CommentUpdateRequest commentUpdateRequest) {
        commentService.updateComment(authentication, commentId, commentUpdateRequest);
        return ResponseEntity.status(200).build();
    }

    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<Void> deleteComment(Authentication authentication, @PathVariable("commentId") Long commentId) {
        commentService.deleteComment(authentication, commentId);
        return ResponseEntity.status(204).build();
    }

    @GetMapping("/{boardId}")
    public ResponseEntity<BoardResponse> getBoard(@PathVariable("boardId") Long boardId) {
        BoardResponse boardResponse = boardService.getBoard(boardId);
        return ResponseEntity.status(200).body(boardResponse);
    }
}

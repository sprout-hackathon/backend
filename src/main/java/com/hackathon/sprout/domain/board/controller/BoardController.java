package com.hackathon.sprout.domain.board.controller;

import com.hackathon.sprout.domain.board.dto.BoardRequest;
import com.hackathon.sprout.domain.board.service.BoardService;
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
}

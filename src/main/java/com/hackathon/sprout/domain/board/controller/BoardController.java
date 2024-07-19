package com.hackathon.sprout.domain.board.controller;

import com.hackathon.sprout.domain.board.dto.BoardRequest;
import com.hackathon.sprout.domain.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}

package com.hackathon.sprout.domain.board.dto;

import com.hackathon.sprout.domain.board.domain.Board;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class BoardResponse {
    private Long boardId;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private BoardType category;
    private Integer views;
    private Long userId;
    private Boolean isAnonymous;
    private int commentNum;

    public BoardResponse(Board board, int commentNum) {
        this.boardId = board.getBoardId();
        this.title = board.getTitle();
        this.content = board.getContent();
        this.createdAt = board.getCreatedAt();
        this.updatedAt = board.getUpdatedAt();
        this.category = board.getCategory();
        this.views = board.getViews();
        this.userId = board.getUserId().getUserId();
        this.isAnonymous = board.getIsAnonymous();
        this.commentNum = commentNum;
    }
}
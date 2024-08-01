package com.hackathon.sprout.domain.board.dto;

import com.hackathon.sprout.domain.board.domain.Board;
import com.hackathon.sprout.domain.board.enums.BoardType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class BoardResponse {
    @Schema(description = "게시글 ID", example = "1")
    private Long boardId;

    @Schema(description = "게시글 제목", example = "제목 예시")
    private String title;

    @Schema(description = "게시글 내용", example = "내용 예시")
    private String content;

    @Schema(description = "작성 시간")
    private LocalDateTime createdAt;

    @Schema(description = "수정 시간")
    private LocalDateTime updatedAt;

    @Schema(description = "카테고리", example = "REVIEW")
    private BoardType category;

    @Schema(description = "조회수", example = "100")
    private Integer views;

    @Schema(description = "작성자 ID", example = "1")
    private Long userId;

    @Schema(description = "익명 여부", example = "true")
    private Boolean isAnonymous;

    @Schema(description = "댓글 수", example = "10")
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
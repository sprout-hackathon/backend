package com.hackathon.sprout.domain.board.dto;

import com.hackathon.sprout.domain.board.domain.Comment;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CommentResponse {
    @Schema(description = "댓글 ID", example = "1")
    private Long commentId;

    @Schema(description = "댓글 내용", example = "댓글 내용 예시")
    private String content;

    @Schema(description = "작성 시간")
    private LocalDateTime createdAt;

    @Schema(description = "부모 댓글 ID", example = "null")
    private Long parentCommentId;

    public CommentResponse(Comment comment) {
        this.commentId = comment.getCommentId();
        this.content = comment.getContent();
        this.createdAt = comment.getCreatedAt();
        this.parentCommentId = comment.getParentCommentId() != null ? comment.getParentCommentId().getCommentId() : null;
    }
}

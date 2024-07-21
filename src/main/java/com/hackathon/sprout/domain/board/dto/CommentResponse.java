package com.hackathon.sprout.domain.board.dto;

import com.hackathon.sprout.domain.board.domain.Comment;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CommentResponse {
    private Long commentId;
    private String content;
    private LocalDateTime createdAt;
    private Long parentCommentId;

    public CommentResponse(Comment comment) {
        this.commentId = comment.getCommentId();
        this.content = comment.getContent();
        this.createdAt = comment.getCreatedAt();
        this.parentCommentId = comment.getParentCommentId() != null ? comment.getParentCommentId().getCommentId() : null;
    }
}
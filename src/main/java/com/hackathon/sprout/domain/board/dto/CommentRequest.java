package com.hackathon.sprout.domain.board.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommentRequest {
    @Schema(description = "댓글 내용", example = "댓글 내용 예시")
    private String content;

    @Schema(description = "게시글 ID", example = "1")
    private Long boardId;

    @Schema(description = "부모 댓글 ID", example = "null")
    private Long parentCommentId;
}
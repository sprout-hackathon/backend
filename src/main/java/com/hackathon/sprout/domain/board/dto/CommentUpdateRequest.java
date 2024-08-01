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
public class CommentUpdateRequest {
    @Schema(description = "댓글 내용", example = "수정된 댓글 내용 예시")
    private String content;
}

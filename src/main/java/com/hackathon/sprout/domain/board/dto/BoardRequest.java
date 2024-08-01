package com.hackathon.sprout.domain.board.dto;

import com.hackathon.sprout.domain.board.enums.BoardType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BoardRequest {
    @Schema(description = "게시글 제목", example = "제목 예시")
    private String title;

    @Schema(description = "게시글 내용", example = "내용 예시")
    private String content;

    @Schema(description = "카테고리", example = "REVIEW")
    private BoardType category;

    @Schema(description = "익명 여부", example = "true")
    private Boolean isAnonymous;
}

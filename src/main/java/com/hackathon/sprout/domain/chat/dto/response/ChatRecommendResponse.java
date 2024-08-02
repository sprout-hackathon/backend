package com.hackathon.sprout.domain.chat.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.util.List;

@Builder
public record ChatRecommendResponse(
        @Schema(description = "채팅 답변", example = "채팅 답변 예시")
        List<String> responseList
) {
}

package com.hackathon.sprout.domain.chat.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
public record ChatResponse(
        @Schema(description = "채팅 답변", example = "채팅 답변 예시")
        String response
) {
}

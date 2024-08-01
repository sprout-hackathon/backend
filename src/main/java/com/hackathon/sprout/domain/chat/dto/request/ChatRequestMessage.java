package com.hackathon.sprout.domain.chat.dto.request;

import com.hackathon.sprout.domain.chat.domain.ChatMessage;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
public record ChatRequestMessage(
        @Schema(description = "채팅 봇인지 여부 (assistant / user)", example = "user")
        String role,

        @Schema(description = "메시지 내용", example = "메시지 내용 예시")
        String content
) {
    public static ChatRequestMessage of(ChatMessage chatMessage) {
        return ChatRequestMessage.builder()
                .role(chatMessage.getIsBot() ? "assistant" : "user")
                .content(chatMessage.getContent())
                .build();
    }

    public static ChatRequestMessage of(String content) {
        return ChatRequestMessage.builder()
                .role("user")
                .content(content)
                .build();
    }
}

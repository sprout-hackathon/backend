package com.hackathon.sprout.domain.chat.dto.request;

import com.hackathon.sprout.domain.chat.domain.ChatMessage;
import lombok.*;

@Builder
public record ChatRequestMessage (
    String role,
    String content
){
    public static ChatRequestMessage of(ChatMessage chatMessage) {
        return ChatRequestMessage.builder()
                .role(chatMessage.getIsBot() ? "system" : "user")
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

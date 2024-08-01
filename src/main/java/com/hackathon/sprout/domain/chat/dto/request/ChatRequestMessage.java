package com.hackathon.sprout.domain.chat.dto.request;

import com.hackathon.sprout.domain.chat.domain.ChatMessage;
import com.hackathon.sprout.domain.country.domain.Language;
import lombok.*;

@Builder
public record ChatRequestMessage (
    String role,
    String content
){
    public final static String ADDITIONAL_PROMPT = " (response without markdown syntax)";

    public static ChatRequestMessage of(ChatMessage chatMessage) {
        return ChatRequestMessage.builder()
                .role(chatMessage.getIsBot() ? "assistant" : "user")
                .content(chatMessage.getContent() + ADDITIONAL_PROMPT)
                .build();
    }

    public static ChatRequestMessage of(String content) {
        return ChatRequestMessage.builder()
                .role("user")
                .content(content + ADDITIONAL_PROMPT)
                .build();
    }

    public static ChatRequestMessage of(String content, Language language) {
        return ChatRequestMessage.builder()
                .role("user")
                .content(content+" - please response to " + language.getLanguageCode() + ADDITIONAL_PROMPT)
                .build();
    }
}

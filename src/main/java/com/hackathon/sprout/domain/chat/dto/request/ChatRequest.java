package com.hackathon.sprout.domain.chat.dto.request;

import com.hackathon.sprout.domain.chat.domain.ChatRoom;
import com.hackathon.sprout.domain.country.domain.Language;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Builder
public record ChatRequest(
        Boolean hasImage,
        @Schema(description = "채팅 메시지 목록")
        List<ChatRequestMessage> messages
) {
    public static ChatRequest of(String content) {
        return ChatRequest.builder()
                .messages(List.of(ChatRequestMessage.of(content)))
                .hasImage(false)
                .build();
    }

    public static ChatRequest ofWithLanguage(String content, Language language) {
        return ChatRequest.builder()
                .messages(List.of(ChatRequestMessage.of(content, language)))
                .hasImage(false)
                .build();
    }

    public static ChatRequest of(ChatRoom chatRoom) {
        return ChatRequest.builder()
                .messages(chatRoom.getChatMessageList().stream().map(ChatRequestMessage::of).toList())
                .hasImage(false)
                .build();
    }

    public static ChatRequest of(ChatRoom chatRoom, String content) {
        List<ChatRequestMessage> messages = Stream.concat(
                chatRoom.getChatMessageList().stream().map(ChatRequestMessage::of),
                Stream.of(ChatRequestMessage.of(content))
        ).collect(Collectors.toList());

        return ChatRequest.builder()
                .messages(messages)
                .hasImage(false)
                .build();
    }

    public static ChatRequest ofWithLanguage(ChatRoom chatRoom, String content) {
        List<ChatRequestMessage> messages = Stream.concat(
                chatRoom.getChatMessageList().stream().map(ChatRequestMessage::of),
                Stream.of(ChatRequestMessage.of(content,chatRoom.getUser().getLanguageCode()))
        ).collect(Collectors.toList());

        return ChatRequest.builder()
                .messages(messages)
                .hasImage(false)
                .build();
    }
}

package com.hackathon.sprout.domain.chat.dto.request;

import com.hackathon.sprout.domain.chat.domain.ChatRoom;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Builder
public record ChatRequest(
        @Schema(description = "채팅 메시지 목록")
        List<ChatRequestMessage> messages
) {
    public static ChatRequest of(String content) {
        return ChatRequest.builder()
                .messages(List.of(ChatRequestMessage.of(content)))
                .build();
    }

    public static ChatRequest of(ChatRoom chatRoom) {
        return ChatRequest.builder()
                .messages(chatRoom.getChatMessageList().stream().map(ChatRequestMessage::of).toList())
                .build();
    }

    public static ChatRequest of(ChatRoom chatRoom, String content) {
        List<ChatRequestMessage> messages = Stream.concat(
                chatRoom.getChatMessageList().stream().map(ChatRequestMessage::of),
                Stream.of(ChatRequestMessage.of(content))
        ).collect(Collectors.toList());

        return ChatRequest.builder()
                .messages(messages)
                .build();
    }
}

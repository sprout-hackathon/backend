package com.hackathon.sprout.domain.chat.dto.response;

import com.hackathon.sprout.domain.chat.domain.ChatMessage;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ChatMessageInitResponse {
    @Schema(description = "채팅방 ID", example = "1")
    private final Long chatRoomId;

    @Schema(description = "채팅 메시지 ID", example = "1")
    private final Long chatMessageId;

    @Schema(description = "채팅 메시지 내용", example = "채팅 메시지 내용 예시")
    private final String content;

    @Schema(description = "메시지 생성 시간")
    private final LocalDateTime createdAt;

    @Schema(description = "봇 여부", example = "false")
    private final boolean isBot;

    public ChatMessageInitResponse(ChatMessage chatMessage) {
        this.chatRoomId = chatMessage.getChatRoom().getChatRoomId();
        this.chatMessageId = chatMessage.getChatMessageId();
        this.content = chatMessage.getContent();
        this.createdAt = chatMessage.getCreatedAt();
        this.isBot = chatMessage.getIsBot();
    }
}
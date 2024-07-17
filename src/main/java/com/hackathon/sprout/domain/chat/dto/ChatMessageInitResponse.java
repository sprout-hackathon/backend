package com.hackathon.sprout.domain.chat.dto;

import com.hackathon.sprout.domain.chat.domain.ChatMessage;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ChatMessageInitResponse {
    private final Long chatRoomId;

    private final Long chatMessageId;

    private final String content;

    private final LocalDateTime createdAt;

    private final boolean isBot;

    public ChatMessageInitResponse(ChatMessage chatMessage){
        this.chatRoomId = chatMessage.getChatRoom().getChatRoomId();
        this.chatMessageId = chatMessage.getChatMessageId();
        this.content = chatMessage.getContent();
        this.createdAt = chatMessage.getCreatedAt();
        this.isBot = chatMessage.getIsBot();
    }
}

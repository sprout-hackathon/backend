package com.hackathon.sprout.domain.chat.dto.response;

import com.hackathon.sprout.domain.chat.domain.ChatMessage;
import com.hackathon.sprout.domain.chat.domain.ChatRoom;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class ChatMessageResponse {
    private final Long chatMessageId;

    private final String content;

    private final LocalDateTime createdAt;

    private final boolean isBot;

    private List<String> recommendedQuestions;

    public ChatMessageResponse(ChatMessage chatMessage){
        this.chatMessageId = chatMessage.getChatMessageId();
        this.content = chatMessage.getContent();
        this.createdAt = chatMessage.getCreatedAt();
        this.isBot = chatMessage.getIsBot();
    }

    public ChatMessageResponse(ChatMessage chatMessage, List<String> recommendedQuestions){
        this.chatMessageId = chatMessage.getChatMessageId();
        this.content = chatMessage.getContent();
        this.createdAt = chatMessage.getCreatedAt();
        this.isBot = chatMessage.getIsBot();
        this.recommendedQuestions = recommendedQuestions;
    }
}

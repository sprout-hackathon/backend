package com.hackathon.sprout.domain.chat.dto.response;

import com.hackathon.sprout.domain.chat.domain.ChatMessage;
import com.hackathon.sprout.domain.chat.domain.ImageMessage;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class ImageChatMessageResponse {
    private final Long imageMessageId;

    private final String content;

    private final LocalDateTime createdAt;

    private final boolean isBot;

    private List<String> recommendedQuestions;

    public ImageChatMessageResponse(ImageMessage message){
        this.imageMessageId = message.getImageMessageId();
        this.content = message.getContent();
        this.createdAt = message.getCreatedAt();
        this.isBot = message.getIsBot();
    }

    public ImageChatMessageResponse(ImageMessage message, List<String> recommendedQuestions){
        this.imageMessageId = message.getImageMessageId();
        this.content = message.getContent();
        this.createdAt = message.getCreatedAt();
        this.isBot = message.getIsBot();
        this.recommendedQuestions = recommendedQuestions;
    }
}

package com.hackathon.sprout.domain.chat.dto.response;

import com.hackathon.sprout.domain.chat.domain.ImageMessage;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ImageChatMessageInitResponse {
    private final Long imageRoomId;

    private final Long imageMessageId;

    private final String content;

    private final LocalDateTime createdAt;

    private final boolean isBot;

    public ImageChatMessageInitResponse(ImageMessage message){
        this.imageRoomId = message.getImageRoom().getImageRoomId();
        this.imageMessageId = message.getImageMessageId();
        this.content = message.getContent();
        this.createdAt = message.getCreatedAt();
        this.isBot = message.getIsBot();
    }
}

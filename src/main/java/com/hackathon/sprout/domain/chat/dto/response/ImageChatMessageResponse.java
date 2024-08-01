package com.hackathon.sprout.domain.chat.dto.response;

import com.hackathon.sprout.domain.chat.domain.ImageMessage;
import com.hackathon.sprout.domain.file.dto.FileResponse;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class ImageChatMessageResponse {
    private final Long imageMessageId;

    private final String content;

    private final LocalDateTime createdAt;

    private final boolean isBot;

    private final List<FileResponse> fileList;

    private List<String> recommendedQuestions;

    public ImageChatMessageResponse(ImageMessage message){
        this.imageMessageId = message.getImageMessageId();
        this.content = message.getContent();
        this.createdAt = message.getCreatedAt();
        this.isBot = message.getIsBot();
        this.fileList = message.getFiles().stream().map(FileResponse::of).toList();
    }

    public ImageChatMessageResponse(ImageMessage message, List<String> recommendedQuestions){
        this.imageMessageId = message.getImageMessageId();
        this.content = message.getContent();
        this.createdAt = message.getCreatedAt();
        this.isBot = message.getIsBot();
        this.fileList = message.getFiles().stream().map(FileResponse::of).toList();
        this.recommendedQuestions = recommendedQuestions;
    }
}

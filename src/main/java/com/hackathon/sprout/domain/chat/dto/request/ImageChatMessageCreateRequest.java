package com.hackathon.sprout.domain.chat.dto.request;

import com.hackathon.sprout.domain.chat.domain.ImageMessage;
import com.hackathon.sprout.domain.chat.domain.ImageRoom;
import com.hackathon.sprout.domain.file.domain.File;

import java.util.List;

public record ImageChatMessageCreateRequest (
    String content,
    Long imageRoomId
){
    public ImageMessage toEntity(ImageRoom imageRoom, List<File> fileList) {
        return ImageMessage.builder()
                .imageRoom(imageRoom)
                .content(content)
                .isBot(false)
                .files(fileList)
                .build();
    }
}
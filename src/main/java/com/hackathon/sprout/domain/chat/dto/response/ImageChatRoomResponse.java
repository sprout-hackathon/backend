package com.hackathon.sprout.domain.chat.dto.response;

import com.hackathon.sprout.domain.chat.domain.ImageRoom;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class ImageChatRoomResponse {
    private final Long imageRoomId;

    private final String title;

    private final LocalDateTime createdAt;

    List<ImageChatMessageResponse> messages;

    public ImageChatRoomResponse(ImageRoom room){
        this.imageRoomId = room.getImageRoomId();
        this.title = room.getTitle();
        this.createdAt = room.getCreatedAt();
        this.messages = room.getImageMessageList().stream().map(ImageChatMessageResponse::new).toList();
    }
}

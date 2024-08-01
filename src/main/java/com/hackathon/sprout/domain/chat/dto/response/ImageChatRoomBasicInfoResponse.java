package com.hackathon.sprout.domain.chat.dto.response;

import com.hackathon.sprout.domain.chat.domain.ImageRoom;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ImageChatRoomBasicInfoResponse {
    private final Long imageRoomId;

    private final String title;

    private final LocalDateTime createdAt;

    public ImageChatRoomBasicInfoResponse(ImageRoom room){
        this.imageRoomId = room.getImageRoomId();
        this.title = room.getTitle();
        this.createdAt = room.getCreatedAt();
    }
}

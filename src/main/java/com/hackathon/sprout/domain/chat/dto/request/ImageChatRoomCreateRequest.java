package com.hackathon.sprout.domain.chat.dto.request;

import com.hackathon.sprout.domain.chat.domain.ImageRoom;
import com.hackathon.sprout.domain.user.domain.User;

public record ImageChatRoomCreateRequest(
        String title
){
    public ImageRoom toEntity(User user) {
        return ImageRoom.builder()
                .title(title)
                .user(user)
                .build();
    }
}

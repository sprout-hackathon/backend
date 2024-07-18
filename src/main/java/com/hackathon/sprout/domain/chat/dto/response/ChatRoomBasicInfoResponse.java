package com.hackathon.sprout.domain.chat.dto.response;

import com.hackathon.sprout.domain.chat.domain.ChatRoom;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ChatRoomBasicInfoResponse {
    private final Long chatRoomId;

    private final String title;

    private final String content;

    private final LocalDateTime createdAt;

    public ChatRoomBasicInfoResponse(ChatRoom chatRoom){
        this.chatRoomId = chatRoom.getChatRoomId();
        this.title = chatRoom.getTitle();
        this.content = chatRoom.getContent();
        this.createdAt = chatRoom.getCreatedAt();
    }
}

package com.hackathon.sprout.domain.chat.dto;

import com.hackathon.sprout.domain.chat.domain.ChatRoom;
import lombok.*;

import java.time.LocalDateTime;

@Getter
public class ChatRoomResponse {
    private final Long chatRoomId;

    private final String title;

    private final String content;

    private final LocalDateTime createdAt;

    private final Long userId;

    public ChatRoomResponse(ChatRoom chatRoom){
        this.chatRoomId = chatRoom.getChatRoomId();
        this.title = chatRoom.getTitle();
        this.content = chatRoom.getContent();
        this.createdAt = chatRoom.getCreatedAt();
        this.userId = chatRoom.getUserId();
    }
}

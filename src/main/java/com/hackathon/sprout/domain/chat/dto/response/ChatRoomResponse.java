package com.hackathon.sprout.domain.chat.dto.response;

import com.hackathon.sprout.domain.chat.domain.ChatRoom;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class ChatRoomResponse {
    private final Long chatRoomId;

    private final String title;

    private final String content;

    private final LocalDateTime createdAt;

    List<ChatMessageResponse> messages;

    public ChatRoomResponse(ChatRoom chatRoom){
        this.chatRoomId = chatRoom.getChatRoomId();
        this.title = chatRoom.getTitle();
        this.content = chatRoom.getContent();
        this.createdAt = chatRoom.getCreatedAt();
        this.messages = chatRoom.getChatMessageList().stream().map(ChatMessageResponse::new).toList();
    }
}

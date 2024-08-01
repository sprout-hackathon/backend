package com.hackathon.sprout.domain.chat.dto.response;

import com.hackathon.sprout.domain.chat.domain.ChatRoom;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ChatRoomBasicInfoResponse {
    @Schema(description = "채팅방 ID", example = "1")
    private final Long chatRoomId;

    @Schema(description = "채팅방 제목", example = "채팅방 제목 예시")
    private final String title;

    @Schema(description = "채팅방 내용", example = "채팅방 내용 예시")
    private final String content;

    @Schema(description = "채팅방 생성 시간")
    private final LocalDateTime createdAt;

    public ChatRoomBasicInfoResponse(ChatRoom chatRoom) {
        this.chatRoomId = chatRoom.getChatRoomId();
        this.title = chatRoom.getTitle();
        this.content = chatRoom.getContent();
        this.createdAt = chatRoom.getCreatedAt();
    }
}

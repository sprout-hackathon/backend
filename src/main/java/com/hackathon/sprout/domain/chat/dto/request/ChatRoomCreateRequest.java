package com.hackathon.sprout.domain.chat.dto.request;

import com.hackathon.sprout.domain.chat.domain.ChatRoom;
import com.hackathon.sprout.domain.user.domain.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChatRoomCreateRequest {
    @Schema(description = "채팅방 제목", example = "채팅방 제목 예시")
    private String title;

    public ChatRoom toEntity(String content, User user) {
        return ChatRoom.builder()
                .title(title)
                .content(content)
                .user(user)
                .build();
    }
}

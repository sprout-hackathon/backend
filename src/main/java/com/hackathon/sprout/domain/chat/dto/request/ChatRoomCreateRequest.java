package com.hackathon.sprout.domain.chat.dto.request;

import com.hackathon.sprout.domain.chat.domain.ChatRoom;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChatRoomCreateRequest {
    private String title;
    private Long userId;

    public ChatRoom toEntity(String content){
        return ChatRoom.builder()
                .title(title)
                .content(content)
                .userId(userId)
                .build();
    }
}

package com.hackathon.sprout.domain.chat.dto.request;

import com.hackathon.sprout.domain.chat.domain.ChatRoom;
import com.hackathon.sprout.domain.user.domain.User;
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
    private User user;

    public ChatRoom toEntity(String content){
        return ChatRoom.builder()
                .title(title)
                .content(content)
                .user(user)
                .build();
    }
}

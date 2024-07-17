package com.hackathon.sprout.domain.chat.dto;

import com.hackathon.sprout.domain.chat.domain.ChatMessage;
import com.hackathon.sprout.domain.chat.domain.ChatRoom;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChatMessageInsert {
    ChatRoom chatRoom;
    String sendMessage;
    String replyMessage;
}

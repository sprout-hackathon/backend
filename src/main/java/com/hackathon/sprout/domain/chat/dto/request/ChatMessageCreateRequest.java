package com.hackathon.sprout.domain.chat.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChatMessageCreateRequest {
    @Schema(description = "채팅 메시지 내용", example = "채팅 메시지 내용 예시")
    String content;

    @Schema(description = "채팅방 ID", example = "1")
    Long chatRoomId;
}

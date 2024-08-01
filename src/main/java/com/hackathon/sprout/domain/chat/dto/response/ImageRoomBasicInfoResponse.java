package com.hackathon.sprout.domain.chat.dto.response;

import com.hackathon.sprout.domain.chat.domain.ImageRoom;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class ImageRoomBasicInfoResponse {
    @Schema(description = "이미지방 ID", example = "1")
    private final Long imageRoomId;

    @Schema(description = "이미지방 제목", example = "채팅방 제목 예시")
    private final String title;

    @Schema(description = "채팅방 생성 시간")
    private final LocalDateTime createdAt;

    @Schema(description = "이미지 URL 리스트")
    private final List<String> imageUrls;

    public ImageRoomBasicInfoResponse(ImageRoom imageRoom, List<String> imageUrls) {
        this.imageRoomId = imageRoom.getImageRoomId();
        this.title = imageRoom.getTitle();
        this.createdAt = imageRoom.getCreatedAt();
        this.imageUrls = imageUrls;
    }
}

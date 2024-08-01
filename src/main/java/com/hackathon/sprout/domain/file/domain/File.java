package com.hackathon.sprout.domain.file.domain;

import com.hackathon.sprout.domain.chat.domain.ImageMessage;
import com.hackathon.sprout.global.shared.BaseTimeEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class File extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "파일 ID", example = "1")
    @Column
    private Long fileId;

    @Schema(description = "업로드된 파일명", example = "example.jpg")
    @Column
    private String uploadFileName;

    @Schema(description = "파일 URL", example = "https://example.com/files/example.jpg")
    @Column(length = 3000)
    private String url;

    @Schema(description = "파일 크기", example = "1024")
    @Column(nullable = false)
    private long fileSize;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "image_message_id")
    @Schema(description = "이미지 메시지")
    private ImageMessage imageMessage;

    @Builder(builderMethodName = "createFileBuilder")
    public File(ImageMessage imageMessage, String uploadFileName, String url, long fileSize) {
        this.imageMessage = imageMessage;
        this.uploadFileName = uploadFileName;
        this.url = url;
        this.fileSize = fileSize;

        imageMessage.getFiles().add(this);
    }
}

package com.hackathon.sprout.domain.file.domain;

import com.hackathon.sprout.domain.chat.domain.ImageMessage;
import com.hackathon.sprout.global.shared.BaseTimeEntity;
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
    @Column
    private Long fileId;

    @Column
    private String uploadFileName;

    @Column(length = 3000)
    private String url;

    @Column(nullable = false)
    private long fileSize;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "image_message_id")
    ImageMessage imageMessage;

    @Builder(builderMethodName = "createFileBuilder")
    public File(ImageMessage imageMessage, String uploadFileName, String url, long fileSize) {
        this.imageMessage = imageMessage;
        this.uploadFileName = uploadFileName;
        this.url = url;
        this.fileSize = fileSize;

        imageMessage.getFiles().add(this);
    }
}

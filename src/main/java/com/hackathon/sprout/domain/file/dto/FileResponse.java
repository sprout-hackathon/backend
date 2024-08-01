package com.hackathon.sprout.domain.file.dto;

import com.hackathon.sprout.domain.file.domain.File;
import lombok.Builder;

@Builder
public record FileResponse (
        Long fileId,
        String uploadFileName,
        String url,
        long fileSize
){
    public static FileResponse of(File file) {
        return FileResponse.builder()
                .fileId(file.getFileId())
                .uploadFileName(file.getUploadFileName())
                .url(file.getUrl())
                .fileSize(file.getFileSize())
                .build();
    }
}

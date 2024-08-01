package com.hackathon.sprout.domain.chat.dto.request;

import com.hackathon.sprout.domain.file.domain.File;
import lombok.Builder;

@Builder
public record ImageChatContent (
    String type,
    String text,
    ImageURL image_url
){
    public static ImageChatContent of(File file) {
        return ImageChatContent.builder()
                .type("image_url")
                .text(null)
                .image_url(new ImageURL(file.getUrl()))
                .build();
    }

    public static ImageChatContent of(String text) {
        return ImageChatContent.builder()
                .type("text")
                .text(text)
                .image_url(null)
                .build();
    }
}

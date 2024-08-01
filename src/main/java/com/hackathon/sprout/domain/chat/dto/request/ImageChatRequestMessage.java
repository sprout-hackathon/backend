package com.hackathon.sprout.domain.chat.dto.request;

import com.hackathon.sprout.domain.chat.domain.ChatMessage;
import com.hackathon.sprout.domain.chat.domain.ImageMessage;
import com.hackathon.sprout.domain.file.domain.File;
import lombok.Builder;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Builder
public record ImageChatRequestMessage(
    String role,
    List<ImageChatContent> content
){
    public static ImageChatRequestMessage of(ImageMessage imageMessage) {
        List<ImageChatContent> content = Stream.concat(
                Stream.of(ImageChatContent.of(imageMessage.getContent())),
                imageMessage.getFiles().stream().map(ImageChatContent::of)
        ).collect(Collectors.toList());

        return ImageChatRequestMessage.builder()
                .role(imageMessage.getIsBot() ? "assistant" : "user")
                .content(content)
                .build();
    }

    public static ImageChatRequestMessage of(String text, List<File> imageFileList) {
        List<ImageChatContent> content = Stream.concat(
                Stream.of(ImageChatContent.of(text)),
                imageFileList.stream().map(ImageChatContent::of)
        ).collect(Collectors.toList());

        return ImageChatRequestMessage.builder()
                .role("user")
                .content(content)
                .build();
    }
}

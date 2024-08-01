package com.hackathon.sprout.domain.chat.dto.request;

import com.hackathon.sprout.domain.chat.domain.ImageRoom;
import com.hackathon.sprout.domain.file.domain.File;
import lombok.Builder;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Builder
public record ImageChatRequest(
        Boolean hasImage,
        List<ImageChatRequestMessage> messages
){
    public static ImageChatRequest of(String content, List<File> imageFileList) {
        return ImageChatRequest.builder()
                .messages(List.of(ImageChatRequestMessage.of(content, imageFileList)))
                .hasImage(true)
                .build();
    }

    public static ImageChatRequest of(ImageRoom imageRoom) {
        return ImageChatRequest.builder()
                .messages(imageRoom.getImageMessageList().stream().map(ImageChatRequestMessage::of).toList())
                .hasImage(true)
                .build();
    }

    public static ImageChatRequest of(ImageRoom imageRoom, String content, List<File> imageFileList) {
        List<ImageChatRequestMessage> messages = Stream.concat(
                imageRoom.getImageMessageList().stream().map(ImageChatRequestMessage::of),
                Stream.of(ImageChatRequestMessage.of(content,imageFileList))
        ).collect(Collectors.toList());

        return ImageChatRequest.builder()
                .messages(messages)
                .hasImage(true)
                .build();
    }
}

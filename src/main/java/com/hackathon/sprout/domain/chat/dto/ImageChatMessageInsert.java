package com.hackathon.sprout.domain.chat.dto;

import com.hackathon.sprout.domain.chat.domain.ImageRoom;
import com.hackathon.sprout.domain.file.domain.File;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ImageChatMessageInsert {
    ImageRoom imageRoom;
    String sendMessage;
    List<File> imageFileList;
    String replyMessage;
}

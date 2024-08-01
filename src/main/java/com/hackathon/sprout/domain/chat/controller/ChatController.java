package com.hackathon.sprout.domain.chat.controller;

import com.hackathon.sprout.domain.chat.domain.ChatMessage;
import com.hackathon.sprout.domain.chat.domain.ImageMessage;
import com.hackathon.sprout.domain.chat.dto.SearchCondition;
import com.hackathon.sprout.domain.chat.dto.request.ChatMessageCreateRequest;
import com.hackathon.sprout.domain.chat.dto.request.ChatRoomCreateRequest;
import com.hackathon.sprout.domain.chat.dto.request.ImageChatRoomCreateRequest;
import com.hackathon.sprout.domain.chat.dto.response.*;
import com.hackathon.sprout.domain.chat.service.ChatService;
import com.hackathon.sprout.domain.chat.service.ImageChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/chats")
public class ChatController {
    private final ChatService chatService;
    private final ImageChatService imageChatService;

    @GetMapping("/rooms")
    public ResponseEntity<List<ChatRoomBasicInfoResponse>> getRoomList(@ModelAttribute SearchCondition condition){
        return ResponseEntity.status(HttpStatus.OK)
                .body(chatService.getChatRoomList(condition).stream().map(ChatRoomBasicInfoResponse::new).toList());
    }

    @PostMapping("/rooms")
    public ResponseEntity<ChatMessageInitResponse> createRoom(@RequestBody ChatRoomCreateRequest request){
        ChatMessage chatMessage = chatService.createChatRoom(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ChatMessageInitResponse(chatMessage));
    }

    @GetMapping("/rooms/{roomId}")
    public ResponseEntity<ChatRoomResponse> getRoom(@PathVariable Long roomId){
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ChatRoomResponse(chatService.getChatRoom(roomId)));
    }

    @PostMapping("/messages")
    public ResponseEntity<ChatMessageResponse> createMessage(@RequestBody ChatMessageCreateRequest request){
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ChatMessageResponse(chatService.saveChatMessage(request), chatService.chatForRecommendation(request.getContent())));
    }

    @DeleteMapping("/rooms/{roomId}")
    public ResponseEntity<Void> deleteRoom(@PathVariable Long roomId) {
        chatService.deleteChatRoom(roomId);
        return ResponseEntity.noContent().build();
    }

    /* 이미지 채팅 */

    @PostMapping(value = "/images/rooms", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ImageChatMessageInitResponse> createRoom(@RequestPart ImageChatRoomCreateRequest request, @RequestPart List<MultipartFile> fileList){
        ImageMessage message = imageChatService.createChatRoom(request,fileList);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ImageChatMessageInitResponse(message));
    }
}

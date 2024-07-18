package com.hackathon.sprout.domain.chat.controller;

import com.hackathon.sprout.domain.chat.domain.ChatMessage;
import com.hackathon.sprout.domain.chat.dto.request.ChatMessageCreateRequest;
import com.hackathon.sprout.domain.chat.dto.request.ChatRoomCreateRequest;
import com.hackathon.sprout.domain.chat.dto.response.ChatMessageInitResponse;
import com.hackathon.sprout.domain.chat.dto.response.ChatMessageResponse;
import com.hackathon.sprout.domain.chat.dto.response.ChatRoomBasicInfoResponse;
import com.hackathon.sprout.domain.chat.dto.response.ChatRoomResponse;
import com.hackathon.sprout.domain.chat.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/chat")
public class ChatController {
    private final ChatService chatService;

    @GetMapping("/room")
    public ResponseEntity<List<ChatRoomBasicInfoResponse>> getRoomList(@RequestParam String date){
        return ResponseEntity.status(HttpStatus.OK)
                .body(chatService.getChatRoomList(date).stream().map(ChatRoomBasicInfoResponse::new).toList());
    }

    @PostMapping("/room")
    public ResponseEntity<ChatMessageInitResponse> createRoom(@RequestBody ChatRoomCreateRequest request){
        ChatMessage chatMessage = chatService.createChatRoom(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ChatMessageInitResponse(chatMessage));
    }

    @GetMapping("/room/{roomId}")
    public ResponseEntity<ChatRoomResponse> getRoom(@PathVariable Long roomId){
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ChatRoomResponse(chatService.getChatRoom(roomId)));
    }

    @PostMapping("/message")
    public ResponseEntity<ChatMessageResponse> createMessage(@RequestBody ChatMessageCreateRequest request){
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ChatMessageResponse(chatService.saveChatMessage(request)));
    }

    @DeleteMapping("/room/{roomId}")
    public ResponseEntity<Void> deleteRoom(@PathVariable Long roomId) {
        chatService.deleteChatRoom(roomId);
        return ResponseEntity.noContent().build();
    }
}

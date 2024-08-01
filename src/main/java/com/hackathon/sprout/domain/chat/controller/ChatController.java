package com.hackathon.sprout.domain.chat.controller;

import com.hackathon.dto.ChatGptRequest;
import com.hackathon.sprout.domain.chat.domain.ChatMessage;
import com.hackathon.sprout.domain.chat.domain.ImageMessage;
import com.hackathon.sprout.domain.chat.dto.ChatSearchCondition;
import com.hackathon.sprout.domain.chat.dto.request.ChatMessageCreateRequest;
import com.hackathon.sprout.domain.chat.dto.request.ChatRoomCreateRequest;
import com.hackathon.sprout.domain.chat.dto.request.ImageChatMessageCreateRequest;
import com.hackathon.sprout.domain.chat.dto.request.ImageChatRoomCreateRequest;
import com.hackathon.sprout.domain.chat.dto.response.*;
import com.hackathon.sprout.domain.chat.service.ChatGptService;
import com.hackathon.sprout.domain.chat.service.ChatService;
import com.hackathon.sprout.domain.chat.service.ImageChatService;
import com.hackathon.sprout.global.error.ErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/chats")
@Tag(name = "채팅", description = "채팅 관련 API")
public class ChatController {
    private final ChatService chatService;
    private final ChatGptService chatGptService;
    private final ImageChatService imageChatService;

    @Operation(summary = "채팅방 목록 조회", description = "조건에 맞는 채팅방 목록을 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "채팅방 목록 조회 성공"),
            @ApiResponse(responseCode = "400", description = "잘못된 요청", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
    })
    @GetMapping("/rooms")
    public ResponseEntity<List<ChatRoomBasicInfoResponse>> getRoomList(@ModelAttribute ChatSearchCondition condition) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(chatService.getChatRoomList(condition).stream().map(ChatRoomBasicInfoResponse::new).toList());
    }

    @Operation(summary = "채팅방 생성", description = "새로운 채팅방을 생성합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "채팅방 생성 성공", content = @Content(schema = @Schema(implementation = ChatMessageInitResponse.class))),
            @ApiResponse(responseCode = "400", description = "잘못된 요청", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "사용자를 찾을 수 없음", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PostMapping("/rooms")
    public ResponseEntity<ChatMessageInitResponse> createRoom(@RequestBody ChatRoomCreateRequest request) {
        ChatMessage chatMessage = chatService.createChatRoom(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ChatMessageInitResponse(chatMessage));
    }

    @Operation(summary = "채팅방 조회", description = "특정 채팅방의 상세 정보를 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "채팅방 조회 성공", content = @Content(schema = @Schema(implementation = ChatRoomResponse.class))),
            @ApiResponse(responseCode = "404", description = "채팅방을 찾을 수 없음", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping("/rooms/{roomId}")
    public ResponseEntity<ChatRoomResponse> getRoom(@PathVariable Long roomId) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ChatRoomResponse(chatService.getChatRoom(roomId)));
    }

    @Operation(summary = "채팅 메시지 생성", description = "새로운 채팅 메시지를 생성합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "채팅 메시지 생성 성공", content = @Content(schema = @Schema(implementation = ChatMessageResponse.class))),
            @ApiResponse(responseCode = "400", description = "잘못된 요청", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "채팅방을 찾을 수 없음", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PostMapping("/messages")
    public ResponseEntity<ChatMessageResponse> createMessage(@RequestBody ChatMessageCreateRequest request) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ChatMessageResponse(chatService.saveChatMessage(request), chatService.chatForRecommendation(request.getContent())));
    }

    @Operation(summary = "채팅방 삭제", description = "특정 채팅방을 삭제합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "채팅방 삭제 성공"),
            @ApiResponse(responseCode = "404", description = "채팅방을 찾을 수 없음", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @DeleteMapping("/rooms/{roomId}")
    public ResponseEntity<Void> deleteRoom(@PathVariable Long roomId) {
        chatService.deleteChatRoom(roomId);
        return ResponseEntity.noContent().build();
    }

    /* 이미지 채팅 */
    @Operation(summary = "이미지방 생성", description = "새로운 이미지방을 생성합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "이미지방 생성 성공", content = @Content(schema = @Schema(implementation = ChatMessageInitResponse.class))),
            @ApiResponse(responseCode = "400", description = "잘못된 요청", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "사용자를 찾을 수 없음", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PostMapping(value = "/images/rooms", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ImageChatMessageInitResponse> createRoom(@RequestPart ImageChatRoomCreateRequest request, @RequestPart List<MultipartFile> fileList) {
        ImageMessage message = imageChatService.createChatRoom(request, fileList);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ImageChatMessageInitResponse(message));
    }

    @Operation(summary = "이미지 메시지 생성", description = "새로운 이미지방을 생성합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "이미지방 생성 성공", content = @Content(schema = @Schema(implementation = ChatMessageInitResponse.class))),
            @ApiResponse(responseCode = "400", description = "잘못된 요청", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "사용자를 찾을 수 없음", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PostMapping(value = "/images/messages", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ImageChatMessageResponse> createImageMessage(@RequestPart ImageChatMessageCreateRequest request, @RequestPart List<MultipartFile> fileList) {
        ImageMessage message = imageChatService.saveChatMessage(request, fileList);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ImageChatMessageResponse(message));
    }

    @Operation(summary = "이미지방 상세 조회", description = "이미지방 번호로 이미지방 정보를 상세 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "이미지방 상세 조회 성공"),
            @ApiResponse(responseCode = "400", description = "잘못된 요청", content = @Content(schema = @Schema(implementation = com.hackathon.sprout.global.error.ErrorResponse.class))),
    })
    @GetMapping("/images/rooms/{imageRoomId}")
    public ResponseEntity<ImageChatRoomResponse> getImageRoomList(@PathVariable Long imageRoomId) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ImageChatRoomResponse(imageChatService.getChatRoom(imageRoomId)));
    }

    @Operation(summary = "이미지방 목록 조회", description = "조건에 맞는 이미지방 목록을 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "이미지방 목록 조회 성공"),
            @ApiResponse(responseCode = "400", description = "잘못된 요청", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
    })
    @GetMapping("/images/rooms")
    public ResponseEntity<List<ImageRoomBasicInfoResponse>> getImageRoomList(@ModelAttribute ChatSearchCondition condition) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(imageChatService.getImageRoomsWithUrls(condition));
    }

    @Operation(summary = "이미지방 삭제", description = "특정 이미지방을 삭제합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "이미지방 삭제 성공"),
            @ApiResponse(responseCode = "404", description = "이미지방을 찾을 수 없음", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @DeleteMapping("/images/rooms/{imageRoomId}")
    public ResponseEntity<Void> deleteImageRoom(@PathVariable Long imageRoomId) {
        imageChatService.deleteChatRoom(imageRoomId);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "음성 번역 및 사투리 교정", description = "음성 번역 및 사투리 교정")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "음성 번역 및 사투리 교정 성공"),
            @ApiResponse(responseCode = "400", description = "잘못된 요청", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
    })
    @PostMapping(value = "/transcribe",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Mono<ResponseEntity<String>> transcribe(@RequestPart("file") MultipartFile file) throws IOException {
        if (!Objects.requireNonNull(file.getOriginalFilename()).endsWith(".wav")) {
            return Mono.just(ResponseEntity.badRequest().body("Only .wav files are allowed"));
        }

        byte[] audioFile = file.getBytes();
        String fileName = file.getOriginalFilename();
        String contentType = file.getContentType();

        return chatGptService.transcribe(audioFile, fileName, contentType)
                .map(response -> ResponseEntity.ok().body(response))
                .onErrorResume(e -> Mono.just(ResponseEntity.status(500).body("An error occurred while processing your request: " + e.getMessage())));
    }
//
//    @PostMapping
//    public Mono<ResponseEntity<String>> chat(@RequestBody ChatGptRequest request) {
//        return chatGptService.chat(request)
//                .map(response -> ResponseEntity.ok().body(response))
//                .onErrorResume(e -> Mono.just(ResponseEntity.status(500).body("An error occurred while processing your request.")));
//    }
}

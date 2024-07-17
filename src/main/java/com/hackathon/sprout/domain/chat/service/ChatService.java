package com.hackathon.sprout.domain.chat.service;

import com.hackathon.sprout.domain.chat.domain.ChatMessage;
import com.hackathon.sprout.domain.chat.domain.ChatRoom;
import com.hackathon.sprout.domain.chat.dto.ChatMessageCreateRequest;
import com.hackathon.sprout.domain.chat.dto.ChatMessageInsert;
import com.hackathon.sprout.domain.chat.dto.ChatRoomCreateRequest;
import com.hackathon.sprout.domain.chat.dto.ChatRoomResponse;
import com.hackathon.sprout.domain.chat.repository.ChatMessageRepository;
import com.hackathon.sprout.domain.chat.repository.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatService{
    private final ChatRoomRepository chatRoomRepository;
    private final ChatMessageRepository chatMessageRepository;

    public String chat(String content) {
        return "지피티 반환 내용";
    }

    public ChatMessage saveChatRoom(ChatRoomCreateRequest request) {
        String reply = chat(request.getTitle());
        ChatRoom chatRoom = chatRoomRepository.save(request.toEntity(reply));

        ChatMessageInsert chatMessageInsert = ChatMessageInsert.builder()
                .chatRoom(chatRoom)
                .replyMessage(reply)
                .sendMessage(request.getTitle())
                .build();

        return saveChatMessage(chatMessageInsert);
    }

    public ChatMessage saveChatMessage(ChatMessageCreateRequest request) {
        String reply = chat(request.getContent());
        ChatRoom chatRoom = chatRoomRepository.findById(request.getChatRoomId()).get();

        ChatMessageInsert chatMessageInsert = ChatMessageInsert.builder()
                .chatRoom(chatRoom)
                .replyMessage(reply)
                .sendMessage(request.getContent())
                .build();

        return saveChatMessage(chatMessageInsert);
    }

    public ChatMessage saveChatMessage(ChatMessageInsert dto){
        ChatMessage sendMessage = ChatMessage.builder()
                .chatRoom(dto.getChatRoom())
                .content(dto.getSendMessage())
                .isBot(false)
                .build();

        ChatMessage replyMessage = ChatMessage.builder()
                .chatRoom(dto.getChatRoom())
                .content(dto.getReplyMessage())
                .isBot(true)
                .build();

        chatMessageRepository.save(sendMessage);
        return chatMessageRepository.save(replyMessage);
    }

    public ChatRoomResponse getChatRoom(Long roomId) {
        ChatRoom chatRoom = chatRoomRepository.findById(roomId).get();
        return new ChatRoomResponse(chatRoom);
    }

    public void deleteChatRoom(Long roomId) {
        chatRoomRepository.deleteById(roomId);
    }

    public List<ChatRoom> getChatRoomList(String date) {
        return chatRoomRepository.findAll();
    }
}

package com.hackathon.sprout.domain.chat.service;

import com.hackathon.sprout.domain.chat.domain.ChatMessage;
import com.hackathon.sprout.domain.chat.domain.ChatRoom;
import com.hackathon.sprout.domain.chat.dto.SearchCondition;
import com.hackathon.sprout.domain.chat.dto.request.ChatMessageCreateRequest;
import com.hackathon.sprout.domain.chat.dto.ChatMessageInsert;
import com.hackathon.sprout.domain.chat.dto.request.ChatRequest;
import com.hackathon.sprout.domain.chat.dto.request.ChatRoomCreateRequest;
import com.hackathon.sprout.domain.chat.dto.response.ChatResponse;
import com.hackathon.sprout.domain.chat.exception.ChatRoomNotFoundException;
import com.hackathon.sprout.domain.chat.repository.ChatMessageRepository;
import com.hackathon.sprout.domain.chat.repository.ChatRoomRepository;
import com.hackathon.sprout.domain.user.exception.UserNotFoundException;
import com.hackathon.sprout.domain.user.repository.UserRepository;
import com.hackathon.sprout.global.shared.DateUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Transactional
public class ChatService{
    private final ChatRoomRepository chatRoomRepository;
    private final ChatMessageRepository chatMessageRepository;
    private final UserRepository userRepository;
    private final WebClient webClient;

    @Value("${ai.base.url}")
    public String AI_BASE_URL;

    public String chat(ChatRequest request) {
        return Objects.requireNonNull(webClient.post()
                .uri(AI_BASE_URL + "/chat")
                .bodyValue(request)
                .retrieve()
                .bodyToMono(ChatResponse.class).block()).response();
    }

    public List<String> chatForRecommendation(String message) {
        //TODO : 실제 GPT API 연동
        List<String> recommendationList = new ArrayList<>();

        recommendationList.add("요양 보호사 자격증 따는 방법");
        recommendationList.add("요양 보호사의 힘든 점");
        recommendationList.add("외국인 요양보호사가 되려면?");

        return recommendationList;
    }


    public ChatMessage createChatRoom(ChatRoomCreateRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userId = (String) authentication.getPrincipal();

        String reply = chat(ChatRequest.of(request.getTitle()));
        ChatRoom chatRoom = chatRoomRepository.save(request.toEntity(reply, userRepository.findById(userId).orElseThrow(UserNotFoundException::new)));

        ChatMessageInsert chatMessageInsert = ChatMessageInsert.builder()
                .chatRoom(chatRoom)
                .replyMessage(reply)
                .sendMessage(request.getTitle())
                .build();

        return saveChatMessage(chatMessageInsert);
    }

    public ChatMessage saveChatMessage(ChatMessageCreateRequest request) {
        ChatRoom chatRoom = chatRoomRepository.findById(request.getChatRoomId()).orElseThrow(ChatRoomNotFoundException::new);

        String reply = chat(ChatRequest.of(chatRoom,request.getContent()));

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

    @Transactional(readOnly = true)
    public ChatRoom getChatRoom(Long roomId) {
        return chatRoomRepository.findById(roomId).orElseThrow(ChatRoomNotFoundException::new);
    }

    public void deleteChatRoom(Long roomId) {
        chatRoomRepository.deleteById(roomId);
    }

    @Transactional(readOnly = true)
    public List<ChatRoom> getChatRoomList(SearchCondition condition) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userId = (String) authentication.getPrincipal();

        LocalDate localDate = DateUtil.convertToLocalDate(condition.date());
        LocalDateTime startDate = DateUtil.toStartOfDay(localDate);
        LocalDateTime endDate = DateUtil.toEndOfDay(localDate);

        return chatRoomRepository.findByUser_IdAndCreatedAtBetween(userId,startDate,endDate);
    }
}

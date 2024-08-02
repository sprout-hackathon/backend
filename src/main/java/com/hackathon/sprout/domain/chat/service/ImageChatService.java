package com.hackathon.sprout.domain.chat.service;

import com.hackathon.sprout.domain.chat.domain.ImageMessage;
import com.hackathon.sprout.domain.chat.domain.ImageRoom;
import com.hackathon.sprout.domain.chat.dto.ChatSearchCondition;
import com.hackathon.sprout.domain.chat.dto.request.ImageChatMessageCreateRequest;
import com.hackathon.sprout.domain.chat.dto.request.ImageChatRequest;
import com.hackathon.sprout.domain.chat.dto.request.ImageChatRoomCreateRequest;
import com.hackathon.sprout.domain.chat.dto.response.ChatResponse;
import com.hackathon.sprout.domain.chat.dto.response.ImageRoomBasicInfoResponse;
import com.hackathon.sprout.domain.chat.exception.ChatRoomNotFoundException;
import com.hackathon.sprout.domain.chat.repository.ImageMessageRepository;
import com.hackathon.sprout.domain.chat.repository.ImageRoomRepository;
import com.hackathon.sprout.domain.file.domain.File;
import com.hackathon.sprout.domain.file.service.FileService;
import com.hackathon.sprout.domain.user.domain.User;
import com.hackathon.sprout.domain.user.exception.UserNotFoundException;
import com.hackathon.sprout.domain.user.repository.UserRepository;
import com.hackathon.sprout.global.shared.DateUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Transactional
public class ImageChatService {
    private final ImageRoomRepository imageRoomRepository;
    private final ImageMessageRepository imageMessageRepository;
    private final UserRepository userRepository;
    private final FileService fileService;
    private final WebClient webClient;

    @Value("${ai.base.url}")
    public String AI_BASE_URL;

    public String chat(ImageChatRequest request) {
        return Objects.requireNonNull(webClient.post()
                .uri(AI_BASE_URL + "/chat")
                .bodyValue(request)
                .retrieve()
                .bodyToMono(ChatResponse.class).block()).response();
    }

    public List<String> chatForRecommendation(String message) {
        List<String> recommendationList = new ArrayList<>();
        recommendationList.add("요양 보호사 자격증 따는 방법");
        recommendationList.add("요양 보호사의 힘든 점");
        recommendationList.add("외국인 요양보호사가 되려면?");
        return recommendationList;
    }

    public ImageMessage saveChatMessage(ImageChatMessageCreateRequest request, List<MultipartFile> imageFileList) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userId = (String) authentication.getPrincipal();
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);

        ImageRoom imageRoom = imageRoomRepository.findById(request.imageRoomId()).orElseThrow(ChatRoomNotFoundException::new);

        // 이미지 채팅방에 채팅 등록
        ImageMessage sendMessage = saveChatMessage(imageRoom, request.content(), false);

        // 이미지 S3에 저장
        List<File> savedFileList = fileService.saveFiles(sendMessage.getImageMessageId(), imageFileList);

        // 이미지와 프롬프트 챗봇에게 전달 후 답변 받아옴
        String reply = chat(ImageChatRequest.ofWithLanguage(request.content(), savedFileList, user.getLanguageCode()));

        return saveChatMessage(imageRoom, reply, true);
    }

    public ImageMessage createChatRoom(ImageChatRoomCreateRequest request, List<MultipartFile> imageFileList) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userId = (String) authentication.getPrincipal();
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);

        ImageRoom imageRoom = imageRoomRepository.save(request.toEntity(user));

        ImageMessage sendMessage = saveChatMessage(imageRoom, request.title(), false);

        List<File> savedFileList = fileService.saveFiles(sendMessage.getImageMessageId(), imageFileList);

        // 이미지와 프롬프트 챗봇에게 전달 후 답변 받아옴
        String reply = chat(ImageChatRequest.ofWithLanguage(request.title(), savedFileList, user.getLanguageCode()));

        return saveChatMessage(imageRoom, reply, true);
    }

    public ImageMessage saveChatMessage(ImageRoom imageRoom, String content, Boolean isBot) {
        ImageMessage sendMessage = ImageMessage.builder()
                .imageRoom(imageRoom)
                .content(content)
                .isBot(isBot)
                .build();

        return imageMessageRepository.save(sendMessage);
    }

    @Transactional(readOnly = true)
    public ImageRoom getChatRoom(Long roomId) {
        return imageRoomRepository.findById(roomId).orElseThrow(ChatRoomNotFoundException::new);
    }

    public void deleteChatRoom(Long imageRoomId) {
        imageRoomRepository.deleteById(imageRoomId);
    }

    @Transactional(readOnly = true)
    public List<ImageRoom> getImageRoomList(ChatSearchCondition condition) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userId = (String) authentication.getPrincipal();

        LocalDate localDate = DateUtil.convertToLocalDate(condition.getDate());
        LocalDateTime startDate = DateUtil.toStartOfDay(localDate);
        LocalDateTime endDate = DateUtil.toEndOfDay(localDate);

        return imageRoomRepository.findByUser_IdAndCreatedAtBetween(userId, startDate, endDate);
    }

    @Transactional(readOnly = true)
    public List<ImageRoomBasicInfoResponse> getImageRoomsWithUrls(ChatSearchCondition condition) {
        List<ImageRoom> imageRooms = getImageRoomList(condition);
        List<ImageRoomBasicInfoResponse> responseList = new ArrayList<>();

        for (ImageRoom imageRoom : imageRooms) {
            List<String> imageUrls = new ArrayList<>();
            for (ImageMessage imageMessage : imageRoom.getImageMessageList()) {
                for (File file : imageMessage.getFiles()) {
                    imageUrls.add(file.getUrl());
                }
            }
            responseList.add(new ImageRoomBasicInfoResponse(imageRoom, imageUrls));
        }

        return responseList;
    }
}

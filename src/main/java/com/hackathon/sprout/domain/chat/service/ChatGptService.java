package com.hackathon.sprout.domain.chat.service;

import com.hackathon.dto.ChatGptRequest;
import com.hackathon.sprout.global.config.OpenAIService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ChatGptService {

    private final OpenAIService openAIService;

    public Mono<String> chat(ChatGptRequest request) {
        return openAIService.chat(request);
    }

    public Mono<String> transcribe(byte[] audioFile, String fileName, String contentType) {
        return openAIService.transcribe(audioFile, fileName, contentType)
                .flatMap(transcriptionText -> openAIService.transcribeAndTranslate(transcriptionText));
    }
}

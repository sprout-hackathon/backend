package com.hackathon.sprout.global.config;

import com.hackathon.dto.ChatGptRequest;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class OpenAIService {

    private static final Logger logger = LoggerFactory.getLogger(OpenAIService.class);

    private final WebClient openAiWebClient;

    @Value("${openai.model}")
    private String model;

    public Mono<String> chat(ChatGptRequest request) {
        logger.info("Sending chat request to OpenAI with model: {}", model);
        Map<String, Object> body = new HashMap<>();
        body.put("model", model);
        body.put("messages", request.getMessages());
        body.put("max_tokens", 1000);

        return openAiWebClient.post()
                .uri("/chat/completions")
                .bodyValue(body)
                .retrieve()
                .bodyToMono(Map.class)
                .map(response -> {
                    logger.info("Received response: {}", response);
                    List<Map<String, Object>> choices = (List<Map<String, Object>>) response.get("choices");
                    Map<String, Object> message = (Map<String, Object>) choices.get(0).get("message");
                    return (String) message.get("content");
                })
                .doOnError(e -> logger.error("Error during chat request", e));
    }

    public Mono<String> transcribe(byte[] audioFile, String fileName, String contentType) {
        logger.info("Sending transcription request to OpenAI");

        MultipartBodyBuilder builder = new MultipartBodyBuilder();
        builder.part("file", new ByteArrayResource(audioFile) {
                    @Override
                    public String getFilename() {
                        return fileName;
                    }
                }).header("Content-Disposition", "form-data; name=\"file\"; filename=\"" + fileName + "\"")
                .header("Content-Type", contentType);

        builder.part("model", "whisper-1");

        return openAiWebClient.post()
                .uri("/audio/transcriptions")
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .bodyValue(builder.build())
                .retrieve()
                .bodyToMono(Map.class)
                .map(response -> {
                    logger.info("Received response: {}", response);
                    return (String) response.get("text");
                })
                .doOnError(e -> logger.error("Error during transcription request", e));
    }

    public Mono<String> transcribeAndTranslate(String transcriptionText) {
        logger.info("Transcribing and translating text: {}", transcriptionText);
        Map<String, Object> systemMessage = new HashMap<>();
        systemMessage.put("role", "system");
        systemMessage.put("content", "당신은 한국 표준어 전문가입니다. 한국의 다양한 사투리를 고려하여 사용자에게 알맞은 표준어를 전달하세요.");

        Map<String, Object> userMessage = new HashMap<>();
        userMessage.put("role", "user");
        userMessage.put("content", "'" + transcriptionText + "'를 한국 표준어로 변환해줘");

        List<Map<String, Object>> messages = List.of(systemMessage, userMessage);

        Map<String, Object> body = new HashMap<>();
        body.put("model", model);
        body.put("messages", messages);
        body.put("max_tokens", 1000);

        return openAiWebClient.post()
                .uri("/chat/completions")
                .bodyValue(body)
                .retrieve()
                .bodyToMono(Map.class)
                .map(response -> {
                    logger.info("Received response: {}", response);
                    List<Map<String, Object>> choices = (List<Map<String, Object>>) response.get("choices");
                    Map<String, Object> message = (Map<String, Object>) choices.get(0).get("message");
                    return (String) message.get("content");
                })
                .doOnError(e -> logger.error("Error during transcribe and translate request", e));
    }
}

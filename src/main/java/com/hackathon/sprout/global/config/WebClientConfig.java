package com.hackathon.sprout.global.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {
    public static String AI_BASE_URL = "http://localhost:8000";

    @Bean
    public WebClient webClient() {
        return WebClient.builder()
                        .baseUrl(AI_BASE_URL)
                        .build();
    }
}
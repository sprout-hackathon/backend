package com.hackathon.sprout.domain.chat.dto.response;

import lombok.Builder;

@Builder
public record ChatResponse (
    String response
){}

package com.hackathon.sprout.global.jwt.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class JwtResponse {
    private final String accessToken;
    private final String refreshToken;
}
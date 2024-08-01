package com.hackathon.sprout.domain.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginRequest {
    @Schema(description = "사용자 ID", example = "user123")
    private String id;

    @Schema(description = "사용자 비밀번호", example = "password123")
    private String password;
}

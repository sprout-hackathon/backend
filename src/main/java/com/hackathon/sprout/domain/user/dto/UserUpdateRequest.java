package com.hackathon.sprout.domain.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateRequest {
    @Schema(description = "사용자 닉네임", example = "nickname123")
    private String nickname;

    @Schema(description = "국가 코드", example = "US")
    private String nationCode;

    @Schema(description = "언어 코드", example = "EN")
    private String languageCode;

    @Schema(description = "언어 능력", example = "5")
    private Byte proficiency;

    @Schema(description = "인증 여부", example = "true")
    private Boolean hasCertification;

    @Schema(description = "인증 코드", example = "cert123")
    private String certificationCode;
}

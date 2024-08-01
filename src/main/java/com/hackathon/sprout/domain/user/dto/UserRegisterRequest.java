package com.hackathon.sprout.domain.user.dto;

import com.hackathon.sprout.domain.workhistory.dto.WorkHistoryRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRegisterRequest {
    @Schema(description = "사용자 ID", example = "user123")
    private String id;

    @Schema(description = "사용자 비밀번호", example = "password123")
    private String password;

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

    @Schema(description = "근무 이력 리스트")
    private List<WorkHistoryRequest> workHistories;
}

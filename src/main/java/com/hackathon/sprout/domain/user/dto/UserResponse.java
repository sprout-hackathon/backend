package com.hackathon.sprout.domain.user.dto;

import com.hackathon.sprout.domain.application.dto.ApplicationResponse;
import com.hackathon.sprout.domain.workhistory.dto.WorkHistoryResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    @Schema(description = "사용자 ID", example = "user123")
    private String id;

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

    @Schema(description = "총 근무 연수", example = "3")
    private int totalWorkYear;

    @Schema(description = "총 근무 개월 수", example = "6")
    private int totalWorkMonth;

    @Schema(description = "지원서 목록")
    private List<ApplicationResponse> applications;

    @Schema(description = "근무 이력 목록")
    private List<WorkHistoryResponse> workHistories;

    @Schema(description = "인증 코드", example = "cert123")
    private String certificationCode;
}
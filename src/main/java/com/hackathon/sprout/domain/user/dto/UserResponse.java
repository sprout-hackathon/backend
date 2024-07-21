package com.hackathon.sprout.domain.user.dto;

import com.hackathon.sprout.domain.application.dto.ApplicationResponse;
import com.hackathon.sprout.domain.workhistory.dto.WorkHistoryResponse;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private String id;
    private String nickname;
    private String nationCode;
    private String languageCode;
    private Byte proficiency;
    private Boolean hasCertification;
    private int totalWorkYear;
    private int totalWorkMonth;
    private List<ApplicationResponse> applications;
    private List<WorkHistoryResponse> workHistories;
    private String certificationCode;
}
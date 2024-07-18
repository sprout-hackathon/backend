package com.hackathon.sprout.domain.user.dto;

import com.hackathon.sprout.domain.workhistory.dto.WorkHistoryRequest;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRegisterRequest {
    private String id;
    private String password;
    private String nickname;
    private String nationCode;
    private String languageCode;
    private Byte proficiency;
    private Boolean hasCertification;
    private String certificationCode;
    private List<WorkHistoryRequest> workHistories;
}

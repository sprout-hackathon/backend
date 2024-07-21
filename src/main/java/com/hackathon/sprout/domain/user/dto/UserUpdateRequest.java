package com.hackathon.sprout.domain.user.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateRequest {
    private String nickname;
    private String nationCode;
    private String languageCode;
    private Byte proficiency;
    private Boolean hasCertification;
    private String certificationCode;
}

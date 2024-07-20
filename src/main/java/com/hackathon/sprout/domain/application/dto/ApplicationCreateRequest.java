package com.hackathon.sprout.domain.application.dto;

import com.hackathon.sprout.domain.application.domain.Application;
import com.hackathon.sprout.domain.recruitment.domain.Recruitment;
import com.hackathon.sprout.domain.user.domain.User;
import lombok.*;

@Data
@NoArgsConstructor
public class ApplicationCreateRequest {
    Long recruitmentId;

    public Application toEntity(User user, Recruitment recruitment){
        return Application.builder()
                .user(user)
                .recruitment(recruitment)
                .build();
    }
}

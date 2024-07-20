package com.hackathon.sprout.domain.workhistory.dto;

import com.hackathon.sprout.domain.hospital.domain.Hospital;
import com.hackathon.sprout.domain.user.domain.User;
import com.hackathon.sprout.domain.workhistory.domain.WorkHistory;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WorkHistoryRequest {
    private Byte workDuration;
    private Long hospitalId;

    public WorkHistory toEntity(Hospital hospital, User user){
        return WorkHistory.builder()
                .workDuration(workDuration)
                .hospital(hospital)
                .user(user)
                .build();
    }
}

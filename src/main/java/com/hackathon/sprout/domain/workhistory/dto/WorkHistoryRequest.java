package com.hackathon.sprout.domain.workhistory.dto;

import com.hackathon.sprout.domain.hospital.domain.Hospital;
import com.hackathon.sprout.domain.user.domain.User;
import com.hackathon.sprout.domain.workhistory.domain.WorkHistory;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WorkHistoryRequest {
    @Schema(description = "근무 기간 (개월)", example = "12")
    private Byte workDuration;

    @Schema(description = "병원 ID", example = "1")
    private Long hospitalId;

    public WorkHistory toEntity(Hospital hospital, User user) {
        return WorkHistory.builder()
                .workDuration(workDuration)
                .hospital(hospital)
                .user(user)
                .build();
    }
}

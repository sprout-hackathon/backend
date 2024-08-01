package com.hackathon.sprout.domain.workhistory.dto;

import com.hackathon.sprout.domain.hospital.dto.HospitalResponse;
import com.hackathon.sprout.domain.workhistory.domain.WorkHistory;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class WorkHistoryResponse {
    @Schema(description = "근무 이력 ID", example = "1")
    private final Long workHistoryId;

    @Schema(description = "근무 기간 (개월)", example = "12")
    private final Byte workDuration;

    @Schema(description = "병원 정보")
    private final HospitalResponse hospital;

    public WorkHistoryResponse(WorkHistory workHistory) {
        this.workHistoryId = workHistory.getWorkHistoryId();
        this.workDuration = workHistory.getWorkDuration();
        this.hospital = new HospitalResponse(workHistory.getHospital());
    }
}

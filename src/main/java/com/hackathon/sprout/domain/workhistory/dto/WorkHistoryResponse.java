package com.hackathon.sprout.domain.workhistory.dto;

import com.hackathon.sprout.domain.hospital.dto.HospitalResponse;
import com.hackathon.sprout.domain.workhistory.domain.WorkHistory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class WorkHistoryResponse {
    private final Long workHistoryId;
    private final Byte workDuration;
    private final HospitalResponse hospital;

    public WorkHistoryResponse(WorkHistory workHistory){
        this.workHistoryId = workHistory.getWorkHistoryId();
        this.workDuration = workHistory.getWorkDuration();
        this.hospital = new HospitalResponse(workHistory.getHospital());
    }
}

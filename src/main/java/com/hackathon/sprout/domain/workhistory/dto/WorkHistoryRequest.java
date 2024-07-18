package com.hackathon.sprout.domain.workhistory.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WorkHistoryRequest {
    private Byte workDuration;
    private Long hospitalId;
}

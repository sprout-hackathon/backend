package com.hackathon.sprout.domain.workhistory.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record WorkHistoryUpdateRequest(
        @Schema(description = "근무 기간 (개월)", example = "12")
        Byte workDuration,

        @Schema(description = "병원 ID", example = "1")
        Long hospitalId
) {
}

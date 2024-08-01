package com.hackathon.sprout.domain.application.dto;

import com.hackathon.sprout.domain.application.enums.ApplicationState;
import io.swagger.v3.oas.annotations.media.Schema;

public record ApplicationStateResponse(
        @Schema(description = "지원 상태", example = "접수완료")
        ApplicationState state) {
    public String getName() {
        return state.getName();
    }
}

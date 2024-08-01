package com.hackathon.sprout.domain.application.dto;

import com.hackathon.sprout.domain.application.enums.ApplicationState;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ApplicationUpdateRequest {
    @Schema(description = "지원 상태", example = "접수완료")
    ApplicationState state;

    public void setState(String state) {
        this.state = ApplicationState.fromName(state);
    }
}
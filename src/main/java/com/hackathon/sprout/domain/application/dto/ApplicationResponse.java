package com.hackathon.sprout.domain.application.dto;

import com.hackathon.sprout.domain.application.domain.Application;
import com.hackathon.sprout.domain.application.enums.ApplicationState;
import com.hackathon.sprout.domain.recruitment.dto.RecruitmentResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ApplicationResponse {
    @Schema(description = "지원서 ID", example = "1")
    private Long applicationId;

    @Schema(description = "지원 상태", example = "접수완료")
    private ApplicationState state;

    @Schema(description = "채용 정보")
    private RecruitmentResponse recruitment;

    public ApplicationResponse(Application application) {
        this.applicationId = application.getApplicationId();
        this.state = application.getState();
        this.recruitment = new RecruitmentResponse(application.getRecruitment());
    }

    public String getState() {
        return state.getName();
    }
}

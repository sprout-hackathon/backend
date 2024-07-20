package com.hackathon.sprout.domain.application.dto;

import com.hackathon.sprout.domain.application.domain.Application;
import com.hackathon.sprout.domain.application.enums.ApplicationState;
import com.hackathon.sprout.domain.recruitment.dto.RecruitmentResponse;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ApplicationResponse {
    private Long applicationId;

    private ApplicationState state;

    private RecruitmentResponse recruitment;

    public ApplicationResponse(Application application){
        this.applicationId = application.getApplicationId();
        this.state = application.getState();
        this.recruitment = new RecruitmentResponse(application.getRecruitment());
    }

    public String getState(){
        return state.getName();
    }
}

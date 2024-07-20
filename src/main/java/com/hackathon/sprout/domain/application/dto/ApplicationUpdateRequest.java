package com.hackathon.sprout.domain.application.dto;

import com.hackathon.sprout.domain.application.enums.ApplicationState;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ApplicationUpdateRequest {
    ApplicationState state;

    public void setState(String state){
        this.state = ApplicationState.fromName(state);
    }
}
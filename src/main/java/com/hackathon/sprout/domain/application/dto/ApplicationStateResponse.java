package com.hackathon.sprout.domain.application.dto;

import com.hackathon.sprout.domain.application.enums.ApplicationState;

public record ApplicationStateResponse(ApplicationState state) {
    public String getName() {
        return state.getName();
    }
}

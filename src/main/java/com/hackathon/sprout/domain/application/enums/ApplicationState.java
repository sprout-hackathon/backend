package com.hackathon.sprout.domain.application.enums;

import lombok.Getter;

@Getter
public enum ApplicationState{
    RECEIVED("접수완료"),
    IN_PROGRESS("심사중"),
    ACCEPTED("합격"),
    REJECTED("불합격");

    private final String name;
    ApplicationState(String name){
        this.name = name;
    }

    // name으로 조회하는 정적 메서드 추가
    public static ApplicationState fromName(String name) {
        for (ApplicationState state : ApplicationState.values()) {
            if (state.getName().equals(name)) {
                return state;
            }
        }
        throw new IllegalArgumentException("No enum constant with name " + name);
    }
}
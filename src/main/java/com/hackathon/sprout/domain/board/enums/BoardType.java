package com.hackathon.sprout.domain.board.enums;

import lombok.Getter;

@Getter
public enum BoardType {
    REVIEW("후기"),
    INFORMATION("정보"),
    QUESTION("Q&A");

    private final String name;

    BoardType(String name) {
        this.name = name;
    }

    // name으로 조회하는 정적 메서드 추가
    public static BoardType fromName(String name) {
        for (BoardType type : BoardType.values()) {
            if (type.getName().equals(name)) {
                return type;
            }
        }
        throw new IllegalArgumentException("No enum constant with name " + name);
    }
}
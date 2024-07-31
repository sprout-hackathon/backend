package com.hackathon.sprout.global.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    INTERNAL_SERVER_ERROR(500, "INTERNAL_SERVER_ERROR", "서버 오류가 발생했습니다."),
    INVALID_TOKEN(401, "INVALID_TOKEN", "유효하지 않은 토큰입니다."),
    EXPIRED_TOKEN(403, "EXPIRED_TOKEN", "만료된 토큰입니다."),
    INVALID_NATION_CODE(400, "INVALID_NATION_CODE", "유효하지 않은 국가 코드입니다."),
    INVALID_LANGUAGE_CODE(400, "INVALID_LANGUAGE_CODE", "유효하지 않은 언어 코드입니다."),
    INVALID_HOSPITAL_ID(400, "INVALID_HOSPITAL_ID", "유효하지 않은 병원 ID입니다."),
    USER_NOT_FOUND(404, "USER_NOT_FOUND", "사용자를 찾을 수 없습니다."),
    BOARD_NOT_FOUND(404, "BOARD_NOT_FOUND", "게시판을 찾을 수 없습니다."),
    COMMENT_NOT_FOUND(404, "COMMENT_NOT_FOUND", "댓글을 찾을 수 없습니다."),
    INVALID_PASSWORD(401, "INVALID_PASSWORD", "비밀번호가 틀렸습니다."),
    CHAT_ROOM_NOT_FOUND(400, "CHAT_ROOM_NOT_FOUND", "채팅방을 찾을 수 없습니다. chatRoomId를 확인해주세요");


    private final int httpStatus;
    private final String code;
    private final String message;
}
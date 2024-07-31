package com.hackathon.sprout.domain.chat.exception;

import com.hackathon.sprout.global.error.ErrorCode;
import com.hackathon.sprout.global.error.ServiceException;
import io.jsonwebtoken.ExpiredJwtException;

public class ChatRoomNotFoundException extends ServiceException {
    public ChatRoomNotFoundException() {
        super(ErrorCode.CHAT_ROOM_NOT_FOUND);
    }
    public ChatRoomNotFoundException(Exception e) {
        super(ErrorCode.CHAT_ROOM_NOT_FOUND, e);
    }
}

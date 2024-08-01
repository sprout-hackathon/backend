package com.hackathon.sprout.domain.chat.exception;

import com.hackathon.sprout.global.error.ErrorCode;
import com.hackathon.sprout.global.error.ServiceException;

public class ImageRoomNotFoundException extends ServiceException {
    public ImageRoomNotFoundException() {
        super(ErrorCode.IMAGE_ROOM_NOT_FOUND);
    }
    public ImageRoomNotFoundException(Exception e) {
        super(ErrorCode.IMAGE_ROOM_NOT_FOUND, e);
    }
}

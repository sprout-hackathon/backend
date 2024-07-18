package com.hackathon.sprout.domain.user.exception;

import com.hackathon.sprout.global.error.ErrorCode;
import com.hackathon.sprout.global.error.ServiceException;

public class InvalidPasswordException extends ServiceException {
    public InvalidPasswordException() {
        super(ErrorCode.INVALID_PASSWORD);
    }
}

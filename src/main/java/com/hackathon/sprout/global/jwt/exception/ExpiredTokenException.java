package com.hackathon.sprout.global.jwt.exception;

import com.hackathon.sprout.global.error.ErrorCode;
import com.hackathon.sprout.global.error.ServiceException;
import io.jsonwebtoken.ExpiredJwtException;

public class ExpiredTokenException extends ServiceException {
    public ExpiredTokenException() {
        super(ErrorCode.EXPIRED_TOKEN);
    }

    public ExpiredTokenException(ExpiredJwtException e) {
        super(ErrorCode.EXPIRED_TOKEN, e);
    }
}

package com.hackathon.sprout.domain.country.exception;

import com.hackathon.sprout.global.error.ErrorCode;
import com.hackathon.sprout.global.error.ServiceException;

public class InvalidNationCodeException extends ServiceException {
    public InvalidNationCodeException() {
        super(ErrorCode.INVALID_NATION_CODE);
    }
}
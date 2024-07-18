package com.hackathon.sprout.domain.country.exception;

import com.hackathon.sprout.global.error.ErrorCode;
import com.hackathon.sprout.global.error.ServiceException;

public class InvalidLanguageCodeException extends ServiceException {
    public InvalidLanguageCodeException() {
        super(ErrorCode.INVALID_LANGUAGE_CODE);
    }
}
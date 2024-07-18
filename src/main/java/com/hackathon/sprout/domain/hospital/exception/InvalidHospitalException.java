package com.hackathon.sprout.domain.hospital.exception;

import com.hackathon.sprout.global.error.ErrorCode;
import com.hackathon.sprout.global.error.ServiceException;

public class InvalidHospitalException extends ServiceException {
    public InvalidHospitalException() {
        super(ErrorCode.INVALID_HOSPITAL_ID);
    }
}
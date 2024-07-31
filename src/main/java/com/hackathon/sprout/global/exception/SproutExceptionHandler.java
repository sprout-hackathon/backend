package com.hackathon.sprout.global.exception;

import com.hackathon.sprout.global.error.ErrorResponse;
import com.hackathon.sprout.global.error.ServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class SproutExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> exceptionHandle(Exception e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<ErrorResponse> exceptionHandle(ServiceException e) {
        return ResponseEntity.status(e.getErrorCode().getHttpStatus()).body(new ErrorResponse(e.getErrorCode()));
    }
}

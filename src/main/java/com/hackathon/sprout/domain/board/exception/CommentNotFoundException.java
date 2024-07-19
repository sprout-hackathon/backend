package com.hackathon.sprout.domain.board.exception;

import com.hackathon.sprout.global.error.ErrorCode;
import com.hackathon.sprout.global.error.ServiceException;

public class CommentNotFoundException extends ServiceException {
    public CommentNotFoundException() {
        super(ErrorCode.COMMENT_NOT_FOUND);
    }
}

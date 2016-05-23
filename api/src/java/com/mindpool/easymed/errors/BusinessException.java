package com.mindpool.easymed.errors;

/**
 * Created by federico on 5/22/16.
 */
public class BusinessException extends RuntimeException{

    private Integer errorCode;

    public BusinessException(String message, Integer errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

}

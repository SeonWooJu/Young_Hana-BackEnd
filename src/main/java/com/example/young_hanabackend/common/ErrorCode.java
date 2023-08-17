package com.example.young_hanabackend.common;

public enum ErrorCode {
    GLOBAL(2),
    ABNORMAL_ACTION(3),

    AUTHENTICATION(10),
    JWT_TOKEN_EXPIRED(11),
    AUTHORIZATION(14),
    ORIGINAL_IS_EQUAL(15),
    DEPENDENCY(15),

    INSERT_FAILED(16),
    UPDATE_FAILED(17),
    DELETE_FAILED(18),
    COPY_FAILED(19),

    REQUEST_BODY_NULL(20);
    ;
    private int errorCode;

    private ErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }

}

package com.jn.xingdaba.customer.infrastructure.exception;

public enum WechatAppletError implements CustomerError {

    CODE2SESSION_ERROR(1000, "登录凭证校验失败")
    ;

    private final int errorCode;
    private final String errorMessage;

    WechatAppletError(int errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    @Override
    public int getErrorCode() {
        return errorCode;
    }

    @Override
    public String getErrorMessage() {
        return errorMessage;
    }
}

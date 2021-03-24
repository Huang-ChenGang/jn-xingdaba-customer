package com.jn.xingdaba.customer.infrastructure.exception;

public enum WechatAppletError implements CustomerError {

    CODE2SESSION_ERROR(1000, "登录凭证校验失败"),
    GET_ACCESS_TOKEN_ERROR(1100, "获取微信小程序后台接口调用凭据失败")
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

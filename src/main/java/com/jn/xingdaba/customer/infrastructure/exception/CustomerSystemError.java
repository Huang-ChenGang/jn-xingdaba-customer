package com.jn.xingdaba.customer.infrastructure.exception;

public enum CustomerSystemError implements CustomerError {
    BAD_REQUEST(400, "请求参数错误"),
    CUSTOMER_SYSTEM_ERROR(500, "客户服务系统异常")
    ;

    private final int errorCode;
    private final String errorMessage;

    CustomerSystemError(int errorCode, String errorMessage) {
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

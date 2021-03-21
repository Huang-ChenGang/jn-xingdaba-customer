package com.jn.xingdaba.customer.infrastructure.exception;

public enum CustomerSystemError implements CustomerError {
    BAD_REQUEST(400, "请求参数错误"),
    CUSTOMER_SYSTEM_ERROR(500, "客户服务系统异常"),
    CUSTOMER_NOT_FOUND(1000, "客户不存在"),
    GET_WECHAT_PHONE_ERROR(1100, "获取微信小程序客户手机号失败"),
    COUPON_NOT_DEFINE(1200, "优惠券未定义")
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

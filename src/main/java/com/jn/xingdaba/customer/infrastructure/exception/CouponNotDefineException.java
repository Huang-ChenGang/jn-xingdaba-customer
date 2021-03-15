package com.jn.xingdaba.customer.infrastructure.exception;

import com.jn.core.exception.JNError;

import javax.validation.constraints.NotNull;

import static com.jn.xingdaba.customer.infrastructure.exception.CustomerSystemError.COUPON_NOT_DEFINE;

public class CouponNotDefineException extends WechatAppletException {
    public CouponNotDefineException() {
        this(COUPON_NOT_DEFINE);
    }

    public CouponNotDefineException(@NotNull JNError error) {
        super(error);
    }
}

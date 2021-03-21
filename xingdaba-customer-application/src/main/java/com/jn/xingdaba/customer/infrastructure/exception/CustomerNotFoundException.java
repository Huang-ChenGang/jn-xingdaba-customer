package com.jn.xingdaba.customer.infrastructure.exception;

import com.jn.core.exception.JNError;

import javax.validation.constraints.NotNull;

import static com.jn.xingdaba.customer.infrastructure.exception.CustomerSystemError.CUSTOMER_NOT_FOUND;

public class CustomerNotFoundException extends WechatAppletException {
    public CustomerNotFoundException() {
        this(CUSTOMER_NOT_FOUND);
    }

    public CustomerNotFoundException(@NotNull JNError error) {
        super(error);
    }
}

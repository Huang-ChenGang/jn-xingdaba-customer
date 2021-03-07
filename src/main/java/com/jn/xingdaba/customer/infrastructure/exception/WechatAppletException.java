package com.jn.xingdaba.customer.infrastructure.exception;

import com.jn.core.exception.JNError;
import com.jn.core.exception.JNException;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class WechatAppletException extends JNException {
    public WechatAppletException(@NotNull JNError error) {
        super(error);
    }

    public WechatAppletException(@NotNull JNError error, Throwable cause) {
        super(error, cause);
    }

    public WechatAppletException(@NotNull JNError error, @NotBlank String message) {
        super(error, message);
    }
}

package com.jn.xingdaba.customer.infrastructure.exception;

import com.jn.core.exception.JNError;

public interface CustomerError extends JNError {
    default int getServiceCode() {
        return 5;
    }
}

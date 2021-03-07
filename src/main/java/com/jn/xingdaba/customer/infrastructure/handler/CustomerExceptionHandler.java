package com.jn.xingdaba.customer.infrastructure.handler;

import com.jn.core.api.ServerResponse;
import com.jn.core.exception.JNException;
import com.jn.xingdaba.customer.infrastructure.exception.WechatAppletException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;

import static com.jn.xingdaba.customer.infrastructure.exception.CustomerSystemError.BAD_REQUEST;
import static com.jn.xingdaba.customer.infrastructure.exception.CustomerSystemError.CUSTOMER_SYSTEM_ERROR;

@Slf4j
@RestControllerAdvice
public class CustomerExceptionHandler extends ResponseEntityExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({
            ConstraintViolationException.class
    })
    public ServerResponse<Void> handleViolationException(ConstraintViolationException exception) {
        return ServerResponse.error(BAD_REQUEST, exception.getMessage());
    }

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler({
            WechatAppletException.class
    })
    public ServerResponse<Void> handleLogicError(JNException exception) {
        log.error("customer system logic error", exception);
        return ServerResponse.error(exception.getJNError());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({
            IllegalStateException.class,
            IllegalArgumentException.class
    })
    public ServerResponse<Void> handleSystemError(RuntimeException exception) {
        log.error("customer system error", exception);
        return ServerResponse.error(CUSTOMER_SYSTEM_ERROR);
    }
}

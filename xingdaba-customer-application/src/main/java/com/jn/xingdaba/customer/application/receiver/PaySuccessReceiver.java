package com.jn.xingdaba.customer.application.receiver;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jn.xingdaba.customer.infrastructure.exception.WechatAppletException;
import com.jn.xingdaba.pay.api.PaySuccessMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import static com.jn.xingdaba.customer.infrastructure.exception.WechatAppletError.HANDLE_PAY_SUCCESS_ERROR;

@Slf4j
@Component
public class PaySuccessReceiver {
    private final ObjectMapper objectMapper;

    public PaySuccessReceiver(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @RabbitListener(bindings = @QueueBinding(
            exchange = @Exchange("PaySuccess"),
            key = "WechatApplet",
            value = @Queue("PaySuccessToCustomer")
    ))
    public void handleMessage(String message) {
        log.info("pay success message from pay center: {}", message);
        PaySuccessMessage paySuccessMessage;
        try {
            paySuccessMessage = objectMapper.readValue(message, PaySuccessMessage.class);
        } catch (JsonProcessingException e) {
            log.error("format message from pay center error.", e);
            throw new WechatAppletException(HANDLE_PAY_SUCCESS_ERROR, e.getMessage());
        }

    }
}

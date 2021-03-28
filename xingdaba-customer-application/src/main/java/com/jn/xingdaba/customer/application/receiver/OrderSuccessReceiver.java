package com.jn.xingdaba.customer.application.receiver;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jn.xingdaba.customer.application.dto.CouponDefineDto;
import com.jn.xingdaba.customer.application.service.CouponDefineService;
import com.jn.xingdaba.customer.application.service.CustomerCouponService;
import com.jn.xingdaba.customer.infrastructure.exception.WechatAppletException;
import com.jn.xingdaba.order.api.QuoteSuccessMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import static com.jn.xingdaba.customer.infrastructure.exception.WechatAppletError.HANDLE_QUOTE_SUCCESS_ERROR;

@Slf4j
@Component
public class OrderSuccessReceiver {
    private final ObjectMapper objectMapper;
    private final CouponDefineService couponDefineService;
    private final CustomerCouponService customerCouponService;

    public OrderSuccessReceiver(ObjectMapper objectMapper,
                                CouponDefineService couponDefineService,
                                CustomerCouponService customerCouponService) {
        this.objectMapper = objectMapper;
        this.couponDefineService = couponDefineService;
        this.customerCouponService = customerCouponService;
    }

    @RabbitListener(bindings = @QueueBinding(
            exchange = @Exchange("QuoteSuccess"),
            key = "QuoteSuccess",
            value = @Queue("QuoteSuccessToCustomer")
    ))
    public void handleMessage(String message) {
        log.info("quote success message from order center: {}", message);
        QuoteSuccessMessage quoteSuccessMessage;
        try {
            quoteSuccessMessage = objectMapper.readValue(message, QuoteSuccessMessage.class);
        } catch (JsonProcessingException e) {
            log.error("format message from order center error.", e);
            throw new WechatAppletException(HANDLE_QUOTE_SUCCESS_ERROR, e.getMessage());
        }

        CouponDefineDto minusCoupon = couponDefineService.findMinusCoupon(quoteSuccessMessage.getQuoteAmount());
        if (minusCoupon != null) {
            customerCouponService.sendMinusCoupon(quoteSuccessMessage.getCustomerId(), CouponDefineDto.toModel(minusCoupon));
        }
    }
}

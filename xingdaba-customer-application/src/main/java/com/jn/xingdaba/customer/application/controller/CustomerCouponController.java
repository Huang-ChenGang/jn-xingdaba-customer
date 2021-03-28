package com.jn.xingdaba.customer.application.controller;

import com.jn.core.api.ServerResponse;
import com.jn.xingdaba.customer.api.CustomerFitCouponResponseData;
import com.jn.xingdaba.customer.application.service.CustomerCouponService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Slf4j
@Validated
@RestController
@RequestMapping("/customer-coupons")
public class CustomerCouponController {
    private final CustomerCouponService service;

    public CustomerCouponController(CustomerCouponService service) {
        this.service = service;
    }

    @GetMapping("/fit")
    public ServerResponse<CustomerFitCouponResponseData> findFitCoupon(@NotBlank String customerId, @NotNull BigDecimal conditionAmount) {
        log.info("find customer fit coupon for customerId: {}, conditionAmount: {}", customerId, conditionAmount);
        return ServerResponse.success(CustomerFitCouponResponseData.fromDto(service.findFitCoupon(customerId, conditionAmount)));
    }
}

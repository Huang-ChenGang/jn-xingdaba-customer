package com.jn.xingdaba.customer.application.service;

import com.jn.xingdaba.customer.application.dto.CustomerCouponDto;
import com.jn.xingdaba.customer.domain.model.CouponDefine;

import java.math.BigDecimal;

public interface CustomerCouponService {
    void sendRegisterCoupon(String customerId, CouponDefine couponDefine);

    void sendMinusCoupon(String customerId, CouponDefine couponDefine);

    CustomerCouponDto findFitCoupon(String customerId, BigDecimal conditionAmount);

    void useCoupon(String id);
}

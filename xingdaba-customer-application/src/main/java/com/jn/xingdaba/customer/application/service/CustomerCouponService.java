package com.jn.xingdaba.customer.application.service;

import com.jn.xingdaba.customer.domain.model.CouponDefine;

public interface CustomerCouponService {
    void sendRegisterCoupon(String customerId, CouponDefine couponDefine);

    void sendMinusCoupon(String customerId, CouponDefine couponDefine);
}

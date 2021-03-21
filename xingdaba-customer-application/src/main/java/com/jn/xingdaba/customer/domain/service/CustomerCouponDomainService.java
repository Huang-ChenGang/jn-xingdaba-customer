package com.jn.xingdaba.customer.domain.service;

import com.jn.xingdaba.customer.domain.model.CustomerCoupon;

public interface CustomerCouponDomainService {
    String save(CustomerCoupon model);

    boolean hasRegisterCoupon(String customerId);
}

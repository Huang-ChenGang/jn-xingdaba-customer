package com.jn.xingdaba.customer.domain.service;

import com.jn.xingdaba.customer.domain.model.CustomerCoupon;

import java.math.BigDecimal;
import java.util.List;

public interface CustomerCouponDomainService {
    String save(CustomerCoupon model);

    boolean hasRegisterCoupon(String customerId);

    boolean hasMinusCoupon(String customerId, BigDecimal conditionAmount, BigDecimal valueAmount);

    CustomerCoupon findFitCoupon(String customerId, BigDecimal conditionAmount);

    CustomerCoupon findById(String id);

    List<CustomerCoupon> findByCustomerIdAndCouponState(String customerId, String couponState);
}

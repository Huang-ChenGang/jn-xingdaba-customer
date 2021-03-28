package com.jn.xingdaba.customer.domain.repository;

import com.jn.xingdaba.customer.domain.model.CustomerCoupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface CustomerCouponRepository extends JpaRepository<CustomerCoupon, String>, JpaSpecificationExecutor<CustomerCoupon> {
    Optional<CustomerCoupon> findByCustomerIdAndGiveTypeAndCouponStateAndIsDelete(String customerId, String giveType, String couponState, String isDelete);

    Optional<CustomerCoupon> findByCustomerIdAndGiveTypeAndConditionAmountAndValueAmountAndCouponStateAndIsDelete(String customerId, String giveType, BigDecimal conditionAmount, BigDecimal valueAmount, String couponState, String isDelete);

    Optional<CustomerCoupon> findFirstByCustomerIdAndConditionAmountLessThanEqualAndCouponStateAndIsDeleteOrderByConditionAmountDesc(String customerId, BigDecimal conditionAmount, String couponState, String isDelete);

    List<CustomerCoupon> findByCustomerIdAndCouponState(String customerId, String couponState);
}

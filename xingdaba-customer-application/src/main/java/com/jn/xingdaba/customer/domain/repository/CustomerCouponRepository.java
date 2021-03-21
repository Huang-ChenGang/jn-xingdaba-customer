package com.jn.xingdaba.customer.domain.repository;

import com.jn.xingdaba.customer.domain.model.CustomerCoupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface CustomerCouponRepository extends JpaRepository<CustomerCoupon, String>, JpaSpecificationExecutor<CustomerCoupon> {
    Optional<CustomerCoupon> findByCustomerIdAndGiveTypeAndIsDelete(String customerId, String giveType, String isDelete);
}

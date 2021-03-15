package com.jn.xingdaba.customer.domain.repository;

import com.jn.xingdaba.customer.domain.model.CouponDefine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface CouponDefineRepository extends JpaRepository<CouponDefine, String>, JpaSpecificationExecutor<CouponDefine> {
    Optional<CouponDefine> findByGiveType(String giveType);
}

package com.jn.xingdaba.customer.domain.service;

import com.jn.xingdaba.customer.domain.model.CouponDefine;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.List;

public interface CouponDefineDomainService {
    Page<CouponDefine> findAll(Pageable pageable);

    CouponDefine findByGiveType(String giveType);

    CouponDefine findMinusCoupon(BigDecimal conditionAmount);

    String save(CouponDefine model);

    void deleteOrRestore(List<String> ids);
}

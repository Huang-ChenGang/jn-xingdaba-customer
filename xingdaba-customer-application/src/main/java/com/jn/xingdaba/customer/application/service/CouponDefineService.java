package com.jn.xingdaba.customer.application.service;

import com.jn.xingdaba.customer.api.CouponDefineRequestData;
import com.jn.xingdaba.customer.application.dto.CouponDefineDto;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;

public interface CouponDefineService {
    Page<CouponDefineDto> findAll(CouponDefineRequestData requestData);

    CouponDefineDto findMinusCoupon(BigDecimal conditionAmount);

    String save(CouponDefineDto dto);
}

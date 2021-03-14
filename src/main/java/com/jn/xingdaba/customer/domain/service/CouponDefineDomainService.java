package com.jn.xingdaba.customer.domain.service;

import com.jn.xingdaba.customer.domain.model.CouponDefine;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CouponDefineDomainService {
    Page<CouponDefine> findAll(Pageable pageable);
}

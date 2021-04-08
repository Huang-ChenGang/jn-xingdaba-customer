package com.jn.xingdaba.customer.application.service;

import com.jn.xingdaba.customer.api.CouponDefineRequestData;
import com.jn.xingdaba.customer.application.dto.CouponDefineDto;
import com.jn.xingdaba.customer.domain.service.CouponDefineDomainService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Arrays;

@Slf4j
@Service
public class CouponDefineServiceImpl implements CouponDefineService {
    private final CouponDefineDomainService domainService;

    public CouponDefineServiceImpl(CouponDefineDomainService domainService) {
        this.domainService = domainService;
    }

    @Override
    public Page<CouponDefineDto> findAll(CouponDefineRequestData requestData) {
        log.info("find all coupon define for: {}", requestData);
        Pageable pageable = PageRequest.of(requestData.getPageNo(), requestData.getPageSize());
        return domainService.findAll(pageable).map(CouponDefineDto::fromModel);
    }

    @Override
    public CouponDefineDto findMinusCoupon(BigDecimal conditionAmount) {
        return CouponDefineDto.fromModel(domainService.findMinusCoupon(conditionAmount));
    }

    @Override
    public String save(CouponDefineDto dto) {
        return domainService.save(CouponDefineDto.toModel(dto));
    }

    @Override
    public void deleteOrRestore(String ids) {
        log.info("delete or restore coupon define for: {}", ids);
        domainService.deleteOrRestore(Arrays.asList(ids.split(",")));
    }
}

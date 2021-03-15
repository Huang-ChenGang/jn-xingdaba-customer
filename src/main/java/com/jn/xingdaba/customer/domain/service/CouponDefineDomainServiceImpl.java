package com.jn.xingdaba.customer.domain.service;

import com.jn.xingdaba.customer.domain.model.CouponDefine;
import com.jn.xingdaba.customer.domain.repository.CouponDefineRepository;
import com.jn.xingdaba.customer.infrastructure.exception.CouponNotDefineException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CouponDefineDomainServiceImpl implements CouponDefineDomainService {
    private final CouponDefineRepository repository;

    public CouponDefineDomainServiceImpl(CouponDefineRepository repository) {
        this.repository = repository;
    }

    @Override
    public Page<CouponDefine> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public CouponDefine findByGiveType(String giveType) {
        return repository.findByGiveType(giveType).orElseThrow(CouponNotDefineException::new);
    }
}

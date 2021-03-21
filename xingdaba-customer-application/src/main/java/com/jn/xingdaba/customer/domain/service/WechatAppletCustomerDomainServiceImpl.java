package com.jn.xingdaba.customer.domain.service;

import com.jn.core.builder.KeyBuilder;
import com.jn.xingdaba.customer.domain.model.WechatAppletCustomer;
import com.jn.xingdaba.customer.domain.repository.WechatAppletCustomerRepository;
import com.jn.xingdaba.customer.infrastructure.exception.CustomerNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Optional;

@Slf4j
@Service
public class WechatAppletCustomerDomainServiceImpl implements WechatAppletCustomerDomainService {
    private final WechatAppletCustomerRepository repository;
    private final KeyBuilder keyBuilder;

    public WechatAppletCustomerDomainServiceImpl(WechatAppletCustomerRepository repository, KeyBuilder keyBuilder) {
        this.repository = repository;
        this.keyBuilder = keyBuilder;
    }

    @Override
    public Optional<WechatAppletCustomer> findByOpenId(String openId) {
        return repository.findByOpenId(openId);
    }

    @Override
    public WechatAppletCustomer save(WechatAppletCustomer model) {
        if (StringUtils.isEmpty(model.getId())) {
            model.setId(keyBuilder.getUniqueKey());
        }
        if (StringUtils.isEmpty(model.getIsDelete())) {
            model.setIsDelete("0");
        }

        return repository.save(model);
    }

    @Override
    public WechatAppletCustomer findById(String id) {
        return repository.findById(id).orElseThrow(CustomerNotFoundException::new);
    }
}

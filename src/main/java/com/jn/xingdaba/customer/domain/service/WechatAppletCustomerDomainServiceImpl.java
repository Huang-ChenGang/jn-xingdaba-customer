package com.jn.xingdaba.customer.domain.service;

import com.jn.xingdaba.customer.domain.model.WechatAppletCustomer;
import com.jn.xingdaba.customer.domain.repository.WechatAppletCustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class WechatAppletCustomerDomainServiceImpl implements WechatAppletCustomerDomainService {
    private final WechatAppletCustomerRepository repository;

    public WechatAppletCustomerDomainServiceImpl(WechatAppletCustomerRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<WechatAppletCustomer> findByOpenId(String openId) {
        return repository.findByOpenId(openId);
    }

    @Override
    public WechatAppletCustomer save(WechatAppletCustomer model) {
        return repository.save(model);
    }
}

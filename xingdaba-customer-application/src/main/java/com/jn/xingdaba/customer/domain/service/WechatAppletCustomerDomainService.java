package com.jn.xingdaba.customer.domain.service;

import com.jn.xingdaba.customer.domain.model.WechatAppletCustomer;

import java.util.Optional;

public interface WechatAppletCustomerDomainService {
    Optional<WechatAppletCustomer> findByOpenId(String openId);

    WechatAppletCustomer save(WechatAppletCustomer model);

    WechatAppletCustomer findById(String id);
}

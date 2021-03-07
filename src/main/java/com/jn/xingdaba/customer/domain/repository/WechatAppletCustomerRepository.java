package com.jn.xingdaba.customer.domain.repository;

import com.jn.xingdaba.customer.domain.model.WechatAppletCustomer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WechatAppletCustomerRepository extends JpaRepository<WechatAppletCustomer, String>, JpaSpecificationExecutor<WechatAppletCustomer> {
    Optional<WechatAppletCustomer> findByOpenId(String openId);
}

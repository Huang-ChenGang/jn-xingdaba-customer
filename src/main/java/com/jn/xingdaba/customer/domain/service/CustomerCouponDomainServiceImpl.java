package com.jn.xingdaba.customer.domain.service;

import com.jn.core.builder.KeyBuilder;
import com.jn.xingdaba.customer.domain.model.CustomerCoupon;
import com.jn.xingdaba.customer.domain.repository.CustomerCouponRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Slf4j
@Service
public class CustomerCouponDomainServiceImpl implements CustomerCouponDomainService {
    private final CustomerCouponRepository repository;
    private final KeyBuilder keyBuilder;

    public CustomerCouponDomainServiceImpl(CustomerCouponRepository repository,
                                           KeyBuilder keyBuilder) {
        this.repository = repository;
        this.keyBuilder = keyBuilder;
    }

    @Override
    public String save(CustomerCoupon model) {
        if (StringUtils.isEmpty(model.getId())) {
            model.setId(keyBuilder.getUniqueKey());
        }
        if (StringUtils.isEmpty(model.getIsDelete())) {
            model.setIsDelete("0");
        }

        return repository.save(model).getId();
    }

    @Override
    public boolean hasRegisterCoupon(String customerId) {
        return repository.findByCustomerIdAndGiveTypeAndIsDelete(customerId, "reg", "0").isPresent();
    }

}

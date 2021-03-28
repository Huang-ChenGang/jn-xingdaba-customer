package com.jn.xingdaba.customer.domain.service;

import com.jn.core.builder.KeyBuilder;
import com.jn.xingdaba.customer.domain.model.CustomerCoupon;
import com.jn.xingdaba.customer.domain.repository.CustomerCouponRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;

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
        return repository.findByCustomerIdAndGiveTypeAndCouponStateAndIsDelete(customerId, "reg", "gave", "0").isPresent();
    }

    @Override
    public boolean hasMinusCoupon(String customerId, BigDecimal conditionAmount, BigDecimal valueAmount) {
        return repository.findByCustomerIdAndGiveTypeAndConditionAmountAndValueAmountAndCouponStateAndIsDelete(customerId, "minus", conditionAmount, valueAmount, "gave", "0").isPresent();
    }

    @Override
    public CustomerCoupon findFitCoupon(String customerId, BigDecimal conditionAmount) {
        return repository.findFirstByCustomerIdAndConditionAmountLessThanEqualAndCouponStateAndIsDeleteOrderByConditionAmountDesc(customerId, conditionAmount, "gave", "0").orElse(null);
    }

}

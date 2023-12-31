package com.jn.xingdaba.customer.domain.service;

import com.jn.core.builder.KeyBuilder;
import com.jn.xingdaba.customer.domain.model.CustomerCoupon;
import com.jn.xingdaba.customer.domain.repository.CustomerCouponRepository;
import com.jn.xingdaba.customer.infrastructure.exception.CouponNotDefineException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

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

        Optional<CustomerCoupon> oldValue = repository.findById(model.getId());
        if (oldValue.isPresent()) {
            model.setCreateTime(oldValue.get().getCreateTime());
            model.setCreateBy(oldValue.get().getCreateBy());
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

    @Override
    public CustomerCoupon findById(String id) {
        return repository.findById(id).orElseThrow(CouponNotDefineException::new);
    }

    @Override
    public List<CustomerCoupon> findByCustomerIdAndCouponState(String customerId, String couponState) {
        return repository.findByCustomerIdAndCouponState(customerId, couponState);
    }

}

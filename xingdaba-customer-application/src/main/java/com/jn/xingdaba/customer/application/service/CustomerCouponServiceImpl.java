package com.jn.xingdaba.customer.application.service;

import com.jn.xingdaba.customer.application.dto.CustomerCouponDto;
import com.jn.xingdaba.customer.domain.model.CouponDefine;
import com.jn.xingdaba.customer.domain.model.CustomerCoupon;
import com.jn.xingdaba.customer.domain.service.CustomerCouponDomainService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Slf4j
@Service
public class CustomerCouponServiceImpl implements CustomerCouponService {
    private final CustomerCouponDomainService domainService;

    public CustomerCouponServiceImpl(CustomerCouponDomainService domainService) {
        this.domainService = domainService;
    }

    @Override
    public void sendRegisterCoupon(String customerId, CouponDefine couponDefine) {
        CustomerCoupon customerCoupon = initSendCoupon(couponDefine);
        customerCoupon.setCustomerId(customerId);

        if (!domainService.hasRegisterCoupon(customerId)) {
            domainService.save(customerCoupon);
        }
    }

    @Override
    public void sendMinusCoupon(String customerId, CouponDefine couponDefine) {
        CustomerCoupon customerCoupon = initSendCoupon(couponDefine);
        customerCoupon.setCustomerId(customerId);

        if (!domainService.hasMinusCoupon(customerId, couponDefine.getConditionAmount(), couponDefine.getValueAmount())) {
            domainService.save(customerCoupon);
        }
    }

    @Override
    public CustomerCouponDto findFitCoupon(String customerId, BigDecimal conditionAmount) {
        return CustomerCouponDto.fromModel(domainService.findFitCoupon(customerId, conditionAmount));
    }

    @Override
    public void useCoupon(String id) {
        CustomerCoupon customerCoupon = domainService.findById(id);
        customerCoupon.setCouponState("used");
        domainService.save(customerCoupon);
    }

    @Override
    public Integer findAvailableCouponCount(String customerId) {
        return domainService.findByCustomerIdAndCouponState(customerId, "gave").size();
    }

    private CustomerCoupon initSendCoupon(CouponDefine couponDefine) {
        CustomerCoupon customerCoupon = new CustomerCoupon();
        BeanUtils.copyProperties(couponDefine, customerCoupon);
        customerCoupon.setCouponState("gave");

        if ("day".equals(couponDefine.getValidType())) {
            customerCoupon.setValidDateBegin(LocalDateTime.now());
            customerCoupon.setValidDateEnd(customerCoupon.getValidDateBegin().plusDays(couponDefine.getValidDay()));
        }

        return customerCoupon;
    }
}

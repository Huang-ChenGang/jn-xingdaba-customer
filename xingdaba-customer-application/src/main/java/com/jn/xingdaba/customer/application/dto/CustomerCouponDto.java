package com.jn.xingdaba.customer.application.dto;

import com.jn.xingdaba.customer.domain.model.CustomerCoupon;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public final class CustomerCouponDto {

    private String id;

    private String customerId;

    private String couponState;

    private String mainTitle;

    private String subTitle;

    private BigDecimal valueAmount;

    private BigDecimal conditionAmount;

    private LocalDateTime validDateBegin;

    private LocalDateTime validDateEnd;

    private String giveType;

    private String isDelete;

    public static CustomerCouponDto fromModel(CustomerCoupon model) {
        CustomerCouponDto dto = new CustomerCouponDto();
        BeanUtils.copyProperties(model, dto);
        return dto;
    }
}

package com.jn.xingdaba.customer.api;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jn.xingdaba.customer.application.dto.CustomerCouponDto;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public final class CustomerCouponResponseData {

    private String id;

    private String couponState;

    private String mainTitle;

    private String subTitle;

    private BigDecimal valueAmount;

    private BigDecimal conditionAmount;

    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    private LocalDateTime validDateBegin;

    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    private LocalDateTime validDateEnd;

    public static CustomerCouponResponseData fromDto(CustomerCouponDto dto) {
        if (dto == null) {
            return null;
        }
        CustomerCouponResponseData responseData = new CustomerCouponResponseData();
        BeanUtils.copyProperties(dto, responseData);
        return responseData;
    }
}

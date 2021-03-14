package com.jn.xingdaba.customer.api;

import lombok.Data;

import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

@Data
public final class CouponDefineRequestData {
    @PositiveOrZero
    private Integer pageNo;

    @Positive
    private Integer pageSize;
}

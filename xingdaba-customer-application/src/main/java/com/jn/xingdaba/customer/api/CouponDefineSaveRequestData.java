package com.jn.xingdaba.customer.api;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public final class CouponDefineSaveRequestData {

    private String id;

    private String mainTitle;

    private String subTitle;

    private BigDecimal valueAmount;

    private BigDecimal conditionAmount;

    private String validType;

    private Integer validDay;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDateTime validDateBegin;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDateTime validDateEnd;

    private String giveType;

    private String isDelete;
}

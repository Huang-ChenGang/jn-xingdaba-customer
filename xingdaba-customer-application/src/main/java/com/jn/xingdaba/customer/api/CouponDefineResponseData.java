package com.jn.xingdaba.customer.api;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jn.xingdaba.customer.application.dto.CouponDefineDto;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public final class CouponDefineResponseData {

    private String id;

    private String mainTitle;

    private String subTitle;

    private BigDecimal valueAmount;

    private BigDecimal conditionAmount;

    private String validType;

    private Integer validDay;

    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime validDateBegin;

    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime validDateEnd;

    private String giveType;

    private String isDelete;

    public static CouponDefineResponseData fromDto(CouponDefineDto dto) {
        CouponDefineResponseData responseData = new CouponDefineResponseData();
        BeanUtils.copyProperties(dto, responseData);
        return responseData;
    }
}

package com.jn.xingdaba.customer.api;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jn.xingdaba.customer.application.dto.CouponDefineDto;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public final class CouponDefineResponseData {

    private String id;

    private String mainTitle;

    private String subTitle;

    private BigDecimal valueAmount;

    private BigDecimal conditionAmount;

    private String validType;

    private Integer validDay;

    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    private LocalDate validDateBegin;

    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    private LocalDate validDateEnd;

    private String giveType;

    private String isDelete;

    public static CouponDefineResponseData fromDto(CouponDefineDto dto) {
        CouponDefineResponseData responseData = new CouponDefineResponseData();
        BeanUtils.copyProperties(dto, responseData);
        return responseData;
    }
}

package com.jn.xingdaba.customer.application.dto;

import com.jn.xingdaba.customer.domain.model.CouponDefine;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public final class CouponDefineDto {

    private String id;

    private String mainTitle;

    private String subTitle;

    private BigDecimal valueAmount;

    private BigDecimal conditionAmount;

    private String validType;

    private Integer validDay;

    private LocalDateTime validDateBegin;

    private LocalDateTime validDateEnd;

    private String giveType;

    private String isDelete;

    public static CouponDefineDto fromModel(CouponDefine model) {
        CouponDefineDto dto = new CouponDefineDto();
        BeanUtils.copyProperties(model, dto);
        return dto;
    }
}

package com.jn.xingdaba.customer.application.dto;

import com.jn.xingdaba.customer.api.CouponDefineSaveRequestData;
import com.jn.xingdaba.customer.domain.model.CouponDefine;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public final class CouponDefineDto {

    private String id;

    private String mainTitle;

    private String subTitle;

    private BigDecimal valueAmount;

    private BigDecimal conditionAmount;

    private String validType;

    private Integer validDay;

    private LocalDate validDateBegin;

    private LocalDate validDateEnd;

    private String giveType;

    private String isDelete;

    public static CouponDefineDto fromModel(CouponDefine model) {
        if (model == null) {
            return null;
        }
        CouponDefineDto dto = new CouponDefineDto();
        BeanUtils.copyProperties(model, dto);
        return dto;
    }

    public static CouponDefine toModel(CouponDefineDto dto) {
        CouponDefine model = new CouponDefine();
        BeanUtils.copyProperties(dto, model);
        return model;
    }

    public static CouponDefineDto fromRequestData(CouponDefineSaveRequestData requestData) {
        CouponDefineDto dto = new CouponDefineDto();
        BeanUtils.copyProperties(requestData, dto);
        return dto;
    }
}

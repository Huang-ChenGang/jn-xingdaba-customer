package com.jn.xingdaba.customer.api;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public final class CouponDefineSaveRequestData {

    private String id;

    @NotBlank(message = "主标题不能为空")
    private String mainTitle;

    @NotBlank(message = "副标题不能为空")
    private String subTitle;

    @NotNull(message = "有效金额不能为空")
    private BigDecimal valueAmount;

    @NotNull(message = "条件金额不能为空")
    private BigDecimal conditionAmount;

    @NotBlank(message = "验证类型不能为空")
    private String validType;

    private Integer validDay;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDateTime validDateBegin;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDateTime validDateEnd;

    @NotBlank(message = "赠送类型不能为空")
    private String giveType;

    private String isDelete;
}

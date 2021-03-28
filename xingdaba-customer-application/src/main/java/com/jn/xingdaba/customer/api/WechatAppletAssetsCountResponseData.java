package com.jn.xingdaba.customer.api;

import lombok.Data;

import java.math.BigDecimal;

@Data
public final class WechatAppletAssetsCountResponseData {

    /** 余额 **/
    private BigDecimal balance;

    /** 星币 **/
    private BigDecimal starMoney;

    /** 优惠券数量 **/
    private Integer couponCount;

    /** 发票数量 **/
    private Integer invoiceCount;
}

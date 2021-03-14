package com.jn.xingdaba.customer.application.dto;

import lombok.Data;

@Data
public final class WechatAppletCustomerDto {
    private String id;

    private String nickName;
    private String realName;

    private String gender;

    private String city;

    private String province;

    private String country;

    private String avatarUrl;

    private String mobile;

    private String language;

    private String isDelete;
}

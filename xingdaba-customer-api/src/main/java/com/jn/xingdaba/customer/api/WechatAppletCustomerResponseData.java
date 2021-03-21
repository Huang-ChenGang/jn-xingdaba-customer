package com.jn.xingdaba.customer.api;

import lombok.Data;

@Data
public final class WechatAppletCustomerResponseData {
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

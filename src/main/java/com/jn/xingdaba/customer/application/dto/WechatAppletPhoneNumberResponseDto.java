package com.jn.xingdaba.customer.application.dto;

import lombok.Data;

@Data
public final class WechatAppletPhoneNumberResponseDto {
    private String phoneNumber;
    private String purePhoneNumber;
    private String countryCode;
}

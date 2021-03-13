package com.jn.xingdaba.customer.api;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public final class WechatPhoneRequestData {

    @NotBlank
    private String loginKey;

    @NotBlank
    private String encryptedData;

    @NotBlank
    private String iv;
}

package com.jn.xingdaba.customer.application.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;

@Data
public final class WechatAppletCode2SessionResponseDto {
    @JsonAlias("openid")
    private String openId;

    @JsonAlias("session_key")
    private String sessionKey;

    @JsonAlias("errcode")
    private Integer errorCode;

    @JsonAlias("errmsg")
    private String errorMessage;
}

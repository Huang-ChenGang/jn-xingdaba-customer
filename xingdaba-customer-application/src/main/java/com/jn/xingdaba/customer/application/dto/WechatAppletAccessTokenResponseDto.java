package com.jn.xingdaba.customer.application.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;

@Data
public final class WechatAppletAccessTokenResponseDto {
    @JsonAlias("access_token")
    private String accessToken;

    @JsonAlias("expires_in")
    private Integer expiresIn;

    @JsonAlias("errcode")
    private Integer errorCode;

    @JsonAlias("errmsg")
    private String errorMessage;
}

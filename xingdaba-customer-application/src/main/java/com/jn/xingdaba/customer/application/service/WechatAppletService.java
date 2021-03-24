package com.jn.xingdaba.customer.application.service;

public interface WechatAppletService {
    String ACCESS_TOKEN_REDIS_KEY = "wechat-applet-access-token";
    Long ACCESS_TOKEN_VALID_SECONDS = 7000L;
    Long ACCESS_TOKEN_MIN_SURPLUS_SECONDS = 30L;

    String getAccessToken();
}

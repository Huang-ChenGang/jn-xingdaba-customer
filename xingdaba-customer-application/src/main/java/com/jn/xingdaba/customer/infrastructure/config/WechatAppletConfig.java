package com.jn.xingdaba.customer.infrastructure.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "wechat-applet")
public class WechatAppletConfig {
    private String appId;
    private String appSecret;
    private String code2SessionUrl;
    private String getAccessTokenUrl;
}

package com.jn.xingdaba.customer.application.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jn.xingdaba.customer.application.dto.WechatAppletAccessTokenResponseDto;
import com.jn.xingdaba.customer.infrastructure.config.WechatAppletConfig;
import com.jn.xingdaba.customer.infrastructure.exception.WechatAppletException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.TimeUnit;

import static com.jn.xingdaba.customer.infrastructure.exception.WechatAppletError.GET_ACCESS_TOKEN_ERROR;

@Slf4j
@Service
public class WechatAppletServiceImpl implements WechatAppletService {
    private final RestTemplate restTemplate;
    private final WechatAppletConfig wechatAppletConfig;
    private final ObjectMapper objectMapper;
    private final StringRedisTemplate stringRedisTemplate;

    public WechatAppletServiceImpl(RestTemplateBuilder restTemplateBuilder,
                                   WechatAppletConfig wechatAppletConfig,
                                   ObjectMapper objectMapper, StringRedisTemplate stringRedisTemplate) {
        this.restTemplate = restTemplateBuilder.build();
        this.wechatAppletConfig = wechatAppletConfig;
        this.objectMapper = objectMapper;
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @Override
    public String getAccessToken() {
        String accessToken;
        Boolean hasAccessToken = stringRedisTemplate.hasKey(ACCESS_TOKEN_REDIS_KEY);
        if (hasAccessToken != null && hasAccessToken) {
            accessToken = stringRedisTemplate.opsForValue().get(ACCESS_TOKEN_REDIS_KEY);

            if (StringUtils.hasText(accessToken)) {
                // 获取redis中有效期
                Long surplusSeconds = stringRedisTemplate.getExpire(ACCESS_TOKEN_REDIS_KEY, TimeUnit.SECONDS);
                if (surplusSeconds == null || surplusSeconds < ACCESS_TOKEN_MIN_SURPLUS_SECONDS) {
                    // 重新获取并设置redis有效期
                    accessToken = getWechatAppletAccessToken();
                    stringRedisTemplate.opsForValue().set(ACCESS_TOKEN_REDIS_KEY, accessToken, ACCESS_TOKEN_VALID_SECONDS, TimeUnit.SECONDS);
                }

                log.info("find access token: {}", accessToken);
                return accessToken;
            }

        }

        accessToken = getWechatAppletAccessToken();
        stringRedisTemplate.opsForValue().set(ACCESS_TOKEN_REDIS_KEY, accessToken, ACCESS_TOKEN_VALID_SECONDS, TimeUnit.SECONDS);
        return accessToken;
    }

    private String getWechatAppletAccessToken() {
        String getAccessTokenUrl = wechatAppletConfig.getGetAccessTokenUrl();
        getAccessTokenUrl = getAccessTokenUrl.replace("$APPID$", wechatAppletConfig.getAppId());
        getAccessTokenUrl = getAccessTokenUrl.replace("$SECRET$", wechatAppletConfig.getAppSecret());

        String wechatResponseJson;
        try {
            wechatResponseJson = restTemplate.getForObject(getAccessTokenUrl, String.class);
        } catch (RestClientException e) {
            log.error("get access token error.", e);
            throw new WechatAppletException(GET_ACCESS_TOKEN_ERROR, e.getMessage());
        }
        log.info("wechat access token response: {}", wechatResponseJson);
        WechatAppletAccessTokenResponseDto wechatResponse;
        try {
            wechatResponse = objectMapper.readValue(wechatResponseJson, WechatAppletAccessTokenResponseDto.class);
        } catch (JsonProcessingException e) {
            log.error("get access token error.", e);
            throw new WechatAppletException(GET_ACCESS_TOKEN_ERROR, e.getMessage());
        }

        return wechatResponse.getAccessToken();
    }

}

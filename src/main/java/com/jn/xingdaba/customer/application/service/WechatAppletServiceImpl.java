package com.jn.xingdaba.customer.application.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jn.core.builder.KeyBuilder;
import com.jn.xingdaba.customer.application.dto.WechatAppletCode2SessionResponseDto;
import com.jn.xingdaba.customer.application.dto.WechatAppletCustomerDto;
import com.jn.xingdaba.customer.domain.model.WechatAppletCustomer;
import com.jn.xingdaba.customer.domain.service.WechatAppletCustomerDomainService;
import com.jn.xingdaba.customer.infrastructure.config.WechatAppletConfig;
import com.jn.xingdaba.customer.infrastructure.exception.WechatAppletException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

import static com.jn.xingdaba.customer.infrastructure.exception.WechatAppletError.CODE2SESSION_ERROR;

@Slf4j
@Service
public class WechatAppletServiceImpl implements WechatAppletService {
    private final WechatAppletConfig wechatAppletConfig;
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;
    private final WechatAppletCustomerDomainService domainService;
    private final KeyBuilder keyBuilder;

    public WechatAppletServiceImpl(WechatAppletConfig wechatAppletConfig,
                                   RestTemplateBuilder restTemplateBuilder,
                                   ObjectMapper objectMapper,
                                   WechatAppletCustomerDomainService domainService,
                                   KeyBuilder keyBuilder) {
        this.wechatAppletConfig = wechatAppletConfig;
        this.restTemplate = restTemplateBuilder.build();
        this.objectMapper = objectMapper;
        this.domainService = domainService;
        this.keyBuilder = keyBuilder;
    }

    @Override
    public String login(String code) {
        log.info("wechat applet login for code: {}", code);
        String code2SessionUrl = wechatAppletConfig.getCode2SessionUrl();
        code2SessionUrl = code2SessionUrl.replace("$APPID$", wechatAppletConfig.getAppId());
        code2SessionUrl = code2SessionUrl.replace("$SECRET$", wechatAppletConfig.getAppSecret());
        code2SessionUrl = code2SessionUrl.replace("$JSCODE$", code);

        String code2SessionResponse;
        try {
            code2SessionResponse = restTemplate.getForObject(code2SessionUrl, String.class);
        } catch (RestClientException e) {
            log.error("wechat applet code2session error.", e);
            throw new WechatAppletException(CODE2SESSION_ERROR);
        }
        log.info("code2SessionResponse: {}", code2SessionResponse);
        WechatAppletCode2SessionResponseDto responseDto;
        try {
            responseDto = objectMapper.readValue(code2SessionResponse, WechatAppletCode2SessionResponseDto.class);
        } catch (JsonProcessingException e) {
            log.error("wechat applet code2session error.", e);
            throw new WechatAppletException(CODE2SESSION_ERROR);
        }

        if (responseDto.getErrorCode() != null && responseDto.getErrorCode() != 0) {
            log.error("wechat applet code2session error: {}", responseDto.getErrorMessage());
            throw new WechatAppletException(CODE2SESSION_ERROR, responseDto.getErrorMessage());
        }

        WechatAppletCustomerDto customerDto;
        Optional<WechatAppletCustomer> optionalCustomer = domainService.findByOpenId(responseDto.getOpenId());
        if (optionalCustomer.isPresent()) {
            customerDto = WechatAppletCustomerDto.fromModel(optionalCustomer.get());
        } else {
            customerDto = new WechatAppletCustomerDto();
            customerDto.setId(keyBuilder.getUniqueKey());
            customerDto.setOpenId(responseDto.getOpenId());
        }
        customerDto.setSessionKey(responseDto.getSessionKey());

        return domainService.save(WechatAppletCustomerDto.toModel(customerDto)).getId();
    }
}

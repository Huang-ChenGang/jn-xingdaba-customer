package com.jn.xingdaba.customer.application.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jn.xingdaba.customer.api.WechatPhoneRequestData;
import com.jn.xingdaba.customer.application.dto.WechatAppletCode2SessionResponseDto;
import com.jn.xingdaba.customer.application.dto.WechatAppletCustomerDto;
import com.jn.xingdaba.customer.application.dto.WechatAppletPhoneNumberResponseDto;
import com.jn.xingdaba.customer.domain.model.CouponDefine;
import com.jn.xingdaba.customer.domain.model.WechatAppletCustomer;
import com.jn.xingdaba.customer.domain.service.CouponDefineDomainService;
import com.jn.xingdaba.customer.domain.service.WechatAppletCustomerDomainService;
import com.jn.xingdaba.customer.infrastructure.WechatDecryptor;
import com.jn.xingdaba.customer.infrastructure.config.WechatAppletConfig;
import com.jn.xingdaba.customer.infrastructure.exception.WechatAppletException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

import static com.jn.xingdaba.customer.infrastructure.exception.CustomerSystemError.GET_WECHAT_PHONE_ERROR;
import static com.jn.xingdaba.customer.infrastructure.exception.WechatAppletError.CODE2SESSION_ERROR;

@Slf4j
@Service
public class WechatAppletCustomerServiceImpl implements WechatAppletCustomerService {
    private final WechatAppletConfig wechatAppletConfig;
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;
    private final WechatAppletCustomerDomainService domainService;
    private final CustomerCouponService customerCouponService;
    private final CouponDefineDomainService couponDefineDomainService;

    public WechatAppletCustomerServiceImpl(WechatAppletConfig wechatAppletConfig,
                                           RestTemplateBuilder restTemplateBuilder,
                                           ObjectMapper objectMapper,
                                           WechatAppletCustomerDomainService domainService,
                                           CustomerCouponService customerCouponService,
                                           CouponDefineDomainService couponDefineDomainService) {
        this.wechatAppletConfig = wechatAppletConfig;
        this.restTemplate = restTemplateBuilder.build();
        this.objectMapper = objectMapper;
        this.domainService = domainService;
        this.customerCouponService = customerCouponService;
        this.couponDefineDomainService = couponDefineDomainService;
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
            throw new WechatAppletException(CODE2SESSION_ERROR, e.getMessage());
        }
        log.info("code2SessionResponse: {}", code2SessionResponse);
        WechatAppletCode2SessionResponseDto responseDto;
        try {
            responseDto = objectMapper.readValue(code2SessionResponse, WechatAppletCode2SessionResponseDto.class);
        } catch (JsonProcessingException e) {
            log.error("wechat applet code2session error.", e);
            throw new WechatAppletException(CODE2SESSION_ERROR, e.getMessage());
        }

        if (responseDto.getErrorCode() != null && responseDto.getErrorCode() != 0) {
            log.error("wechat applet code2session error: {}", responseDto.getErrorMessage());
            throw new WechatAppletException(CODE2SESSION_ERROR, responseDto.getErrorMessage());
        }

        WechatAppletCustomer customerInfo;
        Optional<WechatAppletCustomer> optionalCustomer = domainService.findByOpenId(responseDto.getOpenId());
        if (optionalCustomer.isPresent()) {
            customerInfo = optionalCustomer.get();
        } else {
            customerInfo = new WechatAppletCustomer();
            customerInfo.setOpenId(responseDto.getOpenId());
        }
        customerInfo.setSessionKey(responseDto.getSessionKey());

        return domainService.save(customerInfo).getId();
    }

    @Override
    public String getPhone(WechatPhoneRequestData requestData) {
        log.info("get wechat applet phone number for: {}", requestData);
        WechatAppletCustomer customerInfo = domainService.findById(requestData.getLoginKey());

        String phoneNumber;
        try {
            WechatAppletPhoneNumberResponseDto wechatResponse = objectMapper.readValue(
                    WechatDecryptor.decrypt(requestData.getEncryptedData(), requestData.getIv(), customerInfo.getSessionKey()),
                    WechatAppletPhoneNumberResponseDto.class);
            log.info("wechat response: {}", wechatResponse);
            phoneNumber = wechatResponse.getPhoneNumber();
        } catch (JsonProcessingException e) {
            log.error("get wechat phone number error", e);
            throw new WechatAppletException(GET_WECHAT_PHONE_ERROR);
        }

        customerInfo.setMobile(phoneNumber);
        domainService.save(customerInfo);

        CouponDefine registerCoupon = couponDefineDomainService.findByGiveType("reg");
        customerCouponService.sendRegisterCoupon(customerInfo.getId(), registerCoupon);

        return phoneNumber;
    }

    @Override
    public String save(WechatAppletCustomerDto dto) {
        log.info("save wechat applet customer info for: {}", dto);
        WechatAppletCustomer model = domainService.findById(dto.getId());
        BeanUtils.copyProperties(dto, model);
        return domainService.save(model).getId();
    }

    @Override
    public String findOpenIdById(String id) {
        return domainService.findById(id).getOpenId();
    }

}

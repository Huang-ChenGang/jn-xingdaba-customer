package com.jn.xingdaba.customer.application.controller;

import com.jn.core.api.ServerResponse;
import com.jn.xingdaba.customer.api.WechatAppletCustomerSaveRequestData;
import com.jn.xingdaba.customer.api.WechatPhoneRequestData;
import com.jn.xingdaba.customer.application.service.WechatAppletCustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Slf4j
@Validated
@RestController
@RequestMapping("/wechat/applet/customers")
public class WechatAppletCustomerController {
    private final WechatAppletCustomerService service;

    public WechatAppletCustomerController(WechatAppletCustomerService service) {
        this.service = service;
    }

    @PostMapping("/login/{code}")
    public ServerResponse<String> login(@PathVariable @NotBlank String code) {
        return ServerResponse.success(service.login(code));
    }

    @GetMapping("/phone")
    public ServerResponse<String> getPhone(WechatPhoneRequestData requestData) {
        return ServerResponse.success(service.getPhone(requestData));
    }

    @PostMapping
    public ServerResponse<String> save(@RequestBody @Validated @NotNull WechatAppletCustomerSaveRequestData requestData) {
        return ServerResponse.success(service.save(WechatAppletCustomerSaveRequestData.toDto(requestData)));
    }

    @GetMapping("/open-id/{customerId}")
    public ServerResponse<String> getOpenId(@PathVariable @NotBlank String customerId) {
        return ServerResponse.success(service.findOpenIdById(customerId));
    }
}

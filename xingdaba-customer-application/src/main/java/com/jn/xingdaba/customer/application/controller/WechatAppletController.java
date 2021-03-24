package com.jn.xingdaba.customer.application.controller;

import com.jn.core.api.ServerResponse;
import com.jn.xingdaba.customer.application.service.WechatAppletService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Validated
@RestController
@RequestMapping("/wechat/applet")
public class WechatAppletController {
    private final WechatAppletService service;

    public WechatAppletController(WechatAppletService service) {
        this.service = service;
    }

    @GetMapping("/access-token")
    public ServerResponse<String> getAccessToken() {
        return ServerResponse.success(service.getAccessToken());
    }
}

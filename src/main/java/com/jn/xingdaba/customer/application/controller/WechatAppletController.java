package com.jn.xingdaba.customer.application.controller;

import com.jn.core.api.ServerResponse;
import com.jn.xingdaba.customer.application.service.WechatAppletService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;

@Slf4j
@Validated
@RestController
@RequestMapping("/wechat")
public class WechatAppletController {
    private final WechatAppletService service;

    public WechatAppletController(WechatAppletService service) {
        this.service = service;
    }

    @PostMapping("/login/{code}")
    public ServerResponse<String> login(@PathVariable @NotBlank String code) {
        return ServerResponse.success(service.login(code));
    }
}

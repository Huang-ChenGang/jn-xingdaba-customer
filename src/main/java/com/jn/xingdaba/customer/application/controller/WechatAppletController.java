package com.jn.xingdaba.customer.application.controller;

import com.jn.core.api.ServerResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;

@Slf4j
@Validated
@RestController
@RequestMapping("/wechat")
public class WechatAppletController {

    @PostMapping("/login/{code}")
    public ServerResponse<String> login(@PathVariable @NotBlank String code) {
        return ServerResponse.success("wechat applet login success code: " + code);
    }
}

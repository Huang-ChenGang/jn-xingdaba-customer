package com.jn.xingdaba.customer.application.controller;

import com.jn.core.api.JnPageResponse;
import com.jn.core.api.ServerResponse;
import com.jn.xingdaba.customer.api.CouponDefineRequestData;
import com.jn.xingdaba.customer.api.CouponDefineResponseData;
import com.jn.xingdaba.customer.api.CouponDefineSaveRequestData;
import com.jn.xingdaba.customer.application.dto.CouponDefineDto;
import com.jn.xingdaba.customer.application.service.CouponDefineService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

@Slf4j
@Validated
@RestController
@RequestMapping("/coupon-definition")
public class CouponDefineController {
    private final CouponDefineService service;

    public CouponDefineController(CouponDefineService service) {
        this.service = service;
    }

    @GetMapping("/pageable")
    public ServerResponse<JnPageResponse<CouponDefineResponseData>> findAll(CouponDefineRequestData requestData) {
        Page<CouponDefineResponseData> pageableResult = service.findAll(requestData).map(CouponDefineResponseData::fromDto);
        log.info("find pageable coupon define result: {}", pageableResult);
        return ServerResponse.success(JnPageResponse.of(pageableResult));
    }

    @PostMapping
    public ServerResponse<String> save(@RequestBody @NotNull @Validated CouponDefineSaveRequestData requestData) {
        log.info("save coupon definition for request data: {}", requestData);
        return ServerResponse.success(service.save(CouponDefineDto.fromRequestData(requestData)));
    }
}

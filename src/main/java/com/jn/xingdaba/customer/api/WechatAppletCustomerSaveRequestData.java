package com.jn.xingdaba.customer.api;

import com.jn.xingdaba.customer.application.dto.WechatAppletCustomerDto;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotBlank;

@Data
public final class WechatAppletCustomerSaveRequestData {
    @NotBlank
    private String id;

    @NotBlank
    private String nickName;

    private String realName;

    private String gender;

    private String city;

    private String province;

    private String country;

    private String avatarUrl;

    private String mobile;

    private String language;

    private String isDelete;

    public static WechatAppletCustomerDto toDto(WechatAppletCustomerSaveRequestData requestData) {
        WechatAppletCustomerDto dto = new WechatAppletCustomerDto();
        BeanUtils.copyProperties(requestData, dto);
        return dto;
    }
}

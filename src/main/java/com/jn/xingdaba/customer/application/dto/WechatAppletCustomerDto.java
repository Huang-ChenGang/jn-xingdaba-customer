package com.jn.xingdaba.customer.application.dto;

import com.jn.xingdaba.customer.domain.model.WechatAppletCustomer;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import org.springframework.util.StringUtils;

@Data
public final class WechatAppletCustomerDto {
    private String id;

    private String openId;

    private String nickName;
    private String realName;

    private String gender;

    private String city;

    private String province;

    private String country;

    private String avatarUrl;

    private String mobile;

    private String language;

    private String sessionKey;

    private String isDelete;

    public static WechatAppletCustomer toModel(WechatAppletCustomerDto dto) {
        WechatAppletCustomer model = new WechatAppletCustomer();
        BeanUtils.copyProperties(dto, model);

        if (StringUtils.isEmpty(model.getIsDelete())) {
            model.setIsDelete("0");
        }

        return model;
    }

    public static WechatAppletCustomerDto fromModel(WechatAppletCustomer model) {
        WechatAppletCustomerDto dto = new WechatAppletCustomerDto();
        BeanUtils.copyProperties(model, dto);
        return dto;
    }
}

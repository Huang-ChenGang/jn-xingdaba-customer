package com.jn.xingdaba.customer.application.dto;

import com.jn.xingdaba.customer.api.WechatAppletCustomerResponseData;
import com.jn.xingdaba.customer.domain.model.WechatAppletCustomer;
import lombok.Data;
import org.springframework.beans.BeanUtils;

@Data
public final class WechatAppletCustomerDto {
    private String id;

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

    public static WechatAppletCustomerResponseData toResponseData(WechatAppletCustomerDto dto) {
        WechatAppletCustomerResponseData responseData = new WechatAppletCustomerResponseData();
        BeanUtils.copyProperties(dto, responseData);
        return responseData;
    }

    public static WechatAppletCustomerDto fromModel(WechatAppletCustomer model) {
        WechatAppletCustomerDto dto = new WechatAppletCustomerDto();
        BeanUtils.copyProperties(model, dto);
        return dto;
    }
}

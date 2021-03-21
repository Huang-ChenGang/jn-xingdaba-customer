package com.jn.xingdaba.customer.application.service;

import com.jn.xingdaba.customer.api.WechatPhoneRequestData;
import com.jn.xingdaba.customer.application.dto.WechatAppletCustomerDto;

public interface WechatAppletService {

    String login(String code);

    String getPhone(WechatPhoneRequestData requestData);

    String save(WechatAppletCustomerDto dto);
}

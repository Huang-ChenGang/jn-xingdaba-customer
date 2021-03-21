package com.jn.xingdaba.customer.application.service;

import com.jn.xingdaba.customer.api.WechatPhoneRequestData;
import com.jn.xingdaba.customer.application.dto.WechatAppletCustomerDto;

public interface WechatAppletCustomerService {

    String login(String code);

    String getPhone(WechatPhoneRequestData requestData);

    String save(WechatAppletCustomerDto dto);

    String findOpenIdById(String id);
}

package com.jn.xingdaba.customer.application.service;

import com.jn.xingdaba.customer.api.WechatPhoneRequestData;

public interface WechatAppletService {

    String login(String code);

    String getPhone(WechatPhoneRequestData requestData);
}

package com.jn.xingdaba.customer.domain.model;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Data
@Entity
public class WechatAppletCustomer {
    @Id
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

    @CreatedDate
    private String createBy;

    private LocalDateTime createTime;

    @LastModifiedDate
    private String updateBy;

    private LocalDateTime updateTime;
}

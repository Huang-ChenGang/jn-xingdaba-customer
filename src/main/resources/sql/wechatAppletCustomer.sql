DROP TABLE IF EXISTS `wechat_applet_customer`;
CREATE TABLE `wechat_applet_customer` (
  `id` varchar(32) NOT NULL COMMENT '主键id',
  `open_id` varchar(32) NOT NULL COMMENT 'openId',
  `nick_name` varchar(32) DEFAULT NULL COMMENT '昵称',
  `gender` char(1) DEFAULT NULL COMMENT '性别 0:未知 1:男 2:女',
  `city` varchar(32) DEFAULT NULL COMMENT '城市',
  `province` varchar(32) DEFAULT NULL COMMENT '省份',
  `country` varchar(32) DEFAULT NULL COMMENT '国家',
  `avatar_url` text COMMENT '头像',
  `real_name` varchar(32) DEFAULT NULL COMMENT '真实姓名',
  `mobile` varchar(32) DEFAULT NULL COMMENT '手机号码',
  `language` varchar(32) DEFAULT NULL COMMENT '语言',
  `session_key` varchar(32) DEFAULT NULL COMMENT '用户登录秘钥',
  `is_delete` char(1) DEFAULT '0' COMMENT '是否删除 0:未删除 1:已删除',
  `create_by` varchar(32) DEFAULT NULL COMMENT '创建人',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` varchar(32) DEFAULT NULL COMMENT '更新人',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `open_id_unique` (`open_id`) USING BTREE COMMENT 'openId唯一'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='微信小程序用户表';
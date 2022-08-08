use portal_knight;

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
   `userid` int NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT '用户id',
   `username` varchar(20) NOT NULL COMMENT '用户名称',
   `password` varchar(50) NOT NULL COMMENT '用户密码',
   `email` varchar(30) NOT NULL unique COMMENT '用户邮件',
   `rootpath` varchar(255) NOT NULL COMMENT '用户根路径',
   `create_time` timestamp COMMENT '创建时间',
   `modified_time` timestamp COMMENT '修改时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
select * from `user`;
# delete from `user` where userid = 1;

DROP TABLE IF EXISTS `device`;
CREATE TABLE `device` (
   `device_id` varchar(36) PRIMARY KEY COMMENT '设备id',
   `userid` int NOT NULL COMMENT '用户id',
   `device_desc` varchar(50) COMMENT '设备描述信息',
   `is_online` boolean NOT NULL COMMENT '是否在线',
   `token` varchar(36) COMMENT 'token',
   `create_time` datetime COMMENT '创建时间',
   `modified_time` datetime COMMENT '修改时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
select * from `device`;

DROP TABLE IF EXISTS `file`;
CREATE TABLE `file` (
   `fileid` int NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT '文件id',
   `userid` varchar(36) NOT NULL COMMENT '用户id',
   `filepath` varchar(50) NOT NULL COMMENT '文件路径',
   `is_delete` tinyint NOT NULL COMMENT '是否删除',
   `is_file` tinyint NOT NULL COMMENT '是否为文件',
   `md5` varchar(3) COMMENT '文件md5值',
   `create_time` datetime COMMENT '创建时间',
   `modified_time` datetime COMMENT '修改时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
select * from `file`;



DROP TABLE IF EXISTS `match_sinopec_info`;
CREATE TABLE `match_sinopec_info`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `lng` decimal(11,8) DEFAULT 0 COMMENT '经度',
  `lat` decimal(10,8) DEFAULT 0 COMMENT '维度',
  `address` varchar(200) DEFAULT 0 COMMENT 'APP地址',
  `sinopec_name` varchar(200) DEFAULT '' COMMENT '中国石化加油站名称',
  `phone` varchar(20) DEFAULT 0 COMMENT '电话',
  `state` char(1) DEFAULT 0 COMMENT '状态 0启动 1异常',
  `remark` varchar(200) DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`id`)
)  ;
CREATE INDEX IDX_LNG_SIN ON match_sinopec_info(lng,lat);
CREATE INDEX IDX_LAT_SIN ON match_sinopec_info(lat);

DROP TABLE IF EXISTS `match_ford_info`;
CREATE TABLE `match_ford_info`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `lng` decimal(11,8) DEFAULT 0 COMMENT '经度',
  `lat` decimal(10,8) DEFAULT 0 COMMENT '维度',
  `address` varchar(200) DEFAULT '' COMMENT 'APP地址',
  `ford_name` varchar(200) DEFAULT '' COMMENT '福特公司名称',
  `phone` varchar(20) DEFAULT '' COMMENT '电话',
  `state` char(1) DEFAULT 0 COMMENT '状态 0启动 1异常',
  `remark` varchar(200) DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`id`)
) ;
CREATE INDEX IDX_LNG ON match_ford_info(lng,lat);
CREATE INDEX IDX_LAT ON match_ford_info(lat);

DROP TABLE IF EXISTS `match_opera_log`;
CREATE TABLE `match_opera_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `user_id` varchar(20) NOT NULL DEFAULT '0' COMMENT '用户id',
  `crt_tm` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '日志生成时间',
  `state` char(1) DEFAULT 0 COMMENT '状态 0中石化 1 4S店',
  `query_name` varchar(200) DEFAULT '' COMMENT '查询信息名称',
  `ip` varchar(20) NOT NULL DEFAULT '' COMMENT 'ip地址',
  `remark` varchar(100) DEFAULT NULL COMMENT '备注描述',
   PRIMARY KEY (`id`)
);
CREATE INDEX IDX_USER_ID ON match_opera_log(user_id);
CREATE INDEX IDX_QUERY_NAME ON match_opera_log(query_name);
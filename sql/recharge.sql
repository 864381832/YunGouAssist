/*
Navicat MySQL Data Transfer

Source Server         : yg
Source Server Version : 50614
Source Host           : localhost:3306
Source Database       : yungou_assist

Target Server Type    : MYSQL
Target Server Version : 50614
File Encoding         : 65001

Date: 2016-04-01 12:09:58
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for recharge
-- ----------------------------
DROP TABLE IF EXISTS `recharge`;
CREATE TABLE `recharge` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `account` varchar(40) DEFAULT NULL COMMENT '账号',
  `password` varchar(32) DEFAULT NULL COMMENT '密码',
  `moneyValue` int(11) DEFAULT NULL COMMENT '面值',
  `isUse` int(11) DEFAULT NULL COMMENT '是否消费',
  `useTime` datetime DEFAULT NULL COMMENT '消费时间',
  `userid` varchar(40) DEFAULT NULL COMMENT '充值账号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='充值卡';

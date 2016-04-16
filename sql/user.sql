/*
Navicat MySQL Data Transfer

Source Server         : yg
Source Server Version : 50614
Source Host           : localhost:3306
Source Database       : yungou_assist

Target Server Type    : MYSQL
Target Server Version : 50614
File Encoding         : 65001

Date: 2016-04-01 12:10:15
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userid` varchar(40) NOT NULL COMMENT '用户登录id',
  `password` varchar(32) NOT NULL COMMENT '密码',
  `registerTime` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '记录用户注册时间',
  `loginNumber` int(11) DEFAULT '1' COMMENT '记录用户登录次数',
  `userType` int(11) DEFAULT '1' COMMENT '用户类型',
  `expirationTime` datetime DEFAULT NULL,
  `isOnLine` int(11) DEFAULT '0' COMMENT '是否在线',
  `loginTime` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '最后一次登录时间',
  `phoneId` varchar(40) DEFAULT NULL,
  `userInfo` varchar(120) DEFAULT NULL COMMENT '用户信息',
  `remarksInfo` varchar(120) DEFAULT NULL COMMENT '备注信息',
  `renewNumber` int(11) DEFAULT '0' COMMENT '续费次数',
  PRIMARY KEY (`id`),
  UNIQUE KEY `userid_UNIQUE` (`userid`)
) ENGINE=InnoDB AUTO_INCREMENT=2138 DEFAULT CHARSET=utf8 COMMENT='用户表';

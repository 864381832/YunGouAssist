/*
Navicat MySQL Data Transfer

Source Server         : yg
Source Server Version : 50614
Source Host           : localhost:3306
Source Database       : yungou_assist

Target Server Type    : MYSQL
Target Server Version : 50614
File Encoding         : 65001

Date: 2016-04-01 12:09:46
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userid` varchar(40) DEFAULT NULL COMMENT '用户id',
  `password` varchar(32) DEFAULT NULL COMMENT '密码',
  `loginTime` datetime DEFAULT NULL COMMENT '最后一次登录时间',
  `loginNumber` int(11) DEFAULT '1' COMMENT '登录次数',
  `userType` int(11) DEFAULT '1' COMMENT '用户类型',
  `remarksInfo` varchar(120) DEFAULT NULL COMMENT '备注信息',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8 COMMENT='管理员表';

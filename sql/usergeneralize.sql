/*
Navicat MySQL Data Transfer

Source Server         : yg
Source Server Version : 50614
Source Host           : localhost:3306
Source Database       : yungou_assist

Target Server Type    : MYSQL
Target Server Version : 50614
File Encoding         : 65001

Date: 2016-04-01 12:10:20
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for usergeneralize
-- ----------------------------
DROP TABLE IF EXISTS `usergeneralize`;
CREATE TABLE `usergeneralize` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userid` varchar(40) DEFAULT NULL COMMENT '用户id',
  `generalizeId` int(11) DEFAULT NULL COMMENT '推广id',
  `generalizeTime` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '推广注册时间',
  `isGetRewards` int(11) DEFAULT '0' COMMENT '是否领取奖励',
  `getRewardsTime` datetime DEFAULT NULL COMMENT '领取奖励时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 COMMENT='用户推广记录';

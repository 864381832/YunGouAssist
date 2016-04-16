/*
Navicat MySQL Data Transfer

Source Server         : yg
Source Server Version : 50614
Source Host           : localhost:3306
Source Database       : yungou_assist

Target Server Type    : MYSQL
Target Server Version : 50614
File Encoding         : 65001

Date: 2016-04-01 12:10:30
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for yungou_record
-- ----------------------------
DROP TABLE IF EXISTS `yungou_record`;
CREATE TABLE `yungou_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `buyCode` int(11) DEFAULT NULL,
  `buyID` int(11) DEFAULT NULL,
  `buyNum` int(11) DEFAULT NULL COMMENT '购买数量',
  `buyTime` bigint(20) DEFAULT NULL,
  `goodsPeriod` int(11) DEFAULT NULL COMMENT '商品期数',
  `userWeb` int(11) DEFAULT NULL COMMENT '用户WebID',
  `dateSum` int(11) DEFAULT NULL COMMENT '计算数字',
  `rnoNum` mediumtext COMMENT '云购码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=417868 DEFAULT CHARSET=utf8 COMMENT='云购记录';

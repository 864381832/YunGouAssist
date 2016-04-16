/*
Navicat MySQL Data Transfer

Source Server         : yg
Source Server Version : 50614
Source Host           : localhost:3306
Source Database       : yungou_assist

Target Server Type    : MYSQL
Target Server Version : 50614
File Encoding         : 65001

Date: 2016-04-01 12:10:25
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for yungou_publish
-- ----------------------------
DROP TABLE IF EXISTS `yungou_publish`;
CREATE TABLE `yungou_publish` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `goodsID` int(11) DEFAULT NULL COMMENT '商品id',
  `codePeriod` int(11) DEFAULT NULL COMMENT '期数',
  `codeID` int(11) DEFAULT NULL COMMENT '云购id',
  `codeRNO` int(11) DEFAULT NULL COMMENT '幸运码',
  `awardPosition` int(11) DEFAULT NULL COMMENT '中奖位置',
  `userName` varchar(45) DEFAULT NULL COMMENT '中奖人姓名',
  `buyCount` int(11) DEFAULT NULL COMMENT '购买人次',
  `buyStartPosition` varchar(20) DEFAULT NULL COMMENT '购买起始位置',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=938 DEFAULT CHARSET=utf8 COMMENT='云购揭晓';

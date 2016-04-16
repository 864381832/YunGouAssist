/*
Navicat MySQL Data Transfer

Source Server         : yg
Source Server Version : 50614
Source Host           : localhost:3306
Source Database       : yungou_assist

Target Server Type    : MYSQL
Target Server Version : 50614
File Encoding         : 65001

Date: 2016-04-01 12:10:03
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for renew
-- ----------------------------
DROP TABLE IF EXISTS `renew`;
CREATE TABLE `renew` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userid` varchar(40) DEFAULT NULL COMMENT '用户id',
  `renewMoney` float DEFAULT NULL COMMENT '续费金额',
  `renewTime` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '续费时间',
  `userInfo` varchar(120) DEFAULT NULL COMMENT '代理信息',
  `isDeal` int(11) DEFAULT '0' COMMENT '是否处理',
  `dealTime` datetime DEFAULT NULL COMMENT '处理时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=107 DEFAULT CHARSET=utf8 COMMENT='续费表';

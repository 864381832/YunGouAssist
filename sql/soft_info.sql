/*
Navicat MySQL Data Transfer

Source Server         : yg
Source Server Version : 50614
Source Host           : localhost:3306
Source Database       : yungou_assist

Target Server Type    : MYSQL
Target Server Version : 50614
File Encoding         : 65001

Date: 2016-04-01 12:10:10
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for soft_info
-- ----------------------------
DROP TABLE IF EXISTS `soft_info`;
CREATE TABLE `soft_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `value` varchar(240) DEFAULT NULL,
  `value2` varchar(120) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

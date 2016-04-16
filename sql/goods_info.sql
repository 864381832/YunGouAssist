/*
Navicat MySQL Data Transfer

Source Server         : yg
Source Server Version : 50614
Source Host           : localhost:3306
Source Database       : yungou_assist

Target Server Type    : MYSQL
Target Server Version : 50614
File Encoding         : 65001

Date: 2016-04-01 12:09:53
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for goods_info
-- ----------------------------
DROP TABLE IF EXISTS `goods_info`;
CREATE TABLE `goods_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `goodsID` int(11) DEFAULT NULL COMMENT '商品id',
  `goodsSName` varchar(100) DEFAULT NULL COMMENT '商品名称',
  `codeID` int(11) DEFAULT NULL COMMENT '商品首期id',
  `codeLimitBuy` int(11) DEFAULT NULL COMMENT '限购次数',
  `goodsPic` varchar(30) DEFAULT NULL COMMENT '商品图片地址',
  `isSale` int(11) DEFAULT NULL COMMENT '是否下架',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=710 DEFAULT CHARSET=utf8 COMMENT='商品详情';

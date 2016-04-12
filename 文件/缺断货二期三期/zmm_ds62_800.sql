/*
Navicat MySQL Data Transfer

Source Server         : storefeedback_800_master
Source Server Version : 50621
Source Host           : 10.128.45.8:3306
Source Database       : storefeedback

Target Server Type    : MYSQL
Target Server Version : 50621
File Encoding         : 65001

Date: 2015-08-18 15:37:11
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `zmm_ds62`
-- ----------------------------
DROP TABLE IF EXISTS `zmm_ds62`;
CREATE TABLE `zmm_ds62` (
  `REQUEST` varchar(30) NOT NULL,
  `DATAPAKID` decimal(6,0) NOT NULL,
  `RECORD` int(11) NOT NULL,
  `SALES_GRP` varchar(3) DEFAULT NULL,
  `ZDEPTMNT` varchar(4) DEFAULT NULL,
  `ZREGION` varchar(6) DEFAULT NULL,
  `RPA_WGH2` varchar(9) DEFAULT NULL,
  `ZMAT_CAT` varchar(4) DEFAULT NULL,
  `PROD_HIER` varchar(18) DEFAULT NULL,
  `ZARTICLE` varchar(18) DEFAULT NULL,
  `CALDAY` date DEFAULT NULL,
  `ZINV_QTY` decimal(17,3) DEFAULT NULL,
  `UNIT` varchar(3) DEFAULT NULL,
  `ZKI01148` double DEFAULT NULL,
  `ZKI01153` decimal(17,3) DEFAULT NULL,
  `ZPUR_TYPE` varchar(1) DEFAULT NULL,
  `RECORDMODE` varchar(1) DEFAULT NULL,
  `ZRATE` decimal(17,3) DEFAULT NULL,
  `FLAG` varchar(1) DEFAULT NULL,
  `ZINV_AMB` decimal(17,2) DEFAULT NULL,
  `CURRENCY` varchar(5) DEFAULT NULL,
  `insert_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '写数据时间',
  PRIMARY KEY (`REQUEST`,`DATAPAKID`,`RECORD`),
  KEY `pk_date_category_buytype_brand_goods` (`CALDAY`,`ZPUR_TYPE`,`ZMAT_CAT`,`PROD_HIER`,`ZARTICLE`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of zmm_ds62
-- ----------------------------

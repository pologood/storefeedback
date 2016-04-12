/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50173
Source Host           : localhost:3306
Source Database       : storefeedback

Target Server Type    : MYSQL
Target Server Version : 50173
File Encoding         : 65001

Date: 2015-08-19 13:47:24
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `category_position`
-- ----------------------------
DROP TABLE IF EXISTS `category_position`;
CREATE TABLE `category_position` (
  `id` varchar(64) NOT NULL,
  `position_code` varchar(50) DEFAULT NULL,
  `position_desc` varchar(100) DEFAULT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `goods_category_id` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_Reference_51` (`goods_category_id`) USING BTREE,
  CONSTRAINT `category_position_ibfk_1` FOREIGN KEY (`goods_category_id`) REFERENCES `goods_category_config` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- ----------------------------
-- Table structure for `feedback_push_category_config`
-- ----------------------------
DROP TABLE IF EXISTS `feedback_push_category_config`;
CREATE TABLE `feedback_push_category_config` (
  `id` varchar(64) NOT NULL,
  `category_code` varchar(50) DEFAULT NULL,
  `category_name` varchar(100) DEFAULT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for `feedback_push_category_position_config`
-- ----------------------------
DROP TABLE IF EXISTS `feedback_push_category_position_config`;
CREATE TABLE `feedback_push_category_position_config` (
  `id` varchar(36) NOT NULL,
  `category_code` varchar(10) DEFAULT NULL,
  `category_name` varchar(20) DEFAULT NULL,
  `src_org_type` int(11) DEFAULT NULL,
  `src_org_number` varchar(36) DEFAULT NULL,
  `src_org_name` varchar(36) DEFAULT NULL,
  `is_gmzb` int(11) DEFAULT NULL,
  `org_number` varchar(36) DEFAULT NULL,
  `org_name` varchar(36) DEFAULT NULL,
  `position` varchar(36) DEFAULT NULL,
  `position_code` varchar(36) DEFAULT NULL,
  `position_desc` varchar(36) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for `goods_category_config`
-- ----------------------------
DROP TABLE IF EXISTS `goods_category_config`;
CREATE TABLE `goods_category_config` (
  `id` varchar(64) NOT NULL,
  `category_code` varchar(50) DEFAULT NULL,
  `category_name` varchar(100) DEFAULT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for `result_no_reason`
-- ----------------------------
DROP TABLE IF EXISTS `result_no_reason`;
CREATE TABLE `result_no_reason` (
  `result_no_code` int(11) NOT NULL AUTO_INCREMENT,
  `result_no_name` varchar(64) DEFAULT NULL,
  `is_using` int(11) DEFAULT NULL,
  `sort_order` int(11) DEFAULT NULL,
  PRIMARY KEY (`result_no_code`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for `sap_feedback`
-- ----------------------------
DROP TABLE IF EXISTS `sap_feedback`;
CREATE TABLE `sap_feedback` (
  `record_id` varchar(64) NOT NULL COMMENT '缺断货记录ID',
  `region_code` varchar(20) DEFAULT NULL COMMENT '大区编码',
  `region_name` varchar(100) DEFAULT NULL COMMENT '大区名称',
  `first_subsection_code` varchar(20) DEFAULT NULL COMMENT '一级分部编码',
  `first_subsection_name` varchar(100) DEFAULT NULL COMMENT '一级分部名称',
  `second_subsection_code` varchar(20) DEFAULT NULL COMMENT '二级分部编码',
  `second_subsection_name` varchar(100) DEFAULT NULL COMMENT '二级分部名称',
  `first_category_code` varchar(20) DEFAULT NULL COMMENT '一级品类编码',
  `first_category_name` varchar(100) DEFAULT NULL COMMENT '一级品类名称',
  `second_category_code` varchar(20) DEFAULT NULL COMMENT '二级品类编码',
  `second_category_name` varchar(100) DEFAULT NULL COMMENT '二级品类名称',
  `brand_code` varchar(20) DEFAULT NULL COMMENT '品牌编码',
  `brand_name` varchar(100) DEFAULT NULL COMMENT '品牌名称',
  `goods_code` varchar(20) DEFAULT NULL COMMENT '商品编码',
  `goods_name` varchar(100) DEFAULT NULL COMMENT '商品名称',
  `inventory_count` bigint(20) DEFAULT NULL COMMENT '库存数量',
  `sale_count` bigint(20) DEFAULT NULL COMMENT '销售数量',
  `sale_income` double DEFAULT NULL COMMENT '销售收入',
  `sale_order` bigint(20) DEFAULT NULL COMMENT '全国销售排名',
  `out_rate` double DEFAULT NULL COMMENT '缺货率',
  `out_order` bigint(20) DEFAULT NULL COMMENT '缺货排名',
  `is_stock` int(11) DEFAULT NULL COMMENT '是否断货',
  `purchase_type` int(11) DEFAULT NULL COMMENT '采购类型',
  PRIMARY KEY (`record_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sap_feedback
-- ----------------------------

-- ----------------------------
-- Table structure for `sap_order`
-- ----------------------------
DROP TABLE IF EXISTS `sap_order`;
CREATE TABLE `sap_order` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `order_id` varchar(10) DEFAULT NULL,
  `order_date` varchar(8) DEFAULT NULL,
  `order_content` varchar(5) DEFAULT NULL,
  `goods_code` varchar(18) DEFAULT NULL,
  `goods_cn_text` varchar(100) DEFAULT NULL,
  `order_num` bigint(13) DEFAULT NULL,
  `order_to_num` bigint(13) DEFAULT NULL,
  `last_receive_date` varchar(8) DEFAULT NULL,
  `plan_date` varchar(8) DEFAULT NULL,
  `on_the_road_num` bigint(13) DEFAULT NULL,
  `order_flag` varchar(1) DEFAULT NULL,
  `order_type` varchar(4) DEFAULT NULL,
  `place_id` varchar(4) DEFAULT NULL,
  `place_name` varchar(30) DEFAULT NULL,
  `stock_type_id` varchar(4) DEFAULT NULL,
  `stock_type_name` varchar(16) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `order_id` (`order_id`)
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sap_order
-- ----------------------------

-- ----------------------------
-- Table structure for `sapfeedback_handler`
-- ----------------------------
DROP TABLE IF EXISTS `sapfeedback_handler`;
CREATE TABLE `sapfeedback_handler` (
  `id` varchar(64) NOT NULL,
  `REQUEST` varchar(30) DEFAULT NULL,
  `DATAPAKID` decimal(10,0) DEFAULT NULL,
  `RECORD` int(11) DEFAULT NULL,
  `is_push` int(11) DEFAULT NULL,
  `push_time` datetime DEFAULT NULL,
  `is_handler` int(11) DEFAULT NULL,
  `handler_result` int(11) DEFAULT NULL,
  `result_no_code` int(11) DEFAULT NULL,
  `result_no_name` varchar(64) DEFAULT NULL,
  `result_yes_order_num` int(11) DEFAULT NULL,
  `handler_emp_number` varchar(8) DEFAULT NULL,
  `handler_emp_name` varchar(20) DEFAULT NULL,
  `handler_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_Reference_26` (`REQUEST`,`DATAPAKID`,`RECORD`),
  KEY `FK_fk_reason_no_dict_sapfeedback_result` (`result_no_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sapfeedback_handler
-- ----------------------------

-- ----------------------------
-- Table structure for `sapfeedback_push`
-- ----------------------------
DROP TABLE IF EXISTS `sapfeedback_push`;
CREATE TABLE `sapfeedback_push` (
  `id` varchar(36) NOT NULL,
  `push_data_date` date DEFAULT NULL,
  `push_category` varchar(10) DEFAULT NULL,
  `push_org` varchar(20) DEFAULT NULL,
  `push_position` varchar(20) DEFAULT NULL,
  `push_content` varchar(5000) DEFAULT NULL,
  `push_emp_id` varchar(36) DEFAULT NULL,
  `push_emp_number` varchar(8) DEFAULT NULL,
  `push_emp_name` varchar(20) DEFAULT NULL,
  `push_time` datetime DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sapfeedback_push
-- ----------------------------

-- ----------------------------
-- Table structure for `sapfeedback_result_order`
-- ----------------------------
DROP TABLE IF EXISTS `sapfeedback_result_order`;
CREATE TABLE `sapfeedback_result_order` (
  `id` varchar(64) NOT NULL,
  `order_id` varchar(10) NOT NULL,
  `sap_order_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sapfeedback_result_order
-- ----------------------------

-- ----------------------------
-- Table structure for `t_new_category`
-- ----------------------------
DROP TABLE IF EXISTS `t_new_category`;
CREATE TABLE `t_new_category` (
  `id` varchar(64) NOT NULL,
  `report_employee_id` varchar(10) DEFAULT NULL,
  `report_company_id` varchar(64) DEFAULT NULL,
  `report_employee_name` varchar(30) DEFAULT NULL,
  `category_code` varchar(20) DEFAULT NULL,
  `category_desc` varchar(60) DEFAULT NULL,
  `model_code` varchar(30) DEFAULT NULL,
  `model_desc` varchar(60) DEFAULT NULL,
  `main_desc` varchar(100) DEFAULT NULL,
  `dealer` varchar(100) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `is_push` char(1) DEFAULT NULL,
  `is_handle` char(1) DEFAULT NULL COMMENT 'Y 已处理 N 未处理',
  `is_import` char(1) DEFAULT NULL COMMENT 'Y 已引进 N 未引进',
  `not_import_desc` varchar(100) DEFAULT NULL,
  `not_import_code` varchar(30) DEFAULT NULL,
  `googs_code` varchar(10) DEFAULT NULL,
  `Handle_employee_id` varchar(10) DEFAULT NULL,
  `Handle_employee_name` varchar(30) DEFAULT NULL,
  `handle_company_id` varchar(64) DEFAULT NULL,
  `handle_time` timestamp NULL DEFAULT NULL,
  `import_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_Reference_21` (`not_import_code`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_new_category
-- ----------------------------

-- ----------------------------
-- Table structure for `t_new_category_rel`
-- ----------------------------
DROP TABLE IF EXISTS `t_new_category_rel`;
CREATE TABLE `t_new_category_rel` (
  `id` varchar(64) NOT NULL,
  `handle_emp_id` varchar(10) NOT NULL,
  PRIMARY KEY (`id`,`handle_emp_id`),
  KEY `FK_Reference_18` (`handle_emp_id`) USING BTREE,
  CONSTRAINT `t_new_category_rel_ibfk_1` FOREIGN KEY (`handle_emp_id`) REFERENCES `t_new_category` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_new_category_rel
-- ----------------------------

-- ----------------------------
-- Table structure for `t_new_category_status`
-- ----------------------------
DROP TABLE IF EXISTS `t_new_category_status`;
CREATE TABLE `t_new_category_status` (
  `table_name` varchar(20) DEFAULT NULL,
  `column_name` varchar(20) DEFAULT NULL,
  `id` varchar(64) NOT NULL,
  `reason_desc` varchar(64) DEFAULT NULL,
  `sts` char(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for `t_new_img`
-- ----------------------------
DROP TABLE IF EXISTS `t_new_img`;
CREATE TABLE `t_new_img` (
  `id` varchar(64) NOT NULL,
  `category_model_id` varchar(64) DEFAULT NULL,
  `type` varchar(64) DEFAULT NULL,
  `img_info` mediumtext,
  `img_desc` varchar(64) DEFAULT NULL,
  `sts` char(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_new_img
-- ----------------------------

-- ----------------------------
-- Table structure for `t_new_message_push_record`
-- ----------------------------
DROP TABLE IF EXISTS `t_new_message_push_record`;
CREATE TABLE `t_new_message_push_record` (
  `id` varchar(64) NOT NULL,
  `title` varchar(20) DEFAULT NULL,
  `content` varchar(60) DEFAULT NULL,
  `type` varchar(4) DEFAULT NULL,
  `notification_id` varchar(64) DEFAULT NULL,
  `notification_employee_id` varchar(64) DEFAULT NULL,
  `notification_employee_name` varchar(10) DEFAULT NULL,
  `notification_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `report_id` varchar(60) DEFAULT NULL,
  `report_emp_id` varchar(60) DEFAULT NULL,
  `report_emp_name` varchar(60) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_new_message_push_record
-- ----------------------------

-- ----------------------------
-- Table structure for `t_new_model`
-- ----------------------------
DROP TABLE IF EXISTS `t_new_model`;
CREATE TABLE `t_new_model` (
  `id` varchar(64) NOT NULL,
  `report_employee_id` varchar(10) DEFAULT NULL,
  `report_company_id` varchar(64) DEFAULT NULL,
  `report_employee_name` varchar(30) DEFAULT NULL,
  `category_code` varchar(20) DEFAULT NULL,
  `category_desc` varchar(60) DEFAULT NULL,
  `brand_code` varchar(60) DEFAULT NULL,
  `brand_name` varchar(60) DEFAULT NULL,
  `model_code` varchar(30) DEFAULT NULL,
  `model_desc` varchar(60) DEFAULT NULL,
  `main_desc` varchar(100) DEFAULT NULL,
  `dealer` varchar(100) DEFAULT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `is_push` char(1) DEFAULT NULL,
  `is_handle` char(1) DEFAULT NULL COMMENT 'Y 已处理 N 未处理',
  `is_import` char(1) DEFAULT NULL COMMENT 'Y 已引进 N 未引进',
  `not_import_desc` varchar(100) DEFAULT NULL,
  `not_import_code` varchar(30) DEFAULT NULL,
  `googs_code` varchar(10) DEFAULT NULL,
  `Handle_employee_id` varchar(10) DEFAULT NULL,
  `Handle_employee_name` varchar(30) DEFAULT NULL,
  `handle_company_id` varchar(64) DEFAULT NULL,
  `handle_time` timestamp NULL DEFAULT NULL,
  `import_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_new_model
-- ----------------------------

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


DROP TABLE IF EXISTS `person`;
CREATE TABLE `person` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `address` varchar(100) DEFAULT NULL,
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` varchar(64) NOT NULL,
  `username` varchar(20) DEFAULT NULL,
  `password` varchar(30) DEFAULT NULL,
  `name` varchar(10) DEFAULT NULL,
  `is_admin` tinyint(4) DEFAULT NULL,
  `startus` tinyint(4) DEFAULT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `user` VALUES ('1', 'admin', 'ISMvKXpXpadDiUoOSoAfww==', 'admin', '1', '1', '2015-03-19 14:16:17');

DROP TABLE IF EXISTS `region`;
CREATE TABLE `region` (
  `region_code` varchar(20) NOT NULL,
  `region_des` varchar(40) DEFAULT NULL,
  `update_flag` varchar(5) DEFAULT NULL,
  PRIMARY KEY (`region_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `division`;
CREATE TABLE `division` (
  `division_code` varchar(20) NOT NULL,
  `division_desc` varchar(40) DEFAULT NULL,
  `region_code` varchar(20) DEFAULT NULL,
  `update_flag` varchar(5) DEFAULT NULL,
  PRIMARY KEY (`division_code`),
  KEY `FK_Reference_11` (`region_code`),
  CONSTRAINT `FK_Reference_11` FOREIGN KEY (`region_code`) REFERENCES `region` (`region_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `second_division`;
CREATE TABLE `second_division` (
  `second_division_code` varchar(20) NOT NULL,
  `second_division_des` varchar(40) DEFAULT NULL,
  `first_division_code` varchar(20) DEFAULT NULL,
  `update_flag` varchar(5) DEFAULT NULL,
  PRIMARY KEY (`second_division_code`),
  KEY `FK_Reference_15` (`first_division_code`),
  CONSTRAINT `FK_Reference_15` FOREIGN KEY (`first_division_code`) REFERENCES `division` (`division_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `store`;
CREATE TABLE `store` (
  `store_code` varchar(10) NOT NULL,
  `store_name` varchar(30) DEFAULT NULL,
  `sale_org` varchar(10) DEFAULT NULL,
  `company_code` varchar(10) DEFAULT NULL,
  `second_division_code` varchar(10) DEFAULT NULL,
  `second_division_des` varchar(20) DEFAULT NULL,
  `division_code` varchar(10) DEFAULT NULL,
  `division_des` varchar(20) DEFAULT NULL,
  `store_address` varchar(30) DEFAULT NULL,
  `store_tel` varchar(30) DEFAULT NULL,
  `update_flag` varchar(5) DEFAULT NULL,
  PRIMARY KEY (`store_code`),
  KEY `FK_Reference_13` (`second_division_code`),
  KEY `FK_Reference_14` (`division_code`),
  CONSTRAINT `FK_Reference_13` FOREIGN KEY (`second_division_code`) REFERENCES `second_division` (`second_division_code`),
  CONSTRAINT `FK_Reference_14` FOREIGN KEY (`division_code`) REFERENCES `division` (`division_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `goods_category`;
CREATE TABLE `goods_category` (
  `class_code` varchar(20) NOT NULL,
  `class_name` varchar(200) DEFAULT NULL,
  `class_level` varchar(6) DEFAULT NULL,
  `parent_class_code` varchar(20) DEFAULT NULL,
  `is_leaf` tinyint(4) DEFAULT NULL,
  `division_code` varchar(20) DEFAULT NULL,
  `division_name` varchar(60) DEFAULT NULL,
  `update_flag` varchar(5) DEFAULT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`class_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `goods_brand`;
CREATE TABLE `goods_brand` (
  `brand_code` varchar(20) NOT NULL,
  `cn_text` varchar(40) DEFAULT NULL,
  `en_text` varchar(40) DEFAULT NULL,
  `brand_class` varchar(20) DEFAULT NULL,
  `update_flag` varchar(5) DEFAULT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`brand_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods` (
  `goods_code` varchar(40) NOT NULL,
  `goods_barcode` varchar(20) DEFAULT NULL,
  `goods_name` varchar(200) DEFAULT NULL,
  `specifications_model` varchar(60) DEFAULT NULL,
  `goods_category` varchar(20) DEFAULT NULL,
  `goods_brand` varchar(20) DEFAULT NULL,
  `extended_warranty_price_floor` varchar(20) DEFAULT NULL,
  `extended_warranty_price_cap` varchar(20) DEFAULT NULL,
  `output_tax_rate` varchar(10) DEFAULT NULL,
  `units_of_measurement` varchar(10) DEFAULT NULL,
  `unit_of_measure_text` varchar(40) DEFAULT NULL,
  `whether_business_gifts` tinyint(4) DEFAULT NULL,
  `lot_id` varchar(2) DEFAULT NULL,
  `product_attributes` varchar(4) DEFAULT NULL,
  `goods_class` varchar(10) DEFAULT NULL,
  `place_of_origin` varchar(20) DEFAULT NULL,
  `goods_weight` varchar(20) DEFAULT NULL,
  `goods_height` varchar(20) DEFAULT NULL,
  `category_level1` varchar(20) DEFAULT NULL,
  `category_level2` varchar(20) DEFAULT NULL,
  `category_level3` varchar(20) DEFAULT NULL,
  `selling` varchar(300) DEFAULT NULL,
  `update_flag` varchar(5) DEFAULT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`goods_code`),
  KEY `FK_Reference_1` (`goods_brand`),
  CONSTRAINT `FK_Reference_1` FOREIGN KEY (`goods_brand`) REFERENCES `goods_brand` (`brand_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `feedback`;
CREATE TABLE `feedback` (
  `id` varchar(64) NOT NULL,
  `sponsor_id` varchar(64) DEFAULT NULL,
  `sponsor_employee_id` varchar(64) DEFAULT NULL,
  `sponsor_employee_name` varchar(10) DEFAULT NULL,
  `sponsor_company_id` varchar(64) DEFAULT NULL,
  `first_category` varchar(20) DEFAULT NULL,
  `second_category` varchar(20) DEFAULT NULL,
  `brand_code` varchar(20) DEFAULT NULL,
  `goods_code` varchar(40) DEFAULT NULL,
  `lack_category` varchar(6) DEFAULT NULL,
  `anticipated_dates_sold_out` varchar(20) DEFAULT NULL,
  `sale_out_date` int(11) DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `store_code` varchar(64) DEFAULT NULL,
  `has_return` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_Reference_5` (`lack_category`),
  KEY `FK_Reference_8` (`goods_code`),
  KEY `FK_Reference_9` (`brand_code`),
  CONSTRAINT `FK_Reference_5` FOREIGN KEY (`lack_category`) REFERENCES `lack_category` (`category_code`),
  CONSTRAINT `FK_Reference_8` FOREIGN KEY (`goods_code`) REFERENCES `goods` (`goods_code`),
  CONSTRAINT `FK_Reference_9` FOREIGN KEY (`brand_code`) REFERENCES `goods_brand` (`brand_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `feedback_reciever`;
CREATE TABLE `feedback_reciever` (
  `feedback_id` varchar(64) NOT NULL,
  `feedback_person_id` varchar(64) NOT NULL,
  PRIMARY KEY (`feedback_id`,`feedback_person_id`),
  CONSTRAINT `FK_Reference_16` FOREIGN KEY (`feedback_id`) REFERENCES `feedback` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `feedback_record`;
CREATE TABLE `feedback_record` (
  `id` varchar(64) NOT NULL,
  `feedback_person_employee_id` varchar(64) DEFAULT NULL,
  `feedback_person_employee_name` varchar(10) DEFAULT NULL,
  `feedback_person_id` varchar(64) DEFAULT NULL,
  `feedback_content` varchar(200) DEFAULT NULL,
  `feedback_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `feedback_id` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_Reference_10` (`feedback_id`),
  CONSTRAINT `FK_Reference_10` FOREIGN KEY (`feedback_id`) REFERENCES `feedback` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `feedback_message_push_record`;
CREATE TABLE `feedback_message_push_record` (
  `id` varchar(64) NOT NULL,
  `title` varchar(20) DEFAULT NULL,
  `content` varchar(60) DEFAULT NULL,
  `type` tinyint(4) DEFAULT NULL,
  `notification_id` varchar(64) DEFAULT NULL COMMENT '接收人',
  `notification_employee_id` varchar(64) DEFAULT NULL COMMENT '接收人员工号',
  `notification_employee_name` varchar(10) DEFAULT NULL COMMENT '接收人姓名',
  `notification_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `send_id` varchar(64) DEFAULT NULL COMMENT '发送人主键id.2中类型，app系统触发，人工',
  `send_employee_id` varchar(64) DEFAULT NULL COMMENT '发送人员工号',
  `send_employee_name` varchar(64) DEFAULT NULL COMMENT '发送人姓名',
  `feedback_id` varchar(64) DEFAULT NULL COMMENT '缺货记录ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `lack_category`;
CREATE TABLE `lack_category` (
  `category_code` varchar(6) NOT NULL,
  `category_name` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`category_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `lack_category` VALUES ('1', '门店');
INSERT INTO `lack_category` VALUES ('2', '配送');

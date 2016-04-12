DROP TABLE IF EXISTS `lack_division`;
CREATE TABLE `lack_division` (
  `category_code` varchar(6) NOT NULL,
  `category_name` varchar(10) DEFAULT NULL,
  `division_code` varchar(6) NOT NULL,
  `division_name` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `lack_division` VALUES ('1', '门店', '0010', 'OA');
INSERT INTO `lack_division` VALUES ('1', '门店', '0009', '电脑');
INSERT INTO `lack_division` VALUES ('1', '门店', '0008', '数码');
INSERT INTO `lack_division` VALUES ('1', '门店', '0007', '通讯');
INSERT INTO `lack_division` VALUES ('2', '配送', '0006', '小家电');
INSERT INTO `lack_division` VALUES ('2', '配送', '0005', '厨卫');
INSERT INTO `lack_division` VALUES ('2', '配送', '0004', '空调');
INSERT INTO `lack_division` VALUES ('2', '配送', '0003', '冰洗');
INSERT INTO `lack_division` VALUES ('2', '配送', '0002', '音响');
INSERT INTO `lack_division` VALUES ('2', '配送', '0001', '彩电');

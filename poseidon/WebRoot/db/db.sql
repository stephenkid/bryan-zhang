/*
Navicat MySQL Data Transfer

Source Server         : poseidon
Source Server Version : 50508
Source Host           : localhost:3306
Source Database       : poseidon

Target Server Type    : MYSQL
Target Server Version : 50508
File Encoding         : 65001

Date: 2011-01-19 16:23:35
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `t_login`
-- ----------------------------
DROP TABLE IF EXISTS `t_login`;
CREATE TABLE `t_login` (
  `login_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `login_email` varchar(40) NOT NULL,
  `login_password` varchar(100) NOT NULL,
  `login_name` varchar(40) NOT NULL,
  `telephone` varchar(40) DEFAULT NULL,
  `mobile` varchar(40) DEFAULT NULL,
  `address` varchar(100) DEFAULT NULL,
  `tax` varchar(40) DEFAULT NULL,
  `is_avail` bit(1) DEFAULT NULL,
  `input_date` datetime DEFAULT NULL,
  `edit_date` datetime DEFAULT NULL,
  `memo` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`login_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_login
-- ----------------------------
INSERT INTO `t_login` VALUES ('1', 'root@163.com', '111111', 'root', null, null, null, null, null, null, null, null);

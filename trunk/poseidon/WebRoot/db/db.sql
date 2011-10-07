

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


-- ----------------------------
-- Table structure for t_person
-- ----------------------------
CREATE TABLE `t_person` (
  `id` bigint(20) NOT NULL auto_increment,
  `name` varchar(20) default NULL,
  `age` int(11) default NULL,
  `address` varchar(50) default NULL,
  `mobile` varchar(20) default NULL,
  `email` varchar(50) default NULL,
  `company` varchar(50) default NULL,
  `title` varchar(50) default NULL,
  `create_time` datetime default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

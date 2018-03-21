/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 50096
Source Host           : localhost:3306
Source Database       : act

Target Server Type    : MYSQL
Target Server Version : 50096
File Encoding         : 65001

Date: 2018-02-10 17:14:37
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for emp
-- ----------------------------
DROP TABLE IF EXISTS `emp`;
CREATE TABLE `emp` (
  `id` varchar(32) NOT NULL,
  `name` varchar(12) default NULL,
  `age` int(3) default NULL,
  `create_date` datetime default NULL,
  `update_date` datetime default NULL,
  `del_flag` varchar(2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

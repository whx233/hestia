/*
Navicat MySQL Data Transfer

Source Server         : whx
Source Server Version : 50626
Source Host           : localhost:3306
Source Database       : jason

Target Server Type    : MYSQL
Target Server Version : 50626
File Encoding         : 65001

Date: 2018-08-08 16:17:09
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `system_menu`
-- ----------------------------
DROP TABLE IF EXISTS `system_menu`;
CREATE TABLE `system_menu` (
  `menu_id` varchar(20) NOT NULL COMMENT '菜单ID',
  `path` varchar(100) DEFAULT NULL COMMENT '请求路径',
  `name` varchar(50) DEFAULT NULL COMMENT '菜单名称',
  `component` varchar(100) DEFAULT NULL COMMENT '显示组件',
  `is_leaf` tinyint(1) DEFAULT NULL COMMENT '是否叶子',
  `auto_expand` tinyint(1) DEFAULT NULL COMMENT '是否展开',
  `hidden` tinyint(1) DEFAULT NULL COMMENT '是否隐藏',
  `parents_id` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of system_menu
-- ----------------------------
INSERT INTO `system_menu` VALUES ('0', '/root', '根目录', 'root', '0', '0', '0', '0');
INSERT INTO `system_menu` VALUES ('1', '/abc', '菜单DEMO', 'demo', '0', '0', '0', '0');
INSERT INTO `system_menu` VALUES ('2', '/abcd', 'form表单', 'form', '1', '0', '0', '1');
INSERT INTO `system_menu` VALUES ('3', '/efg', 'table表格', 'table', '1', '0', '0', '1');
INSERT INTO `system_menu` VALUES ('4', '/xtgl', '系统管理', 'xtgl', '0', '0', '0', '0');
INSERT INTO `system_menu` VALUES ('5', '/ncjk', '内存监控', 'sss', '1', '0', '0', '4');
INSERT INTO `system_menu` VALUES ('6', '/pp', '登陆用户', 'eee', '1', '0', '0', '4');

-- ----------------------------
-- Table structure for `system_role`
-- ----------------------------
DROP TABLE IF EXISTS `system_role`;
CREATE TABLE `system_role` (
  `role_id` tinyint(3) NOT NULL DEFAULT '0',
  `role_name` varchar(50) NOT NULL,
  `token` varchar(30) NOT NULL,
  `state` varchar(1) DEFAULT NULL COMMENT '1为有效',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of system_role
-- ----------------------------
INSERT INTO `system_role` VALUES ('1', '超级管理员', 'admin', '1');
INSERT INTO `system_role` VALUES ('2', '信息中心', 'tech', '1');
INSERT INTO `system_role` VALUES ('3', '编辑部', 'editor', '1');
INSERT INTO `system_role` VALUES ('4', '后勤部', 'hq', '1');

-- ----------------------------
-- Table structure for `system_role_menu`
-- ----------------------------
DROP TABLE IF EXISTS `system_role_menu`;
CREATE TABLE `system_role_menu` (
  `role_id` varchar(5) NOT NULL,
  `menu_id` varchar(20) NOT NULL DEFAULT '',
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`role_id`,`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of system_role_menu
-- ----------------------------
INSERT INTO `system_role_menu` VALUES ('1', '0', '2018-07-06 16:05:55');
INSERT INTO `system_role_menu` VALUES ('1', '1', '2018-07-06 16:06:03');
INSERT INTO `system_role_menu` VALUES ('1', '2', '2018-07-06 16:06:11');
INSERT INTO `system_role_menu` VALUES ('1', '3', '2018-07-06 17:40:06');
INSERT INTO `system_role_menu` VALUES ('1', '4', '2018-07-06 17:42:50');
INSERT INTO `system_role_menu` VALUES ('1', '5', '2018-07-06 17:43:00');
INSERT INTO `system_role_menu` VALUES ('1', '6', '2018-07-06 17:43:07');

-- ----------------------------
-- Table structure for `system_role_user`
-- ----------------------------
DROP TABLE IF EXISTS `system_role_user`;
CREATE TABLE `system_role_user` (
  `user_role_id` int(11) NOT NULL DEFAULT '0',
  `user_id` bigint(16) DEFAULT NULL,
  `role_id` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`user_role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of system_role_user
-- ----------------------------

-- ----------------------------
-- Table structure for `system_sequence`
-- ----------------------------
DROP TABLE IF EXISTS `system_sequence`;
CREATE TABLE `system_sequence` (
  `seq_type` varchar(20) NOT NULL DEFAULT '',
  `current_num` bigint(16) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`seq_type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of system_sequence
-- ----------------------------
INSERT INTO `system_sequence` VALUES ('SYSTEM', '9', '2018-07-06 15:47:30');

-- ----------------------------
-- Table structure for `system_users`
-- ----------------------------
DROP TABLE IF EXISTS `system_users`;
CREATE TABLE `system_users` (
  `user_id` bigint(12) NOT NULL DEFAULT '0',
  `user_name` varchar(50) NOT NULL,
  `nick_name` varchar(50) NOT NULL,
  `password` varchar(80) DEFAULT NULL,
  `state` varchar(2) DEFAULT NULL COMMENT '1为有效',
  `introduction` varchar(500) DEFAULT NULL COMMENT '介绍',
  `avatar` varchar(600) DEFAULT NULL COMMENT '头像',
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of system_users
-- ----------------------------
INSERT INTO `system_users` VALUES ('1', 'whx', '狂奔的大象', '123', '1', 'whx的自我介绍', 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif', '2018-08-08 10:22:02');
INSERT INTO `system_users` VALUES ('2', 'abc', '取个名字', '111', '1', 'abc的自动我救赎', null, '2018-08-08 10:44:12');
INSERT INTO `system_users` VALUES ('3', 'kk', '张三疯', 'ab', '1', 'kk是我', null, '2018-08-08 10:44:10');

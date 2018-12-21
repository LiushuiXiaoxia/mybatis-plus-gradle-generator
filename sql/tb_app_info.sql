/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50724
 Source Host           : localhost:3306
 Source Schema         : mpg_test

 Target Server Type    : MySQL
 Target Server Version : 50724
 File Encoding         : 65001

 Date: 21/12/2018 17:45:59
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_app_info
-- ----------------------------
DROP TABLE IF EXISTS `tb_app_info`;
CREATE TABLE `tb_app_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `pkg_name` varchar(100) NOT NULL COMMENT 'package name',
  `platform` varchar(20) NOT NULL COMMENT '平台类型，Android，iOS',
  `app_name` varchar(50) NOT NULL COMMENT 'app name',
  `device` int(11) DEFAULT NULL COMMENT '设备 1：Phone 2：Pad 3：TV 4：WEB',
  `create_by` varchar(50) DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` varchar(50) DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `is_delete` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`),
  KEY `idx_pkg_name_plaform` (`pkg_name`,`platform`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='App 信息表';

SET FOREIGN_KEY_CHECKS = 1;

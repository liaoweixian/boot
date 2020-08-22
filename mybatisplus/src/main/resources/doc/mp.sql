/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50727
 Source Host           : localhost:3306
 Source Schema         : mp

 Target Server Type    : MySQL
 Target Server Version : 50727
 File Encoding         : 65001

 Date: 22/08/2020 22:03:07
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `name` varchar(30) DEFAULT NULL COMMENT '姓名',
  `age` int(11) DEFAULT NULL COMMENT '年龄',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `manager_id` bigint(20) DEFAULT NULL COMMENT '直属上级id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `manager_fk` (`manager_id`),
  CONSTRAINT `manager_fk` FOREIGN KEY (`manager_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
BEGIN;
INSERT INTO `user` VALUES (1087982257332887553, '大boss', 40, 'boss@baomidou.com', NULL, '2019-01-11 14:20:20');
INSERT INTO `user` VALUES (1088248166370832385, '王天风', 26, '999999@qq.com', 1087982257332887553, '2019-02-05 11:12:22');
INSERT INTO `user` VALUES (1088250446457389058, '李艺伟', 30, 'baomidou@qq.com', 1088248166370832385, '2019-02-14 08:31:16');
INSERT INTO `user` VALUES (1094590409767661570, '张雨琪', 31, 'zjq@baomidou.com', 1088248166370832385, '2019-01-14 09:15:15');
INSERT INTO `user` VALUES (1094592041087729666, '刘红雨', 32, 'lhm@baomidou.com', 1088248166370832385, '2019-01-14 09:48:16');
INSERT INTO `user` VALUES (1296457014901297154, '刘明强', 31, NULL, 1088248166370832385, '2020-08-20 14:40:14');
INSERT INTO `user` VALUES (1296458461562847233, '向北', 26, NULL, 1088248166370832385, '2020-08-20 14:45:59');
INSERT INTO `user` VALUES (1296459929158234114, '向南', 27, '965932944@qq.com', 1088248166370832385, '2020-08-20 14:51:49');
INSERT INTO `user` VALUES (1296460398127562753, '向东', 27, '965932944@qq.com', 1088248166370832385, '2020-08-20 14:53:41');
INSERT INTO `user` VALUES (1296461767311994882, '向前', 25, '965932944@qq.com', 1088248166370832385, '2020-08-20 14:59:07');
INSERT INTO `user` VALUES (1297107296295395329, '向化', 23, '965932944@qq.com', 1088248166370832385, '2020-08-22 09:44:13');
INSERT INTO `user` VALUES (1297158948557725698, '向化', 23, '965932944@qq.com', 1088248166370832385, '2020-08-22 13:09:28');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;

/*
 Navicat Premium Data Transfer

 Source Server         : localmysql
 Source Server Type    : MySQL
 Source Server Version : 80012
 Source Host           : localhost:3306
 Source Schema         : meow

 Target Server Type    : MySQL
 Target Server Version : 80012
 File Encoding         : 65001

 Date: 30/11/2018 22:48:58
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for xx
-- ----------------------------
DROP TABLE IF EXISTS `xx`;
CREATE TABLE `xx` (
  `id` int(11) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `color` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of xx
-- ----------------------------
BEGIN;
INSERT INTO `xx` VALUES (1, '可可', 1, '奶牛');
INSERT INTO `xx` VALUES (2, '大花', 2, '大橘');
INSERT INTO `xx` VALUES (3, '喵喵', 2, '蓝猫');
INSERT INTO `xx` VALUES (4, '哼哼', 3, '蓝猫');
INSERT INTO `xx` VALUES (6, '汪汪', 2, '大橘');
INSERT INTO `xx` VALUES (7, 'kk', 1, '白猫');
INSERT INTO `xx` VALUES (8, '喵', 2, '蓝猫');
INSERT INTO `xx` VALUES (9, '喵喵喵', 2, '蓝猫');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;

/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50731
 Source Host           : localhost:3306
 Source Schema         : CarManagment

 Target Server Type    : MySQL
 Target Server Version : 50731
 File Encoding         : 65001

 Date: 31/10/2020 22:24:58
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for userList
-- ----------------------------
DROP TABLE IF EXISTS `userList`;
CREATE TABLE `userList` (
  `userName` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `administrators` tinyint(1) NOT NULL,
  PRIMARY KEY (`userName`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of userList
-- ----------------------------
BEGIN;
INSERT INTO `userList` VALUES ('233', '233', 1);
INSERT INTO `userList` VALUES ('wangzihan', 'root', 0);
INSERT INTO `userList` VALUES ('zhaoying', '001116', 1);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;

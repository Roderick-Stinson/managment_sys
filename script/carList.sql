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

 Date: 31/10/2020 22:24:49
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for carList
-- ----------------------------
DROP TABLE IF EXISTS `carList`;
CREATE TABLE `carList` (
  `carId` int(11) NOT NULL AUTO_INCREMENT,
  `carManufactory` varchar(255) NOT NULL,
  `carModel` varchar(255) NOT NULL,
  `carPrice` int(11) NOT NULL,
  `isAvaiable` tinyint(1) NOT NULL,
  PRIMARY KEY (`carId`)
) ENGINE=InnoDB AUTO_INCREMENT=465 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of carList
-- ----------------------------
BEGIN;
INSERT INTO `carList` VALUES (455, 'asdfsdf', 'fdsfasdf', 444, 1);
INSERT INTO `carList` VALUES (457, 'hongqi', 'H5', 444, 1);
INSERT INTO `carList` VALUES (463, 'fdsafasdf', 'fsdafasdf', 33333, 1);
INSERT INTO `carList` VALUES (464, 'bbbb', 'bbb', 333, 1);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;

/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80018
 Source Host           : localhost:3306
 Source Schema         : zhoudaxiao

 Target Server Type    : MySQL
 Target Server Version : 80018
 File Encoding         : 65001

 Date: 11/01/2020 21:37:49
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
  `id` int(1) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `priority` int(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1014 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of category
-- ----------------------------
BEGIN;
INSERT INTO `category` VALUES (0, '其他', -1);
INSERT INTO `category` VALUES (1, '口红', 3);
INSERT INTO `category` VALUES (1013, '鞋子', 2);
COMMIT;

-- ----------------------------
-- Table structure for image
-- ----------------------------
DROP TABLE IF EXISTS `image`;
CREATE TABLE `image` (
  `id` int(1) unsigned NOT NULL AUTO_INCREMENT,
  `productId` int(1) unsigned DEFAULT NULL,
  `url` char(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `isCarousel` tinyint(1) unsigned DEFAULT NULL,
  `isAvatar` tinyint(1) unsigned DEFAULT NULL,
  `priority` int(1) DEFAULT NULL,
  `bucketKey` char(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=125 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of image
-- ----------------------------
BEGIN;
INSERT INTO `image` VALUES (114, NULL, 'https://yiseven-dev-1300292849.cos.ap-guangzhou.myqcloud.com/carousel/2020-01-11-20:11:03-1.jpg', 1, 0, 0, 'carousel/2020-01-11-20:11:03-1.jpg');
INSERT INTO `image` VALUES (115, NULL, 'https://yiseven-dev-1300292849.cos.ap-guangzhou.myqcloud.com/carousel/2020-01-11-20:11:08-2.png', 1, 0, 0, 'carousel/2020-01-11-20:11:08-2.png');
INSERT INTO `image` VALUES (116, NULL, 'https://yiseven-dev-1300292849.cos.ap-guangzhou.myqcloud.com/carousel/2020-01-11-20:11:12-3.jpeg', 1, 0, 0, 'carousel/2020-01-11-20:11:12-3.jpeg');
INSERT INTO `image` VALUES (117, NULL, 'https://yiseven-dev-1300292849.cos.ap-guangzhou.myqcloud.com/carousel/2020-01-11-20:11:15-4.jpg', 1, 0, 0, 'carousel/2020-01-11-20:11:15-4.jpg');
INSERT INTO `image` VALUES (118, NULL, 'https://yiseven-dev-1300292849.cos.ap-guangzhou.myqcloud.com/carousel/2020-01-11-20:11:20-5.jpeg', 1, 0, 0, 'carousel/2020-01-11-20:11:20-5.jpeg');
INSERT INTO `image` VALUES (119, NULL, 'https://yiseven-dev-1300292849.cos.ap-guangzhou.myqcloud.com/carousel/2020-01-11-20:11:24-6.jpg', 1, 0, 0, 'carousel/2020-01-11-20:11:24-6.jpg');
INSERT INTO `image` VALUES (122, 45, 'https://yiseven-dev-1300292849.cos.ap-guangzhou.myqcloud.com/product/2020-01-11-21:18:43-2.png', 0, 1, 2, 'product/2020-01-11-21:18:43-2.png');
INSERT INTO `image` VALUES (123, 45, 'https://yiseven-dev-1300292849.cos.ap-guangzhou.myqcloud.com/product/2020-01-11-21:18:48-5.jpeg', 0, 0, 1, 'product/2020-01-11-21:18:48-5.jpeg');
INSERT INTO `image` VALUES (124, 46, 'https://yiseven-dev-1300292849.cos.ap-guangzhou.myqcloud.com/product/2020-01-11-21:24:20-4.jpg', 0, 1, 1, 'product/2020-01-11-21:24:20-4.jpg');
COMMIT;

-- ----------------------------
-- Table structure for person
-- ----------------------------
DROP TABLE IF EXISTS `person`;
CREATE TABLE `person` (
  `id` int(1) unsigned NOT NULL AUTO_INCREMENT,
  `username` char(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `email` char(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `phone` char(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `role` int(1) NOT NULL,
  `createDate` datetime DEFAULT NULL,
  `lastUpdateDate` datetime DEFAULT NULL,
  `status` int(1) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of person
-- ----------------------------
BEGIN;
INSERT INTO `person` VALUES (26, '邓锦辉', 'f715334e8ea49b97642a3c64ac3e406d', '543527507@qq.com', '18819258246', 1, '2019-10-08 14:33:57', '2019-10-08 14:33:57', 1);
INSERT INTO `person` VALUES (30, '123', 'd9b1d7db4cd6e70935368a1efb10e377', '123@qq.com', '123', 0, '2020-01-11 06:07:38', '2020-01-11 06:07:38', 1);
COMMIT;

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
  `id` int(1) unsigned NOT NULL AUTO_INCREMENT,
  `name` char(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `categoryId` int(1) unsigned DEFAULT NULL,
  `brandId` int(1) unsigned DEFAULT NULL,
  `introduction` char(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `detail` text,
  `originalPrice` decimal(11,2) DEFAULT NULL,
  `discountPrice` decimal(11,2) DEFAULT NULL,
  `agentPrice` decimal(11,2) DEFAULT NULL,
  `secondAgentPrice` decimal(11,2) DEFAULT NULL,
  `priority` int(1) DEFAULT NULL,
  `pageviews` int(1) unsigned DEFAULT NULL,
  `like` int(1) unsigned DEFAULT NULL,
  `totalSales` int(1) unsigned DEFAULT NULL,
  `monthlySales` int(1) unsigned DEFAULT NULL,
  `freight` decimal(11,2) DEFAULT NULL,
  `stock` int(1) DEFAULT NULL,
  `isNew` tinyint(1) unsigned DEFAULT NULL,
  `createBy` varchar(255) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `lastUpdateBy` varchar(255) DEFAULT NULL,
  `lastUpdateTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of product
-- ----------------------------
BEGIN;
INSERT INTO `product` VALUES (45, 'test shoes', 1013, NULL, '测试一下', '测试依稀下', 111.11, 100.00, 222.22, NULL, 7, 9, NULL, NULL, NULL, 7.00, 778, 0, '123', '2020-01-11 07:18:50', '123', '2020-01-11 07:26:08');
INSERT INTO `product` VALUES (46, 'test 2', 1013, NULL, '', '', 333.00, 333.00, 444.00, NULL, 4, 6, NULL, NULL, NULL, NULL, NULL, 1, '123', '2020-01-11 07:24:21', '123', '2020-01-11 07:26:48');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;

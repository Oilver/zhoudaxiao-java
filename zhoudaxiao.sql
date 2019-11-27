/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 80017
Source Host           : localhost:3306
Source Database       : zhoudaxiao

Target Server Type    : MYSQL
Target Server Version : 80017
File Encoding         : 65001

Date: 2019-11-26 21:52:32
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `category`
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
  `id` int(3) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `priority` int(3) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1013 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES ('1', '口红', '3');
INSERT INTO `category` VALUES ('999', '其他', '-1');
INSERT INTO `category` VALUES ('1001', '手表', '99');
INSERT INTO `category` VALUES ('1008', '潮鞋', '4');
INSERT INTO `category` VALUES ('1011', '戒指', '2');
INSERT INTO `category` VALUES ('1012', '面膜', '2');

-- ----------------------------
-- Table structure for `image`
-- ----------------------------
DROP TABLE IF EXISTS `image`;
CREATE TABLE `image` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `productId` int(11) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `isCarousel` int(1) DEFAULT NULL,
  `isAvatar` int(1) DEFAULT NULL,
  `priority` int(2) DEFAULT NULL,
  `bucketKey` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=107 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of image
-- ----------------------------
INSERT INTO `image` VALUES ('43', null, 'https://yiseven-1300292849.cos.ap-guangzhou.myqcloud.com/product/2019-11-17-11:56:50-timg.png', '0', '0', '0', 'product/2019-11-17-11:56:50-timg.png');
INSERT INTO `image` VALUES ('65', null, 'https://yiseven-1300292849.cos.ap-guangzhou.myqcloud.com/carousel/2019-11-23-14:47:30-娇兰1.png', '1', '0', '10', 'carousel/2019-11-23-14:47:30-娇兰1.png');
INSERT INTO `image` VALUES ('70', null, 'https://yiseven-1300292849.cos.ap-guangzhou.myqcloud.com/carousel/2019-11-23-15:03:16-logo.jpg', '1', '0', '0', 'carousel/2019-11-23-15:03:16-logo.jpg');
INSERT INTO `image` VALUES ('74', null, 'https://yiseven-1300292849.cos.ap-guangzhou.myqcloud.com/carousel/2019-11-23-15:25:43-娇兰2.png', '1', '0', '8', 'carousel/2019-11-23-15:25:43-娇兰2.png');
INSERT INTO `image` VALUES ('80', null, 'https://yiseven-1300292849.cos.ap-guangzhou.myqcloud.com/carousel/2019-11-23-16:16:53-timg - Copy.png', '1', '0', '20', 'carousel/2019-11-23-16:16:53-timg - Copy.png');
INSERT INTO `image` VALUES ('82', '20', 'https://yiseven-1300292849.cos.ap-guangzhou.myqcloud.com/product/2019-11-24-22:27:43-123.jpg', '0', '1', '3', 'product/2019-11-24-22:27:43-123.jpg');
INSERT INTO `image` VALUES ('83', '20', 'https://yiseven-1300292849.cos.ap-guangzhou.myqcloud.com/product/2019-11-24-22:27:46-timg - Copy (3).png', '0', '0', '2', 'product/2019-11-24-22:27:46-timg - Copy (3).png');
INSERT INTO `image` VALUES ('84', '20', 'https://yiseven-1300292849.cos.ap-guangzhou.myqcloud.com/product/2019-11-24-22:27:50-娇兰3.png', '0', '0', '1', 'product/2019-11-24-22:27:50-娇兰3.png');
INSERT INTO `image` VALUES ('85', '21', 'https://yiseven-1300292849.cos.ap-guangzhou.myqcloud.com/product/2019-11-25-09:51:01-timg - Copy (3).png', '0', '1', '1', 'product/2019-11-25-09:51:01-timg - Copy (3).png');
INSERT INTO `image` VALUES ('86', '26', 'https://yiseven-1300292849.cos.ap-guangzhou.myqcloud.com/product/2019-11-25-11:00:52-娇兰3.png', '0', '1', '1', 'product/2019-11-25-11:00:52-娇兰3.png');
INSERT INTO `image` VALUES ('100', '7', 'https://yiseven-1300292849.cos.ap-guangzhou.myqcloud.com/product/2019-11-26-13:59:47-娇兰1.png', '0', '1', '0', 'product/2019-11-26-13:59:47-娇兰1.png');
INSERT INTO `image` VALUES ('101', '7', 'https://yiseven-1300292849.cos.ap-guangzhou.myqcloud.com/product/2019-11-26-13:59:58-娇兰2.png', '0', '0', '0', 'product/2019-11-26-13:59:58-娇兰2.png');
INSERT INTO `image` VALUES ('102', '7', 'https://yiseven-1300292849.cos.ap-guangzhou.myqcloud.com/product/2019-11-26-14:00:02-娇兰3.png', '0', '0', '0', 'product/2019-11-26-14:00:02-娇兰3.png');
INSERT INTO `image` VALUES ('103', null, 'https://yiseven-1300292849.cos.ap-guangzhou.myqcloud.com/carousel/2019-11-26-15:51:39-123.jpg', '1', '0', '0', 'carousel/2019-11-26-15:51:39-123.jpg');
INSERT INTO `image` VALUES ('104', '31', 'https://yiseven-1300292849.cos.ap-guangzhou.myqcloud.com/product/2019-11-26-15:55:01-111.jpg', '0', '1', '2', 'product/2019-11-26-15:55:01-111.jpg');

-- ----------------------------
-- Table structure for `product`
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `categoryId` int(3) DEFAULT NULL,
  `brandId` int(11) DEFAULT NULL,
  `introduction` varchar(255) DEFAULT NULL,
  `detail` text,
  `originalPrice` double(10,2) DEFAULT NULL,
  `discountPrice` double(10,2) DEFAULT NULL,
  `agentPrice` double(10,2) DEFAULT NULL,
  `secondAgentPrice` double(10,2) DEFAULT NULL,
  `priority` int(2) DEFAULT NULL,
  `pageviews` int(11) DEFAULT NULL,
  `like` int(11) DEFAULT NULL,
  `totalSales` int(11) DEFAULT NULL,
  `monthlySales` int(11) DEFAULT NULL,
  `freight` double(4,2) DEFAULT NULL,
  `stock` int(11) DEFAULT NULL,
  `isNew` int(1) DEFAULT NULL,
  `createBy` varchar(255) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `lastUpdateBy` varchar(255) DEFAULT NULL,
  `lastUpdateTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of product
-- ----------------------------
INSERT INTO `product` VALUES ('7', '娇兰44', '1', null, '娇兰贵族', '娇兰你值得信赖', '340.00', '299.00', '333.00', null, '0', '202', null, null, null, '7.00', '7', '0', null, '2019-11-16 23:52:23', null, '2019-11-26 14:00:05');
INSERT INTO `product` VALUES ('16', '绿水鬼', '1001', null, '绿水鬼', '很牛逼的绿水鬼', '66666.00', '6666.00', null, null, '0', '2', null, null, null, null, '5', '0', null, '2019-11-24 19:13:23', null, '2019-11-24 19:13:23');
INSERT INTO `product` VALUES ('17', 'DW手表', '1001', null, 'Dw', 'DW手表', '1400.00', '1000.00', '700.00', null, '0', '0', null, null, null, null, '10', '0', null, '2019-11-24 19:19:10', null, '2019-11-24 19:19:10');
INSERT INTO `product` VALUES ('18', 'DW戒指', '999', null, 'Dw戒指', 'Dw戒指好看@！', '300.00', '260.00', '200.00', null, '0', '1', null, null, null, null, '10', '0', null, '2019-11-24 19:20:32', null, '2019-11-24 19:20:32');
INSERT INTO `product` VALUES ('20', '更新测试', '1011', null, '121212121', '1212121121212121212', '1212.00', '1212.12', '1212.00', null, '6', '48', null, null, null, '12.00', '1212', '1', null, '2019-11-24 22:31:43', null, '2019-11-25 14:04:11');
INSERT INTO `product` VALUES ('21', '黑水鬼', '1001', null, '黑水鬼', '劳力士黑水鬼', '66666.00', '55555.00', '5555.00', null, '10', '30', null, null, null, null, '10', '1', null, '2019-11-25 09:51:13', null, '2019-11-25 09:51:13');
INSERT INTO `product` VALUES ('24', '1121', '1', null, '1212', '121212', '2121.00', '12.00', '2121.00', null, '0', '0', null, null, null, null, '12122', '0', null, '2019-11-25 10:58:34', null, '2019-11-25 10:58:34');
INSERT INTO `product` VALUES ('26', '123123', '1', null, '3233', '132321', '31313.00', '21231.00', '123123.00', null, '0', '4', null, null, null, null, '312312', '0', null, '2019-11-25 11:00:54', null, '2019-11-25 11:00:54');
INSERT INTO `product` VALUES ('31', '卡西欧手表', '1001', null, '卡西欧', '卡西欧手表黑金最好看', '1400.00', '1100.00', '700.00', null, '8', '10', null, null, null, '10.00', '5', '1', null, '2019-11-26 15:55:11', null, '2019-11-26 16:30:04');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(4) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `email` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `phone` varchar(20) NOT NULL,
  `role` int(4) NOT NULL COMMENT '閻熸瑦甯熸竟?',
  `createDate` datetime DEFAULT NULL,
  `lastUpdateDate` datetime DEFAULT NULL,
  `status` int(1) NOT NULL,
  PRIMARY KEY (`id`,`password`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('26', '邓锦辉', 'f715334e8ea49b97642a3c64ac3e406d', '543527507@qq.com', '18819258246', '1', '2019-10-08 14:33:57', '2019-10-08 14:33:57', '1');
INSERT INTO `user` VALUES ('27', '999', 'c8361638b335a0e77e73e7e3c895513d', '999@qq.com', '999', '0', '2019-11-15 11:11:56', '2019-11-15 11:11:56', '1');
INSERT INTO `user` VALUES ('28', '123', 'd9b1d7db4cd6e70935368a1efb10e377', '123@qq.com', '123', '0', '2019-11-22 16:58:52', '2019-11-22 16:58:52', '1');
INSERT INTO `user` VALUES ('29', '121', 'a7625f2478bba123a77864a797cd05ec', '121@qq.com', '121', '0', '2019-11-22 17:37:14', '2019-11-22 17:37:14', '1');

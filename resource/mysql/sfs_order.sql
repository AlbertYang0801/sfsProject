/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50727
 Source Host           : localhost:3306
 Source Schema         : sfs_order

 Target Server Type    : MySQL
 Target Server Version : 50727
 File Encoding         : 65001

 Date: 16/08/2020 20:24:10
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sfs_orders
-- ----------------------------
DROP TABLE IF EXISTS `sfs_orders`;
CREATE TABLE `sfs_orders`  (
  `order_number` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '订单号',
  `initial_price` float(8, 2) NULL DEFAULT NULL COMMENT '定价',
  `price` float(8, 2) NULL DEFAULT NULL COMMENT '交易价',
  `start_time` datetime(0) NOT NULL COMMENT '起始时间',
  `end_time` datetime(0) NOT NULL COMMENT '结束时间',
  `status` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '交易状态',
  `user_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户id',
  `details` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '订单明细',
  PRIMARY KEY (`order_number`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sfs_orders
-- ----------------------------
INSERT INTO `sfs_orders` VALUES ('299036931059486720', 0.01, 0.01, '2018-04-05 12:26:00', '2018-04-05 14:26:00', '401001', '49', '[{\"itemId\":\"4028e581617f945f01617f9dabc40000\",\"itemNum\":1,\"itemPrice\":0.01,\"orderNumber\":\"299036931059486720\"}]');
INSERT INTO `sfs_orders` VALUES ('299118286820741120', 0.01, 0.01, '2018-04-05 17:49:17', '2018-04-05 19:49:17', '401001', '49', '[{\"itemId\":\"4028e58161bd22e60161bd23672a0001\",\"itemNum\":1,\"itemPrice\":0.01,\"orderNumber\":\"299118286820741120\"}]');
INSERT INTO `sfs_orders` VALUES ('299118455888941056', 0.01, 0.01, '2018-04-05 17:49:57', '2018-04-05 19:49:57', '401001', '49', '[{\"itemId\":\"4028e58161bd22e60161bd23672a0001\",\"itemNum\":1,\"itemPrice\":0.01,\"orderNumber\":\"299118455888941056\"}]');
INSERT INTO `sfs_orders` VALUES ('299144273360982016', 0.01, 0.01, '2018-04-05 19:32:33', '2018-04-05 21:32:33', '401002', '49', '[{\"itemId\":\"4028e58161bcf7f40161bcf8b77c0000\",\"itemNum\":1,\"itemPrice\":0.01,\"orderNumber\":\"299144273360982016\"}]');
INSERT INTO `sfs_orders` VALUES ('299202627802370048', 0.01, 0.01, '2018-04-05 23:24:26', '2018-04-06 01:24:26', '401001', '49', '[{\"itemId\":\"4028e58161bd22e60161bd23672a0001\",\"itemNum\":1,\"itemPrice\":0.01,\"orderNumber\":\"299202627802370048\"}]');
INSERT INTO `sfs_orders` VALUES ('299226261577142272', 0.01, 0.01, '2018-04-06 00:58:50', '2018-04-06 02:58:50', '401001', '49', '[{\"itemId\":\"4028e58161bd3b380161bd3bcd2f0000\",\"itemNum\":1,\"itemPrice\":0.01,\"orderNumber\":\"299226261577142272\"}]');
INSERT INTO `sfs_orders` VALUES ('299226499146715136', 0.01, 0.01, '2018-04-06 00:59:17', '2018-04-06 02:59:17', '401001', '49', '[{\"itemId\":\"4028e58161bd3b380161bd3bcd2f0000\",\"itemNum\":1,\"itemPrice\":0.01,\"orderNumber\":\"299226499146715136\"}]');
INSERT INTO `sfs_orders` VALUES ('317320500991102976', 0.01, 0.01, '2018-05-25 23:18:23', '2018-05-26 01:18:23', '401001', '49', '[{\"itemId\":\"4028e58161bd22e60161bd23672a0001\",\"itemNum\":1,\"itemPrice\":0.01,\"orderNumber\":\"317320500991102976\"}]');
INSERT INTO `sfs_orders` VALUES ('317320549372399616', 0.01, 0.01, '2018-05-25 23:18:35', '2018-05-26 01:18:35', '401001', '49', '[{\"itemId\":\"4028e58161bd22e60161bd23672a0001\",\"itemNum\":1,\"itemPrice\":0.01,\"orderNumber\":\"317320549372399616\"}]');
INSERT INTO `sfs_orders` VALUES ('317326221119983616', 0.01, 0.01, '2018-05-25 23:41:07', '2018-05-26 01:41:07', '401001', '49', '[{\"itemId\":\"402885816243d2dd016243f24c030002\",\"itemNum\":1,\"itemPrice\":0.01,\"orderNumber\":\"317326221119983616\"}]');
INSERT INTO `sfs_orders` VALUES ('317532756458737664', 0.01, 0.01, '2018-05-26 13:21:49', '2018-05-26 15:21:49', '401001', '49', '[{\"itemId\":\"4028e58161bd22e60161bd23672a0001\",\"itemNum\":1,\"itemPrice\":0.01,\"orderNumber\":\"317532756458737664\"}]');
INSERT INTO `sfs_orders` VALUES ('317532808245809152', 0.01, 0.01, '2018-05-26 13:22:01', '2018-05-26 15:22:01', '401001', '49', '[{\"itemId\":\"4028e58161bd22e60161bd23672a0001\",\"itemNum\":1,\"itemPrice\":0.01,\"orderNumber\":\"317532808245809152\"}]');
INSERT INTO `sfs_orders` VALUES ('317532890600968192', 0.01, 0.01, '2018-05-26 13:22:21', '2018-05-26 15:22:21', '401001', '49', '[{\"itemId\":\"4028e58161bd22e60161bd23672a0001\",\"itemNum\":1,\"itemPrice\":0.01,\"orderNumber\":\"317532890600968192\"}]');
INSERT INTO `sfs_orders` VALUES ('317587489890373632', 99.00, 99.00, '2018-05-26 16:59:18', '2018-05-26 18:59:18', '401001', '49', '[{\"itemId\":\"402885816243d2dd016243f24c030002\",\"itemNum\":1,\"itemPrice\":99.0,\"orderNumber\":\"317587489890373632\"}]');
INSERT INTO `sfs_orders` VALUES ('317590240250695680', 99.00, 99.00, '2018-05-26 17:10:14', '2018-05-26 19:10:14', '401001', '49', '[{\"itemId\":\"402885816243d2dd016243f24c030002\",\"itemNum\":1,\"itemPrice\":99.0,\"orderNumber\":\"317590240250695680\"}]');
INSERT INTO `sfs_orders` VALUES ('317600970689613824', 99.00, 99.00, '2018-05-26 17:52:52', '2018-05-26 19:52:52', '401001', '49', '[{\"itemId\":\"402885816243d2dd016243f24c030002\",\"itemNum\":1,\"itemPrice\":99.0,\"orderNumber\":\"317600970689613824\"}]');
INSERT INTO `sfs_orders` VALUES ('317651443140399104', 99.00, 99.00, '2018-05-26 21:13:26', '2018-05-26 23:13:26', '401001', '49', '[{\"itemId\":\"402885816243d2dd016243f24c030002\",\"itemNum\":1,\"itemPrice\":99.0,\"orderNumber\":\"317651443140399104\"}]');
INSERT INTO `sfs_orders` VALUES ('317651457044516864', 1.01, 1.01, '2018-05-26 21:13:29', '2018-05-26 23:13:29', '401001', '49', '[{\"itemId\":\"402885816243d2dd016243f24c030002\",\"itemNum\":1,\"itemPrice\":99.0,\"orderNumber\":\"317651457044516864\"}]');
INSERT INTO `sfs_orders` VALUES ('318344201396162560', 0.01, 0.01, '2018-05-28 19:06:12', '2018-05-28 21:06:12', '401002', '49', '[{\"itemId\":\"402885816243d2dd016243f24c030002\",\"itemNum\":1,\"itemPrice\":0.01,\"orderNumber\":\"318344201396162560\"}]');
INSERT INTO `sfs_orders` VALUES ('319855888196571136', 0.01, 0.01, '2018-06-01 23:13:07', '2018-06-02 01:13:07', '401002', '49', '[{\"endTime\":1585709437000,\"itemId\":\"4028e58161bd22e60161bd23672a0001\",\"itemNum\":1,\"itemPrice\":0.01,\"orderNumber\":\"319855888196571136\",\"startTime\":1522551031000}]');
INSERT INTO `sfs_orders` VALUES ('319867386222481408', 0.01, 0.01, '2018-06-01 23:58:48', '2018-06-02 01:58:48', '401001', '49', '[{\"endTime\":1585709437000,\"itemId\":\"4028e58161bd22e60161bd23672a0001\",\"itemNum\":1,\"itemPrice\":0.01,\"orderNumber\":\"319867386222481408\",\"startTime\":1522551031000,\"valid\":\"204001\"}]');
INSERT INTO `sfs_orders` VALUES ('319867403872112640', 0.01, 0.01, '2018-06-01 23:58:52', '2018-06-02 01:58:52', '401002', '49', '[{\"endTime\":1585709437000,\"itemId\":\"4028e58161bd22e60161bd23672a0001\",\"itemNum\":1,\"itemPrice\":0.01,\"orderNumber\":\"319867403872112640\",\"startTime\":1522551031000,\"valid\":\"204001\"}]');

-- ----------------------------
-- Table structure for sfs_orders_detail
-- ----------------------------
DROP TABLE IF EXISTS `sfs_orders_detail`;
CREATE TABLE `sfs_orders_detail`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `order_number` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '订单号',
  `item_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商品id',
  `item_num` int(8) NOT NULL COMMENT '商品数量',
  `item_price` float(8, 2) NOT NULL COMMENT '金额',
  `valid` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '课程有效性',
  `start_time` datetime(0) NOT NULL COMMENT '课程开始时间',
  `end_time` datetime(0) NOT NULL COMMENT '课程结束时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `xc_orders_detail_unique`(`order_number`, `item_id`) USING BTREE,
  CONSTRAINT `fk_xc_orders_detail_order_number` FOREIGN KEY (`order_number`) REFERENCES `sfs_orders` (`order_number`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sfs_orders_detail
-- ----------------------------
INSERT INTO `sfs_orders_detail` VALUES ('297e02f76397e0d7016397e11eba0000', '317320500991102976', '4028e58161bd22e60161bd23672a0001', 1, 0.01, '', '0000-00-00 00:00:00', '0000-00-00 00:00:00');
INSERT INTO `sfs_orders_detail` VALUES ('297e02f76397e0d7016397e14b190002', '317320549372399616', '4028e58161bd22e60161bd23672a0001', 1, 0.01, '', '0000-00-00 00:00:00', '0000-00-00 00:00:00');
INSERT INTO `sfs_orders_detail` VALUES ('297e02f76397e0d7016397f5ed510004', '317326221119983616', '402885816243d2dd016243f24c030002', 1, 0.01, '', '0000-00-00 00:00:00', '0000-00-00 00:00:00');
INSERT INTO `sfs_orders_detail` VALUES ('297e02f7639ae4fc01639ae54fa70000', '317532756458737664', '4028e58161bd22e60161bd23672a0001', 1, 0.01, '', '0000-00-00 00:00:00', '0000-00-00 00:00:00');
INSERT INTO `sfs_orders_detail` VALUES ('297e02f7639ae4fc01639ae57c940002', '317532808245809152', '4028e58161bd22e60161bd23672a0001', 1, 0.01, '', '0000-00-00 00:00:00', '0000-00-00 00:00:00');
INSERT INTO `sfs_orders_detail` VALUES ('297e02f7639ae4fc01639ae5c9440004', '317532890600968192', '4028e58161bd22e60161bd23672a0001', 1, 0.01, '', '0000-00-00 00:00:00', '0000-00-00 00:00:00');
INSERT INTO `sfs_orders_detail` VALUES ('297e02f7639ae4fc01639bac6ae90006', '317587489890373632', '402885816243d2dd016243f24c030002', 1, 99.00, '', '0000-00-00 00:00:00', '0000-00-00 00:00:00');
INSERT INTO `sfs_orders_detail` VALUES ('297e02f7639ae4fc01639bb66c500008', '317590240250695680', '402885816243d2dd016243f24c030002', 1, 99.00, '', '0000-00-00 00:00:00', '0000-00-00 00:00:00');
INSERT INTO `sfs_orders_detail` VALUES ('297e02f7639ae4fc01639bdd75d1000a', '317600970689613824', '402885816243d2dd016243f24c030002', 1, 99.00, '', '0000-00-00 00:00:00', '0000-00-00 00:00:00');
INSERT INTO `sfs_orders_detail` VALUES ('297e02f7639c1a3c01639c95149c0000', '317651443140399104', '402885816243d2dd016243f24c030002', 1, 99.00, '', '0000-00-00 00:00:00', '0000-00-00 00:00:00');
INSERT INTO `sfs_orders_detail` VALUES ('297e02f7639c1a3c01639c9520db0002', '317651457044516864', '402885816243d2dd016243f24c030002', 1, 99.00, '', '0000-00-00 00:00:00', '0000-00-00 00:00:00');
INSERT INTO `sfs_orders_detail` VALUES ('297e02f763a6634d0163a66d529b0000', '318344201396162560', '402885816243d2dd016243f24c030002', 1, 0.01, '', '0000-00-00 00:00:00', '0000-00-00 00:00:00');
INSERT INTO `sfs_orders_detail` VALUES ('402881e663bbbf210163bbe8cf120000', '319855888196571136', '4028e58161bd22e60161bd23672a0001', 1, 0.01, '', '2018-04-01 10:50:31', '2020-04-01 10:50:37');
INSERT INTO `sfs_orders_detail` VALUES ('402881e663bc11970163bc12a26e0000', '319867386222481408', '4028e58161bd22e60161bd23672a0001', 1, 0.01, '204001', '2018-04-01 10:50:31', '2020-04-01 10:50:37');
INSERT INTO `sfs_orders_detail` VALUES ('402881e663bc11970163bc12b2370002', '319867403872112640', '4028e58161bd22e60161bd23672a0001', 1, 0.01, '204001', '2018-04-01 10:50:31', '2020-04-01 10:50:37');
INSERT INTO `sfs_orders_detail` VALUES ('4028858162940c510162940de2100000', '299036931059486720', '4028e581617f945f01617f9dabc40000', 1, 0.01, '', '0000-00-00 00:00:00', '0000-00-00 00:00:00');
INSERT INTO `sfs_orders_detail` VALUES ('4028858162944c9801629535db390000', '299118286820741120', '4028e58161bd22e60161bd23672a0001', 1, 0.01, '', '0000-00-00 00:00:00', '0000-00-00 00:00:00');
INSERT INTO `sfs_orders_detail` VALUES ('4028858162944c980162953676d80002', '299118455888941056', '4028e58161bd22e60161bd23672a0001', 1, 0.01, '', '0000-00-00 00:00:00', '0000-00-00 00:00:00');
INSERT INTO `sfs_orders_detail` VALUES ('402885816295920a0162959464640000', '299144273360982016', '4028e58161bcf7f40161bcf8b77c0000', 1, 0.01, '', '0000-00-00 00:00:00', '0000-00-00 00:00:00');
INSERT INTO `sfs_orders_detail` VALUES ('402885816295be9901629668af9c0000', '299202627802370048', '4028e58161bd22e60161bd23672a0001', 1, 0.01, '', '0000-00-00 00:00:00', '0000-00-00 00:00:00');
INSERT INTO `sfs_orders_detail` VALUES ('402885816295be99016296bf1df10002', '299226261577142272', '4028e58161bd3b380161bd3bcd2f0000', 1, 0.01, '', '0000-00-00 00:00:00', '0000-00-00 00:00:00');
INSERT INTO `sfs_orders_detail` VALUES ('402885816295be99016296bf85b70004', '299226499146715136', '4028e58161bd3b380161bd3bcd2f0000', 1, 0.01, '', '0000-00-00 00:00:00', '0000-00-00 00:00:00');

-- ----------------------------
-- Table structure for sfs_orders_pay
-- ----------------------------
DROP TABLE IF EXISTS `sfs_orders_pay`;
CREATE TABLE `sfs_orders_pay`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `order_number` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '订单号',
  `pay_number` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '支付系统订单号',
  `status` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '交易状态',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `xc_orders_pay_order_number_unique`(`order_number`) USING BTREE,
  UNIQUE INDEX `xc_orders_pay_pay_number_unique`(`pay_number`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sfs_orders_pay
-- ----------------------------
INSERT INTO `sfs_orders_pay` VALUES ('297e02f76397e0d7016397e11ec00001', '317320500991102976', NULL, '402001');
INSERT INTO `sfs_orders_pay` VALUES ('297e02f76397e0d7016397e14b190003', '317320549372399616', NULL, '402001');
INSERT INTO `sfs_orders_pay` VALUES ('297e02f76397e0d7016397f5ed510005', '317326221119983616', NULL, '402001');
INSERT INTO `sfs_orders_pay` VALUES ('297e02f7639ae4fc01639ae54fac0001', '317532756458737664', NULL, '402001');
INSERT INTO `sfs_orders_pay` VALUES ('297e02f7639ae4fc01639ae57c940003', '317532808245809152', NULL, '402001');
INSERT INTO `sfs_orders_pay` VALUES ('297e02f7639ae4fc01639ae5c9440005', '317532890600968192', NULL, '402001');
INSERT INTO `sfs_orders_pay` VALUES ('297e02f7639ae4fc01639bac6aeb0007', '317587489890373632', NULL, '402001');
INSERT INTO `sfs_orders_pay` VALUES ('297e02f7639ae4fc01639bb66c500009', '317590240250695680', NULL, '402001');
INSERT INTO `sfs_orders_pay` VALUES ('297e02f7639ae4fc01639bdd75d1000b', '317600970689613824', NULL, '402001');
INSERT INTO `sfs_orders_pay` VALUES ('297e02f7639c1a3c01639c95149f0001', '317651443140399104', NULL, '402001');
INSERT INTO `sfs_orders_pay` VALUES ('297e02f7639c1a3c01639c9520db0003', '317651457044516864', NULL, '402001');
INSERT INTO `sfs_orders_pay` VALUES ('297e02f763a6634d0163a66d529f0001', '318344201396162560', NULL, '402002');
INSERT INTO `sfs_orders_pay` VALUES ('402881e663bbbf210163bbe8cf160001', '319855888196571136', NULL, '402002');
INSERT INTO `sfs_orders_pay` VALUES ('402881e663bc11970163bc12a2740001', '319867386222481408', NULL, '402001');
INSERT INTO `sfs_orders_pay` VALUES ('402881e663bc11970163bc12b2370003', '319867403872112640', NULL, '402002');
INSERT INTO `sfs_orders_pay` VALUES ('40288581627cdb0e01627ce2f8ad0009', '297406656009342976', NULL, '402001');
INSERT INTO `sfs_orders_pay` VALUES ('4028858162863b6d0162864231b60001', '298066138997592064', NULL, '402001');
INSERT INTO `sfs_orders_pay` VALUES ('4028858162940c510162940de2150001', '299036931059486720', NULL, '402001');
INSERT INTO `sfs_orders_pay` VALUES ('4028858162944c9801629535db3f0001', '299118286820741120', NULL, '402001');
INSERT INTO `sfs_orders_pay` VALUES ('4028858162944c980162953676d80003', '299118455888941056', NULL, '402001');
INSERT INTO `sfs_orders_pay` VALUES ('402885816295920a0162959464670001', '299144273360982016', NULL, '402002');
INSERT INTO `sfs_orders_pay` VALUES ('402885816295be9901629668afa30001', '299202627802370048', NULL, '402001');
INSERT INTO `sfs_orders_pay` VALUES ('402885816295be99016296bf1df10003', '299226261577142272', NULL, '402001');
INSERT INTO `sfs_orders_pay` VALUES ('402885816295be99016296bf85b70005', '299226499146715136', NULL, '402001');

-- ----------------------------
-- Table structure for sfs_task
-- ----------------------------
DROP TABLE IF EXISTS `sfs_task`;
CREATE TABLE `sfs_task`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '任务id',
  `create_time` datetime(0) NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  `delete_time` datetime(0) NULL DEFAULT NULL,
  `task_type` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '任务类型',
  `mq_exchange` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '交换机名称',
  `mq_routingkey` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'routingkey',
  `request_body` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '任务请求的内容',
  `version` int(10) NULL DEFAULT NULL COMMENT '乐观锁版本号',
  `status` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '任务状态',
  `errormsg` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '任务错误信息',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sfs_task
-- ----------------------------
INSERT INTO `sfs_task` VALUES ('10', '2018-04-04 22:58:20', '2020-06-26 23:25:56', '2018-07-16 12:24:36', NULL, 'ex_learning_addchoosecourse', 'addchoosecourse', '{\"userId\":\"49\",\"courseId\":\"4028e581617f945f01617f9dabc40000\"}', NULL, '10201', NULL);
INSERT INTO `sfs_task` VALUES ('11', '2018-07-16 12:28:03', '2020-06-26 23:25:56', '2018-07-16 12:29:11', NULL, 'ex_learning_addchoosecourse', 'addchoosecourse', '{\"userId\":\"49\",\"courseId\":\"4028e581617f945f01617f9dabc40001\"}', NULL, NULL, NULL);

-- ----------------------------
-- Table structure for sfs_task_his
-- ----------------------------
DROP TABLE IF EXISTS `sfs_task_his`;
CREATE TABLE `sfs_task_his`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '任务id',
  `create_time` datetime(0) NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  `delete_time` datetime(0) NULL DEFAULT NULL,
  `task_type` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '任务类型',
  `mq_exchange` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '交换机名称',
  `mq_routingkey` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'routingkey',
  `request_body` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '任务请求的内容',
  `version` int(10) NULL DEFAULT 0 COMMENT '乐观锁版本号',
  `status` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '任务状态',
  `errormsg` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sfs_task_his
-- ----------------------------
INSERT INTO `sfs_task_his` VALUES ('10', '2018-04-04 22:58:20', '2018-07-20 12:24:10', '2018-07-16 12:24:36', NULL, 'ex_learning_addchoosecourse', 'addchoosecourse', '{\"userId\":\"49\",\"courseId\":\"4028e581617f945f01617f9dabc40000\"}', NULL, '10201', NULL);
INSERT INTO `sfs_task_his` VALUES ('11', '2018-07-16 12:28:03', '2018-07-20 12:24:10', '2018-07-16 12:29:11', NULL, 'ex_learning_addchoosecourse', 'addchoosecourse', '{\"userId\":\"49\",\"courseId\":\"4028e581617f945f01617f9dabc40001\"}', NULL, NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;

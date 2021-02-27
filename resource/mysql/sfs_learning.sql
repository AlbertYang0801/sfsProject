/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50727
 Source Host           : localhost:3306
 Source Schema         : sfs_learning

 Target Server Type    : MySQL
 Target Server Version : 50727
 File Encoding         : 65001

 Date: 16/08/2020 20:24:04
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sfs_choose_course
-- ----------------------------
DROP TABLE IF EXISTS `sfs_choose_course`;
CREATE TABLE `sfs_choose_course`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `course_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '课程id',
  `user_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户id',
  `charge` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '收费规则',
  `price` float(8, 2) NULL DEFAULT NULL COMMENT '课程价格',
  `valid` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '有效性',
  `start_time` datetime(0) NULL DEFAULT NULL,
  `end_time` datetime(0) NULL DEFAULT NULL,
  `status` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '选课状态',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `xc_learning_list_unique`(`course_id`, `user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sfs_choose_course
-- ----------------------------
INSERT INTO `sfs_choose_course` VALUES ('40288581629123300162912af5630001', '4028e581617f945f01617f9dabc40000', '49', NULL, 0.01, NULL, NULL, NULL, '501001');
INSERT INTO `sfs_choose_course` VALUES ('4028858162936228016295b613650000', '4028e58161bcf7f40161bcf8b77c0000', '49', NULL, 0.01, NULL, '2018-04-01 10:50:31', '2020-04-01 10:50:37', '501001');
INSERT INTO `sfs_choose_course` VALUES ('402885816298a126016298ac09a10001', '4028e58161bd22e60161bd23672a0001', '49', NULL, NULL, NULL, '2018-04-01 10:50:31', '2020-04-01 10:50:37', '501001');

-- ----------------------------
-- Table structure for sfs_learning_course
-- ----------------------------
DROP TABLE IF EXISTS `sfs_learning_course`;
CREATE TABLE `sfs_learning_course`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `course_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '正在学习的课程id',
  `user_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户id',
  `valid` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `start_time` datetime(0) NULL DEFAULT NULL,
  `end_time` datetime(0) NULL DEFAULT NULL,
  `status` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '选课状态',
  `teachplan_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '课程章节id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sfs_learning_course
-- ----------------------------
INSERT INTO `sfs_learning_course` VALUES ('4028fe81724ffa850172540cc53b001b', '4028fe81724f104f01724f2f83140000', '4028fe81723c1fd001723c2ba3f50002', NULL, '2020-05-27 10:53:43', NULL, NULL, '4028fe81724ffb180172515565cd0003');
INSERT INTO `sfs_learning_course` VALUES ('4028fe81724ffa85017254273048001c', '4028fe81724ffb180172515ea348000d', '4028fe81723c1fd001723c2ba3f50002', NULL, '2020-05-27 11:22:34', NULL, NULL, '4028fe81724ffb18017251611fec0014');

-- ----------------------------
-- Table structure for sfs_learning_list
-- ----------------------------
DROP TABLE IF EXISTS `sfs_learning_list`;
CREATE TABLE `sfs_learning_list`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `course_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '收藏课程id',
  `user_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户id',
  `start_time` datetime(0) NULL DEFAULT NULL,
  `end_time` datetime(0) NULL DEFAULT NULL,
  `status` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sfs_learning_list
-- ----------------------------
INSERT INTO `sfs_learning_list` VALUES ('4028fe81724ffa85017254275131001d', '4028fe81724f104f01724f2f83140000', '4028fe81723c1fd001723c2ba3f50002', '2020-05-27 11:22:42', NULL, '0');

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
INSERT INTO `sfs_task_his` VALUES ('10', '2018-04-04 22:58:20', '2018-07-13 22:58:54', '2018-07-16 12:24:36', NULL, 'ex_learning_addchoosecourse', 'addchoosecourse', '{\"userId\":\"49\",\"courseId\":\"4028e581617f945f01617f9dabc40000\"}', NULL, '10201', NULL);
INSERT INTO `sfs_task_his` VALUES ('11', '2018-07-16 12:28:03', '2018-07-15 12:28:04', '2018-07-16 12:29:11', NULL, 'ex_learning_addchoosecourse', 'addchoosecourse', '{\"userId\":\"49\",\"courseId\":\"4028e581617f945f01617f9dabc40001\"}', NULL, NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;

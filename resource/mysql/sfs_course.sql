/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50727
 Source Host           : localhost:3306
 Source Schema         : sfs_course

 Target Server Type    : MySQL
 Target Server Version : 50727
 File Encoding         : 65001

 Date: 16/08/2020 20:23:56
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
  `name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '分类名称',
  `label` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '分类标签默认和名称一样',
  `parentid` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '父结点id',
  `isshow` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否显示',
  `orderby` int(4) NULL DEFAULT NULL COMMENT '排序字段',
  `isleaf` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否叶子',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES ('1', '根结点', '根结点', '0', '1', 1, '0');
INSERT INTO `category` VALUES ('1-1', '零基础', '零基础', '1', '1', 1, '0');
INSERT INTO `category` VALUES ('1-1-1', '英语音标', '英语音标', '1-1', '1', 1, '1');
INSERT INTO `category` VALUES ('1-1-2', '英语语法', '英语语法', '1-1', '1', 2, '1');
INSERT INTO `category` VALUES ('1-1-3', '入门课程', '入门课程', '1-1', '1', 3, '1');
INSERT INTO `category` VALUES ('1-1-4', '学习方法', '学习方法', '1-1', '1', 4, '1');
INSERT INTO `category` VALUES ('1-1-5', '其它', '其它', '1-1', '1', 5, '1');
INSERT INTO `category` VALUES ('1-2', '听力', '听力', '1', '1', 2, '0');
INSERT INTO `category` VALUES ('1-2-1', '中考听力', '中考听力', '1-2', '1', 1, '1');
INSERT INTO `category` VALUES ('1-2-2', '四级听力', '四级听力', '1-2', '1', 2, '1');
INSERT INTO `category` VALUES ('1-2-3', '六级听力', '六级听力', '1-2', '1', 3, '1');
INSERT INTO `category` VALUES ('1-2-4', '有声美文', '有声美文', '1-2', '1', 4, '1');
INSERT INTO `category` VALUES ('1-2-5', '有声热点', '有声热点', '1-2', '1', 5, '1');
INSERT INTO `category` VALUES ('1-2-6', '影视原音', '影视原音', '1-2', '1', 6, '1');
INSERT INTO `category` VALUES ('1-2-7', '其它', '其它', '1-2', '1', 7, '1');
INSERT INTO `category` VALUES ('1-3', '口语', '口语', '1', '1', 3, '0');
INSERT INTO `category` VALUES ('1-3-1', '口语对话', '口语对话', '1-3', '1', 1, '1');
INSERT INTO `category` VALUES ('1-3-2', '职场口语', '职场口语', '1-3', '1', 2, '1');
INSERT INTO `category` VALUES ('1-3-3', '口语交际', '口语交际', '1-3', '1', 3, '1');
INSERT INTO `category` VALUES ('1-3-4', '口语课程', '口语课程', '1-3', '1', 4, '1');
INSERT INTO `category` VALUES ('1-3-5', '其它', '其它', '1-3', '1', 5, '1');
INSERT INTO `category` VALUES ('1-4', '阅读', '阅读', '1', '1', 4, '0');
INSERT INTO `category` VALUES ('1-4-1', '热点速递', '热点速递', '1-4', '1', 1, '1');
INSERT INTO `category` VALUES ('1-4-2', '一词日历', '一词日历', '1-4', '1', 2, '1');
INSERT INTO `category` VALUES ('1-4-3', '专项阅读', '专项阅读', '1-4', '1', 3, '1');
INSERT INTO `category` VALUES ('1-4-4', '应试阅读', '应试阅读', '1-4', '1', 4, '1');
INSERT INTO `category` VALUES ('1-4-5', '其它', '其它', '1-4', '1', 5, '1');
INSERT INTO `category` VALUES ('1-5', '英语四级', '英语四级', '1', '1', 5, '0');
INSERT INTO `category` VALUES ('1-5-1', '历年真题', '历年真题', '1-5', '1', 1, '1');
INSERT INTO `category` VALUES ('1-5-2', '考前冲刺', '考前冲刺', '1-5', '1', 2, '1');
INSERT INTO `category` VALUES ('1-5-3', '技巧解读', '技巧解读', '1-5', '1', 3, '1');
INSERT INTO `category` VALUES ('1-5-4', '口试', '口试', '1-5', '1', 4, '1');
INSERT INTO `category` VALUES ('1-5-5', '其它', '其它', '1-5', '1', 5, '1');
INSERT INTO `category` VALUES ('1-6', '留学', '留学', '1', '1', 6, '0');
INSERT INTO `category` VALUES ('1-6-1', '雅思', '雅思', '1-6', '1', 1, '1');
INSERT INTO `category` VALUES ('1-6-2', '托福', '托福', '1-6', '1', 2, '1');
INSERT INTO `category` VALUES ('1-6-3', '外教口语', '外教口语', '1-6', '1', 3, '1');
INSERT INTO `category` VALUES ('1-6-4', '多国语言', '多国语言', '1-6', '1', 4, '1');
INSERT INTO `category` VALUES ('1-6-5', '其它', '其它', '1-6', '1', 5, '1');
INSERT INTO `category` VALUES ('1-7', '考试英语', '考试英语', '1', '1', 7, '0');
INSERT INTO `category` VALUES ('1-7-1', '专四', '专四', '1-7', '1', 1, '1');
INSERT INTO `category` VALUES ('1-7-2', '专八', '专八', '1-7', '1', 2, '1');
INSERT INTO `category` VALUES ('1-7-3', '考研英语', '考研英语', '1-7', '1', 3, '1');
INSERT INTO `category` VALUES ('1-7-4', '职场考证', '职场考证', '1-7', '1', 4, '1');
INSERT INTO `category` VALUES ('1-7-5', '其它', '其它', '1-7', '1', 5, '1');

-- ----------------------------
-- Table structure for course_base
-- ----------------------------
DROP TABLE IF EXISTS `course_base`;
CREATE TABLE `course_base`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '课程名称',
  `users` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '适用人群',
  `mt` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '课程大分类',
  `grade` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '课程等级',
  `studymodel` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '学习模式',
  `teachmode` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '授课模式',
  `description` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '课程介绍',
  `st` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '课程小分类',
  `status` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '课程状态(202001:未发布;202002:发布;202003:已下线)',
  `company_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '教育机构',
  `user_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建用户',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of course_base
-- ----------------------------
INSERT INTO `course_base` VALUES ('4028fe81724f104f01724f2f83140000', '英语零基础入门(英式音标)', '1.零起点外语学习者；2.英语语音有较大问题，造成听力、口语障碍，需要系统学习语音的学习者。', '1-1', '200001', '201001', NULL, '零起点，字母音标扎实实学', '1-1-1', '202002', NULL, NULL);
INSERT INTO `course_base` VALUES ('4028fe81724ffb180172515833540006', '英语零基础入门(美式音标)', '\n1.零起点外语学习者；\n\n2.英语语音有较大问题，造成听力、口语障碍，需要系统学习语音的学习者；\n\n3.希望学习美音的学习者。', '1-1', '200001', '201001', NULL, '零起点，字母音标扎实学，语音障碍全解决，充分利用网络教学的优势，在线互动学习，将鲜活有趣的实用表达融入零基础教学中，强调从英语学习起步阶段便打好语音基础，为进一步学习做好充分的准备。', '1-1-1', '202002', NULL, NULL);
INSERT INTO `course_base` VALUES ('4028fe81724ffb180172515ea348000d', '英语专业四级（TEM4）', '\n1.参加英语专业四级考试的学生;\n\n2.有词汇和语法基础，希望增加解题技巧的培训，提高做题正确率的学生;\n\n3.希望在专业四级考试当中取得高分的学生。', '1-7', '200002', '201001', NULL, '每天辛勤的晨读、做笔记、练听力、背单词\n\n仍旧通不过专四考试？\n\n只是一个人埋头苦学，却不知道考点在哪里？哪些知识点必须掌握？\n\n当然会事倍功半！\n\n我们不仅仅需要个人的努力，更需要老师的指点，提高学习效率', '1-7-1', '202002', NULL, NULL);
INSERT INTO `course_base` VALUES ('4028fe81724ffb180172515f3bbe000e', '英语专业四级（TEM4）进阶版', '\n1.参加英语专业四级考试的学生;\n\n2.有词汇和语法基础，希望增加解题技巧的培训，提高做题正确率的学生;\n\n3.希望在专业四级考试当中取得高分的学生。', '1-7', '200003', '201001', NULL, '\n1.参加英语专业四级考试的学生;\n\n2.有词汇和语法基础，希望增加解题技巧的培训，提高做题正确率的学生;\n\n3.希望在专业四级考试当中取得高分的学生。', '1-7-1', '202002', NULL, NULL);
INSERT INTO `course_base` VALUES ('4028fe81724ffb180172542098da0029', '测试', '大学', '1-1', '200001', '201001', NULL, '这是个测试', '1-1-1', '202001', NULL, NULL);

-- ----------------------------
-- Table structure for course_market
-- ----------------------------
DROP TABLE IF EXISTS `course_market`;
CREATE TABLE `course_market`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '课程id',
  `charge` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '收费规则，对应数据字典',
  `valid` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '有效性，对应数据字典',
  `expires` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '过期时间',
  `qq` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '咨询qq',
  `price` float(10, 2) NULL DEFAULT NULL COMMENT '价格',
  `price_old` float(10, 2) NULL DEFAULT NULL COMMENT '原价',
  `start_time` datetime(0) NULL DEFAULT NULL COMMENT '课程有效期-开始时间',
  `end_time` datetime(0) NULL DEFAULT NULL COMMENT '课程有效期-结束时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of course_market
-- ----------------------------
INSERT INTO `course_market` VALUES ('4028fe8170f29ab40170f3aa93750000', '203001', '204001', '2020-05-14 17:35:34', '123', NULL, NULL, NULL, NULL);
INSERT INTO `course_market` VALUES ('4028fe8170f29ab40170f3af885b0003', '203002', '204002', '2020-03-29 21:26:34', '1614550916', 100.00, NULL, '2020-03-03 00:00:00', '2020-03-26 00:00:00');
INSERT INTO `course_market` VALUES ('4028fe8171012162017101783d260000', '203001', '204001', '2020-03-22 17:01:30', '12324', NULL, NULL, NULL, NULL);
INSERT INTO `course_market` VALUES ('4028fe817222806801722280ecaa0000', '203002', '204001', '2020-05-17 20:03:12', '231', 41.00, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for course_off
-- ----------------------------
DROP TABLE IF EXISTS `course_off`;
CREATE TABLE `course_off`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
  `name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '课程名称',
  `users` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '适用人群',
  `mt` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '大分类',
  `st` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '小分类',
  `grade` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '课程等级',
  `studymodel` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '学习模式',
  `description` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '课程介绍',
  `timestamp` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '时间戳',
  `charge` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '收费规则，对应数据字典',
  `valid` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '有效性，对应数据字典',
  `qq` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '咨询qq',
  `price` float(10, 2) NOT NULL COMMENT '价格',
  `price_old` float(10, 2) NULL DEFAULT NULL COMMENT '原价格',
  `expires` timestamp(0) NULL DEFAULT NULL COMMENT '过期时间',
  `pic` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '课程图片',
  `teachplan` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '课程计划',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for course_pic
-- ----------------------------
DROP TABLE IF EXISTS `course_pic`;
CREATE TABLE `course_pic`  (
  `courseid` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '课程id',
  `pic` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '图片id',
  PRIMARY KEY (`courseid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of course_pic
-- ----------------------------
INSERT INTO `course_pic` VALUES ('4028fe8170f29ab40170f3aa93750000', 'group1/M00/00/00/wKh-bl69EESAYmhpAAN9Ax89XCg128.png');
INSERT INTO `course_pic` VALUES ('4028fe8170f29ab40170f3af885b0003', 'group1/M00/00/00/wKh-bl65lhaAS_0FAAegjh4-P08285.jpg');
INSERT INTO `course_pic` VALUES ('4028fe8171012162017101783d260000', 'group1/M00/00/00/wKh-bl53ME-ATMKPAAn99A6qLHI065.jpg');
INSERT INTO `course_pic` VALUES ('4028fe817222806801722280ecaa0000', 'group1/M00/00/00/wKh-bl7BLVKAFJIRAAn2AaMo7nY400.jpg');
INSERT INTO `course_pic` VALUES ('4028fe81724f104f01724f2f83140000', 'group1/M00/00/00/wKh-bl7Ml4WACl0vAAAsCez7EkY091.png');
INSERT INTO `course_pic` VALUES ('4028fe81724ffb180172515833540006', 'group1/M00/00/00/wKh-bl7NGC-AG42WAABpxY7F3yw053.png');
INSERT INTO `course_pic` VALUES ('4028fe81724ffb180172515ea348000d', 'group1/M00/00/00/wKh-bl7NGfKABFgtAABmVPEV9_E700.png');
INSERT INTO `course_pic` VALUES ('4028fe81724ffb180172515f3bbe000e', 'group1/M00/00/00/wKh-bl7NGhyAWA2MAABmVPEV9_E968.png');
INSERT INTO `course_pic` VALUES ('4028fe81724ffb180172542098da0029', 'group1/M00/00/00/wKh-bl7NQJeAZX84AAAdd1wGfD4566.png');

-- ----------------------------
-- Table structure for course_pre
-- ----------------------------
DROP TABLE IF EXISTS `course_pre`;
CREATE TABLE `course_pre`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
  `name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '课程名称',
  `users` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '适用人群',
  `mt` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '大分类',
  `st` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '小分类',
  `grade` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '课程等级',
  `studymodel` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '学习模式',
  `description` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '课程介绍',
  `status` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '课程状态',
  `timestamp` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '时间戳',
  `charge` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '收费规则，对应数据字典',
  `valid` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '有效性，对应数据字典',
  `qq` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '咨询qq',
  `price` float(10, 2) NOT NULL COMMENT '价格',
  `price_old` float(10, 2) NULL DEFAULT NULL COMMENT '原价格',
  `expires` timestamp(0) NULL DEFAULT NULL COMMENT '过期时间',
  `pic` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '课程图片',
  `teachplan` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '课程计划',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for course_pub
-- ----------------------------
DROP TABLE IF EXISTS `course_pub`;
CREATE TABLE `course_pub`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
  `name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '课程名称',
  `users` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '适用人群',
  `mt` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '大分类',
  `st` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '小分类',
  `grade` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '课程等级',
  `studymodel` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '学习模式',
  `teachmode` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '教育模式',
  `description` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '课程介绍',
  `timestamp` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '时间戳logstash使用',
  `charge` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '收费规则，对应数据字典',
  `valid` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '有效性，对应数据字典',
  `qq` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '咨询qq',
  `price` float(10, 2) NULL DEFAULT NULL COMMENT '价格',
  `price_old` float(10, 2) NULL DEFAULT NULL COMMENT '原价格',
  `expires` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '过期时间',
  `start_time` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '课程有效期-开始时间',
  `end_time` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '课程有效期-结束时间',
  `pic` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '课程图片',
  `teachplan` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '课程计划',
  `pub_time` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '发布时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of course_pub
-- ----------------------------
INSERT INTO `course_pub` VALUES ('4028fe81724f104f01724f2f83140000', '英语零基础入门(英式音标)', '1.零起点外语学习者；2.英语语音有较大问题，造成听力、口语障碍，需要系统学习语音的学习者。', '1-1', '1-1-1', '200001', '201001', NULL, '零起点，字母音标扎实实学', '2020-05-26 23:03:48', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'group1/M00/00/00/wKh-bl7Ml4WACl0vAAAsCez7EkY091.png', '{\"children\":[{\"children\":[{\"id\":\"4028fe81724ffb180172515565cd0003\",\"mediaFileOriginalName\":\"听力.avi\",\"mediaId\":\"520c28207e7da11cc7d329ecc55625d7\",\"pname\":\"No.1字母音标学习(1)\"},{\"id\":\"4028fe81724ffb1801725155832c0004\",\"mediaFileOriginalName\":\"听力.avi\",\"mediaId\":\"520c28207e7da11cc7d329ecc55625d7\",\"pname\":\"No.2字母音标学习(2)\"}],\"id\":\"4028fe81724ffb1801725154dfdf0001\",\"pname\":\"第一章\"},{\"children\":[{\"id\":\"4028fe81724ffb1801725155d3810005\",\"mediaFileOriginalName\":\"听力.avi\",\"mediaId\":\"520c28207e7da11cc7d329ecc55625d7\",\"pname\":\"入门音标课\"}],\"id\":\"4028fe81724ffb1801725154ff640002\",\"pname\":\"第二章\"}],\"id\":\"4028fe81724ffb180172515475a00000\",\"pname\":\"英语零基础入门\"}', '2020-05-26 23:03:48');
INSERT INTO `course_pub` VALUES ('4028fe81724ffb180172515833540006', '英语零基础入门(美式音标)', '\n1.零起点外语学习者；\n\n2.英语语音有较大问题，造成听力、口语障碍，需要系统学习语音的学习者；\n\n3.希望学习美音的学习者。', '1-1', '1-1-1', '200001', '201001', NULL, '零起点，字母音标扎实学，语音障碍全解决，充分利用网络教学的优势，在线互动学习，将鲜活有趣的实用表达融入零基础教学中，强调从英语学习起步阶段便打好语音基础，为进一步学习做好充分的准备。', '2020-05-26 23:04:10', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'group1/M00/00/00/wKh-bl7NGC-AG42WAABpxY7F3yw053.png', '{\"children\":[{\"children\":[{\"id\":\"4028fe81724ffb180172515b6503000a\",\"mediaFileOriginalName\":\"音标.avi\",\"mediaId\":\"1a21cd2b974606592dfd438e10e4c46c\",\"pname\":\"1.1 认识音标\"},{\"id\":\"4028fe81724ffb180172515b818c000b\",\"mediaFileOriginalName\":\"音标.avi\",\"mediaId\":\"1a21cd2b974606592dfd438e10e4c46c\",\"pname\":\"1.2 学习音标\"}],\"id\":\"4028fe81724ffb180172515ae0090008\",\"pname\":\"第一章\"},{\"children\":[{\"id\":\"4028fe81724ffb180172515bbd1c000c\",\"mediaFileOriginalName\":\"音标.avi\",\"mediaId\":\"1a21cd2b974606592dfd438e10e4c46c\",\"pname\":\"2.1 课程比较\"}],\"id\":\"4028fe81724ffb180172515afd750009\",\"pname\":\"第二章\"}],\"id\":\"4028fe81724ffb180172515ae0040007\",\"pname\":\"英语零基础入门(美式音标)\"}', '2020-05-26 23:04:09');
INSERT INTO `course_pub` VALUES ('4028fe81724ffb180172515ea348000d', '英语专业四级（TEM4）', '\n1.参加英语专业四级考试的学生;\n\n2.有词汇和语法基础，希望增加解题技巧的培训，提高做题正确率的学生;\n\n3.希望在专业四级考试当中取得高分的学生。', '1-7', '1-7-1', '200002', '201001', NULL, '每天辛勤的晨读、做笔记、练听力、背单词\n\n仍旧通不过专四考试？\n\n只是一个人埋头苦学，却不知道考点在哪里？哪些知识点必须掌握？\n\n当然会事倍功半！\n\n我们不仅仅需要个人的努力，更需要老师的指点，提高学习效率', '2020-05-26 23:04:13', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'group1/M00/00/00/wKh-bl7NGfKABFgtAABmVPEV9_E700.png', '{\"children\":[{\"children\":[{\"id\":\"4028fe81724ffb18017251611fec0014\",\"mediaFileOriginalName\":\"听力.avi\",\"mediaId\":\"520c28207e7da11cc7d329ecc55625d7\",\"pname\":\"00-听写速记方法\"},{\"id\":\"4028fe81724ffb180172516165000015\",\"mediaFileOriginalName\":\"完形填空.avi\",\"mediaId\":\"51e61c689decc235d5861588a9cda93c\",\"pname\":\"01-写作讲解\"}],\"id\":\"4028fe81724ffb18017251605a740010\",\"pname\":\"单元0\"},{\"children\":[{\"id\":\"4028fe81724ffb180172516231150017\",\"mediaFileOriginalName\":\"写作.avi\",\"mediaId\":\"c5c75d70f382e6016d2f506d134eee11\",\"pname\":\"03-逻辑链接词以及高分句型讲解\"},{\"id\":\"4028fe81724ffb1801725160ed100013\",\"mediaFileOriginalName\":\"音标.avi\",\"mediaId\":\"1a21cd2b974606592dfd438e10e4c46c\",\"pname\":\"01-英语专业四级【试听课】\"},{\"id\":\"4028fe81724ffb1801725161cc680016\",\"mediaFileOriginalName\":\"写作.avi\",\"mediaId\":\"c5c75d70f382e6016d2f506d134eee11\",\"pname\":\"02-语法讲解\"}],\"id\":\"4028fe81724ffb180172516076700011\",\"pname\":\"单元1\"},{\"children\":[{\"id\":\"4028fe81724ffb180172516dcb7c0028\",\"mediaFileOriginalName\":\"写作.avi\",\"mediaId\":\"c5c75d70f382e6016d2f506d134eee11\",\"pname\":\"课程总结\"}],\"id\":\"4028fe81724ffb180172516089670012\",\"pname\":\"单元2\"}],\"id\":\"4028fe81724ffb18017251605a71000f\",\"pname\":\"英语专业四级（TEM4）\"}', '2020-05-26 23:04:13');
INSERT INTO `course_pub` VALUES ('4028fe81724ffb180172515f3bbe000e', '英语专业四级（TEM4）进阶版', '\n1.参加英语专业四级考试的学生;\n\n2.有词汇和语法基础，希望增加解题技巧的培训，提高做题正确率的学生;\n\n3.希望在专业四级考试当中取得高分的学生。', '1-7', '1-7-1', '200003', '201001', NULL, '\n1.参加英语专业四级考试的学生;\n\n2.有词汇和语法基础，希望增加解题技巧的培训，提高做题正确率的学生;\n\n3.希望在专业四级考试当中取得高分的学生。', '2020-05-26 23:04:16', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'group1/M00/00/00/wKh-bl7NGhyAWA2MAABmVPEV9_E968.png', '{\"children\":[{\"children\":[{\"id\":\"4028fe81724ffb18017251635d2e001e\",\"mediaFileOriginalName\":\"听力.avi\",\"mediaId\":\"520c28207e7da11cc7d329ecc55625d7\",\"pname\":\"试听课\"}],\"id\":\"4028fe81724ffb180172516298310019\",\"pname\":\"试听单元\"},{\"children\":[{\"id\":\"4028fe81724ffb18017251637a01001f\",\"mediaFileOriginalName\":\"写作.avi\",\"mediaId\":\"c5c75d70f382e6016d2f506d134eee11\",\"pname\":\"写作01\"},{\"id\":\"4028fe81724ffb180172516386fc0020\",\"mediaFileOriginalName\":\"写作.avi\",\"mediaId\":\"c5c75d70f382e6016d2f506d134eee11\",\"pname\":\"写作02\"},{\"id\":\"4028fe81724ffb180172516387ab0021\",\"mediaFileOriginalName\":\"完形填空.avi\",\"mediaId\":\"51e61c689decc235d5861588a9cda93c\",\"pname\":\"写作03\"}],\"id\":\"4028fe81724ffb1801725162ba7a001a\",\"pname\":\"写作\"},{\"children\":[{\"id\":\"4028fe81724ffb1801725163e0d80022\",\"mediaFileOriginalName\":\"音标.avi\",\"mediaId\":\"1a21cd2b974606592dfd438e10e4c46c\",\"pname\":\"听力01\"},{\"id\":\"4028fe81724ffb1801725163ef2d0023\",\"mediaFileOriginalName\":\"写作.avi\",\"mediaId\":\"c5c75d70f382e6016d2f506d134eee11\",\"pname\":\"听力02\"}],\"id\":\"4028fe81724ffb1801725162da32001b\",\"pname\":\"听力\"},{\"children\":[{\"id\":\"4028fe81724ffb18017251640f1b0024\",\"mediaFileOriginalName\":\"完形填空.avi\",\"mediaId\":\"51e61c689decc235d5861588a9cda93c\",\"pname\":\"完形填空01\"},{\"id\":\"4028fe81724ffb18017251644e130025\",\"mediaFileOriginalName\":\"写作.avi\",\"mediaId\":\"c5c75d70f382e6016d2f506d134eee11\",\"pname\":\"完形填空02\"}],\"id\":\"4028fe81724ffb18017251630442001c\",\"pname\":\"完形填空\"},{\"children\":[{\"id\":\"4028fe81724ffb18017251646ec10026\",\"mediaFileOriginalName\":\"完形填空.avi\",\"mediaId\":\"51e61c689decc235d5861588a9cda93c\",\"pname\":\"阅读01\"},{\"id\":\"4028fe81724ffb180172516482010027\",\"mediaFileOriginalName\":\"写作.avi\",\"mediaId\":\"c5c75d70f382e6016d2f506d134eee11\",\"pname\":\"阅读02\"}],\"id\":\"4028fe81724ffb18017251631a4f001d\",\"pname\":\"阅读\"}],\"id\":\"4028fe81724ffb1801725162982e0018\",\"pname\":\"英语专业四级（TEM4）进阶版\"}', '2020-05-26 23:04:16');

-- ----------------------------
-- Table structure for teachplan
-- ----------------------------
DROP TABLE IF EXISTS `teachplan`;
CREATE TABLE `teachplan`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `pname` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `parentid` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `grade` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '层级，分为1、2、3级',
  `ptype` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '课程类型:1视频、2文档',
  `description` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '章节及课程时介绍',
  `timelength` double(5, 2) NULL DEFAULT NULL COMMENT '时长，单位分钟',
  `courseid` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '课程id',
  `orderby` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '排序字段',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '状态：未发布、已发布',
  `trylearn` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否试学',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of teachplan
-- ----------------------------
INSERT INTO `teachplan` VALUES ('4028e58161bd3b380161bd40cf340009', '英语口语六级课程', '0', '1', NULL, NULL, NULL, '4028fe8171012162017101783d260000', NULL, '0', NULL);
INSERT INTO `teachplan` VALUES ('4028fe81710121620171017a1a4a0002', '第一章', '4028fe81710121620171017a1a440001', '2', '1', '导语', 12.00, '4028fe8171012162017101783d260000', '1', '0', NULL);
INSERT INTO `teachplan` VALUES ('4028fe81710121620171017a37c70003', '1.2', '4028fe81710121620171017a1a4a0002', '3', '1', '导语', 12.00, '4028fe8171012162017101783d260000', '1', '0', NULL);
INSERT INTO `teachplan` VALUES ('4028fe8171017c810171017df1170000', '1.1', '4028fe81710121620171017a1a4a0002', '3', '1', NULL, 13.00, '4028fe8171012162017101783d260000', '1', '0', NULL);
INSERT INTO `teachplan` VALUES ('4028fe8171017c810171017fb30a0001', '1.2', '4028fe81710121620171017a1a4a0002', '3', '1', NULL, 12.00, '4028fe8171012162017101783d260000', '2', '0', NULL);
INSERT INTO `teachplan` VALUES ('4028fe8171017c810171018096240002', '1.4', '4028fe81710121620171017a1a4a0002', '3', '1', NULL, 123.00, '4028fe8171012162017101783d260000', '4', '0', NULL);
INSERT INTO `teachplan` VALUES ('4028fe8171017c8101710180d5480003', '第二章', '4028fe81710121620171017a1a440001', '2', '1', NULL, 123.00, '4028fe8171012162017101783d260000', '2', '0', NULL);
INSERT INTO `teachplan` VALUES ('4028fe8171017c81017101944a1d0004', '第三章', '4028fe81710121620171017a1a440001', '2', '1', '导语', 12.00, '4028fe8171012162017101783d260000', '3', '0', NULL);
INSERT INTO `teachplan` VALUES ('4028fe81712676a90171267958af0000', '123123', '0', '1', NULL, NULL, NULL, '4028fe8170f29ab40170f3af885b0003', NULL, '0', NULL);
INSERT INTO `teachplan` VALUES ('4028fe81712676a90171267958cc0001', '第一章', '4028fe81712676a90171267958af0000', '2', '1', '无', 15.00, '4028fe8170f29ab40170f3af885b0003', '1', '0', NULL);
INSERT INTO `teachplan` VALUES ('4028fe81712676a90171267984c60002', '第二章', '4028fe81712676a90171267958af0000', '2', '1', '无', 15.00, '4028fe8170f29ab40170f3af885b0003', '2', '0', NULL);
INSERT INTO `teachplan` VALUES ('4028fe8171870f630171871140e80000', '测试修改', '0', '1', NULL, NULL, NULL, '4028fe8170f29ab40170f3aa93750000', NULL, '0', NULL);
INSERT INTO `teachplan` VALUES ('4028fe8171870f630171871141090001', '第一章', '4028fe8171870f630171871140e80000', '2', '1', '23', 32.00, '4028fe8170f29ab40170f3aa93750000', '1', '0', NULL);
INSERT INTO `teachplan` VALUES ('4028fe81718e019f01718e0654ba0000', '测试', '4028fe81712676a90171267958cc0001', '3', '1', '23', 999.99, '4028fe8170f29ab40170f3af885b0003', '3213', '0', NULL);
INSERT INTO `teachplan` VALUES ('4028fe8171b1be070171b1bf66470000', '基础音标', '4028fe81712676a90171267984c60002', '3', '1', '13', 15.00, '4028fe8170f29ab40170f3af885b0003', '2', '0', NULL);
INSERT INTO `teachplan` VALUES ('4028fe8171b1be070171b1f2c2780001', '哈哈哈', '4028fe81712676a90171267984c60002', '3', '1', '4', 15.00, '4028fe8170f29ab40170f3af885b0003', '3', '0', NULL);
INSERT INTO `teachplan` VALUES ('4028fe8171d040120171d04738e50000', '第一章', '4028e58161bd3b380161bd40cf340009', '2', '1', '123', 15.00, '4028fe8171012162017101783d260000', '1', '0', NULL);
INSERT INTO `teachplan` VALUES ('4028fe8171d040120171d0474f490001', '第二章', '4028e58161bd3b380161bd40cf340009', '2', '1', '123', 15.00, '4028fe8171012162017101783d260000', '2', '0', NULL);
INSERT INTO `teachplan` VALUES ('4028fe8171d040120171d0476f0d0002', '1.1', '4028fe8171d040120171d04738e50000', '3', '1', '123', 15.00, '4028fe8171012162017101783d260000', '1', '0', NULL);
INSERT INTO `teachplan` VALUES ('4028fe8172045b2901720505b9c40001', '2.1', '4028fe8171d040120171d0474f490001', '3', '1', 'hahahah', 15.00, '4028fe8171012162017101783d260000', '1', '0', NULL);
INSERT INTO `teachplan` VALUES ('4028fe8172126aa60172128c5c1f0000', '口语', '4028fe8171870f630171871141090001', '3', '1', '23', 32.00, '4028fe8170f29ab40170f3aa93750000', '1', '0', NULL);
INSERT INTO `teachplan` VALUES ('4028fe81722280680172228466680001', '32132', '0', '1', NULL, NULL, NULL, '4028fe817222806801722280ecaa0000', NULL, '0', NULL);
INSERT INTO `teachplan` VALUES ('4028fe81722280680172228471e00002', '321', '4028fe81722280680172228466680001', '2', '1', '213', 12.00, '4028fe817222806801722280ecaa0000', '3213', '0', NULL);
INSERT INTO `teachplan` VALUES ('4028fe81722280680172228491ce0003', '11421', '4028fe81722280680172228471e00002', '3', '1', '213', 12.00, '4028fe817222806801722280ecaa0000', '3213', '0', NULL);
INSERT INTO `teachplan` VALUES ('4028fe81724ffb180172515475a00000', '英语零基础入门', '0', '1', NULL, NULL, NULL, '4028fe81724f104f01724f2f83140000', NULL, '0', NULL);
INSERT INTO `teachplan` VALUES ('4028fe81724ffb1801725154dfdf0001', '第一章', '4028fe81724ffb180172515475a00000', '2', '1', NULL, 10.00, '4028fe81724f104f01724f2f83140000', '1', '0', NULL);
INSERT INTO `teachplan` VALUES ('4028fe81724ffb1801725154ff640002', '第二章', '4028fe81724ffb180172515475a00000', '2', '1', NULL, 10.00, '4028fe81724f104f01724f2f83140000', '2', '0', NULL);
INSERT INTO `teachplan` VALUES ('4028fe81724ffb180172515565cd0003', 'No.1字母音标学习(1)', '4028fe81724ffb1801725154dfdf0001', '3', '1', NULL, 10.00, '4028fe81724f104f01724f2f83140000', '1', '0', NULL);
INSERT INTO `teachplan` VALUES ('4028fe81724ffb1801725155832c0004', 'No.2字母音标学习(2)', '4028fe81724ffb1801725154dfdf0001', '3', '1', NULL, 10.00, '4028fe81724f104f01724f2f83140000', '2', '0', NULL);
INSERT INTO `teachplan` VALUES ('4028fe81724ffb1801725155d3810005', '入门音标课', '4028fe81724ffb1801725154ff640002', '3', '1', NULL, 10.00, '4028fe81724f104f01724f2f83140000', '2', '0', NULL);
INSERT INTO `teachplan` VALUES ('4028fe81724ffb180172515ae0040007', '英语零基础入门(美式音标)', '0', '1', NULL, NULL, NULL, '4028fe81724ffb180172515833540006', NULL, '0', NULL);
INSERT INTO `teachplan` VALUES ('4028fe81724ffb180172515ae0090008', '第一章', '4028fe81724ffb180172515ae0040007', '2', '1', NULL, 10.00, '4028fe81724ffb180172515833540006', '1', '0', NULL);
INSERT INTO `teachplan` VALUES ('4028fe81724ffb180172515afd750009', '第二章', '4028fe81724ffb180172515ae0040007', '2', '1', NULL, 10.00, '4028fe81724ffb180172515833540006', '2', '0', NULL);
INSERT INTO `teachplan` VALUES ('4028fe81724ffb180172515b6503000a', '1.1 认识音标', '4028fe81724ffb180172515ae0090008', '3', '1', NULL, 10.00, '4028fe81724ffb180172515833540006', '1', '0', NULL);
INSERT INTO `teachplan` VALUES ('4028fe81724ffb180172515b818c000b', '1.2 学习音标', '4028fe81724ffb180172515ae0090008', '3', '1', NULL, 10.00, '4028fe81724ffb180172515833540006', '2', '0', NULL);
INSERT INTO `teachplan` VALUES ('4028fe81724ffb180172515bbd1c000c', '2.1 课程比较', '4028fe81724ffb180172515afd750009', '3', '1', NULL, 10.00, '4028fe81724ffb180172515833540006', '1', '0', NULL);
INSERT INTO `teachplan` VALUES ('4028fe81724ffb18017251605a71000f', '英语专业四级（TEM4）', '0', '1', NULL, NULL, NULL, '4028fe81724ffb180172515ea348000d', NULL, '0', NULL);
INSERT INTO `teachplan` VALUES ('4028fe81724ffb18017251605a740010', '单元0', '4028fe81724ffb18017251605a71000f', '2', '1', '试听单元', 8.00, '4028fe81724ffb180172515ea348000d', '1', '0', NULL);
INSERT INTO `teachplan` VALUES ('4028fe81724ffb180172516076700011', '单元1', '4028fe81724ffb18017251605a71000f', '2', '1', '', 8.00, '4028fe81724ffb180172515ea348000d', '2', '0', NULL);
INSERT INTO `teachplan` VALUES ('4028fe81724ffb180172516089670012', '单元2', '4028fe81724ffb18017251605a71000f', '2', '1', '', 8.00, '4028fe81724ffb180172515ea348000d', '3', '0', NULL);
INSERT INTO `teachplan` VALUES ('4028fe81724ffb1801725160ed100013', '01-英语专业四级【试听课】', '4028fe81724ffb180172516076700011', '3', '1', '', 8.00, '4028fe81724ffb180172515ea348000d', '1', '0', NULL);
INSERT INTO `teachplan` VALUES ('4028fe81724ffb18017251611fec0014', '00-听写速记方法', '4028fe81724ffb18017251605a740010', '3', '1', '', 8.00, '4028fe81724ffb180172515ea348000d', '3', '0', NULL);
INSERT INTO `teachplan` VALUES ('4028fe81724ffb180172516165000015', '01-写作讲解', '4028fe81724ffb18017251605a740010', '3', '1', '', 8.00, '4028fe81724ffb180172515ea348000d', '3', '0', NULL);
INSERT INTO `teachplan` VALUES ('4028fe81724ffb1801725161cc680016', '02-语法讲解', '4028fe81724ffb180172516076700011', '3', '1', '', 8.00, '4028fe81724ffb180172515ea348000d', '2', '0', NULL);
INSERT INTO `teachplan` VALUES ('4028fe81724ffb180172516231150017', '03-逻辑链接词以及高分句型讲解', '4028fe81724ffb180172516076700011', '3', '1', '', 8.00, '4028fe81724ffb180172515ea348000d', '1', '0', NULL);
INSERT INTO `teachplan` VALUES ('4028fe81724ffb1801725162982e0018', '英语专业四级（TEM4）进阶版', '0', '1', NULL, NULL, NULL, '4028fe81724ffb180172515f3bbe000e', NULL, '0', NULL);
INSERT INTO `teachplan` VALUES ('4028fe81724ffb180172516298310019', '试听单元', '4028fe81724ffb1801725162982e0018', '2', '1', NULL, 1.00, '4028fe81724ffb180172515f3bbe000e', '1', '0', NULL);
INSERT INTO `teachplan` VALUES ('4028fe81724ffb1801725162ba7a001a', '写作', '4028fe81724ffb1801725162982e0018', '2', '1', NULL, 1.00, '4028fe81724ffb180172515f3bbe000e', '2', '0', NULL);
INSERT INTO `teachplan` VALUES ('4028fe81724ffb1801725162da32001b', '听力', '4028fe81724ffb1801725162982e0018', '2', '1', NULL, 1.00, '4028fe81724ffb180172515f3bbe000e', '3', '0', NULL);
INSERT INTO `teachplan` VALUES ('4028fe81724ffb18017251630442001c', '完形填空', '4028fe81724ffb1801725162982e0018', '2', '1', NULL, 1.00, '4028fe81724ffb180172515f3bbe000e', '4', '0', NULL);
INSERT INTO `teachplan` VALUES ('4028fe81724ffb18017251631a4f001d', '阅读', '4028fe81724ffb1801725162982e0018', '2', '1', NULL, 1.00, '4028fe81724ffb180172515f3bbe000e', '5', '0', NULL);
INSERT INTO `teachplan` VALUES ('4028fe81724ffb18017251635d2e001e', '试听课', '4028fe81724ffb180172516298310019', '3', '1', NULL, 1.00, '4028fe81724ffb180172515f3bbe000e', '1', '0', NULL);
INSERT INTO `teachplan` VALUES ('4028fe81724ffb18017251637a01001f', '写作01', '4028fe81724ffb1801725162ba7a001a', '3', '1', NULL, 1.00, '4028fe81724ffb180172515f3bbe000e', '1', '0', NULL);
INSERT INTO `teachplan` VALUES ('4028fe81724ffb180172516386fc0020', '写作02', '4028fe81724ffb1801725162ba7a001a', '3', '1', NULL, 1.00, '4028fe81724ffb180172515f3bbe000e', '2', '0', NULL);
INSERT INTO `teachplan` VALUES ('4028fe81724ffb180172516387ab0021', '写作03', '4028fe81724ffb1801725162ba7a001a', '3', '1', NULL, 1.00, '4028fe81724ffb180172515f3bbe000e', '3', '0', NULL);
INSERT INTO `teachplan` VALUES ('4028fe81724ffb1801725163e0d80022', '听力01', '4028fe81724ffb1801725162da32001b', '3', '1', NULL, 1.00, '4028fe81724ffb180172515f3bbe000e', '1', '0', NULL);
INSERT INTO `teachplan` VALUES ('4028fe81724ffb1801725163ef2d0023', '听力02', '4028fe81724ffb1801725162da32001b', '3', '1', NULL, 1.00, '4028fe81724ffb180172515f3bbe000e', '2', '0', NULL);
INSERT INTO `teachplan` VALUES ('4028fe81724ffb18017251640f1b0024', '完形填空01', '4028fe81724ffb18017251630442001c', '3', '1', NULL, 1.00, '4028fe81724ffb180172515f3bbe000e', '1', '0', NULL);
INSERT INTO `teachplan` VALUES ('4028fe81724ffb18017251644e130025', '完形填空02', '4028fe81724ffb18017251630442001c', '3', '1', NULL, 1.00, '4028fe81724ffb180172515f3bbe000e', '2', '0', NULL);
INSERT INTO `teachplan` VALUES ('4028fe81724ffb18017251646ec10026', '阅读01', '4028fe81724ffb18017251631a4f001d', '3', '1', NULL, 1.00, '4028fe81724ffb180172515f3bbe000e', '1', '0', NULL);
INSERT INTO `teachplan` VALUES ('4028fe81724ffb180172516482010027', '阅读02', '4028fe81724ffb18017251631a4f001d', '3', '1', NULL, 1.00, '4028fe81724ffb180172515f3bbe000e', '2', '0', NULL);
INSERT INTO `teachplan` VALUES ('4028fe81724ffb180172516dcb7c0028', '课程总结', '4028fe81724ffb180172516089670012', '3', '1', NULL, 11.00, '4028fe81724ffb180172515ea348000d', '1', '0', NULL);
INSERT INTO `teachplan` VALUES ('4028fe81724ffb18017254223d18002a', '测试', '0', '1', NULL, NULL, NULL, '4028fe81724ffb180172542098da0029', NULL, '0', NULL);
INSERT INTO `teachplan` VALUES ('4028fe81724ffb18017254223d1a002b', '第一章', '4028fe81724ffb18017254223d18002a', '2', '1', NULL, 10.00, '4028fe81724ffb180172542098da0029', '1', '0', NULL);
INSERT INTO `teachplan` VALUES ('4028fe81724ffb1801725422560e002c', '第二章', '4028fe81724ffb18017254223d18002a', '2', '1', NULL, 10.00, '4028fe81724ffb180172542098da0029', '2', '0', NULL);
INSERT INTO `teachplan` VALUES ('4028fe81724ffb18017254227c2d002d', '第一个视频', '4028fe81724ffb18017254223d1a002b', '3', '1', NULL, 10.00, '4028fe81724ffb180172542098da0029', '1', '0', NULL);

-- ----------------------------
-- Table structure for teachplan_media
-- ----------------------------
DROP TABLE IF EXISTS `teachplan_media`;
CREATE TABLE `teachplan_media`  (
  `teachplan_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '课程计划id',
  `media_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '媒资文件id',
  `media_fileoriginalname` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '媒资文件的原始名称',
  `media_url` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '媒资文件访问地址',
  `courseid` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '课程Id',
  PRIMARY KEY (`teachplan_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of teachplan_media
-- ----------------------------
INSERT INTO `teachplan_media` VALUES ('4028e58161bd3b380161bd3fe9220008', '53ac4cca3ddf386c21f4f1cbb4dc9876', '3.avi', '5/3/53ac4cca3ddf386c21f4f1cbb4dc9876/hls/53ac4cca3ddf386c21f4f1cbb4dc9876.m3u8', '4028e58161bd3b380161bd3bcd2f0000');
INSERT INTO `teachplan_media` VALUES ('4028e58161bd3b380161bd40cf340009', '809694a6a974c35e3a36f36850837d7c', '1.avi', '8/0/809694a6a974c35e3a36f36850837d7c/hls/809694a6a974c35e3a36f36850837d7c.m3u8', '4028e58161bd3b380161bd3bcd2f0000');
INSERT INTO `teachplan_media` VALUES ('4028fe81718e019f01718e0654ba0000', '2d9c26f58bc3a456c82277f796e4ab05', '01-Eureka注册中心-Eureka介绍.avi', '2/d/2d9c26f58bc3a456c82277f796e4ab05/hls/2d9c26f58bc3a456c82277f796e4ab05.m3u8', '4028fe8170f29ab40170f3af885b0003');
INSERT INTO `teachplan_media` VALUES ('4028fe8171b1be070171b1bf66470000', '44ec21321ac6aac2fa0f0918171b655c', '02-项目概述-功能构架-功能模块和项目原型 【www.zxit8.com】.avi', '4/4/44ec21321ac6aac2fa0f0918171b655c/hls/44ec21321ac6aac2fa0f0918171b655c.m3u8', '4028fe8170f29ab40170f3af885b0003');
INSERT INTO `teachplan_media` VALUES ('4028fe8171b1be070171b1f2c2780001', '69a81d4b4b3404887ade5b252a5b8ca6', '01-项目概述-功能构架-项目背景 【www.zxit8.com】.avi', '6/9/69a81d4b4b3404887ade5b252a5b8ca6/hls/69a81d4b4b3404887ade5b252a5b8ca6.m3u8', '4028fe8170f29ab40170f3af885b0003');
INSERT INTO `teachplan_media` VALUES ('4028fe8171d040120171d0476f0d0002', '44ec21321ac6aac2fa0f0918171b655c', '02-项目概述-功能构架-功能模块和项目原型 【www.zxit8.com】.avi', '4/4/44ec21321ac6aac2fa0f0918171b655c/hls/44ec21321ac6aac2fa0f0918171b655c.m3u8', '4028fe8171012162017101783d260000');
INSERT INTO `teachplan_media` VALUES ('4028fe8172045b2901720505b9c40001', '69a81d4b4b3404887ade5b252a5b8ca6', '01-项目概述-功能构架-项目背景 【www.zxit8.com】.avi', '6/9/69a81d4b4b3404887ade5b252a5b8ca6/hls/69a81d4b4b3404887ade5b252a5b8ca6.m3u8', '4028fe8171012162017101783d260000');
INSERT INTO `teachplan_media` VALUES ('4028fe8172126aa60172128c5c1f0000', '5fbb79a2016c0eb609ecd0cd3dc48016', 'solr.avi', '5/f/5fbb79a2016c0eb609ecd0cd3dc48016/hls/5fbb79a2016c0eb609ecd0cd3dc48016.m3u8', '4028fe8170f29ab40170f3aa93750000');
INSERT INTO `teachplan_media` VALUES ('4028fe81722280680172228491ce0003', 'c5c75d70f382e6016d2f506d134eee11', 'lucene.avi', 'c/5/c5c75d70f382e6016d2f506d134eee11/hls/c5c75d70f382e6016d2f506d134eee11.m3u8', '4028fe817222806801722280ecaa0000');
INSERT INTO `teachplan_media` VALUES ('4028fe81724ffb180172515565cd0003', '520c28207e7da11cc7d329ecc55625d7', '听力.avi', '5/2/520c28207e7da11cc7d329ecc55625d7/hls/520c28207e7da11cc7d329ecc55625d7.m3u8', '4028fe81724f104f01724f2f83140000');
INSERT INTO `teachplan_media` VALUES ('4028fe81724ffb1801725155832c0004', '520c28207e7da11cc7d329ecc55625d7', '听力.avi', '5/2/520c28207e7da11cc7d329ecc55625d7/hls/520c28207e7da11cc7d329ecc55625d7.m3u8', '4028fe81724f104f01724f2f83140000');
INSERT INTO `teachplan_media` VALUES ('4028fe81724ffb1801725155d3810005', '520c28207e7da11cc7d329ecc55625d7', '听力.avi', '5/2/520c28207e7da11cc7d329ecc55625d7/hls/520c28207e7da11cc7d329ecc55625d7.m3u8', '4028fe81724f104f01724f2f83140000');
INSERT INTO `teachplan_media` VALUES ('4028fe81724ffb180172515b6503000a', '1a21cd2b974606592dfd438e10e4c46c', '音标.avi', '1/a/1a21cd2b974606592dfd438e10e4c46c/hls/1a21cd2b974606592dfd438e10e4c46c.m3u8', '4028fe81724ffb180172515833540006');
INSERT INTO `teachplan_media` VALUES ('4028fe81724ffb180172515b818c000b', '1a21cd2b974606592dfd438e10e4c46c', '音标.avi', '1/a/1a21cd2b974606592dfd438e10e4c46c/hls/1a21cd2b974606592dfd438e10e4c46c.m3u8', '4028fe81724ffb180172515833540006');
INSERT INTO `teachplan_media` VALUES ('4028fe81724ffb180172515bbd1c000c', '1a21cd2b974606592dfd438e10e4c46c', '音标.avi', '1/a/1a21cd2b974606592dfd438e10e4c46c/hls/1a21cd2b974606592dfd438e10e4c46c.m3u8', '4028fe81724ffb180172515833540006');
INSERT INTO `teachplan_media` VALUES ('4028fe81724ffb1801725160ed100013', '1a21cd2b974606592dfd438e10e4c46c', '音标.avi', '1/a/1a21cd2b974606592dfd438e10e4c46c/hls/1a21cd2b974606592dfd438e10e4c46c.m3u8', '4028fe81724ffb180172515ea348000d');
INSERT INTO `teachplan_media` VALUES ('4028fe81724ffb18017251611fec0014', '520c28207e7da11cc7d329ecc55625d7', '听力.avi', '5/2/520c28207e7da11cc7d329ecc55625d7/hls/520c28207e7da11cc7d329ecc55625d7.m3u8', '4028fe81724ffb180172515ea348000d');
INSERT INTO `teachplan_media` VALUES ('4028fe81724ffb180172516165000015', '51e61c689decc235d5861588a9cda93c', '完形填空.avi', '5/1/51e61c689decc235d5861588a9cda93c/hls/51e61c689decc235d5861588a9cda93c.m3u8', '4028fe81724ffb180172515ea348000d');
INSERT INTO `teachplan_media` VALUES ('4028fe81724ffb1801725161cc680016', 'c5c75d70f382e6016d2f506d134eee11', '写作.avi', 'c/5/c5c75d70f382e6016d2f506d134eee11/hls/c5c75d70f382e6016d2f506d134eee11.m3u8', '4028fe81724ffb180172515ea348000d');
INSERT INTO `teachplan_media` VALUES ('4028fe81724ffb180172516231150017', 'c5c75d70f382e6016d2f506d134eee11', '写作.avi', 'c/5/c5c75d70f382e6016d2f506d134eee11/hls/c5c75d70f382e6016d2f506d134eee11.m3u8', '4028fe81724ffb180172515ea348000d');
INSERT INTO `teachplan_media` VALUES ('4028fe81724ffb18017251635d2e001e', '520c28207e7da11cc7d329ecc55625d7', '听力.avi', '5/2/520c28207e7da11cc7d329ecc55625d7/hls/520c28207e7da11cc7d329ecc55625d7.m3u8', '4028fe81724ffb180172515f3bbe000e');
INSERT INTO `teachplan_media` VALUES ('4028fe81724ffb18017251637a01001f', 'c5c75d70f382e6016d2f506d134eee11', '写作.avi', 'c/5/c5c75d70f382e6016d2f506d134eee11/hls/c5c75d70f382e6016d2f506d134eee11.m3u8', '4028fe81724ffb180172515f3bbe000e');
INSERT INTO `teachplan_media` VALUES ('4028fe81724ffb180172516386fc0020', 'c5c75d70f382e6016d2f506d134eee11', '写作.avi', 'c/5/c5c75d70f382e6016d2f506d134eee11/hls/c5c75d70f382e6016d2f506d134eee11.m3u8', '4028fe81724ffb180172515f3bbe000e');
INSERT INTO `teachplan_media` VALUES ('4028fe81724ffb180172516387ab0021', '51e61c689decc235d5861588a9cda93c', '完形填空.avi', '5/1/51e61c689decc235d5861588a9cda93c/hls/51e61c689decc235d5861588a9cda93c.m3u8', '4028fe81724ffb180172515f3bbe000e');
INSERT INTO `teachplan_media` VALUES ('4028fe81724ffb1801725163e0d80022', '1a21cd2b974606592dfd438e10e4c46c', '音标.avi', '1/a/1a21cd2b974606592dfd438e10e4c46c/hls/1a21cd2b974606592dfd438e10e4c46c.m3u8', '4028fe81724ffb180172515f3bbe000e');
INSERT INTO `teachplan_media` VALUES ('4028fe81724ffb1801725163ef2d0023', 'c5c75d70f382e6016d2f506d134eee11', '写作.avi', 'c/5/c5c75d70f382e6016d2f506d134eee11/hls/c5c75d70f382e6016d2f506d134eee11.m3u8', '4028fe81724ffb180172515f3bbe000e');
INSERT INTO `teachplan_media` VALUES ('4028fe81724ffb18017251640f1b0024', '51e61c689decc235d5861588a9cda93c', '完形填空.avi', '5/1/51e61c689decc235d5861588a9cda93c/hls/51e61c689decc235d5861588a9cda93c.m3u8', '4028fe81724ffb180172515f3bbe000e');
INSERT INTO `teachplan_media` VALUES ('4028fe81724ffb18017251644e130025', 'c5c75d70f382e6016d2f506d134eee11', '写作.avi', 'c/5/c5c75d70f382e6016d2f506d134eee11/hls/c5c75d70f382e6016d2f506d134eee11.m3u8', '4028fe81724ffb180172515f3bbe000e');
INSERT INTO `teachplan_media` VALUES ('4028fe81724ffb18017251646ec10026', '51e61c689decc235d5861588a9cda93c', '完形填空.avi', '5/1/51e61c689decc235d5861588a9cda93c/hls/51e61c689decc235d5861588a9cda93c.m3u8', '4028fe81724ffb180172515f3bbe000e');
INSERT INTO `teachplan_media` VALUES ('4028fe81724ffb180172516482010027', 'c5c75d70f382e6016d2f506d134eee11', '写作.avi', 'c/5/c5c75d70f382e6016d2f506d134eee11/hls/c5c75d70f382e6016d2f506d134eee11.m3u8', '4028fe81724ffb180172515f3bbe000e');
INSERT INTO `teachplan_media` VALUES ('4028fe81724ffb180172516dcb7c0028', 'c5c75d70f382e6016d2f506d134eee11', '写作.avi', 'c/5/c5c75d70f382e6016d2f506d134eee11/hls/c5c75d70f382e6016d2f506d134eee11.m3u8', '4028fe81724ffb180172515ea348000d');
INSERT INTO `teachplan_media` VALUES ('4028fe81724ffb18017254227c2d002d', '432057f967f4f893f576235907f67df0', '测试2.avi', '4/3/432057f967f4f893f576235907f67df0/hls/432057f967f4f893f576235907f67df0.m3u8', '4028fe81724ffb180172542098da0029');

-- ----------------------------
-- Table structure for teachplan_media_pub
-- ----------------------------
DROP TABLE IF EXISTS `teachplan_media_pub`;
CREATE TABLE `teachplan_media_pub`  (
  `teachplan_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '课程计划id',
  `media_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '媒资文件id',
  `media_fileoriginalname` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '媒资文件的原始名称',
  `media_url` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '媒资文件访问地址',
  `courseid` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '课程Id',
  `timestamp` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT 'logstash使用',
  PRIMARY KEY (`teachplan_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of teachplan_media_pub
-- ----------------------------
INSERT INTO `teachplan_media_pub` VALUES ('4028fe81718e019f01718e0654ba0000', 'c5c75d70f382e6016d2f506d134eee11', 'lucene.avi', 'c/5/c5c75d70f382e6016d2f506d134eee11/hls/c5c75d70f382e6016d2f506d134eee11.m3u8', '4028fe8170f29ab40170f3af885b0003', '2020-05-15 23:58:55');
INSERT INTO `teachplan_media_pub` VALUES ('4028fe8171b1be070171b1bf66470000', '5fbb79a2016c0eb609ecd0cd3dc48016', 'solr.avi', '5/f/5fbb79a2016c0eb609ecd0cd3dc48016/hls/5fbb79a2016c0eb609ecd0cd3dc48016.m3u8', '4028fe8170f29ab40170f3af885b0003', '2020-05-15 23:58:55');
INSERT INTO `teachplan_media_pub` VALUES ('4028fe8171b1be070171b1f2c2780001', '69a81d4b4b3404887ade5b252a5b8ca6', '01-项目概述-功能构架-项目背景 【www.zxit8.com】.avi', '6/9/69a81d4b4b3404887ade5b252a5b8ca6/hls/69a81d4b4b3404887ade5b252a5b8ca6.m3u8', '4028fe8170f29ab40170f3af885b0003', '2020-05-15 23:58:55');
INSERT INTO `teachplan_media_pub` VALUES ('4028fe8171d040120171d0476f0d0002', '44ec21321ac6aac2fa0f0918171b655c', '02-项目概述-功能构架-功能模块和项目原型 【www.zxit8.com】.avi', '4/4/44ec21321ac6aac2fa0f0918171b655c/hls/44ec21321ac6aac2fa0f0918171b655c.m3u8', '4028fe8171012162017101783d260000', '2020-05-12 02:36:08');
INSERT INTO `teachplan_media_pub` VALUES ('4028fe8172045b2901720505b9c40001', '69a81d4b4b3404887ade5b252a5b8ca6', '01-项目概述-功能构架-项目背景 【www.zxit8.com】.avi', '6/9/69a81d4b4b3404887ade5b252a5b8ca6/hls/69a81d4b4b3404887ade5b252a5b8ca6.m3u8', '4028fe8171012162017101783d260000', '2020-05-12 02:36:08');
INSERT INTO `teachplan_media_pub` VALUES ('4028fe8172126aa60172128c5c1f0000', '5fbb79a2016c0eb609ecd0cd3dc48016', 'solr.avi', '5/f/5fbb79a2016c0eb609ecd0cd3dc48016/hls/5fbb79a2016c0eb609ecd0cd3dc48016.m3u8', '4028fe8170f29ab40170f3aa93750000', '2020-05-14 17:39:05');
INSERT INTO `teachplan_media_pub` VALUES ('4028fe81722280680172228491ce0003', 'c5c75d70f382e6016d2f506d134eee11', 'lucene.avi', 'c/5/c5c75d70f382e6016d2f506d134eee11/hls/c5c75d70f382e6016d2f506d134eee11.m3u8', '4028fe817222806801722280ecaa0000', '2020-05-17 20:03:46');
INSERT INTO `teachplan_media_pub` VALUES ('4028fe81724ffb180172515565cd0003', '520c28207e7da11cc7d329ecc55625d7', '听力.avi', '5/2/520c28207e7da11cc7d329ecc55625d7/hls/520c28207e7da11cc7d329ecc55625d7.m3u8', '4028fe81724f104f01724f2f83140000', '2020-05-26 23:03:48');
INSERT INTO `teachplan_media_pub` VALUES ('4028fe81724ffb1801725155832c0004', '520c28207e7da11cc7d329ecc55625d7', '听力.avi', '5/2/520c28207e7da11cc7d329ecc55625d7/hls/520c28207e7da11cc7d329ecc55625d7.m3u8', '4028fe81724f104f01724f2f83140000', '2020-05-26 23:03:48');
INSERT INTO `teachplan_media_pub` VALUES ('4028fe81724ffb1801725155d3810005', '520c28207e7da11cc7d329ecc55625d7', '听力.avi', '5/2/520c28207e7da11cc7d329ecc55625d7/hls/520c28207e7da11cc7d329ecc55625d7.m3u8', '4028fe81724f104f01724f2f83140000', '2020-05-26 23:03:48');
INSERT INTO `teachplan_media_pub` VALUES ('4028fe81724ffb180172515b6503000a', '1a21cd2b974606592dfd438e10e4c46c', '音标.avi', '1/a/1a21cd2b974606592dfd438e10e4c46c/hls/1a21cd2b974606592dfd438e10e4c46c.m3u8', '4028fe81724ffb180172515833540006', '2020-05-26 23:04:09');
INSERT INTO `teachplan_media_pub` VALUES ('4028fe81724ffb180172515b818c000b', '1a21cd2b974606592dfd438e10e4c46c', '音标.avi', '1/a/1a21cd2b974606592dfd438e10e4c46c/hls/1a21cd2b974606592dfd438e10e4c46c.m3u8', '4028fe81724ffb180172515833540006', '2020-05-26 23:04:09');
INSERT INTO `teachplan_media_pub` VALUES ('4028fe81724ffb180172515bbd1c000c', '1a21cd2b974606592dfd438e10e4c46c', '音标.avi', '1/a/1a21cd2b974606592dfd438e10e4c46c/hls/1a21cd2b974606592dfd438e10e4c46c.m3u8', '4028fe81724ffb180172515833540006', '2020-05-26 23:04:09');
INSERT INTO `teachplan_media_pub` VALUES ('4028fe81724ffb1801725160ed100013', '1a21cd2b974606592dfd438e10e4c46c', '音标.avi', '1/a/1a21cd2b974606592dfd438e10e4c46c/hls/1a21cd2b974606592dfd438e10e4c46c.m3u8', '4028fe81724ffb180172515ea348000d', '2020-05-26 23:04:13');
INSERT INTO `teachplan_media_pub` VALUES ('4028fe81724ffb18017251611fec0014', '520c28207e7da11cc7d329ecc55625d7', '听力.avi', '5/2/520c28207e7da11cc7d329ecc55625d7/hls/520c28207e7da11cc7d329ecc55625d7.m3u8', '4028fe81724ffb180172515ea348000d', '2020-05-26 23:04:13');
INSERT INTO `teachplan_media_pub` VALUES ('4028fe81724ffb180172516165000015', '51e61c689decc235d5861588a9cda93c', '完形填空.avi', '5/1/51e61c689decc235d5861588a9cda93c/hls/51e61c689decc235d5861588a9cda93c.m3u8', '4028fe81724ffb180172515ea348000d', '2020-05-26 23:04:13');
INSERT INTO `teachplan_media_pub` VALUES ('4028fe81724ffb1801725161cc680016', 'c5c75d70f382e6016d2f506d134eee11', '写作.avi', 'c/5/c5c75d70f382e6016d2f506d134eee11/hls/c5c75d70f382e6016d2f506d134eee11.m3u8', '4028fe81724ffb180172515ea348000d', '2020-05-26 23:04:13');
INSERT INTO `teachplan_media_pub` VALUES ('4028fe81724ffb180172516231150017', 'c5c75d70f382e6016d2f506d134eee11', '写作.avi', 'c/5/c5c75d70f382e6016d2f506d134eee11/hls/c5c75d70f382e6016d2f506d134eee11.m3u8', '4028fe81724ffb180172515ea348000d', '2020-05-26 23:04:13');
INSERT INTO `teachplan_media_pub` VALUES ('4028fe81724ffb18017251635d2e001e', '520c28207e7da11cc7d329ecc55625d7', '听力.avi', '5/2/520c28207e7da11cc7d329ecc55625d7/hls/520c28207e7da11cc7d329ecc55625d7.m3u8', '4028fe81724ffb180172515f3bbe000e', '2020-05-26 23:04:16');
INSERT INTO `teachplan_media_pub` VALUES ('4028fe81724ffb18017251637a01001f', 'c5c75d70f382e6016d2f506d134eee11', '写作.avi', 'c/5/c5c75d70f382e6016d2f506d134eee11/hls/c5c75d70f382e6016d2f506d134eee11.m3u8', '4028fe81724ffb180172515f3bbe000e', '2020-05-26 23:04:16');
INSERT INTO `teachplan_media_pub` VALUES ('4028fe81724ffb180172516386fc0020', 'c5c75d70f382e6016d2f506d134eee11', '写作.avi', 'c/5/c5c75d70f382e6016d2f506d134eee11/hls/c5c75d70f382e6016d2f506d134eee11.m3u8', '4028fe81724ffb180172515f3bbe000e', '2020-05-26 23:04:16');
INSERT INTO `teachplan_media_pub` VALUES ('4028fe81724ffb180172516387ab0021', '51e61c689decc235d5861588a9cda93c', '完形填空.avi', '5/1/51e61c689decc235d5861588a9cda93c/hls/51e61c689decc235d5861588a9cda93c.m3u8', '4028fe81724ffb180172515f3bbe000e', '2020-05-26 23:04:16');
INSERT INTO `teachplan_media_pub` VALUES ('4028fe81724ffb1801725163e0d80022', '1a21cd2b974606592dfd438e10e4c46c', '音标.avi', '1/a/1a21cd2b974606592dfd438e10e4c46c/hls/1a21cd2b974606592dfd438e10e4c46c.m3u8', '4028fe81724ffb180172515f3bbe000e', '2020-05-26 23:04:16');
INSERT INTO `teachplan_media_pub` VALUES ('4028fe81724ffb1801725163ef2d0023', 'c5c75d70f382e6016d2f506d134eee11', '写作.avi', 'c/5/c5c75d70f382e6016d2f506d134eee11/hls/c5c75d70f382e6016d2f506d134eee11.m3u8', '4028fe81724ffb180172515f3bbe000e', '2020-05-26 23:04:16');
INSERT INTO `teachplan_media_pub` VALUES ('4028fe81724ffb18017251640f1b0024', '51e61c689decc235d5861588a9cda93c', '完形填空.avi', '5/1/51e61c689decc235d5861588a9cda93c/hls/51e61c689decc235d5861588a9cda93c.m3u8', '4028fe81724ffb180172515f3bbe000e', '2020-05-26 23:04:16');
INSERT INTO `teachplan_media_pub` VALUES ('4028fe81724ffb18017251644e130025', 'c5c75d70f382e6016d2f506d134eee11', '写作.avi', 'c/5/c5c75d70f382e6016d2f506d134eee11/hls/c5c75d70f382e6016d2f506d134eee11.m3u8', '4028fe81724ffb180172515f3bbe000e', '2020-05-26 23:04:16');
INSERT INTO `teachplan_media_pub` VALUES ('4028fe81724ffb18017251646ec10026', '51e61c689decc235d5861588a9cda93c', '完形填空.avi', '5/1/51e61c689decc235d5861588a9cda93c/hls/51e61c689decc235d5861588a9cda93c.m3u8', '4028fe81724ffb180172515f3bbe000e', '2020-05-26 23:04:16');
INSERT INTO `teachplan_media_pub` VALUES ('4028fe81724ffb180172516482010027', 'c5c75d70f382e6016d2f506d134eee11', '写作.avi', 'c/5/c5c75d70f382e6016d2f506d134eee11/hls/c5c75d70f382e6016d2f506d134eee11.m3u8', '4028fe81724ffb180172515f3bbe000e', '2020-05-26 23:04:16');
INSERT INTO `teachplan_media_pub` VALUES ('4028fe81724ffb180172516dcb7c0028', 'c5c75d70f382e6016d2f506d134eee11', '写作.avi', 'c/5/c5c75d70f382e6016d2f506d134eee11/hls/c5c75d70f382e6016d2f506d134eee11.m3u8', '4028fe81724ffb180172515ea348000d', '2020-05-26 23:04:13');

SET FOREIGN_KEY_CHECKS = 1;

# Host: localhost  (Version 5.5.28)
# Date: 2017-01-18 17:28:06
# Generator: MySQL-Front 5.4  (Build 4.73) - http://www.mysqlfront.de/

/*!40101 SET NAMES utf8 */;

#
# Structure for table "item"
#

DROP TABLE IF EXISTS `item`;
CREATE TABLE `item` (
  `Id` varchar(255) NOT NULL DEFAULT '' COMMENT '主键id',
  `content` varchar(255) DEFAULT NULL COMMENT '事项内容',
  `createtime` datetime DEFAULT NULL COMMENT '创建时间',
  `lastmodifytime` datetime DEFAULT NULL COMMENT '最后修改时间',
  `finishtime` datetime DEFAULT NULL COMMENT '完成时间',
  `finishflag` int(11) DEFAULT NULL COMMENT '结束标记（结束为1，否则为0）',
  `deleteflag` int(11) DEFAULT NULL COMMENT '删除标记（删除为1，否则为0）',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "item"
#

INSERT INTO `item` VALUES ('5e78e85d-4fcc-4e74-8d6f-fdf61127062a','这是第一个事项','2017-01-18 17:26:43','2017-01-18 17:27:38',NULL,0,0),('716e7e08-ae4e-4c47-944f-6ac19b9c7b29','点击向上箭头，置顶事项','2017-01-18 17:27:25','2017-01-18 17:27:25',NULL,0,0),('e8dfc94f-ff29-4060-9694-f6e87e6a79bf','点击完成按钮，完成事项','2017-01-18 17:27:02','2017-01-18 17:27:37',NULL,0,0);

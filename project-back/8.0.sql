CREATE DATABASE  IF NOT EXISTS `sal-management-system` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `sal-management-system`;
-- MySQL dump 10.13  Distrib 8.0.18, for Win64 (x86_64)
--
-- Host: localhost    Database: sal-management-system
-- ------------------------------------------------------
-- Server version	8.0.18

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `account`
--

DROP TABLE IF EXISTS `account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `account` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `account` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_index` (`account`) USING BTREE
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account`
--

LOCK TABLES `account` WRITE;
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
/*!40000 ALTER TABLE `account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `actual_salary_item`
--

DROP TABLE IF EXISTS `actual_salary_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `actual_salary_item` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自身id',
  `employee_id` int(11) NOT NULL COMMENT '所属员工id',
  `item_id` int(11) NOT NULL COMMENT '所属项目id',
  `value` double NOT NULL DEFAULT '0' COMMENT '实发工资具体值',
  `state` int(1) NOT NULL DEFAULT '0' COMMENT '状态:表示工资状态，0是计算中，1是暂存待发，2是已发',
  `date` date NOT NULL DEFAULT '2020-01-01' COMMENT '工资所属日期:只取到月份',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='实发项目:工资项目四个类型之一，实发项目只有"工资发放"这一种';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `actual_salary_item`
--

LOCK TABLES `actual_salary_item` WRITE;
/*!40000 ALTER TABLE `actual_salary_item` DISABLE KEYS */;
INSERT INTO `actual_salary_item` VALUES (1,43,13,3250,2,'2020-01-01');
/*!40000 ALTER TABLE `actual_salary_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `calculate_item`
--

DROP TABLE IF EXISTS `calculate_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `calculate_item` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自身id',
  `employee_id` int(11) NOT NULL COMMENT '所属员工id',
  `item_id` int(11) NOT NULL COMMENT '所属项目id',
  `value` double NOT NULL DEFAULT '0' COMMENT '计算结果具体值',
  `date` date NOT NULL DEFAULT '2020-01-01' COMMENT '工资所属日期(只取到月)',
  `state` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='计算项目:工资项目四个类型的一种，是需要使用表达式计算的项目，如：请假扣款要请假次数*单次扣款值';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `calculate_item`
--

LOCK TABLES `calculate_item` WRITE;
/*!40000 ALTER TABLE `calculate_item` DISABLE KEYS */;
INSERT INTO `calculate_item` VALUES (2,43,7,10,'2020-01-01',0),(3,43,18,630,'2020-01-01',0),(4,43,23,240,'2020-01-01',0),(5,43,19,270,'2020-01-01',0),(6,43,24,60,'2020-01-01',0),(7,43,20,60,'2020-01-01',0),(8,43,25,30,'2020-01-01',0),(9,43,21,15,'2020-01-01',0),(10,43,22,24,'2020-01-01',0),(11,43,26,240,'2020-01-01',0),(12,43,27,240,'2020-01-01',0);
/*!40000 ALTER TABLE `calculate_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `department`
--

DROP TABLE IF EXISTS `department`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `department` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '各部门的id',
  `name` varchar(255) NOT NULL DEFAULT '政治部' COMMENT '部门名称',
  `telephone` varchar(255) NOT NULL DEFAULT '0' COMMENT '部门电话',
  `description` varchar(255) DEFAULT NULL COMMENT '部门描述',
  `date` date NOT NULL DEFAULT '2005-06-01' COMMENT '创立日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='部门';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `department`
--

LOCK TABLES `department` WRITE;
/*!40000 ALTER TABLE `department` DISABLE KEYS */;
INSERT INTO `department` VALUES (1,'政治部','88556644','这是一个统治部','1970-01-01'),(2,'外交部','88566644','这是一个外交部','1971-02-02'),(3,'宣传部','88576644',NULL,'1972-03-03'),(4,'市场部 ','0',NULL,'1973-04-04'),(5,'扩大局','0',NULL,'1975-05-05');
/*!40000 ALTER TABLE `department` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '员工id',
  `name` varchar(255) DEFAULT '未录入名称' COMMENT '员工名称',
  `sex` int(1) NOT NULL DEFAULT '1' COMMENT '1男0女',
  `birth` date NOT NULL DEFAULT '0000-00-00' COMMENT '生日',
  `departmentId` int(11) NOT NULL COMMENT '所属部门id',
  `departmentName` varchar(255) NOT NULL DEFAULT '公司' COMMENT '所在部门名称',
  `stationId` int(11) NOT NULL COMMENT '所属岗位id',
  `stationName` varchar(255) NOT NULL DEFAULT '经理' COMMENT '所在岗位名称',
  `employDate` date NOT NULL DEFAULT '2002-01-01' COMMENT '聘请日期',
  `workDate` date NOT NULL DEFAULT '2002-01-01' COMMENT '上岗日期',
  `form` int(1) NOT NULL DEFAULT '1' COMMENT '1正式0临时',
  `tag` int(1) NOT NULL DEFAULT '1' COMMENT '1为在职0为离职',
  `idcard` varchar(18) NOT NULL DEFAULT '' COMMENT '身份证',
  `politics` varchar(255) NOT NULL DEFAULT '群众' COMMENT '政治面貌',
  `source` varchar(255) DEFAULT NULL COMMENT '人员来源',
  `folk` varchar(255) DEFAULT NULL COMMENT '民族',
  `nation` varchar(255) DEFAULT NULL COMMENT '籍贯',
  `phone` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `height` varchar(255) DEFAULT NULL COMMENT '身高',
  `blood` varchar(255) DEFAULT NULL COMMENT '血型',
  `status` varchar(255) DEFAULT NULL COMMENT '婚姻状况',
  `homeplace` varchar(255) DEFAULT NULL COMMENT '出身地',
  `seat` varchar(255) DEFAULT NULL COMMENT '所在地',
  `eduBack` varchar(255) DEFAULT NULL COMMENT '最高学历',
  `eduDegree` varchar(255) DEFAULT NULL COMMENT '最高学位',
  `graSchool` varchar(255) DEFAULT NULL COMMENT '毕业院校',
  `speciality` varchar(255) DEFAULT NULL COMMENT '所学专业',
  `graDate` date DEFAULT NULL COMMENT '毕业日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=58 DEFAULT CHARSET=utf8 COMMENT='员工';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` VALUES (43,'浩一',1,'0000-00-00',1,'政治部',1,'清洁工','0000-00-00','0000-00-00',1,1,'111111111111114300','2',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(44,'任二',1,'0000-00-00',2,'外交部',2,'清洁工','0000-00-00','0000-00-00',1,1,'111111111111114301','2',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(45,'高尔夫',1,'0000-00-00',3,'宣传部',3,'清洁工','0000-00-00','0000-00-00',1,1,'111111111111114302','2',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(46,'球棍',1,'0000-00-00',4,'市场部 ',4,'清洁工','0000-00-00','0000-00-00',1,1,'111111111111114301','2',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(47,'艾达',1,'0000-00-00',5,'扩大局',5,'清洁工','0000-00-00','0000-00-00',1,1,'111111111111113404','2',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(48,'胎薅辣',1,'0000-00-00',1,'政治部',7,'总裁','0000-00-00','0000-00-00',1,1,'111111111111113405','2',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(49,'老人',1,'0000-00-00',2,'外交部',11,'洗碗工','0000-00-00','0000-00-00',1,1,'111111111111114360','2',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(50,'马叉虫',1,'0000-00-00',3,'宣传部',8,'厨师','0000-00-00','0000-00-00',1,1,'111111111111114361','2',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(51,'空气',1,'0000-00-00',4,'市场部 ',6,'维修工','0000-00-00','0000-00-00',1,1,'111111111111114362','2',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(52,'么西否',1,'0000-00-00',5,'扩大局',12,'运输司机','0000-00-00','0000-00-00',1,1,'111111111111113463','2',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(53,'虾头',1,'0000-00-00',1,'政治部',1,'清洁工','0000-00-00','0000-00-00',1,1,'111111111111113464','2',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(54,'用备',1,'0000-00-00',2,'外交部',15,'宣传','0000-00-00','0000-00-00',1,1,'111111111111113465','2',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(55,'烧房',1,'0000-00-00',3,'宣传部',9,'招待员','0000-00-00','0000-00-00',1,1,'111111111111113456','2',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(56,'牙白',1,'0000-00-00',4,'市场部 ',13,'仓库管理员','0000-00-00','0000-00-00',1,1,'111111111111113457','2',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(57,'瓦嘎达',1,'0000-00-00',5,'扩大局',14,'灭虫专家','0000-00-00','0000-00-00',1,1,'111111111111113458','2',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fixed_item`
--

DROP TABLE IF EXISTS `fixed_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `fixed_item` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自身id',
  `employee_id` int(11) NOT NULL COMMENT '所属员工id',
  `item_id` int(11) NOT NULL COMMENT '所属工资项目id',
  `value` double NOT NULL DEFAULT '0' COMMENT '具体值',
  `date` date NOT NULL DEFAULT '2020-01-01' COMMENT '工资项目所属日期(具体到月)',
  `state` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='固定项目:工资项目四个类型之一，表示具体值相对固定无需后续计算的项目，如基本工资，高温津贴等';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fixed_item`
--

LOCK TABLES `fixed_item` WRITE;
/*!40000 ALTER TABLE `fixed_item` DISABLE KEYS */;
INSERT INTO `fixed_item` VALUES (1,45,1,3666,'2020-01-01',0),(2,45,2,300,'2019-01-01',0),(3,45,3,0,'2020-01-01',0),(4,45,4,0,'2020-01-01',0),(5,46,1,4777,'2020-01-01',0),(6,46,2,300,'2020-01-01',0),(7,46,3,0,'2020-01-01',0),(8,46,4,0,'2020-01-01',0),(9,48,1,5888,'2020-01-01',0),(10,48,2,300,'2020-01-01',0),(11,48,3,0,'2020-01-01',0),(12,48,4,0,'2020-01-01',0),(13,43,1,3000,'2020-01-01',0),(14,43,2,300,'2020-01-01',0);
/*!40000 ALTER TABLE `fixed_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `import_item`
--

DROP TABLE IF EXISTS `import_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `import_item` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自身id',
  `employee_id` int(11) NOT NULL COMMENT '所属员工id',
  `item_id` int(11) NOT NULL COMMENT '所属工资项目id',
  `value` int(11) NOT NULL DEFAULT '0' COMMENT '具体指',
  `date` date NOT NULL DEFAULT '2020-01-01' COMMENT '项目所属日期(使用时只需具体到月)',
  `state` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='导入项目:四个工资项目类型之一,是可能需要外部批量导入的如请假或迟到等考勤记录或加班记录等';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `import_item`
--

LOCK TABLES `import_item` WRITE;
/*!40000 ALTER TABLE `import_item` DISABLE KEYS */;
INSERT INTO `import_item` VALUES (2,43,5,5,'2020-02-01',0),(3,43,5,1,'2020-01-01',1);
/*!40000 ALTER TABLE `import_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `item`
--

DROP TABLE IF EXISTS `item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `item` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自身id',
  `name` varchar(45) NOT NULL COMMENT '工资项目名称',
  `type` varchar(45) NOT NULL COMMENT '工资项目类型：包括固定项目，导入项目，计算项目，实发项目',
  `effective` int(11) NOT NULL DEFAULT '1' COMMENT '作用位：标识该项目是增项1还是减项-1还是对实发工资无影响项0，例如高温津贴为增项，则为1，最后计算时项目具体value*1为正，对实发工资起增加效果；迟到扣款为减少项，则为-1，最后计算时项目具体value*-1位负，对实发工资起减少效果。',
  `description` varchar(45) DEFAULT NULL COMMENT '工资项目描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='工资项目，记录各种类型的工资项目';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `item`
--

LOCK TABLES `item` WRITE;
/*!40000 ALTER TABLE `item` DISABLE KEYS */;
INSERT INTO `item` VALUES (1,'基本工资','固定项目',1,'每月基本的工资'),(2,'高温补贴','固定项目',1,'夏天高温的补贴'),(3,'采暖补贴','固定项目',1,'冬天低温的补贴'),(5,'迟到次数 ','导入项目',1,'员工每月迟到次数'),(6,'病假次数','导入项目',1,'员工每月因病请假次数'),(7,'迟到扣款','计算项目',-1,'根据迟到次数计算'),(8,'个人支付公积金','计算项目',-1,'根据每月工资计算'),(9,'病假扣款','计算项目',-1,'根据病假次数计算'),(11,'事假次数','导入项目',1,'每月因事请假次数'),(12,'事假扣款','计算项目',-1,'根据事假次数计算'),(13,'工资发放','实发项目',1,'发工资'),(14,'交通补贴','固定项目',1,'车费等'),(15,'住房补贴','固定项目',1,NULL),(16,'加班次数','导入项目',1,'员工每月加班次数'),(17,'加班工资','计算项目',1,'根据加班次数计算'),(18,'单位缴纳养老保险','计算项目',0,'基本工资*21%'),(19,'单位缴纳医疗保险','计算项目',0,'基本工资*9%'),(20,'单位缴纳失业保险','计算项目',0,'基本工资*2%'),(21,'单位缴纳工伤保险','计算项目',0,'基本工资*0.5%'),(22,'单位缴纳生育保险','计算项目',0,'基本工资*0.8%'),(23,'个人缴纳养老保险','计算项目',-1,'基本工资*8%'),(24,'个人缴纳医疗保险','计算项目',-1,'基本工资*2%'),(25,'个人缴纳失业保险','计算项目',-1,'基本工资*1%'),(26,'单位缴纳住房公积金','计算项目',0,'基本工资*8%'),(27,'个人缴纳住房公积金','计算项目',-1,'基本工资*8%'),(28,'个人所得税','计算项目',-1,NULL);
/*!40000 ALTER TABLE `item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `salary_result`
--

DROP TABLE IF EXISTS `salary_result`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `salary_result` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `employee_id` int(11) NOT NULL COMMENT '员工id',
  `actual_salary` double DEFAULT NULL COMMENT '实发工资',
  `basic_salary` double DEFAULT NULL COMMENT '基本工资',
  `late_count` int(11) DEFAULT NULL COMMENT '迟到次数',
  `sick_leave_count` int(11) DEFAULT NULL COMMENT '病假次数',
  `late_fine` double DEFAULT NULL COMMENT '迟到扣款',
  `event_leave_count` int(11) DEFAULT NULL COMMENT '事假次数',
  `extra_work_count` int(11) DEFAULT NULL COMMENT '加班次数',
  `sick_leave_fine` double DEFAULT NULL COMMENT '病假扣款',
  `event_leave_fine` double DEFAULT NULL COMMENT '事假扣款',
  `extra_work_salary` double DEFAULT NULL COMMENT '加班工资',
  `hot_salary` double DEFAULT NULL COMMENT '高温补贴',
  `cold_salary` double DEFAULT NULL COMMENT '采暖补贴',
  `traffic_salary` double DEFAULT NULL COMMENT '交通补贴',
  `house_salary` double DEFAULT NULL COMMENT '住房补贴',
  `company_paid_old_isurance` double DEFAULT NULL COMMENT '单位缴纳养老保险 ',
  `employee_paid_old_isurance` double DEFAULT NULL COMMENT '个人缴纳养老保险',
  `company_paid_medical_isurance` double DEFAULT NULL COMMENT '单位缴纳医疗保险',
  `employee_paid_medical_isurance` double DEFAULT NULL COMMENT '个人缴纳医疗保险',
  `company_paid_unemployment_isurance` double DEFAULT NULL COMMENT '单位缴纳失业保险',
  `employee_paid_unemployment_isurance` double DEFAULT NULL COMMENT '个人缴纳失业保险',
  `company_paid_injury_isurance` double DEFAULT NULL COMMENT '单位缴纳工伤保险 ',
  `company_paid_pregnant_isurance` double DEFAULT NULL COMMENT '单位缴纳生育保险',
  `compamy_paid_housefund` double DEFAULT NULL COMMENT '单位缴纳住房公积金 ',
  `employee_paid_housefund` double DEFAULT NULL COMMENT '个人缴纳住房公积金 ',
  `tax` double DEFAULT NULL COMMENT '个人所得税 ',
  `date` date DEFAULT NULL COMMENT '工资所属时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=334 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='工资结果:工资条';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `salary_result`
--

LOCK TABLES `salary_result` WRITE;
/*!40000 ALTER TABLE `salary_result` DISABLE KEYS */;
INSERT INTO `salary_result` VALUES (333,45,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,NULL,'2020-09-01');
/*!40000 ALTER TABLE `salary_result` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `station`
--

DROP TABLE IF EXISTS `station`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `station` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL DEFAULT '产品经理' COMMENT '岗位名称',
  `departmentid` int(11) NOT NULL COMMENT '岗位所属部门id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 COMMENT='岗位';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `station`
--

LOCK TABLES `station` WRITE;
/*!40000 ALTER TABLE `station` DISABLE KEYS */;
INSERT INTO `station` VALUES (1,'清洁工',1),(2,'清洁工',2),(3,'清洁工',3),(4,'清洁工',4),(5,'清洁工',5),(6,'维修工',4),(7,'总裁',1),(8,'厨师',3),(9,'招待员',3),(10,'洗碗工',3),(11,'安全质检员',2),(12,'运输司机',5),(13,'仓库管理员',4),(14,'灭虫专家',5),(15,'宣传',2),(16,'拖',5);
/*!40000 ALTER TABLE `station` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'sal-management-system'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-09-21 10:38:21

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
-- Table structure for table `actually_salary_item`
--

DROP TABLE IF EXISTS `actually_salary_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `actually_salary_item` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `employee_id` bigint(20) NOT NULL,
  `item_id` bigint(20) NOT NULL,
  `value` double NOT NULL DEFAULT '0',
  `state` int(1) NOT NULL DEFAULT '0',
  `year` int(11) NOT NULL DEFAULT '2020',
  `month` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `act_salitem_emp_id_idx` (`employee_id`),
  KEY `act_salitem_item_id_idx` (`item_id`),
  CONSTRAINT `act_salitem_emp_id` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`id`),
  CONSTRAINT `act_salitem_item_id` FOREIGN KEY (`item_id`) REFERENCES `item` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `actually_salary_item`
--

LOCK TABLES `actually_salary_item` WRITE;
/*!40000 ALTER TABLE `actually_salary_item` DISABLE KEYS */;
INSERT INTO `actually_salary_item` VALUES (1,43,13,3250,2,2020,1);
/*!40000 ALTER TABLE `actually_salary_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `calculate_item`
--

DROP TABLE IF EXISTS `calculate_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `calculate_item` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `employee_id` bigint(20) NOT NULL,
  `item_id` bigint(20) NOT NULL,
  `expression` varchar(45) DEFAULT NULL,
  `value` double NOT NULL DEFAULT '0',
  `year` int(11) NOT NULL DEFAULT '2020',
  `month` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `calitem_emp_id_idx` (`employee_id`),
  KEY `calitem_item_id_idx` (`item_id`),
  CONSTRAINT `calitem_emp_id` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`id`),
  CONSTRAINT `calitem_item_id` FOREIGN KEY (`item_id`) REFERENCES `item` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `calculate_item`
--

LOCK TABLES `calculate_item` WRITE;
/*!40000 ALTER TABLE `calculate_item` DISABLE KEYS */;
INSERT INTO `calculate_item` VALUES (1,43,7,'(-1)*(5*10)',-50,2020,1);
/*!40000 ALTER TABLE `calculate_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `department`
--

DROP TABLE IF EXISTS `department`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `department` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '各部门的id',
  `name` varchar(255) NOT NULL DEFAULT '部门',
  `type` varchar(255) NOT NULL DEFAULT '公司',
  `telephone` varchar(255) NOT NULL DEFAULT '0',
  `fax` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `predepartment` varchar(255) DEFAULT NULL,
  `date` date NOT NULL DEFAULT '0000-00-00',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `department`
--

LOCK TABLES `department` WRITE;
/*!40000 ALTER TABLE `department` DISABLE KEYS */;
INSERT INTO `department` VALUES (1,'政治部','中央','88556644','321','这是一个统治部','null','1970-01-01'),(2,'外交部','中央','88566644','3210','这是一个外交部','政治部','1971-02-02'),(3,'宣传部','中央','88576644','32103',NULL,'政治部','1972-03-03'),(4,'市场部 ','中央','0',NULL,NULL,'政治部','1973-04-04'),(5,'扩大局','局','0',NULL,NULL,'外交部','1975-05-05');
/*!40000 ALTER TABLE `department` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `sex` int(1) NOT NULL DEFAULT '1' COMMENT '1男0女',
  `birth` date NOT NULL DEFAULT '0000-00-00',
  `departmentId` bigint(20) NOT NULL,
  `departmentName` varchar(255) NOT NULL DEFAULT '公司' COMMENT '所在部门名称',
  `stationId` bigint(20) NOT NULL,
  `stationName` varchar(255) NOT NULL DEFAULT '管理' COMMENT '所在岗位名称',
  `employDate` date NOT NULL DEFAULT '0000-00-00',
  `workDate` date NOT NULL DEFAULT '0000-00-00',
  `form` int(1) NOT NULL DEFAULT '1' COMMENT '1正式0临时',
  `tag` int(1) NOT NULL DEFAULT '1' COMMENT '1为在职0为离职',
  `idcard` varchar(18) NOT NULL DEFAULT '',
  `politics` varchar(255) NOT NULL DEFAULT '2' COMMENT '政治面貌',
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
  PRIMARY KEY (`id`),
  KEY `emp_dep_id_idx` (`departmentId`),
  KEY `emp_sta_id_idx` (`stationId`),
  CONSTRAINT `emp_dep_id` FOREIGN KEY (`departmentId`) REFERENCES `department` (`id`),
  CONSTRAINT `emp_sta_id` FOREIGN KEY (`stationId`) REFERENCES `station` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=58 DEFAULT CHARSET=utf8;
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
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `employee_id` bigint(20) NOT NULL,
  `item_id` bigint(20) NOT NULL,
  `value` double NOT NULL DEFAULT '0',
  `year` int(11) NOT NULL DEFAULT '2020',
  `month` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `fitem_emp_id_idx` (`employee_id`),
  KEY `fitem_item_id_idx` (`item_id`),
  CONSTRAINT `fitem_emp_id` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`id`),
  CONSTRAINT `fitem_item_id` FOREIGN KEY (`item_id`) REFERENCES `item` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fixed_item`
--

LOCK TABLES `fixed_item` WRITE;
/*!40000 ALTER TABLE `fixed_item` DISABLE KEYS */;
INSERT INTO `fixed_item` VALUES (1,45,1,3666,2020,9),(2,45,2,300,2020,9),(3,45,3,0,2020,9),(4,45,4,0,2020,9),(5,46,1,4777,2020,9),(6,46,2,300,2020,9),(7,46,3,0,2020,9),(8,46,4,0,2020,9),(9,48,1,5888,2020,9),(10,48,2,300,2020,9),(11,48,3,0,2020,9),(12,48,4,0,2020,9),(13,43,1,3000,2020,1),(14,43,2,300,2020,1);
/*!40000 ALTER TABLE `fixed_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `import_item`
--

DROP TABLE IF EXISTS `import_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `import_item` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `employee_id` bigint(20) NOT NULL,
  `item_id` bigint(20) NOT NULL,
  `value` int(11) NOT NULL DEFAULT '0',
  `year` int(11) NOT NULL DEFAULT '2020',
  `month` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `imitem_emp_id_idx` (`employee_id`),
  KEY `imitem_item_id_idx` (`item_id`),
  CONSTRAINT `imitem_emp_id` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`id`),
  CONSTRAINT `imitem_item_id` FOREIGN KEY (`item_id`) REFERENCES `item` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `import_item`
--

LOCK TABLES `import_item` WRITE;
/*!40000 ALTER TABLE `import_item` DISABLE KEYS */;
INSERT INTO `import_item` VALUES (2,43,5,5,2020,1);
/*!40000 ALTER TABLE `import_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `item`
--

DROP TABLE IF EXISTS `item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `item` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `type` varchar(45) NOT NULL,
  `effective` int(11) NOT NULL DEFAULT '1',
  `description` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `item`
--

LOCK TABLES `item` WRITE;
/*!40000 ALTER TABLE `item` DISABLE KEYS */;
INSERT INTO `item` VALUES (1,'基本工资','固定项目',1,'每月基本的工资'),(2,'中暑补贴','固定项目',1,'夏天高温的补贴'),(3,'霜冻补贴','固定项目',1,'冬天低温的补贴'),(4,'舒爽补贴','固定项目',1,'春秋舒爽的补贴'),(5,'迟到次数 ','导入项目',1,'员工每月迟到次数'),(6,'病假次数','导入项目',1,'员工每月因病请假次数'),(7,'迟到扣款','计算项目',-1,'根据迟到次数计算'),(8,'个人支付公积金','计算项目',-1,'根据每月工资计算'),(9,'意外保险','计算项目',-1,'根据每月工资计算'),(10,'病假扣款','计算项目',-1,'根据病假次数计算'),(11,'事假次数','导入项目',1,'每月因事请假次数'),(12,'病假 扣款','计算项目',-1,'根据事假次数计算'),(13,'发工资','实发项目',1,'发工资');
/*!40000 ALTER TABLE `item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `station`
--

DROP TABLE IF EXISTS `station`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `station` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL DEFAULT '岗位',
  `type` varchar(255) NOT NULL DEFAULT '管理',
  `departmentid` bigint(20) NOT NULL,
  `number` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `sta_dep_id_idx` (`departmentid`),
  CONSTRAINT `sta_dep_id` FOREIGN KEY (`departmentid`) REFERENCES `department` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `station`
--

LOCK TABLES `station` WRITE;
/*!40000 ALTER TABLE `station` DISABLE KEYS */;
INSERT INTO `station` VALUES (1,'清洁工','后勤',1,101),(2,'清洁工','后勤',2,201),(3,'清洁工','后勤',3,301),(4,'清洁工','后勤 ',4,401),(5,'清洁工','后勤',5,501),(6,'维修工','后勤',4,402),(7,'总裁','管理',1,103),(8,'厨师','一线',3,304),(9,'招待员','一线',3,305),(10,'洗碗工','后勤 ',3,306),(11,'安全质检员','管理',2,207),(12,'运输司机','一线',5,508),(13,'仓库管理员','管理',4,409),(14,'灭虫专家','后勤',5,510),(15,'宣传','外交',2,211),(16,'拖','管理',5,512);
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

-- Dump completed on 2020-09-15 15:47:59

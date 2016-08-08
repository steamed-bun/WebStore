-- MySQL dump 10.13  Distrib 5.7.9, for Win64 (x86_64)
--
-- Host: localhost    Database: bookstore
-- ------------------------------------------------------
-- Server version	5.7.11-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
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
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `account` (
  `ACCOUNTID` int(11) NOT NULL AUTO_INCREMENT,
  `BALANCE` int(11) NOT NULL,
  PRIMARY KEY (`ACCOUNTID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=greek COLLATE=greek_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account`
--

LOCK TABLES `account` WRITE;
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
INSERT INTO `account` VALUES (1,2997),(2,1955);
/*!40000 ALTER TABLE `account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `book`
--

DROP TABLE IF EXISTS `book`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `book` (
  `BOOKID` int(11) NOT NULL AUTO_INCREMENT,
  `AUTHOR` varchar(255) NOT NULL,
  `TITLE` varchar(255) NOT NULL,
  `PRICE` float NOT NULL,
  `PUBLISHINGDATE` date NOT NULL,
  `SALESAMOUNT` int(11) NOT NULL,
  `STORENUMBER` int(11) NOT NULL,
  `REMARK` varchar(255) NOT NULL,
  PRIMARY KEY (`BOOKID`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=gbk;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `book`
--

LOCK TABLES `book` WRITE;
/*!40000 ALTER TABLE `book` DISABLE KEYS */;
INSERT INTO `book` VALUES (1,'song','Hibernate',45,'2016-05-12',52,48,'Good'),(2,'wu','English',56,'2016-05-13',51,49,'Bad'),(3,'赵','Math',78,'2016-05-14',35,22,'Just so so'),(4,'qian','math',45,'2016-05-13',23,5,'Good'),(5,'li','struts',36,'2016-04-13',46,2,'Good'),(6,'份饭','struts',54,'2016-04-13',34,5,'Good'),(7,'面积','Math',68,'2016-04-13',6,67,'Good'),(8,'呵呵','English',23,'2016-04-13',12,89,'Just so so'),(9,'钢铁','Math',99,'2016-04-13',43,45,'Just so so'),(10,'是否','English',87,'2016-04-13',34,23,'Good'),(11,'答','爱迪生',89,'2016-04-13',68,1,'Good');
/*!40000 ALTER TABLE `book` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `trade`
--

DROP TABLE IF EXISTS `trade`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `trade` (
  `TRADEID` int(11) NOT NULL AUTO_INCREMENT,
  `TRADETIME` datetime NOT NULL,
  `USERID` int(11) NOT NULL,
  PRIMARY KEY (`TRADEID`),
  KEY `USER_ID_idx` (`USERID`),
  CONSTRAINT `USERID` FOREIGN KEY (`USERID`) REFERENCES `user_info` (`USERID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=gb2312;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `trade`
--

LOCK TABLES `trade` WRITE;
/*!40000 ALTER TABLE `trade` DISABLE KEYS */;
INSERT INTO `trade` VALUES (6,'2016-05-12 00:00:00',1),(8,'2016-05-18 00:00:00',1),(14,'2016-05-19 00:00:00',1),(15,'2016-05-20 00:00:00',1),(16,'2016-05-20 00:00:00',1),(17,'2016-05-20 00:00:00',2),(18,'2016-05-21 00:00:00',2),(19,'2016-05-21 00:00:00',2),(20,'2016-05-21 00:00:00',2);
/*!40000 ALTER TABLE `trade` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `trade_item`
--

DROP TABLE IF EXISTS `trade_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `trade_item` (
  `ITEMID` int(11) NOT NULL AUTO_INCREMENT,
  `QUANTITY` int(11) NOT NULL,
  `BOOKID` int(11) NOT NULL,
  `TRADEID` int(11) NOT NULL,
  PRIMARY KEY (`ITEMID`),
  KEY `BOOK_ID_idx` (`BOOKID`),
  KEY `TRADE_ID_idx` (`TRADEID`),
  CONSTRAINT `BOOKID` FOREIGN KEY (`BOOKID`) REFERENCES `book` (`BOOKID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `TRADEID` FOREIGN KEY (`TRADEID`) REFERENCES `trade` (`TRADEID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=gb2312;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `trade_item`
--

LOCK TABLES `trade_item` WRITE;
/*!40000 ALTER TABLE `trade_item` DISABLE KEYS */;
INSERT INTO `trade_item` VALUES (1,3,1,6),(2,1,3,6),(3,2,4,6),(4,1,1,6),(5,2,2,8),(6,3,3,6),(7,4,4,6),(8,5,5,8),(9,1,1,14),(10,1,2,14),(11,1,1,15),(12,1,2,15),(13,1,1,16),(14,1,1,17),(15,1,3,18),(16,1,11,18),(17,1,1,19),(18,1,2,19),(19,1,1,20);
/*!40000 ALTER TABLE `trade_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_info`
--

DROP TABLE IF EXISTS `user_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_info` (
  `USERID` int(11) NOT NULL AUTO_INCREMENT,
  `USERNAME` varchar(45) NOT NULL,
  `ACCOUNTID` int(11) NOT NULL,
  PRIMARY KEY (`USERID`),
  KEY `ACCOUNT_ID_idx` (`ACCOUNTID`),
  CONSTRAINT `ACCOUNTID` FOREIGN KEY (`ACCOUNTID`) REFERENCES `account` (`ACCOUNTID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=gb2312;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_info`
--

LOCK TABLES `user_info` WRITE;
/*!40000 ALTER TABLE `user_info` DISABLE KEYS */;
INSERT INTO `user_info` VALUES (1,'bb',1),(2,'aa',2);
/*!40000 ALTER TABLE `user_info` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-05-21 11:13:28

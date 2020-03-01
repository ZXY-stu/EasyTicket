-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: client
-- ------------------------------------------------------
-- Server version	5.7.21-log

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
-- Table structure for table `ticket`
--

DROP TABLE IF EXISTS `ticket`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ticket` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ticketCode` varchar(50) NOT NULL,
  `name` varchar(20) NOT NULL,
  `price` double NOT NULL,
  `briefIntroduction` text,
  `grade` double DEFAULT NULL,
  `dealtime` varchar(20) DEFAULT NULL,
  `imgpath` varchar(50) NOT NULL,
  `sales_nums` char(10) DEFAULT NULL,
  `labels` varchar(35) DEFAULT NULL,
  `nums` int(2) NOT NULL,
  `locate` varchar(20) NOT NULL,
  `original_place` varchar(20) DEFAULT NULL,
  `destination_place` varchar(20) DEFAULT NULL,
  `ticketType` int(11) NOT NULL,
  `userid` int(11) unsigned NOT NULL,
  `areaid` varchar(40) NOT NULL,
  `gatesNum` int(4) NOT NULL,
  `payStatus` varchar(10) NOT NULL,
  `dealLine` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `ticketCode` (`ticketCode`),
  KEY `areaid` (`areaid`),
  KEY `userid` (`userid`),
  CONSTRAINT `ticket_ibfk_1` FOREIGN KEY (`areaid`) REFERENCES `user` (`areaid`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `ticket_ibfk_2` FOREIGN KEY (`userid`) REFERENCES `user` (`userId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ticket`
--

LOCK TABLES `ticket` WRITE;
/*!40000 ALTER TABLE `ticket` DISABLE KEYS */;
INSERT INTO `ticket` VALUES (1,'c21d1224-115b-4709-8afd-fa1c98039c56523','changsha',1.2,'zxy',2.3,'54564','H:\\Easy_Ticket\\app\\src\\main\\res\\drawable\\s2.jpg','1000+',NULL,2,'biejin','ASDASDAS','ASDASDA',1,1,'12345',1,'havePay',''),(2,'dd2ab601-cdb7-4fed-863e-9c0078aa5b11523','changsha',1.2,'zxy',2.3,'54564','H:\\Easy_Ticket\\app\\src\\main\\res\\drawable\\s2.jpg','1000+',NULL,2,'biejin','ASDASDAS','ASDASDA',1,1,'12345',1,'havePay',''),(3,'50ed3f7c-6a48-4bd8-9581-e53f962d6081523','changsha',1.2,'zxy',2.3,'54564','H:\\Easy_Ticket\\app\\src\\main\\res\\drawable\\s2.jpg','1000+',NULL,2,'biejin','ASDASDAS','ASDASDA',1,1,'12345',1,'havePay',''),(4,'c52cabc7-432c-448a-a5c2-fa7478bd7c7f523','changsha',1.2,'zxy',2.3,'54564','H:\\Easy_Ticket\\app\\src\\main\\res\\drawable\\s2.jpg','1000+',NULL,2,'biejin','ASDASDAS','ASDASDA',1,1,'12345',1,'havePay',''),(5,'23eb64c6-2879-4da1-a5ac-6e118b1c317f523','changsha',1.2,'zxy',2.3,'54564','H:\\Easy_Ticket\\app\\src\\main\\res\\drawable\\s2.jpg','1000+',NULL,2,'biejin','ASDASDAS','ASDASDA',1,1,'12345',1,'havePay',''),(6,'c7c8071d-a339-40a9-83d0-8fe6cbc32352523','changsha',1.2,'zxy',2.3,'54564','H:\\Easy_Ticket\\app\\src\\main\\res\\drawable\\s2.jpg','1000+',NULL,2,'biejin','ASDASDAS','ASDASDA',1,1,'123456',1,'havePay',''),(7,'4d72d244-97e6-4aef-a9b0-b5a059efddbf523','changsha',1.2,'zxy',2.3,'54564','H:\\Easy_Ticket\\app\\src\\main\\res\\drawable\\s2.jpg','1000+',NULL,2,'biejin','ASDASDAS','ASDASDA',1,1,'123456',1,'havePay',''),(8,'37f94437-4028-4a96-b702-7ffd1a4a4729523','changsha',1.2,'zxy',2.3,'54564','H:\\Easy_Ticket\\app\\src\\main\\res\\drawable\\s2.jpg','1000+',NULL,2,'biejin','ASDASDAS','ASDASDA',1,1,'123456',1,'havePay',''),(9,'8c9d0ea2-3670-4b8e-a68f-264233ea6411523','changsha',1.2,'zxy',2.3,'54564','H:\\Easy_Ticket\\app\\src\\main\\res\\drawable\\s2.jpg','1000+',NULL,2,'biejin','ASDASDAS','ASDASDA',1,1,'123456',1,'havePay',''),(10,'a69532cd-1e93-422a-98eb-7e3626b9ea6c523','changsha',1.2,'zxy',2.3,'54564','H:\\Easy_Ticket\\app\\src\\main\\res\\drawable\\s2.jpg','1000+',NULL,2,'biejin','ASDASDAS','ASDASDA',1,1,'123456',1,'havePay',''),(11,'35d4c7d6-5468-4ec5-af95-ba2e49569ff0523','changsha',1.2,'zxy',2.3,'54564','H:\\Easy_Ticket\\app\\src\\main\\res\\drawable\\s2.jpg','1000+',NULL,2,'biejin','ASDASDAS','ASDASDA',1,1,'123456',1,'havePay',''),(12,'caec8ae6-9ccb-47f0-b864-6371049b54a3523','changsha',1.2,'zxy',2.3,'54564','H:\\Easy_Ticket\\app\\src\\main\\res\\drawable\\s2.jpg','1000+',NULL,2,'biejin','ASDASDAS','ASDASDA',1,1,'123456',1,'havePay',''),(13,'2d7e4a02-8989-4a22-a171-17c4e358f6aa523','changsha',1.2,'zxy',2.3,'54564','H:\\Easy_Ticket\\app\\src\\main\\res\\drawable\\s2.jpg','1000+',NULL,2,'biejin','ASDASDAS','ASDASDA',1,1,'123456',1,'havePay',''),(14,'25bdc3f8-c9be-4f88-a880-0df9f85a810a523','changsha',1.2,'zxy',2.3,'54564','H:\\Easy_Ticket\\app\\src\\main\\res\\drawable\\s2.jpg','1000+',NULL,2,'biejin','ASDASDAS','ASDASDA',1,1,'123456',1,'havePay','123');
/*!40000 ALTER TABLE `ticket` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-01-08 15:51:53

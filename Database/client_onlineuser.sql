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
-- Table structure for table `onlineuser`
--

DROP TABLE IF EXISTS `onlineuser`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `onlineuser` (
  `connecid` varchar(40) NOT NULL,
  `loginId` int(11) unsigned NOT NULL,
  `areaid` varchar(40) DEFAULT NULL,
  `phone` varchar(20) NOT NULL,
  `userName` varchar(10) NOT NULL,
  `logintime` varchar(20) NOT NULL,
  PRIMARY KEY (`connecid`),
  UNIQUE KEY `loginId` (`loginId`),
  UNIQUE KEY `phone` (`phone`),
  UNIQUE KEY `areaid` (`areaid`),
  CONSTRAINT `onlineuser_ibfk_1` FOREIGN KEY (`loginId`) REFERENCES `user` (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `onlineuser`
--

LOCK TABLES `onlineuser` WRITE;
/*!40000 ALTER TABLE `onlineuser` DISABLE KEYS */;
INSERT INTO `onlineuser` VALUES ('5713cbb0-037e-4567-abd2-006f84c02dda528',3,'185','185','zxy','2018-6-3 22:57:33'),('5e0d92b9-e0e9-4557-875f-cf14dcc2119e523',1,'12345','13257333736','zxy','2018-4-15 23:41:52');
/*!40000 ALTER TABLE `onlineuser` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-01-08 15:51:52

-- MySQL dump 10.13  Distrib 8.0.15, for Win64 (x86_64)
--
-- Host: localhost    Database: otc_landmark
-- ------------------------------------------------------
-- Server version	8.0.15

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `otc_courses`
--

DROP TABLE IF EXISTS `otc_courses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `otc_courses` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `description` mediumtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `price` decimal(16,2) DEFAULT NULL,
  `video_times` int(11) DEFAULT NULL,
  `intructorId` bigint(20) DEFAULT NULL,
  `created_date` date DEFAULT NULL,
  `created_by` date DEFAULT NULL,
  `updated_date` date DEFAULT NULL,
  `updated_by` date DEFAULT NULL,
  `image` varchar(500) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `review` mediumtext COLLATE utf8mb4_unicode_ci,
  `instructorName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`id`),
  KEY `pk_courses_user_idx` (`intructorId`),
  CONSTRAINT `pk_courses_user` FOREIGN KEY (`intructorId`) REFERENCES `otc_user` (`USER_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `otc_courses`
--

LOCK TABLES `otc_courses` WRITE;
/*!40000 ALTER TABLE `otc_courses` DISABLE KEYS */;
INSERT INTO `otc_courses` VALUES (1,'War World Fire','mo ta',5555000.00,NULL,NULL,'2020-04-25',NULL,NULL,NULL,'/upload_course/e056272b-48d5-447c-a5dd-9a79d5ba7fd0.jpg',NULL,'khánh'),(3,'chung khoan','mota ',12312321.00,NULL,NULL,'2020-05-01',NULL,NULL,NULL,'/upload_course/64a416f8-d808-4bae-a0a6-98157fecd94f.jpg',NULL,'khánh'),(4,'Ngon','Rạp nằm ngay trụng tâm quận 1. Cụm rạp là một không gian giải trí rộng lớn để hẹn bạn bè, sinh nhật, họp báo, café hay đơn giản là selfie. Thức uống ngon, Coca-cola sảng khoái, bắp rang phô mai, caramel bổ dưỡng với giá cả cực chất.  Nhiều events liên tục ra mắt các bộ phim mới và các chương trình khuyến mãi hấp dẫn riêng có tại Cinestar Cinema.',5555000.00,NULL,NULL,'2020-05-03',NULL,NULL,NULL,'/upload_course/9bcd8459-2ee7-40d4-945c-ba21ef8d0a25.jpg',NULL,'khánh');
/*!40000 ALTER TABLE `otc_courses` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `otc_courses_clip`
--

DROP TABLE IF EXISTS `otc_courses_clip`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `otc_courses_clip` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `id_title_courses_clip` bigint(20) DEFAULT NULL,
  `title` varchar(150) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `video_times` int(11) DEFAULT NULL,
  `created_date` date DEFAULT NULL,
  `created_by` date DEFAULT NULL,
  `updated_date` date DEFAULT NULL,
  `updated_by` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `pk_courseClip_courseTitle_idx` (`id_title_courses_clip`),
  CONSTRAINT `pk_courseClip_courseTitle` FOREIGN KEY (`id_title_courses_clip`) REFERENCES `otc_title_courses_clip` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `otc_courses_clip`
--

LOCK TABLES `otc_courses_clip` WRITE;
/*!40000 ALTER TABLE `otc_courses_clip` DISABLE KEYS */;
INSERT INTO `otc_courses_clip` VALUES (3,6,'bai viet 1',NULL,'2020-05-01',NULL,'2020-05-02',NULL),(4,7,'bai viet 1',NULL,'2020-05-01',NULL,NULL,NULL),(5,6,'bai viet 2',NULL,'2020-05-01',NULL,'2020-05-02',NULL),(6,7,'bai viet 2',NULL,'2020-05-01',NULL,NULL,NULL),(7,6,'khải',NULL,'2020-05-01',NULL,'2020-05-02',NULL),(8,8,'War World Fire 2',NULL,'2020-05-01',NULL,'2020-05-02',NULL),(9,8,'War World Fire 3',NULL,'2020-05-01',NULL,'2020-05-02',NULL),(10,8,'War World Fire 4',NULL,'2020-05-01',NULL,'2020-05-01',NULL),(27,9,'War World Fire',NULL,'2020-05-02',NULL,NULL,NULL),(28,9,'War World Fire 1',NULL,'2020-05-02',NULL,NULL,NULL);
/*!40000 ALTER TABLE `otc_courses_clip` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `otc_title_courses_clip`
--

DROP TABLE IF EXISTS `otc_title_courses_clip`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `otc_title_courses_clip` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `id_otc_courses` bigint(20) DEFAULT NULL,
  `title` varchar(150) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `video_times` int(11) DEFAULT NULL,
  `source` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `created_date` date DEFAULT NULL,
  `created_by` date DEFAULT NULL,
  `updated_date` date DEFAULT NULL,
  `updated_by` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `pk_title_courses_idx` (`id_otc_courses`),
  CONSTRAINT `pk_title_courses` FOREIGN KEY (`id_otc_courses`) REFERENCES `otc_courses` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `otc_title_courses_clip`
--

LOCK TABLES `otc_title_courses_clip` WRITE;
/*!40000 ALTER TABLE `otc_title_courses_clip` DISABLE KEYS */;
INSERT INTO `otc_title_courses_clip` VALUES (6,1,'War World Fire',NULL,NULL,'2020-05-01',NULL,NULL,NULL),(7,1,'War World Fire 2',NULL,NULL,'2020-05-01',NULL,NULL,NULL),(8,3,'chương 1',NULL,NULL,'2020-05-01',NULL,NULL,NULL),(9,3,'chương 2',NULL,NULL,'2020-05-01',NULL,NULL,NULL);
/*!40000 ALTER TABLE `otc_title_courses_clip` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-05-03 11:21:47

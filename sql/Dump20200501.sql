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
  CONSTRAINT `otc_courses_otc_user_USER_ID_fk` FOREIGN KEY (`id`) REFERENCES `otc_user` (`USER_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `otc_courses`
--

LOCK TABLES `otc_courses` WRITE;
/*!40000 ALTER TABLE `otc_courses` DISABLE KEYS */;
INSERT INTO `otc_courses` VALUES (1,'War World Fire','mo ta',5555000.00,NULL,NULL,'2020-04-25',NULL,NULL,NULL,'/upload_course/e056272b-48d5-447c-a5dd-9a79d5ba7fd0.jpg',NULL,'khánh');
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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `otc_courses_clip`
--

LOCK TABLES `otc_courses_clip` WRITE;
/*!40000 ALTER TABLE `otc_courses_clip` DISABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `otc_title_courses_clip`
--

LOCK TABLES `otc_title_courses_clip` WRITE;
/*!40000 ALTER TABLE `otc_title_courses_clip` DISABLE KEYS */;
INSERT INTO `otc_title_courses_clip` VALUES (6,1,'War World Fire',NULL,NULL,'2020-05-01',NULL,NULL,NULL);
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

-- Dump completed on 2020-05-01 18:25:27

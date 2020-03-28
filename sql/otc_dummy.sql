CREATE DATABASE  IF NOT EXISTS `otc_landmark` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */;
USE `otc_landmark`;
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
-- Table structure for table `otc_category`
--

DROP TABLE IF EXISTS `otc_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `otc_category` (
  `CATEGORY_ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `CATEGORY_CODE` varchar(20) NOT NULL,
  `CATEGORY_NAME` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `PARENT_CATEGORY_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`CATEGORY_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `otc_comment`
--

DROP TABLE IF EXISTS `otc_comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `otc_comment` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `SUBJECT` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `BODY` varchar(4000) NOT NULL,
  `CREATED_BY` bigint(20) NOT NULL,
  `CREATED_DATE` timestamp NOT NULL,
  `UPDATED_BY` bigint(20) DEFAULT NULL,
  `UPDATED_DATE` timestamp NULL DEFAULT NULL,
  `DELETED_BY` bigint(20) DEFAULT NULL,
  `DELETED_DATE` timestamp NULL DEFAULT NULL,
  `ENTRY_ID` bigint(20) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `SUBJECT_UNIQUE` (`SUBJECT`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `otc_comment_attachment`
--

DROP TABLE IF EXISTS `otc_comment_attachment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `otc_comment_attachment` (
  `ID` bigint(20) NOT NULL,
  `COMMENT_ID` bigint(20) NOT NULL,
  `FILE_NAME` varchar(255) NOT NULL,
  `CONTENT_TYPE` varchar(50) NOT NULL,
  `FILE_DATA` blob NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `otc_entry`
--

DROP TABLE IF EXISTS `otc_entry`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `otc_entry` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `SUBJECT` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `BODY` varchar(4000) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `CATEGORY_ID` bigint(20) DEFAULT NULL,
  `SUB_CATEGORY_ID` bigint(20) DEFAULT NULL,
  `RATING` int(11) DEFAULT NULL,
  `CREATED_DATE` timestamp NOT NULL,
  `CREATED_BY` bigint(20) NOT NULL,
  `UPDATED_DATE` timestamp NULL DEFAULT NULL,
  `UPDATED_ BY` bigint(20) DEFAULT NULL,
  `DELETED_DATE` timestamp NULL DEFAULT NULL,
  `DELETED_BY` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `SUBJECT_UNIQUE` (`SUBJECT`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `otc_entry_attachment`
--

DROP TABLE IF EXISTS `otc_entry_attachment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `otc_entry_attachment` (
  `ID` int(11) NOT NULL,
  `ENTRY_ID` bigint(20) NOT NULL,
  `FILE_NAME` varchar(255) NOT NULL,
  `CONTENT_TYPE` varchar(50) NOT NULL,
  `FILE_DATA` blob NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `otc_stock`
--

DROP TABLE IF EXISTS `otc_stock`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `otc_stock` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `STOCK_CODE` varchar(10) NOT NULL,
  `STOCK_NAME` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `STOCK_PRICE` decimal(10,2) NOT NULL,
  `STOCK_AMOUNT` bigint(20) NOT NULL,
  `STOCK_NEW_TYPE` char(1) NOT NULL,
  `CREATED_DATE` timestamp NOT NULL,
  `CREATED_BY` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `UPDATED_DATE` timestamp NULL DEFAULT NULL,
  `UPDATED_BY` bigint(20) DEFAULT NULL,
  `DELETED_DATE` timestamp NULL DEFAULT NULL,
  `DELETED_BY` bigint(20) DEFAULT NULL,
  `STOCK_STATUS` bit(1) NOT NULL DEFAULT b'1',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `STOCK_CODE_UNIQUE` (`STOCK_CODE`),
  UNIQUE KEY `STOCK_NAME_UNIQUE` (`STOCK_NAME`),
  UNIQUE KEY `ID_UNIQUE` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-01-05  8:58:45

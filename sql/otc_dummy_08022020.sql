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
-- Dumping data for table `contact`
--

LOCK TABLES `contact` WRITE;
/*!40000 ALTER TABLE `contact` DISABLE KEYS */;
INSERT INTO `contact` VALUES (1,'Clarence','Ho','1980-07-30',0),(2,'Scott','Tiger','1990-11-02',0),(3,'John','Smith','1964-02-28',0);
/*!40000 ALTER TABLE `contact` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `otc_category`
--

LOCK TABLES `otc_category` WRITE;
/*!40000 ALTER TABLE `otc_category` DISABLE KEYS */;
INSERT INTO `otc_category` VALUES (1,'ve-tgm-group','về tgm group',NULL),(2,'chung-toi-co','chúng tôi có',NULL),(3,'ban-dang-tim','bạn đang tìm',NULL),(4,'uu-dai-thanh-vien','ưu đãi thành viên',NULL),(5,'cong-tac','cộng tác',NULL),(6,'lien-he','liên hệ',NULL),(7,'doi-ngu','đội ngũ',1),(8,'tam-nhin','tầm nhìn',1),(9,'su-menh','sứ mệnh',1),(10,'cam-ket-dich-vu','cam kết dịch vụ',1),(11,'san-otc','sàn otc',2),(12,'chung-khoan','chứng khoán',2),(13,'dao-tao','đào tạo',2),(14,'co-phieu','cổ phiếu',12),(15,'trai-phieu','trái phiếu',12),(16,'phai-sinh','phái sinh',12),(17,'uy-thac','ủy thác',12),(18,'chung-chi-quy','chứng chỉ quỹ',12),(23,'huong-dan-mo','hướng dẫn mở tài khoản',3),(24,'tim-hieu-chung-khoan','các bước tìm hiểu chứng khoán',3),(25,'chung-khoan-co-ban','kiến thức chứng khoán cơ bản',3),(26,'ban-tin-chung-khoan','bản tin chứng khoán',3),(27,'bao-cao-phan-tich','báo cáo phân tích',3),(28,'khoa-hoc-dao-tao','thông tin khóa học đào tạo',3),(29,'thanh-vien-bac','thành viên bạc',4),(30,'thanh-vien-vang','thành viên vàng',4),(31,'tv-kim-cuong','thành viên kim cương',4),(32,'tuyen-dung','tuyển dụng',5),(33,'hop-tac-dao-tao','hợp tác đào tạo',5);
/*!40000 ALTER TABLE `otc_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `otc_comment`
--

LOCK TABLES `otc_comment` WRITE;
/*!40000 ALTER TABLE `otc_comment` DISABLE KEYS */;
/*!40000 ALTER TABLE `otc_comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `otc_comment_attachment`
--

LOCK TABLES `otc_comment_attachment` WRITE;
/*!40000 ALTER TABLE `otc_comment_attachment` DISABLE KEYS */;
/*!40000 ALTER TABLE `otc_comment_attachment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `otc_entry`
--

LOCK TABLES `otc_entry` WRITE;
/*!40000 ALTER TABLE `otc_entry` DISABLE KEYS */;
INSERT INTO `otc_entry` VALUES (1,'abc','<ul>\n	<li>asdasdas</li>\n</ul>\n',1,3,NULL,'','2020-01-25 10:36:08',1,NULL,NULL,NULL,NULL),(2,'def','<ul>\n	<li>def</li>\n</ul>\n',5,7,NULL,'','2020-01-27 07:01:44',1,NULL,NULL,NULL,NULL),(4,'hgt','<p>gsdfs</p>\n',5,7,NULL,'','2020-01-28 01:39:05',1,NULL,NULL,NULL,NULL),(5,'kjh','<p>kjh</p>\n',1,3,NULL,'','2020-01-28 01:40:20',1,NULL,NULL,NULL,NULL),(6,'tttt','<p>tttt</p>\n',1,3,NULL,'','2020-01-28 02:22:56',1,NULL,NULL,NULL,NULL),(7,'kkkk','<p>kkkkkk</p>\n',1,7,NULL,'','2020-01-30 09:57:35',1,NULL,NULL,NULL,NULL),(8,'uuu','<p>uuu</p>\n',1,4,NULL,'','2020-01-28 03:33:40',1,NULL,NULL,NULL,NULL),(9,'haha','<p>haha hihi</p>\n',1,7,NULL,'','2020-01-30 09:51:01',1,NULL,NULL,NULL,NULL),(10,'ab','<p>ababababab</p>\n',1,7,NULL,'','2020-01-30 10:18:42',1,NULL,NULL,NULL,NULL),(12,'dat1','<p>dat1</p>\n',2,16,NULL,'','2020-01-30 10:29:40',1,NULL,NULL,NULL,NULL),(13,'dat2','<p>dat2 edit</p>\n',2,11,NULL,'','2020-01-30 10:34:41',1,NULL,NULL,NULL,NULL),(15,'fdsfs','<p>fadsf</p>\n',2,15,NULL,'E:\\WorkSpace-General\\WorkSpace-SSI\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\OTCLandMark\\upload\\download.jfif','2020-02-02 09:28:52',1,NULL,NULL,NULL,NULL),(16,'upload1','<p>upload1</p>\n',2,15,NULL,'\\upload\\kiem-dich-lang-son-1579849038-4113-1579849246.jpg','2020-02-05 12:24:41',1,NULL,NULL,NULL,NULL),(17,'upload2','<p>upload2</p>\n',4,30,NULL,'\\upload\\kiem-dich-lang-son-1579849038-4113-1579849246.jpg','2020-02-05 12:28:12',1,NULL,NULL,NULL,NULL),(18,'upload3','<p>upload3</p>\n',4,30,NULL,'\\upload\\download.jfif','2020-02-05 13:05:55',1,NULL,NULL,NULL,NULL),(19,'UPLOAD5','<p>UPLOAD5</p>\n',2,14,NULL,'upload\\kiem-dich-lang-son-1579849038-4113-1579849246.jpg','2020-02-05 13:26:15',1,NULL,NULL,NULL,NULL),(20,'update6','<p>update6</p>\n',4,30,NULL,'/upload/download.jfif','2020-02-05 14:02:15',1,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `otc_entry` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `otc_entry_attachment`
--

LOCK TABLES `otc_entry_attachment` WRITE;
/*!40000 ALTER TABLE `otc_entry_attachment` DISABLE KEYS */;
/*!40000 ALTER TABLE `otc_entry_attachment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `otc_stock`
--

LOCK TABLES `otc_stock` WRITE;
/*!40000 ALTER TABLE `otc_stock` DISABLE KEYS */;
/*!40000 ALTER TABLE `otc_stock` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `t1`
--

LOCK TABLES `t1` WRITE;
/*!40000 ALTER TABLE `t1` DISABLE KEYS */;
INSERT INTO `t1` VALUES (2,0,0,0.09),(2,-13.2,0,-1.3),(2,59.6,46.4,4.24),(2,30.4,30.4,8);
/*!40000 ALTER TABLE `t1` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `test_decimal`
--

LOCK TABLES `test_decimal` WRITE;
/*!40000 ALTER TABLE `test_decimal` DISABLE KEYS */;
INSERT INTO `test_decimal` VALUES (4.00),(4.00),(4.00),(4.39),(4.39);
/*!40000 ALTER TABLE `test_decimal` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-02-08 11:28:17

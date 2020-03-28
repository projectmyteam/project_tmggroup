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
-- Table structure for table `contact`
--

DROP TABLE IF EXISTS `contact`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8 ;
CREATE TABLE `contact` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `FIRST_NAME` varchar(60) NOT NULL,
  `LAST_NAME` varchar(40) NOT NULL,
  `BIRTH_DATE` date DEFAULT NULL,
  `VERSION` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `UQ_CONTACT_1` (`FIRST_NAME`,`LAST_NAME`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contact`
--

LOCK TABLES `contact` WRITE;
/*!40000 ALTER TABLE `contact` DISABLE KEYS */;
INSERT INTO `contact` VALUES (1,'Clarence','Ho','1980-07-30',0),(2,'Scott','Tiger','1990-11-02',0),(3,'John','Smith','1964-02-28',0);
/*!40000 ALTER TABLE `contact` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `otc_category`
--

DROP TABLE IF EXISTS `otc_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8 ;
CREATE TABLE `otc_category` (
  `CATEGORY_ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `CATEGORY_CODE` varchar(20) NOT NULL,
  `CATEGORY_NAME` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `PARENT_CATEGORY_ID` bigint(20) DEFAULT NULL,
  `CATEGORY_ICON` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`CATEGORY_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `otc_category`
--

LOCK TABLES `otc_category` WRITE;
/*!40000 ALTER TABLE `otc_category` DISABLE KEYS */;
INSERT INTO `otc_category` VALUES (1,'ve-tgm-group','về tgm group',NULL,NULL),(2,'chung-toi-co','chúng tôi có',NULL,NULL),(3,'ban-dang-tim','bạn đang tìm',NULL,NULL),(4,'uu-dai-thanh-vien','ưu đãi thành viên',NULL,NULL),(5,'cong-tac','cộng tác',NULL,NULL),(6,'lien-he','liên hệ',NULL,NULL),(7,'doi-ngu','đội ngũ',1,'fas fa-users'),(8,'tam-nhin','tầm nhìn',1,'fas fa-diagnoses'),(9,'su-menh','sứ mệnh',1,'fas fa-dove'),(10,'cam-ket-dich-vu','cam kết dịch vụ',1,'fab fa-ethereum'),(11,'san-otc','sàn otc',2,'fas fa-chart-line'),(12,'chung-khoan','chứng khoán',2,'fas fa-layer-group'),(13,'dao-tao','đào tạo',2,'fas fa-book-open'),(14,'co-phieu','cổ phiếu',12,'fab fa-stripe-s'),(15,'trai-phieu','trái phiếu',12,'fas fa-coins'),(16,'phai-sinh','phái sinh',12,'fab fa-battle-net'),(17,'uy-thac','ủy thác',12,'fas fa-hand-holding-usd'),(18,'chung-chi-quy','chứng chỉ quỹ',12,'fas fa-certificate'),(23,'huong-dan-mo','hướng dẫn mở tài khoản',3,'fas fa-hands-helping'),(24,'tim-hieu-chung-khoan','các bước tìm hiểu chứng khoán',3,'fas fa-th-list'),(25,'chung-khoan-co-ban','kiến thức chứng khoán cơ bản',3,'fab fa-leanpub'),(26,'ban-tin-chung-khoan','bản tin chứng khoán',3,'fas fa-newspaper'),(27,'bao-cao-phan-tich','báo cáo phân tích',3,'far fa-chart-bar'),(28,'khoa-hoc-dao-tao','thông tin khóa học đào tạo',3,'fas fa-info'),(29,'thanh-vien-bac','thành viên bạc',4,'fas fa-dollar-sign'),(30,'thanh-vien-vang','thành viên vàng',4,'fas fa-dollar-sign'),(31,'tv-kim-cuong','thành viên kim cương',4,'fas fa-dollar-sign'),(32,'tuyen-dung','tuyển dụng',5,'fas fa-user-plus'),(33,'hop-tac-dao-tao','hợp tác đào tạo',5,'fas fa-handshake');
/*!40000 ALTER TABLE `otc_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `otc_comment`
--

DROP TABLE IF EXISTS `otc_comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8 ;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `otc_comment`
--

LOCK TABLES `otc_comment` WRITE;
/*!40000 ALTER TABLE `otc_comment` DISABLE KEYS */;
/*!40000 ALTER TABLE `otc_comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `otc_comment_attachment`
--

DROP TABLE IF EXISTS `otc_comment_attachment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8 ;
CREATE TABLE `otc_comment_attachment` (
  `ID` bigint(20) NOT NULL,
  `COMMENT_ID` bigint(20) NOT NULL,
  `FILE_NAME` varchar(255) NOT NULL,
  `CONTENT_TYPE` varchar(50) NOT NULL,
  `FILE_DATA` blob NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `otc_comment_attachment`
--

LOCK TABLES `otc_comment_attachment` WRITE;
/*!40000 ALTER TABLE `otc_comment_attachment` DISABLE KEYS */;
/*!40000 ALTER TABLE `otc_comment_attachment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `otc_entry`
--

DROP TABLE IF EXISTS `otc_entry`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8 ;
CREATE TABLE `otc_entry` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `SUBJECT` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `BODY` varchar(20000) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `CATEGORY_ID` bigint(20) DEFAULT NULL,
  `SUB_CATEGORY_ID` bigint(20) DEFAULT NULL,
  `RATING` int(11) DEFAULT NULL,
  `AVATAR` varchar(200) NOT NULL,
  `CREATED_DATE` timestamp NOT NULL,
  `CREATED_BY` bigint(20) NOT NULL,
  `UPDATED_DATE` timestamp NULL DEFAULT NULL,
  `UPDATED_BY` bigint(20) DEFAULT NULL,
  `DELETED_DATE` timestamp NULL DEFAULT NULL,
  `DELETED_BY` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `SUBJECT_UNIQUE` (`SUBJECT`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `otc_entry`
--

LOCK TABLES `otc_entry` WRITE;
/*!40000 ALTER TABLE `otc_entry` DISABLE KEYS */;
INSERT INTO `otc_entry` VALUES (1,'đội ngũ','<p><strong>X&acirc;y dựng đội ngũ</strong>&nbsp;l&agrave; một thuật ngữ tập thể cho c&aacute;c loại hoạt động kh&aacute;c nhau được sử dụng để tăng cường quan hệ x&atilde; hội v&agrave; x&aacute;c định vai tr&ograve; trong c&aacute;c&nbsp;<a href=\"https://vi.wikipedia.org/w/index.php?title=Team&amp;action=edit&amp;redlink=1\" title=\"Team (trang chưa được viết)\">nh&oacute;m</a>, thường li&ecirc;n quan đến c&aacute;c nhiệm vụ hợp t&aacute;c. N&oacute; kh&aacute;c với đ&agrave;o tạo nh&oacute;m, được thiết kế bởi sự kết hợp giữa c&aacute;c nh&agrave; quản l&yacute; doanh nghiệp, học tập v&agrave; ph&aacute;t triển / OD (Nội bộ hoặc b&ecirc;n ngo&agrave;i) v&agrave; Đối t&aacute;c kinh doanh nh&acirc;n sự (nếu c&oacute; vai tr&ograve;) để cải thiện hiệu quả, thay v&igrave; quan hệ giữa c&aacute;c c&aacute; nh&acirc;n.</p>\n\n<p><img alt=\"\" src=\"https://drive.google.com/file/d/1uqnPyUJfYMBAr68k897T11x5eMmU2j6K/preview\" style=\"height:500px; width:500px\" /></p>\n\n<p>&nbsp;</p>\n',1,7,NULL,'/upload/download.jfif','2020-02-09 06:31:31',1,'2020-02-13 14:18:19',1,NULL,NULL),(2,'Thị trường chứng quyền 14/02/2020: Các tín hiệu trái chiều xuất hiện','<p>Thị trường chứng quyền trong phi&ecirc;n giao dịch ng&agrave;y 13/02/2020 c&oacute; diễn biến kh&ocirc;ng mấy t&iacute;ch cực khi to&agrave;n thị trường c&oacute; 33 m&atilde; giảm gi&aacute;, 8 m&atilde; tăng gi&aacute; v&agrave; 9 m&atilde; đứng gi&aacute;. Trong đ&oacute;, CMWG1907 l&agrave; m&atilde; giảm mạnh nhất trong phi&ecirc;n h&ocirc;m nay ở mức 15.6%.</p>\n\n<p>Thị trường chứng quyền 14/02/2020: C&aacute;c t&iacute;n hiệu tr&aacute;i chiều xuất hiện</p>\n\n<p>Thị trường chứng quyền trong phi&ecirc;n giao dịch ng&agrave;y 13/02/2020 c&oacute; diễn biến kh&ocirc;ng mấy t&iacute;ch cực khi to&agrave;n thị trường c&oacute; 33 m&atilde; giảm gi&aacute;, 8 m&atilde; tăng gi&aacute; v&agrave; 9 m&atilde; đứng gi&aacute;. Trong đ&oacute;,&nbsp;<a href=\"https://finance.vietstock.vn/chung-khoan-phai-sinh/CMWG1907/cw-tong-quan.htm\" rel=\"noreferrer\" target=\"_blank\">CMWG1907</a>&nbsp;l&agrave; m&atilde; giảm mạnh nhất trong phi&ecirc;n h&ocirc;m nay ở mức 15.6%.</p>\n\n<p>I. DIỄN BIẾN THỊ TRƯỜNG CHỨNG QUYỀN</p>\n\n<p>Thị trường chứng quyền trong phi&ecirc;n giao dịch ng&agrave;y 13/02/2020 c&oacute; diễn biến kh&ocirc;ng mấy t&iacute;ch cực khi to&agrave;n thị trường c&oacute; 33 m&atilde; giảm gi&aacute;, 8 m&atilde; tăng gi&aacute; v&agrave; 9 m&atilde; đứng gi&aacute;. Trong đ&oacute;,&nbsp;CMWG1907&nbsp;v&agrave;&nbsp;<a href=\"https://finance.vietstock.vn/chung-khoan-phai-sinh/CVNM1902/cw-tong-quan.htm\" rel=\"noreferrer\" target=\"_blank\">CVNM1902</a>&nbsp;l&agrave; hai m&atilde; giảm mạnh nhất trong phi&ecirc;n h&ocirc;m nay ở mức lần lượt l&agrave; 15.60% v&agrave; 15.40%. Ngược lại,&nbsp;<a href=\"https://finance.vietstock.vn/chung-khoan-phai-sinh/CROS2001/cw-tong-quan.htm\" rel=\"noreferrer\" target=\"_blank\">CROS2001</a>&nbsp;l&agrave; m&atilde; tăng mạnh nhất trong phi&ecirc;n nay ở mức 15%.</p>\n\n<p>Khối lượng giao dịch giảm 23.10%; gi&aacute; trị giao dịch cũng giảm 22.28% so với phi&ecirc;n trước đ&oacute;. Khối ngoại tiếp tục b&aacute;n r&ograve;ng trong phi&ecirc;n h&ocirc;m nay với tổng mức b&aacute;n r&ograve;ng hơn 166 ng&agrave;n đơn vị. Trong đ&oacute;,&nbsp;<a href=\"https://finance.vietstock.vn/chung-khoan-phai-sinh/CHPG1909/cw-tong-quan.htm\" rel=\"noreferrer\" target=\"_blank\">CHPG1909</a>&nbsp;v&agrave;&nbsp;<a href=\"https://finance.vietstock.vn/chung-khoan-phai-sinh/CVJC1901/cw-tong-quan.htm\" rel=\"noreferrer\" target=\"_blank\">CVJC1901</a>&nbsp;l&agrave; hai m&atilde; chứng quyền bị khối ngoại b&aacute;n r&ograve;ng nhiều nhất.</p>\n\n<p>Trong phi&ecirc;n giao dịch ng&agrave;y 13/02/2020,&nbsp;<a href=\"https://finance.vietstock.vn/chung-khoan-phai-sinh/CMSN1902/cw-tong-quan.htm\" rel=\"noreferrer\" target=\"_blank\">CMSN1902</a>&nbsp;v&agrave;&nbsp;<a href=\"https://finance.vietstock.vn/chung-khoan-phai-sinh/CPNJ2001/cw-tong-quan.htm\" rel=\"noreferrer\" target=\"_blank\">CPNJ2001</a>&nbsp;tiếp tục l&agrave; hai m&atilde; chứng quyền c&oacute; giao dịch s&ocirc;i động nhất. Tuy nhi&ecirc;n, nếu x&eacute;t về gi&aacute; trị giao dịch th&igrave;&nbsp;<a href=\"https://finance.vietstock.vn/chung-khoan-phai-sinh/CVPB1901/cw-tong-quan.htm\" rel=\"noreferrer\" target=\"_blank\">CVPB1901</a>&nbsp;lại l&agrave; m&atilde; dẫn đầu thị trường.</p>\n',2,11,NULL,'/upload/download.jfif','2020-02-09 07:20:51',1,'2020-02-13 13:02:51',1,NULL,NULL),(3,'Vietstock Daily 14/02: Tốt xấu đan xen','<p>Vietstock Daily 14/02: Tốt xấu đan xen</p>\n',2,13,NULL,'/upload/download.jfif','2020-02-09 07:21:23',1,'2020-02-13 12:59:52',1,NULL,NULL),(4,'FPT: Khoản thưởng cuối năm cho các nhân viên mảng CNTT ảnh hưởng đến lợi nhuận, năm 2020 duy trì tăng trưởng khoảng 20%','<p>CTCP FPT (FPT) đ&atilde; c&ocirc;ng bố KQKD 2019 với doanh thu đạt 27,7 ngh&igrave;n tỷ đồng (tăng 19%) v&agrave; LNST sau lợi &iacute;ch CĐTS đạt 3,1 ngh&igrave;n tỷ đồng (tăng 20%). Kết quả n&agrave;y tương ứng với LNST sau lợi &iacute;ch CĐTS qu&yacute; 4/2019 đạt 757 tỷ đồng, giảm 4% do:</p>\n\n<p>(1) ghi nhận chi ph&iacute; thưởng cuối năm cho nh&acirc;n vi&ecirc;n mảng Xuất khẩu Phần mềm v&agrave; CNTT trong nước;</p>\n\n<p>(2) trong qu&yacute; 4/2018, FPT ghi nhận khoảng 100 tỷ đồng LN bất thường từ việc ho&agrave;n nhập dự ph&ograve;ng đầu tư t&agrave;i ch&iacute;nh;</p>\n\n<p>(3) LN k&eacute;m khả quan tại c&ocirc;ng ty li&ecirc;n kết FPT Retail.</p>\n\n<p>Ban l&atilde;nh đạo kỳ vọng FPT sẽ duy tr&igrave; mức tăng trưởng trong năm 2020 tương tự 2019.</p>\n\n<p>D&ugrave; lợi nhuận 2019 kh&ocirc;ng đạt dự b&aacute;o, Chứng kho&aacute;n Bản Việt (VCSC) vẫn duy tr&igrave; quan điểm t&iacute;ch cực với triển vọng tăng trưởng của FPT, được dẫn dắt bởi c&aacute;c mảng kinh doanh trụ cột bao gồm Xuất khẩu Phần mềm, Dịch vụ Viễn th&ocirc;ng v&agrave; Gi&aacute;o dục. Trong đ&oacute;:</p>\n\n<p>(1) Mảng Xuất khẩu Phần mềm: Doanh thu tăng 29%, LNTT tăng 27% khi c&aacute;c thị trường đều ghi nhận tăng trưởng mạnh mẽ. Thị trường Nhật Bản (chiếm 51% doanh thu mảng Xuất khẩu Phần mềm) ghi nhận tăng trưởng 18% trong năm 2019. Trong khi đ&oacute;, doanh thu từ c&aacute;c thị trường Mỹ/EU/APAC tăng mạnh 47%/27%/43%. Theo FPT, tăng trưởng doanh thu đến từ c&aacute;c kh&aacute;ch h&agrave;ng mới trong khi c&aacute;c hợp đồng hiện tại được k&yacute; mới với gi&aacute; trị cao hơn.</p>\n\n<p>Tăng trưởng doanh thu thừ thị trường Nhật Bản trong năm nay chững lại so với mức tăng 30% ghi nhận trong năm trước, một phần do c&ocirc;ng ty t&aacute;i cơ cấu danh mục kh&aacute;ch h&agrave;ng tại Nhật Bản khi tập trung nhiều hơn v&agrave;o c&aacute;c kh&aacute;ch h&agrave;ng lớn. Ban l&atilde;nh đạo kỳ vọng tăng trưởng sẽ phục hồi trong năm 2020.</p>\n',2,15,NULL,'/upload/download.jfif','2020-02-09 07:21:48',1,'2020-02-13 13:19:33',1,NULL,NULL),(5,'hdmtk1','<p>aaaa aaaa aaaa aaaa aaaa aaaa aaaa aaaa aaaa aaaa aaaa aaaa aaaa aaaa aaaa aaaa aaaa aaaa aaaa aaaa aaaa aaaa aaaa aaaa aaaa aaaa aaaa aaaa aaaa aaaa aaaa aaaa aaaa aaaa aaaa aaaa aaaa aaaa aaaa aaaa aaaa aaaa</p>\n',3,23,NULL,'/upload/download.jfif','2020-02-09 09:05:41',1,'2020-02-13 12:50:15',1,NULL,NULL),(6,'Korea Investment Management (KIM) hoàn tất mua lại Công ty quản lý quỹ Hùng Việt','<p>hdmtk3</p>\n',3,23,NULL,'/upload/download.jfif','2020-02-09 09:08:36',1,'2020-02-13 13:20:44',1,NULL,NULL),(7,'Phân tích kỹ thuật phiên chiều 13/02: Rung lắc trở lại','<p>cbthck1</p>\n',3,24,NULL,'/upload/000-1oh2vs-1-1580262359-6703-1580262456.jpg','2020-02-09 09:09:16',1,'2020-02-13 13:21:21',1,NULL,NULL),(8,'SIP, LMH, SFI, NBB, DTL, VCP, VLP, DTT, CCA: Thông tin giao dịch lượng lớn cổ phiếu','<p>cbthck2</p>\n',3,24,NULL,'/upload/aa.png','2020-02-09 09:09:50',1,'2020-02-13 13:21:47',1,NULL,NULL),(9,'Nhà đầu tư thận trọng chờ thời, SBT bùng nổ phiên thứ 3 liên tiếp bất chấp chứng khoán đi ngang','<p>ktckcb1</p>\n',3,25,NULL,'/upload/download.jfif','2020-02-09 09:44:35',1,'2020-02-13 13:22:09',1,NULL,NULL),(10,'MSCI Frontier Markets Index giữ nguyên số lượng cổ phiếu Việt Nam trong kỳ cơ cấu tháng 2','<p>btck1</p>\n',3,26,NULL,'/upload/download.jfif','2020-02-09 09:44:57',1,'2020-02-13 13:22:23',1,NULL,NULL),(11,'VGT - Trong nguy có cơ','<p>bcpt1</p>\n',3,27,NULL,'/upload/download.jfif','2020-02-09 09:45:34',1,'2020-02-13 13:22:52',1,NULL,NULL),(12,'Ngày 13/02/2020: 10 cổ phiếu “nóng” dưới góc nhìn PTKT của Vietstock','<p>ttkhdt1</p>\n',3,28,NULL,'/upload/download.jfif','2020-02-09 09:46:12',1,'2020-02-13 13:23:21',1,NULL,NULL),(14,'Học sinh TP HCM nghỉ hết tháng 2','<p>Trước diễn biến phức tạp của dịch nCoV, UBND TP HCM cho gần 2 triệu học sinh nghỉ học đến ng&agrave;y 29/2, đồng thời kiến nghị trung ương cho nghỉ hết th&aacute;ng 3.</p>\n\n<p>Quyết định được UBND TP HCM đưa ra chiều 14/2, sau hai lần cho học sinh nghỉ từ ng&agrave;y&nbsp;<a href=\"https://vnexpress.net/giao-duc/hoc-sinh-sai-gon-duoc-nghi-mot-tuan-4049249.html\">3 đến 9/2</a>, rồi&nbsp;<a href=\"https://vnexpress.net/giao-duc/hoc-sinh-tp-hcm-duoc-nghi-them-mot-tuan-4051182.html\">k&eacute;o d&agrave;i</a>&nbsp;đến ng&agrave;y 16/2 để ph&ograve;ng tr&aacute;nh dịch bệnh.</p>\n\n<p>Ngo&agrave;i ra, TP HCM sẽ kiến nghị Ch&iacute;nh phủ, Bộ Gi&aacute;o dục v&agrave; Đ&agrave;o tạo cho ph&eacute;p học sinh, sinh vi&ecirc;n, học vi&ecirc;n c&aacute;c cơ sở gi&aacute;o dục nghề nghiệp nghỉ hết th&aacute;ng 3, đồng thời điều chỉnh kế hoạch thời gian năm học 2019-2020.</p>\n\n<p>Cụ thể, học kỳ II dự kiến bắt đầu từ th&aacute;ng 4 đến th&aacute;ng 7, để nghỉ h&egrave; v&agrave;o th&aacute;ng 8. Năm học mới bắt đầu từ th&aacute;ng 9 để ho&agrave;n tất chương tr&igrave;nh học tập năm học 2019-2020.</p>\n\n<p>Theo &ocirc;ng Từ Lương (Ph&oacute; gi&aacute;m đốc Sở Th&ocirc;ng tin v&agrave; Truyền th&ocirc;ng TP HCM), phương &aacute;n n&agrave;y được sự thống nhất của Thường trực Th&agrave;nh uỷ, UBND th&agrave;nh phố v&agrave; c&aacute;c sở ng&agrave;nh trong cuộc họp b&agrave;n phương &aacute;n nghỉ học ph&ograve;ng dịch nCoV, v&agrave;o tối c&ugrave;ng ng&agrave;y.</p>\n\n<p><img alt=\"\" src=\"https://i-vnexpress.vnecdn.net/2020/02/14/nghi-ho-c-pho-ng-di-ch-nCoV-3959-1581686779.jpg\" style=\"height:513px; width:750px\" /></p>\n',1,8,NULL,'/upload/aa.png','2020-02-14 15:00:30',1,NULL,NULL,NULL,NULL),(15,'Thêm hơn 2.600 người nhiễm virus corona','<p>Trung Quốc ghi nhận th&ecirc;m 2.641 trường hợp nhiễm virus corona, n&acirc;ng tổng số người nhiễm tr&ecirc;n thế giới l&ecirc;n 67.100, trong đ&oacute; c&oacute; 1.526 ca tử vong.</p>\n\n<p>Tỉnh Hồ Bắc, t&acirc;m điểm dịch vi&ecirc;m phổi corona (Covid-19), c&oacute; th&ecirc;m 139 ca tử vong v&agrave; 2.420 ca nhiễm mới, n&acirc;ng tổng số người chết v&agrave; nhiễm bệnh tại tỉnh l&ecirc;n lần lượt l&agrave; 1.457 v&agrave; 54.406, theo số liệu được Ủy ban Y tế tỉnh n&agrave;y c&ocirc;ng bố h&ocirc;m nay.</p>\n\n<p><img alt=\"\" src=\"https://i-vnexpress.vnecdn.net/2020/02/15/890c9682-4d77-11ea-9b4e-9c1040-9394-5843-1581723728.jpg\" style=\"height:450px; width:750px\" /></p>\n',2,15,NULL,'/upload/aa.png','2020-02-15 03:07:13',1,NULL,NULL,NULL,NULL),(17,'Ông Tập kêu gọi cải tổ hệ thống y tế khẩn cấp','<p>Chủ tịch Trung Quốc k&ecirc;u gọi cải tổ hệ thống quản l&yacute; v&agrave; ứng ph&oacute; y tế khẩn cấp khi nước n&agrave;y nỗ lực chống dịch vi&ecirc;m phổi.</p>\n\n<p>&quot;Kh&ocirc;ng c&oacute; sự chuẩn bị đầy đủ cho một thảm họa - &nbsp;c&aacute;c đ&aacute;nh gi&aacute; rủi ro, nghi&ecirc;n cứu v&agrave; quản l&yacute; chuy&ecirc;n s&acirc;u cho c&aacute;c trường hợp khẩn cấp kh&ocirc;ng được thực hiện. Kh&ocirc;ng c&oacute; sự gi&aacute;m s&aacute;t v&agrave; hệ thống cảnh b&aacute;o sớm ph&ugrave; hợp, v&agrave; nền tảng quản l&yacute; khẩn cấp phải được cải thiện&quot;, &ocirc;ng Tập Cận B&igrave;nh n&oacute;i trong cuộc họp với l&atilde;nh đạo cấp cao Trung Quốc h&ocirc;m qua ở Bắc Kinh.</p>\n\n<p>&Ocirc;ng Tập n&oacute;i th&ecirc;m đ&acirc;y l&agrave; b&agrave;i kiểm tra lớn đối với hệ thống quản trị v&agrave; năng lực quốc gia của Trung Quốc trong bối cảnh nước n&agrave;y đang phải căng m&igrave;nh ứng ph&oacute; với dịch vi&ecirc;m phổi corona (Covid-19).</p>\n',1,9,NULL,'/upload/aa.png','2020-02-15 04:29:31',1,NULL,NULL,NULL,NULL),(26,'Căn nhà hoang Tuấn \'Khỉ\' ẩn náu','<h1>Căn nh&agrave; hoang Tuấn &#39;Khỉ&#39; ẩn n&aacute;u</h1>\n',5,32,NULL,'/upload/aa.png','2020-02-15 06:05:03',1,NULL,NULL,NULL,NULL),(28,'Ngư dân kiếm tiền triệu nhờ trúng ốc ruốc','<h1>Ngư d&acirc;n kiếm tiền triệu nhờ tr&uacute;ng ốc ruốc</h1>\n',2,17,NULL,'/upload/aa.png','2020-02-15 06:10:11',1,NULL,NULL,NULL,NULL),(29,'aaaaaaaaaaaa','<p>aaaaaaaaa</p>\n',4,31,NULL,'/upload/download.jfif','2020-02-15 06:11:02',1,'2020-02-15 08:26:16',1,NULL,NULL),(30,'Bánh mì thanh long','<h1>B&aacute;nh m&igrave; thanh long</h1>\n\n<p><img alt=\"\" src=\"https://i-vnexpress.vnecdn.net/2020/02/15/d082de8ab1f52aa12483ef97391799-6619-9057-1581732153.jpg\" style=\"height:450px; width:750px\" /></p>\n',5,33,NULL,'/upload/aa.png','2020-02-15 06:16:25',1,'2020-02-15 06:19:57',1,NULL,NULL),(31,'aaaa','<p>dasda</p>\n',6,NULL,NULL,'/upload/aa.png','2020-02-16 06:23:55',1,NULL,NULL,NULL,NULL),(32,'fdadfa','<p>fdadfa</p>\n',1,9,NULL,'/upload/entry/avarta000-1oh2vs-1-1580262359-6703-1580262456.jpg','2020-02-17 14:17:45',1,NULL,NULL,NULL,NULL),(33,'fdsafas','<p>fasdfas</p>\n',6,NULL,NULL,'/upload/entryaa.png','2020-02-17 15:50:58',1,NULL,NULL,NULL,NULL),(34,'dasfsa','<p>fasdfasd</p>\n',6,NULL,NULL,'/upload/entry000-1oh2vs-1-1580262359-6703-1580262456.jpg','2020-02-17 15:52:18',1,NULL,NULL,NULL,NULL),(35,'vvvvvvvv','<p>vvvvvvvvv</p>\n',6,NULL,NULL,'/upload/entryaa.png','2020-02-17 15:54:39',1,NULL,NULL,NULL,NULL),(36,'fsdfs','<p>fasdfas</p>\n',5,33,NULL,'/upload/entry000-1oh2vs-1-1580262359-6703-1580262456.jpg','2020-02-17 15:56:35',1,NULL,NULL,NULL,NULL),(37,'ss','<p>ss</p>\n',4,30,NULL,'/upload/aa.png','2020-02-24 14:44:23',1,NULL,NULL,NULL,NULL),(38,'tedy','<p>&nbsp;</p>\n\n<p>Sở Y tế đ&atilde; nỗ lực li&ecirc;n hệ được một kh&aacute;ch sạn ở quận Sơn Tr&agrave; để chuyển kh&aacute;ch từ Bệnh viện Phổi đến. Th&agrave;nh phố sẽ lo chi ph&iacute; ở.&nbsp;B&aacute;c sĩ L&ecirc; Th&agrave;nh Ph&uacute;c - Gi&aacute;m đốc Bệnh viện Phổi cho biết, &quot;nh&oacute;m du kh&aacute;ch H&agrave;n Quốc sau đ&oacute; lại kh&ocirc;ng muốn đến kh&aacute;ch sạn khi biết c&oacute; một người Việt bị sốt tr&ecirc;n c&ugrave;ng chuyến bay. Họ lo ngại kh&ocirc;ng được chăm s&oacute;c y tế cũng như ph&aacute;t sinh tiền ăn uống&quot;.</p>\n\n<p>Sau nhiều giải th&iacute;ch của l&atilde;nh đạo Sở Y tế, nh&acirc;n vi&ecirc;n Đại sứ qu&aacute;n v&agrave; Tổng l&atilde;nh sự qu&aacute;n H&agrave;n Quốc tại Việt Nam, nh&oacute;m người H&agrave;n Quốc mới v&agrave;o khu c&aacute;ch ly.</p>\n\n<p>&nbsp;</p>\n',2,11,NULL,'/upload_entry/aa.png','2020-02-24 16:44:46',1,NULL,NULL,NULL,NULL),(39,'asfs','<p>fsaf</p>\n',2,15,NULL,'/upload_entry/000-1oh2vs-1-1580262359-6703-1580262456.jpg','2020-02-25 13:54:09',1,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `otc_entry` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `otc_entry_attachment`
--

DROP TABLE IF EXISTS `otc_entry_attachment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8 ;
CREATE TABLE `otc_entry_attachment` (
  `ID` int(11) NOT NULL,
  `ENTRY_ID` bigint(20) NOT NULL,
  `FILE_NAME` varchar(255) NOT NULL,
  `CONTENT_TYPE` varchar(50) NOT NULL,
  `FILE_DATA` blob NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `otc_entry_attachment`
--

LOCK TABLES `otc_entry_attachment` WRITE;
/*!40000 ALTER TABLE `otc_entry_attachment` DISABLE KEYS */;
/*!40000 ALTER TABLE `otc_entry_attachment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `otc_news`
--

DROP TABLE IF EXISTS `otc_news`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8 ;
CREATE TABLE `otc_news` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `SUBJECT` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `BODY` varchar(20000) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `AVATAR` varchar(200) NOT NULL,
  `ENTRY_ID` bigint(20) NOT NULL,
  `CATEGORY_ID` bigint(20) NOT NULL,
  `SUB_CATEGORY_ID` bigint(20) NOT NULL,
  `CREATED_DATE` timestamp NOT NULL,
  `CREATED_BY` bigint(20) NOT NULL,
  `UPDATED_DATE` timestamp NULL DEFAULT NULL,
  `UPDATED_BY` bigint(20) DEFAULT NULL,
  `DELETED_DATE` timestamp NULL DEFAULT NULL,
  `DELETED_BY` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `otc_news`
--

LOCK TABLES `otc_news` WRITE;
/*!40000 ALTER TABLE `otc_news` DISABLE KEYS */;
/*!40000 ALTER TABLE `otc_news` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `otc_stock`
--

DROP TABLE IF EXISTS `otc_stock`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8 ;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `otc_stock`
--

LOCK TABLES `otc_stock` WRITE;
/*!40000 ALTER TABLE `otc_stock` DISABLE KEYS */;
/*!40000 ALTER TABLE `otc_stock` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t1`
--

DROP TABLE IF EXISTS `t1`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8 ;
CREATE TABLE `t1` (
  `i` int(11) DEFAULT NULL,
  `d1` double DEFAULT NULL,
  `d2` double DEFAULT NULL,
  `d3` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t1`
--

LOCK TABLES `t1` WRITE;
/*!40000 ALTER TABLE `t1` DISABLE KEYS */;
INSERT INTO `t1` VALUES (2,0,0,0.09),(2,-13.2,0,-1.3),(2,59.6,46.4,4.24),(2,30.4,30.4,8);
/*!40000 ALTER TABLE `t1` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `test_decimal`
--

DROP TABLE IF EXISTS `test_decimal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8 ;
CREATE TABLE `test_decimal` (
  `test` double(16,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

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

-- Dump completed on 2020-02-27 21:24:05

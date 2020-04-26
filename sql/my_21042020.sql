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
INSERT INTO `otc_category` VALUES (1,'ve-tgm-group','Về Tgm Group ',NULL,NULL),(2,'chung-toi-co','Chúng Tôi Có ',NULL,NULL),(3,'ban-dang-tim','Bạn Đang Tìm ',NULL,NULL),(4,'uu-dai-thanh-vien','Ưu Đãi Thành Viên ',NULL,NULL),(5,'cong-tac','Cộng Tác ',NULL,NULL),(6,'lien-he','Liên Hệ',NULL,NULL),(7,'doi-ngu','Đội Ngũ ',1,'fas fa-users'),(8,'tam-nhin','Tầm Nhìn ',1,'fas fa-diagnoses'),(9,'su-menh','Sứ Mệnh ',1,'fas fa-dove'),(10,'cam-ket-dich-vu','Cam Kết Dịch Vụ ',1,'fab fa-ethereum'),(11,'san-otc','Sàn Otc ',2,'fas fa-chart-line'),(12,'chung-khoan','Chứng Khoán ',2,'fas fa-layer-group'),(13,'dao-tao','Đào Tạo ',2,'fas fa-book-open'),(14,'co-phieu','Cổ Phiếu ',12,'fab fa-stripe-s'),(15,'trai-phieu','Trái Phiếu ',12,'fas fa-coins'),(16,'phai-sinh','Phái Sinh ',12,'fab fa-battle-net'),(17,'uy-thac','Ủy Thác ',12,'fas fa-hand-holding-usd'),(18,'chung-chi-quy','Chứng Chỉ Quỹ ',12,'fas fa-certificate'),(23,'huong-dan-mo','Hướng Dẫn Mở Tài Khoản ',3,'fas fa-hands-helping'),(24,'tim-hieu-chung-khoan','Các Bước Tìm Hiểu Chứng Khoán ',3,'fas fa-th-list'),(25,'chung-khoan-co-ban','Kiến Thức Chứng Khoán Cơ Bản ',3,'fab fa-leanpub'),(26,'ban-tin-chung-khoan','Bản Tin Chứng Khoán ',3,'fas fa-newspaper'),(27,'bao-cao-phan-tich','Báo Cáo Phân Tích ',3,'far fa-chart-bar'),(28,'khoa-hoc-dao-tao','Thông Tin Khóa Học Đào Tạo ',3,'fas fa-info'),(29,'thanh-vien-bac','Thành Viên Bạc ',4,'fas fa-dollar-sign'),(30,'thanh-vien-vang','Thành Viên Vàng ',4,'fas fa-dollar-sign'),(31,'tv-kim-cuong','Thành Viên Kim Cương ',4,'fas fa-dollar-sign'),(32,'tuyen-dung','Tuyển Dụng ',5,'fas fa-user-plus'),(33,'hop-tac-dao-tao','Hợp Tác Đào Tạo ',5,'fas fa-handshake');
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
  `BODY` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
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
) ENGINE=InnoDB AUTO_INCREMENT=95 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `otc_entry`
--

LOCK TABLES `otc_entry` WRITE;
/*!40000 ALTER TABLE `otc_entry` DISABLE KEYS */;
INSERT INTO `otc_entry` VALUES (1,'đội ngũ','<p><strong>X&acirc;y dựng đội ngũ</strong>&nbsp;l&agrave; một thuật ngữ tập thể cho c&aacute;c loại hoạt động kh&aacute;c nhau được sử dụng để tăng cường quan hệ x&atilde; hội v&agrave; x&aacute;c định vai tr&ograve; trong c&aacute;c&nbsp;<a href=\"https://vi.wikipedia.org/w/index.php?title=Team&amp;action=edit&amp;redlink=1\" title=\"Team (trang chưa được viết)\">nh&oacute;m</a>, thường li&ecirc;n quan đến c&aacute;c nhiệm vụ hợp t&aacute;c. N&oacute; kh&aacute;c với đ&agrave;o tạo nh&oacute;m, được thiết kế bởi sự kết hợp giữa c&aacute;c nh&agrave; quản l&yacute; doanh nghiệp, học tập v&agrave; ph&aacute;t triển / OD (Nội bộ hoặc b&ecirc;n ngo&agrave;i) v&agrave; Đối t&aacute;c kinh doanh nh&acirc;n sự (nếu c&oacute; vai tr&ograve;) để cải thiện hiệu quả, thay v&igrave; quan hệ giữa c&aacute;c c&aacute; nh&acirc;n.</p>\n\n<p><img alt=\"\" src=\"https://drive.google.com/file/d/1uqnPyUJfYMBAr68k897T11x5eMmU2j6K/preview\" style=\"height:500px; width:500px\" /></p>\n\n<p>&nbsp;</p>\n',1,7,NULL,'/upload/download.jfif','2020-02-09 06:31:31',1,'2020-02-13 14:18:19',1,NULL,NULL),(2,'Thị trường chứng quyền 14/02/2020: Các tín hiệu trái chiều xuất hiện','<p>Thị trường chứng quyền trong phi&ecirc;n giao dịch ng&agrave;y 13/02/2020 c&oacute; diễn biến kh&ocirc;ng mấy t&iacute;ch cực khi to&agrave;n thị trường c&oacute; 33 m&atilde; giảm gi&aacute;, 8 m&atilde; tăng gi&aacute; v&agrave; 9 m&atilde; đứng gi&aacute;. Trong đ&oacute;, CMWG1907 l&agrave; m&atilde; giảm mạnh nhất trong phi&ecirc;n h&ocirc;m nay ở mức 15.6%.</p>\n\n<p>Thị trường chứng quyền 14/02/2020: C&aacute;c t&iacute;n hiệu tr&aacute;i chiều xuất hiện</p>\n\n<p>Thị trường chứng quyền trong phi&ecirc;n giao dịch ng&agrave;y 13/02/2020 c&oacute; diễn biến kh&ocirc;ng mấy t&iacute;ch cực khi to&agrave;n thị trường c&oacute; 33 m&atilde; giảm gi&aacute;, 8 m&atilde; tăng gi&aacute; v&agrave; 9 m&atilde; đứng gi&aacute;. Trong đ&oacute;,&nbsp;<a href=\"https://finance.vietstock.vn/chung-khoan-phai-sinh/CMWG1907/cw-tong-quan.htm\" rel=\"noreferrer\" target=\"_blank\">CMWG1907</a>&nbsp;l&agrave; m&atilde; giảm mạnh nhất trong phi&ecirc;n h&ocirc;m nay ở mức 15.6%.</p>\n\n<p>I. DIỄN BIẾN THỊ TRƯỜNG CHỨNG QUYỀN</p>\n\n<p>Thị trường chứng quyền trong phi&ecirc;n giao dịch ng&agrave;y 13/02/2020 c&oacute; diễn biến kh&ocirc;ng mấy t&iacute;ch cực khi to&agrave;n thị trường c&oacute; 33 m&atilde; giảm gi&aacute;, 8 m&atilde; tăng gi&aacute; v&agrave; 9 m&atilde; đứng gi&aacute;. Trong đ&oacute;,&nbsp;CMWG1907&nbsp;v&agrave;&nbsp;<a href=\"https://finance.vietstock.vn/chung-khoan-phai-sinh/CVNM1902/cw-tong-quan.htm\" rel=\"noreferrer\" target=\"_blank\">CVNM1902</a>&nbsp;l&agrave; hai m&atilde; giảm mạnh nhất trong phi&ecirc;n h&ocirc;m nay ở mức lần lượt l&agrave; 15.60% v&agrave; 15.40%. Ngược lại,&nbsp;<a href=\"https://finance.vietstock.vn/chung-khoan-phai-sinh/CROS2001/cw-tong-quan.htm\" rel=\"noreferrer\" target=\"_blank\">CROS2001</a>&nbsp;l&agrave; m&atilde; tăng mạnh nhất trong phi&ecirc;n nay ở mức 15%.</p>\n\n<p>Khối lượng giao dịch giảm 23.10%; gi&aacute; trị giao dịch cũng giảm 22.28% so với phi&ecirc;n trước đ&oacute;. Khối ngoại tiếp tục b&aacute;n r&ograve;ng trong phi&ecirc;n h&ocirc;m nay với tổng mức b&aacute;n r&ograve;ng hơn 166 ng&agrave;n đơn vị. Trong đ&oacute;,&nbsp;<a href=\"https://finance.vietstock.vn/chung-khoan-phai-sinh/CHPG1909/cw-tong-quan.htm\" rel=\"noreferrer\" target=\"_blank\">CHPG1909</a>&nbsp;v&agrave;&nbsp;<a href=\"https://finance.vietstock.vn/chung-khoan-phai-sinh/CVJC1901/cw-tong-quan.htm\" rel=\"noreferrer\" target=\"_blank\">CVJC1901</a>&nbsp;l&agrave; hai m&atilde; chứng quyền bị khối ngoại b&aacute;n r&ograve;ng nhiều nhất.</p>\n\n<p>Trong phi&ecirc;n giao dịch ng&agrave;y 13/02/2020,&nbsp;<a href=\"https://finance.vietstock.vn/chung-khoan-phai-sinh/CMSN1902/cw-tong-quan.htm\" rel=\"noreferrer\" target=\"_blank\">CMSN1902</a>&nbsp;v&agrave;&nbsp;<a href=\"https://finance.vietstock.vn/chung-khoan-phai-sinh/CPNJ2001/cw-tong-quan.htm\" rel=\"noreferrer\" target=\"_blank\">CPNJ2001</a>&nbsp;tiếp tục l&agrave; hai m&atilde; chứng quyền c&oacute; giao dịch s&ocirc;i động nhất. Tuy nhi&ecirc;n, nếu x&eacute;t về gi&aacute; trị giao dịch th&igrave;&nbsp;<a href=\"https://finance.vietstock.vn/chung-khoan-phai-sinh/CVPB1901/cw-tong-quan.htm\" rel=\"noreferrer\" target=\"_blank\">CVPB1901</a>&nbsp;lại l&agrave; m&atilde; dẫn đầu thị trường.</p>\n',2,11,NULL,'/upload_entry/8f2c132a-b0da-4a35-82f6-8bc0c1253365.png','2020-02-09 07:20:51',1,'2020-04-05 00:24:30',1,NULL,NULL),(3,'Vietstock Daily 14/02: Tốt xấu đan xen','<p>Vietstock Daily 14/02: Tốt xấu đan xen</p>\n',2,13,NULL,'/upload/download.jfif','2020-02-09 07:21:23',1,'2020-02-13 12:59:52',1,NULL,NULL),(6,'Korea Investment Management (KIM) hoàn tất mua lại Công ty quản lý quỹ Hùng Việt','<p>hdmtk3</p>\n',3,23,NULL,'/upload/download.jfif','2020-02-09 09:08:36',1,'2020-02-13 13:20:44',1,NULL,NULL),(7,'Phân tích kỹ thuật phiên chiều 13/02: Rung lắc trở lại','<p>cbthck1</p>\n',3,24,NULL,'/upload/000-1oh2vs-1-1580262359-6703-1580262456.jpg','2020-02-09 09:09:16',1,'2020-02-13 13:21:21',1,NULL,NULL),(8,'SIP, LMH, SFI, NBB, DTL, VCP, VLP, DTT, CCA: Thông tin giao dịch lượng lớn cổ phiếu','<p>cbthck2</p>\n',3,24,NULL,'/upload/aa.png','2020-02-09 09:09:50',1,'2020-02-13 13:21:47',1,NULL,NULL),(9,'Nhà đầu tư thận trọng chờ thời, SBT bùng nổ phiên thứ 3 liên tiếp bất chấp chứng khoán đi ngang','<p>ktckcb1</p>\n',3,25,NULL,'/upload/download.jfif','2020-02-09 09:44:35',1,'2020-02-13 13:22:09',1,NULL,NULL),(10,'MSCI Frontier Markets Index giữ nguyên số lượng cổ phiếu Việt Nam trong kỳ cơ cấu tháng 2','<p>btck1</p>\n',3,26,NULL,'/upload/download.jfif','2020-02-09 09:44:57',1,'2020-02-13 13:22:23',1,NULL,NULL),(11,'VGT - Trong nguy có cơ','<p>bcpt1</p>\n',3,27,NULL,'/upload/download.jfif','2020-02-09 09:45:34',1,'2020-02-13 13:22:52',1,NULL,NULL),(12,'Ngày 13/02/2020: 10 cổ phiếu “nóng” dưới góc nhìn PTKT của Vietstock','<p>ttkhdt1</p>\n',3,28,NULL,'/upload/download.jfif','2020-02-09 09:46:12',1,'2020-02-13 13:23:21',1,NULL,NULL),(14,'Học sinh TP HCM nghỉ hết tháng 2','<p>Trước diễn biến phức tạp của dịch nCoV, UBND TP HCM cho gần 2 triệu học sinh nghỉ học đến ng&agrave;y 29/2, đồng thời kiến nghị trung ương cho nghỉ hết th&aacute;ng 3.</p>\n\n<p>Quyết định được UBND TP HCM đưa ra chiều 14/2, sau hai lần cho học sinh nghỉ từ ng&agrave;y&nbsp;<a href=\"https://vnexpress.net/giao-duc/hoc-sinh-sai-gon-duoc-nghi-mot-tuan-4049249.html\">3 đến 9/2</a>, rồi&nbsp;<a href=\"https://vnexpress.net/giao-duc/hoc-sinh-tp-hcm-duoc-nghi-them-mot-tuan-4051182.html\">k&eacute;o d&agrave;i</a>&nbsp;đến ng&agrave;y 16/2 để ph&ograve;ng tr&aacute;nh dịch bệnh.</p>\n\n<p>Ngo&agrave;i ra, TP HCM sẽ kiến nghị Ch&iacute;nh phủ, Bộ Gi&aacute;o dục v&agrave; Đ&agrave;o tạo cho ph&eacute;p học sinh, sinh vi&ecirc;n, học vi&ecirc;n c&aacute;c cơ sở gi&aacute;o dục nghề nghiệp nghỉ hết th&aacute;ng 3, đồng thời điều chỉnh kế hoạch thời gian năm học 2019-2020.</p>\n\n<p>Cụ thể, học kỳ II dự kiến bắt đầu từ th&aacute;ng 4 đến th&aacute;ng 7, để nghỉ h&egrave; v&agrave;o th&aacute;ng 8. Năm học mới bắt đầu từ th&aacute;ng 9 để ho&agrave;n tất chương tr&igrave;nh học tập năm học 2019-2020.</p>\n\n<p>Theo &ocirc;ng Từ Lương (Ph&oacute; gi&aacute;m đốc Sở Th&ocirc;ng tin v&agrave; Truyền th&ocirc;ng TP HCM), phương &aacute;n n&agrave;y được sự thống nhất của Thường trực Th&agrave;nh uỷ, UBND th&agrave;nh phố v&agrave; c&aacute;c sở ng&agrave;nh trong cuộc họp b&agrave;n phương &aacute;n nghỉ học ph&ograve;ng dịch nCoV, v&agrave;o tối c&ugrave;ng ng&agrave;y.</p>\n\n<p><img alt=\"\" src=\"https://i-vnexpress.vnecdn.net/2020/02/14/nghi-ho-c-pho-ng-di-ch-nCoV-3959-1581686779.jpg\" style=\"height:513px; width:750px\" /></p>\n',1,8,NULL,'/upload/aa.png','2020-02-14 15:00:30',1,NULL,NULL,NULL,NULL),(15,'Thêm hơn 2.600 người nhiễm virus corona','<p>Trung Quốc ghi nhận th&ecirc;m 2.641 trường hợp nhiễm virus corona, n&acirc;ng tổng số người nhiễm tr&ecirc;n thế giới l&ecirc;n 67.100, trong đ&oacute; c&oacute; 1.526 ca tử vong.</p>\n\n<p>Tỉnh Hồ Bắc, t&acirc;m điểm dịch vi&ecirc;m phổi corona (Covid-19), c&oacute; th&ecirc;m 139 ca tử vong v&agrave; 2.420 ca nhiễm mới, n&acirc;ng tổng số người chết v&agrave; nhiễm bệnh tại tỉnh l&ecirc;n lần lượt l&agrave; 1.457 v&agrave; 54.406, theo số liệu được Ủy ban Y tế tỉnh n&agrave;y c&ocirc;ng bố h&ocirc;m nay.</p>\n\n<p><img alt=\"\" src=\"https://i-vnexpress.vnecdn.net/2020/02/15/890c9682-4d77-11ea-9b4e-9c1040-9394-5843-1581723728.jpg\" style=\"height:450px; width:750px\" /></p>\n',2,15,NULL,'/upload/aa.png','2020-02-15 03:07:13',1,NULL,NULL,NULL,NULL),(17,'Ông Tập kêu gọi cải tổ hệ thống y tế khẩn cấp','<p>Chủ tịch Trung Quốc k&ecirc;u gọi cải tổ hệ thống quản l&yacute; v&agrave; ứng ph&oacute; y tế khẩn cấp khi nước n&agrave;y nỗ lực chống dịch vi&ecirc;m phổi.</p>\n\n<p>&quot;Kh&ocirc;ng c&oacute; sự chuẩn bị đầy đủ cho một thảm họa - &nbsp;c&aacute;c đ&aacute;nh gi&aacute; rủi ro, nghi&ecirc;n cứu v&agrave; quản l&yacute; chuy&ecirc;n s&acirc;u cho c&aacute;c trường hợp khẩn cấp kh&ocirc;ng được thực hiện. Kh&ocirc;ng c&oacute; sự gi&aacute;m s&aacute;t v&agrave; hệ thống cảnh b&aacute;o sớm ph&ugrave; hợp, v&agrave; nền tảng quản l&yacute; khẩn cấp phải được cải thiện&quot;, &ocirc;ng Tập Cận B&igrave;nh n&oacute;i trong cuộc họp với l&atilde;nh đạo cấp cao Trung Quốc h&ocirc;m qua ở Bắc Kinh.</p>\n\n<p>&Ocirc;ng Tập n&oacute;i th&ecirc;m đ&acirc;y l&agrave; b&agrave;i kiểm tra lớn đối với hệ thống quản trị v&agrave; năng lực quốc gia của Trung Quốc trong bối cảnh nước n&agrave;y đang phải căng m&igrave;nh ứng ph&oacute; với dịch vi&ecirc;m phổi corona (Covid-19).</p>\n',1,9,NULL,'/upload/aa.png','2020-02-15 04:29:31',1,NULL,NULL,NULL,NULL),(26,'Căn nhà hoang Tuấn \'Khỉ\' ẩn náu','<h1>Căn nh&agrave; hoang Tuấn &#39;Khỉ&#39; ẩn n&aacute;u</h1>\n',5,32,NULL,'/upload/aa.png','2020-02-15 06:05:03',1,NULL,NULL,NULL,NULL),(29,'aaaaaaaaaaaa','<p>aaaaaaaaa</p>\n',4,31,NULL,'/upload/download.jfif','2020-02-15 06:11:02',1,'2020-02-15 08:26:16',1,NULL,NULL),(32,'fdadfa','<p>fdadfa</p>\n',1,9,NULL,'/upload/entry/avarta000-1oh2vs-1-1580262359-6703-1580262456.jpg','2020-02-17 14:17:45',1,NULL,NULL,NULL,NULL),(36,'fsdfs','<p>fasdfas</p>\n',5,33,NULL,'/upload/entry000-1oh2vs-1-1580262359-6703-1580262456.jpg','2020-02-17 15:56:35',1,NULL,NULL,NULL,NULL),(37,'ss','<p>ss</p>\n',4,30,NULL,'/upload/aa.png','2020-02-24 14:44:23',1,NULL,NULL,NULL,NULL),(38,'tedy','<p>&nbsp;</p>\n\n<p>Sở Y tế đ&atilde; nỗ lực li&ecirc;n hệ được một kh&aacute;ch sạn ở quận Sơn Tr&agrave; để chuyển kh&aacute;ch từ Bệnh viện Phổi đến. Th&agrave;nh phố sẽ lo chi ph&iacute; ở.&nbsp;B&aacute;c sĩ L&ecirc; Th&agrave;nh Ph&uacute;c - Gi&aacute;m đốc Bệnh viện Phổi cho biết, &quot;nh&oacute;m du kh&aacute;ch H&agrave;n Quốc sau đ&oacute; lại kh&ocirc;ng muốn đến kh&aacute;ch sạn khi biết c&oacute; một người Việt bị sốt tr&ecirc;n c&ugrave;ng chuyến bay. Họ lo ngại kh&ocirc;ng được chăm s&oacute;c y tế cũng như ph&aacute;t sinh tiền ăn uống&quot;.</p>\n\n<p>Sau nhiều giải th&iacute;ch của l&atilde;nh đạo Sở Y tế, nh&acirc;n vi&ecirc;n Đại sứ qu&aacute;n v&agrave; Tổng l&atilde;nh sự qu&aacute;n H&agrave;n Quốc tại Việt Nam, nh&oacute;m người H&agrave;n Quốc mới v&agrave;o khu c&aacute;ch ly.</p>\n\n<p>&nbsp;</p>\n',2,11,NULL,'/upload_entry/aa.png','2020-02-24 16:44:46',1,NULL,NULL,NULL,NULL),(39,'asfs','<p>fsaf</p>\n',2,15,NULL,'/upload_entry/000-1oh2vs-1-1580262359-6703-1580262456.jpg','2020-02-25 13:54:09',1,NULL,NULL,NULL,NULL),(40,'Điều kiện xét hạng và quyền lợi hội viên vàng của Viettel','<p>aa</p>\n',4,29,NULL,'/upload_entry/aa.png','2020-02-27 15:24:24',1,'2020-02-27 15:34:16',1,NULL,NULL),(41,'tv','<p>tv</p>\n',4,30,NULL,'/upload_entry/aa.png','2020-02-28 13:54:48',1,NULL,NULL,NULL,NULL),(42,'kim cuong','<p>kim cuong</p>\n',3,27,NULL,'/upload_entry/70a3d6b7-1b6a-459e-8474-a7ea88f84c60.jpg','2020-02-28 14:18:06',1,NULL,NULL,NULL,NULL),(47,'fadsfa','<p>sdfsdfsdf</p>\n',3,27,NULL,'/upload_entry/59c58d12-2f2f-414a-936c-a5f63332ebf8.jpg','2020-02-29 02:02:15',1,'2020-02-29 05:39:28',1,NULL,NULL),(49,'dsfs','<p>fsdfs</p>\n',3,26,NULL,'/upload_entry/bab8adb7-3f19-4a30-93cf-46101f62b2f0.png','2020-02-29 06:07:10',1,NULL,NULL,NULL,NULL),(50,'fsdaf','<p>fsfad</p>\n',5,32,NULL,'/upload_entry/f20f94ae-7679-4e0b-8ad3-cfdb5fde3b32.png','2020-02-29 06:08:24',1,NULL,NULL,NULL,NULL),(51,'gfsgfsd','<p>gsfgdsffasdf</p>\n',4,29,NULL,'/upload_entry/2efd5682-3447-4a1b-b4cb-b556581d3b5e.png','2020-02-29 06:09:16',1,'2020-02-29 06:42:36',1,NULL,NULL),(52,'fdsafasd','<p>fasfasdfs</p>\n',4,30,NULL,'/upload_entry/bc6c0ff7-8408-4702-9592-41fe0ab6126d.jpg','2020-02-29 06:46:52',1,'2020-02-29 06:47:12',1,NULL,NULL),(54,'fasf','<p>fasd</p>\n',4,30,NULL,'/upload_entry/2cc4b959-7e44-4c34-8aa1-b9201243d210.png','2020-02-29 07:50:09',1,NULL,NULL,NULL,NULL),(55,'afdaf','<p>fadfadsa</p>\n',4,31,NULL,'/upload_entry/76f1c053-3ba4-42fb-888f-1dcfdbbc202c.png','2020-02-29 07:57:54',1,'2020-02-29 08:13:51',1,NULL,NULL),(56,'fdsf','<p>fsd</p>\n',2,16,NULL,'/upload_entry/b1d7c891-a66d-499e-937a-a2a411044b5a.jpg','2020-02-29 08:14:05',1,NULL,NULL,NULL,NULL),(57,'dsdsfasd','<p>fadsf</p>\n',4,29,NULL,'/upload_entry/4568320e-be37-459c-8233-ddd7791f59bd.jpg','2020-03-01 05:42:03',1,'2020-03-04 07:22:44',1,NULL,NULL),(59,'tp1','<p>tp1</p>\n',2,15,NULL,'/upload_entry/9c0c95c6-9b05-4bf6-ad9f-7849e96e4fd3.jpg','2020-03-08 07:20:29',1,NULL,NULL,NULL,NULL),(62,'tpp1','<p>tpp1</p>\n',2,15,NULL,'/upload_entry/d976c343-3d4c-4da6-bac9-6c0acab498af.jpg','2020-03-08 07:21:06',1,NULL,NULL,NULL,NULL),(63,'tpp2','<p>tpp2</p>\n',2,15,NULL,'/upload_entry/68a6b695-8ca7-4bd3-9a2b-1ecf4425d55d.jpg','2020-03-08 07:21:20',1,NULL,NULL,NULL,NULL),(64,'tpp3','<p>tpp3</p>\n',2,15,NULL,'/upload_entry/d5d53d7e-b9bf-44f5-bd2c-50471402fc3f.jpg','2020-03-08 07:21:36',1,NULL,NULL,NULL,NULL),(65,'tpp4','<p>tpp4</p>\n',2,15,NULL,'/upload_entry/4b080bb3-9de6-499b-83ac-73e83ec79cd4.jpg','2020-03-08 07:21:51',1,NULL,NULL,NULL,NULL),(67,'sdfas','<p>fasdfa</p>\n',5,33,NULL,'/upload_entry/db54e687-49d3-4095-94c9-fce38e5099cf.png','2020-03-27 16:10:29',1,NULL,NULL,NULL,NULL),(68,'adfasdf','<p>adfasdf</p>\n',4,30,NULL,'/upload_entry/67167188-6181-4ae8-97c9-4f8ca817fab2.png','2020-04-01 23:10:34',1,NULL,NULL,NULL,NULL),(69,'sssssssss','<p>sssssss</p>\n',4,30,NULL,'/upload_entry/60a60f85-eefa-476d-9b62-d21e9513747c.png','2020-04-01 23:23:48',1,NULL,NULL,NULL,NULL),(70,'adfdaffds','<p>fasdfasdfsdf</p>\n',3,27,NULL,'/upload_entry/9a628312-eb55-4b0f-99f5-25d261d092b9.png','2020-04-01 23:25:13',1,NULL,NULL,NULL,NULL),(71,'aloha1','<p>aloha1</p>\n',12,17,NULL,'/upload_entry/0bc45261-c18b-41c9-9542-cf9f81752ad5.jfif','2020-04-01 23:27:06',1,NULL,NULL,NULL,NULL),(72,'hhh1','<p>hhh1</p>\n',4,30,NULL,'/upload_entry/7f09b3b9-a9cd-42d0-87e7-f264b7784a5f.png','2020-04-01 23:36:48',1,NULL,NULL,NULL,NULL),(74,'hhh3','<p>hhh3</p>\n',4,29,NULL,'/upload_entry/604ed302-374d-4d33-b1a9-fcb6f030af41.png','2020-04-02 00:20:08',1,NULL,NULL,NULL,NULL),(76,'hhh4','<p>hhh4</p>\n',4,30,NULL,'/upload_entry/ba71134b-4c8a-4f7d-b2c6-1723899843c6.png','2020-04-02 00:23:53',1,NULL,NULL,NULL,NULL),(84,'Người gốc Việt nhiễm nCoV: \'Xin nghĩ kỹ trước khi ra khỏi nhà\'','<p>MỸTom Nguyễn, người gốc Việt ở bang Georgia, thều th&agrave;o cảnh b&aacute;o mọi người &quot;h&atilde;y ở nh&agrave;&quot; sau 6 tuần chiến đấu với Covid-19, c&oacute; l&uacute;c tưởng kh&ocirc;ng thể qua khỏi.&nbsp;</p>\n\n<p>&quot;B&aacute;c sĩ kể hai tuần trước, &ocirc;ng ấy nghĩ t&ocirc;i sẽ kh&ocirc;ng thể qua khỏi&quot;, Tom Nguyễn, n&oacute;i&nbsp;trong video quay ng&agrave;y 8/4, từ tr&ecirc;n giường bệnh ở th&agrave;nh phố Lawrenceville, bang Georgia. &quot;Xin h&atilde;y nghĩ kỹ trước khi ra khỏi nh&agrave;, kh&ocirc;ng đ&aacute;ng để l&agrave;m thế đ&acirc;u! H&atilde;y ở nh&agrave;, c&aacute;ch ly với x&atilde; hội v&agrave; giữ g&igrave;n sức khoẻ&quot;.</p>\n\n<p>Hồi th&aacute;ng hai, Tom xuất hiện c&aacute;c triệu chứng m&agrave; anh nghĩ l&agrave; c&uacute;m. Người đ&agrave;n &ocirc;ng gốc Việt ngo&agrave;i 40 tuổi trước đ&oacute; ho&agrave;n to&agrave;n khoẻ mạnh v&agrave; kh&ocirc;ng hề nghi ngờ m&igrave;nh nhiễm nCoV. Tuy nhi&ecirc;n, một tuần sau, anh bắt đầu kh&ocirc;ng thể thở nổi. Anh nhập viện v&agrave;i ng&agrave;y rồi được cho về nh&agrave;.&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>\n\n<p>&quot;Hai ng&agrave;y sau, vợ t&ocirc;i gọi xe cấp cứu. Bệnh viện th&ocirc;ng b&aacute;o kết quả x&eacute;t nghiệm trước đ&oacute; cho thấy t&ocirc;i đ&atilde; mắc Covid-19&quot;, Tom kể. &quot;7 ng&agrave;y mới c&oacute; kết quả. H&atilde;y tin t&ocirc;i, kh&ocirc;ng dễ d&agrave;ng g&igrave; để được x&eacute;t nghiệm. C&aacute;c cơ sở y tế chỉ x&eacute;t nghiệm&nbsp;cho những người&nbsp;c&oacute; triệu chứng nặng hoặc ai đ&atilde; mắc bệnh. Bạn kh&ocirc;ng thể đến đ&oacute; v&agrave; y&ecirc;u cầu x&eacute;t nghiệm&quot;.&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>\n\n<p>Đến tuần thứ tư nằm viện, t&igrave;nh trạng của Tom diễn tiến xấu v&agrave; được chuyển v&agrave;o ph&ograve;ng điều trị t&iacute;ch cực. Đ&atilde; c&oacute; l&uacute;c b&aacute;c sĩ nghĩ rằng anh sẽ kh&ocirc;ng thể qua khỏi. Tuy nhi&ecirc;n, bệnh t&igrave;nh của Tom sau đ&oacute; dần cải thiện v&agrave; b&acirc;y giờ anh đ&atilde; được chuyển sang ph&ograve;ng điều trị b&igrave;nh thường.</p>\n\n<p>Mỹ hiện l&agrave; v&ugrave;ng dịch lớn nhất thế giới với&nbsp;hơn 420.000 ca nhiễm, trong đ&oacute; hơn 14.000 ca tử vong. Giới chức Mỹ&nbsp;nhận định số người chết&nbsp;sẽ giảm đ&aacute;ng kể trong những ng&agrave;y tới, khi c&aacute;c biện ph&aacute;p c&aacute;ch biệt cộng đồng ph&aacute;t huy&nbsp;hiệu quả trong ngăn chặn&nbsp;nCoV l&acirc;y lan.&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>\n\n<p>Tom cho hay anh muốn cập nhật qu&aacute; tr&igrave;nh chữa bệnh của m&igrave;nh v&agrave; hy vọng video sẽ gi&uacute;p mọi người nhận ra mắc Covid-19&nbsp;thực sự&nbsp;l&agrave; như thế n&agrave;o.&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>\n\n<p>&quot;Cảm ơn những người đ&atilde; cầu nguyện cho t&ocirc;i. B&acirc;y giờ t&ocirc;i sẽ tiếp tục chiến đấu v&agrave; ra khỏi đ&acirc;y, về nh&agrave; với gia đ&igrave;nh&quot;, anh n&oacute;i. &quot;H&atilde;y giữ g&igrave;n sức khoẻ của c&aacute;c bạn. Đ&oacute; l&agrave; tất cả những g&igrave; t&ocirc;i c&oacute; thể n&oacute;i&quot;.</p>\n\n<p><img alt=\"\" src=\"https://i1-vnexpress.vnecdn.net/2020/04/09/92827518-227958494980016-53021-3647-4301-1586422838.jpg?w=1020&amp;h=0&amp;q=100&amp;dpr=1&amp;fit=crop&amp;s=HPdpc-dOlq8dynUgmKzAIw\" style=\"height:516px; width:749px\" /></p>\n\n<p>Mỹ hiện l&agrave; v&ugrave;ng dịch lớn nhất thế giới với&nbsp;hơn 420.000 ca nhiễm, trong đ&oacute; hơn 14.000 ca tử vong. Giới chức Mỹ&nbsp;nhận định số người chết&nbsp;sẽ giảm đ&aacute;ng kể trong những ng&agrave;y tới, khi c&aacute;c biện ph&aacute;p c&aacute;ch biệt cộng đồng ph&aacute;t huy&nbsp;hiệu quả trong ngăn chặn&nbsp;nCoV l&acirc;y lan.&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>\n\n<p>Tom cho hay anh muốn cập nhật qu&aacute; tr&igrave;nh chữa bệnh của m&igrave;nh v&agrave; hy vọng video sẽ gi&uacute;p mọi người nhận ra mắc Covid-19&nbsp;thực sự&nbsp;l&agrave; như thế n&agrave;o.&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>\n\n<p>&quot;Cảm ơn những người đ&atilde; cầu nguyện cho t&ocirc;i. B&acirc;y giờ t&ocirc;i sẽ tiếp tục chiến đấu v&agrave; ra khỏi đ&acirc;y, về nh&agrave; với gia đ&igrave;nh&quot;, anh n&oacute;i. &quot;H&atilde;y giữ g&igrave;n sức khoẻ của c&aacute;c bạn. Đ&oacute; l&agrave; tất cả những g&igrave; t&ocirc;i c&oacute; thể n&oacute;i&quot;.</p>\n',2,14,NULL,'/upload_entry/6818581d-9a3c-45f1-88cc-5909a57fcf4c.jpg','2020-04-02 09:55:16',1,'2020-04-09 06:08:50',1,NULL,NULL),(94,'lien he','<p>lien he</p>\n',6,6,NULL,'/upload_entry/eef0155d-85e1-4ebb-8f3c-74d474c26fd3.jpg','2020-04-09 01:40:15',1,NULL,NULL,NULL,NULL);
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
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `otc_news`
--

LOCK TABLES `otc_news` WRITE;
/*!40000 ALTER TABLE `otc_news` DISABLE KEYS */;
INSERT INTO `otc_news` VALUES (20,'Chính phủ chốt gói 61.580 tỷ cho người yếu thế',NULL,'/upload_news/59abc65c-5cb4-41a7-bb46-41476fe58112.jpg',14,1,8,'2020-04-05 07:15:54',1,'2020-04-09 07:13:54',1,NULL,NULL),(21,'Bác sĩ công an trên tuyến đầu chống dịch',NULL,'/upload_news/ebfcf9fa-6b4a-402a-b818-dc80620e0cc4.jpg',17,1,9,'2020-04-05 07:16:29',1,'2020-04-09 07:14:00',1,NULL,NULL),(22,'Lái xe của bệnh nhân 17: \'Tôi mệt rã rời không còn sức lực\'',NULL,'/upload_news/d256251e-bf43-438a-819d-2a812fd2c651.jpg',6,3,23,'2020-04-05 07:16:54',1,'2020-04-09 07:14:06',1,NULL,NULL);
/*!40000 ALTER TABLE `otc_news` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `otc_role`
--

DROP TABLE IF EXISTS `otc_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8 ;
CREATE TABLE `otc_role` (
  `ROLE_ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `AUTHORITY` varchar(45) NOT NULL,
  `DESCRIPTION` varchar(45) NOT NULL,
  PRIMARY KEY (`ROLE_ID`),
  UNIQUE KEY `AUTHORITY_UNIQUE` (`AUTHORITY`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `otc_role`
--

LOCK TABLES `otc_role` WRITE;
/*!40000 ALTER TABLE `otc_role` DISABLE KEYS */;
INSERT INTO `otc_role` VALUES (1,'ROLE_ADMIN','System Admin'),(2,'ROLE_USER','System User');
/*!40000 ALTER TABLE `otc_role` ENABLE KEYS */;
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
-- Table structure for table `otc_user`
--

DROP TABLE IF EXISTS `otc_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8 ;
CREATE TABLE `otc_user` (
  `USER_ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `USER_NAME` varchar(45) NOT NULL,
  `PASSWORD` varchar(100) NOT NULL,
  `FULL_NAME` varchar(45) NOT NULL,
  `EMAIL` varchar(45) DEFAULT NULL,
  `TELEPHONE` varchar(45) DEFAULT NULL,
  `ADDRESS` varchar(45) DEFAULT NULL,
  `ENABLED` char(1) NOT NULL DEFAULT 'Y',
  `CREATED_DATE` timestamp NOT NULL,
  `CREATED_BY` bigint(20) NOT NULL,
  `UPDATED_DATE` timestamp NULL DEFAULT NULL,
  `UPDATED_BY` bigint(20) DEFAULT NULL,
  `DELETED_DATE` timestamp NULL DEFAULT NULL,
  `DELETED_BY` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`USER_ID`),
  UNIQUE KEY `USER_NAME_UNIQUE` (`USER_NAME`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `otc_user`
--

LOCK TABLES `otc_user` WRITE;
/*!40000 ALTER TABLE `otc_user` DISABLE KEYS */;
INSERT INTO `otc_user` VALUES (1,'khanh','$2a$10$qsC8PkOjSdEliafi8XfDF.LKNnNsuLPc5cF.d1Qm2n5ELVAPgKoF6','Nguyễn Văn Khánh',NULL,NULL,NULL,'Y','2020-04-10 07:15:54',1,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `otc_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `otc_user_role`
--

DROP TABLE IF EXISTS `otc_user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8 ;
CREATE TABLE `otc_user_role` (
  `USER_ID` bigint(20) NOT NULL,
  `ROLE_ID` bigint(20) NOT NULL,
  PRIMARY KEY (`USER_ID`,`ROLE_ID`),
  KEY `FK_USER_ROLE_2_idx` (`ROLE_ID`),
  CONSTRAINT `FK_USER_ROLE_1` FOREIGN KEY (`USER_ID`) REFERENCES `otc_user` (`USER_ID`),
  CONSTRAINT `FK_USER_ROLE_2` FOREIGN KEY (`ROLE_ID`) REFERENCES `otc_role` (`ROLE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `otc_user_role`
--

LOCK TABLES `otc_user_role` WRITE;
/*!40000 ALTER TABLE `otc_user_role` DISABLE KEYS */;
INSERT INTO `otc_user_role` VALUES (1,1);
/*!40000 ALTER TABLE `otc_user_role` ENABLE KEYS */;
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

-- Dump completed on 2020-04-11  9:45:36

DROP TABLE IF EXISTS `otc_document`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8 ;
CREATE TABLE `otc_document` (
  `ID` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `SUBJECT` VARCHAR(200) NOT NULL,
  `DESCRIPTION` TEXT NOT NULL,
  `FIRST_PAGE_IMG` VARCHAR(500) NOT NULL,
  `FULL_FILE` VARCHAR(500) NOT NULL,
  `PREVIEW_FILE` VARCHAR(500) NOT NULL,
  PRIMARY KEY (`ID`))
 ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
 
 ALTER TABLE `otc_landmark`.`otc_user` 
ADD COLUMN `AVATAR` VARCHAR(200) NULL DEFAULT NULL AFTER `DELETED_BY`;

ALTER TABLE `otc_landmark`.`otc_user` 
CHANGE COLUMN `FULL_NAME` `FULL_NAME` VARCHAR(200) NOT NULL ,
CHANGE COLUMN `EMAIL` `EMAIL` VARCHAR(50) NULL DEFAULT NULL ,
CHANGE COLUMN `ADDRESS` `ADDRESS` VARCHAR(500) NULL DEFAULT NULL ;



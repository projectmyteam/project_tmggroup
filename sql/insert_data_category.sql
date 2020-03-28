INSERT INTO `otc_landmark`.`otc_category` (`CATEGORY_ID`, `CATEGORY_CODE`, `CATEGORY_NAME`) VALUES ('1', 'CK', 'Chứng khoán');
INSERT INTO `otc_landmark`.`otc_category` (`CATEGORY_ID`, `CATEGORY_CODE`, `CATEGORY_NAME`, `PARENT_CATEGORY_ID`) VALUES ('2', 'CP', 'Cổ phiếu ', '1');
INSERT INTO `otc_landmark`.`otc_category` (`CATEGORY_ID`, `CATEGORY_CODE`, `CATEGORY_NAME`, `PARENT_CATEGORY_ID`) VALUES ('3', 'TP', 'Trái phiếu', '1');
INSERT INTO `otc_landmark`.`otc_category` (`CATEGORY_ID`, `CATEGORY_CODE`, `CATEGORY_NAME`, `PARENT_CATEGORY_ID`) VALUES ('4', 'PS', 'Phái sinh', '1');
INSERT INTO `otc_landmark`.`otc_category` (`CATEGORY_ID`, `CATEGORY_CODE`, `CATEGORY_NAME`) VALUES ('5', 'DT', 'Đào tạo');
INSERT INTO `otc_landmark`.`otc_category` (`CATEGORY_ID`, `CATEGORY_CODE`, `CATEGORY_NAME`, `PARENT_CATEGORY_ID`) VALUES ('6', 'ON', 'Online', '5');
INSERT INTO `otc_landmark`.`otc_category` (`CATEGORY_ID`, `CATEGORY_CODE`, `CATEGORY_NAME`, `PARENT_CATEGORY_ID`) VALUES ('7', 'OFF', 'Offline', '5');

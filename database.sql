SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

CREATE DATABASE IF NOT EXISTS newdb;
USE newdb;

-- ----------------------------
-- Table structure for address
-- ----------------------------
DROP TABLE IF EXISTS `address`;
CREATE TABLE `address`  (
  `address_id` int NOT NULL AUTO_INCREMENT,
  `cu_address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `cid` int NULL DEFAULT NULL,
  `did` int NULL DEFAULT NULL,
  PRIMARY KEY (`address_id`) USING BTREE,
  INDEX `FK2uftfxae973hl3iuthmvmkoxo`(`cid`) USING BTREE,
  INDEX `FK25r6hwihqbeejvuc6v3jdgilh`(`did`) USING BTREE,
  CONSTRAINT `FK25r6hwihqbeejvuc6v3jdgilh` FOREIGN KEY (`did`) REFERENCES `district` (`did`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK2uftfxae973hl3iuthmvmkoxo` FOREIGN KEY (`cid`) REFERENCES `city` (`cid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of address
-- ----------------------------
INSERT INTO `address` VALUES (1, 'Kharkiv', 1, 1);

-- ----------------------------
-- Table structure for breed_pet
-- ----------------------------
DROP TABLE IF EXISTS `breed_pet`;
CREATE TABLE `breed_pet`  (
  `br_id` int NOT NULL AUTO_INCREMENT,
  `br_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `type_pet_id` int NULL DEFAULT NULL,
  PRIMARY KEY (`br_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 41 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of breed_pet
-- ----------------------------
INSERT INTO `breed_pet` VALUES (1, 'Dog', 1);

-- ----------------------------
-- Table structure for calendar_info
-- ----------------------------
DROP TABLE IF EXISTS `calendar_info`;
CREATE TABLE `calendar_info`  (
  `cid` int NOT NULL AUTO_INCREMENT,
  `cbg_color` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `cborder_color` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ccolor` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `cdrag_bg_color` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `cname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`cid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of calendar_info
-- ----------------------------
INSERT INTO `calendar_info` VALUES (1, '#4287f5', '#4287f5', '#ffffff', '#9e5fff', 'Appointment');
INSERT INTO `calendar_info` VALUES (2, '#6cf542', '#6cf542', '#ffffff', '#9e5fff', 'Patient');
INSERT INTO `calendar_info` VALUES (3, '#f54242', '#f54242', '#ffffff', '#9e5fff', 'Calendar');

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category`  (
  `category_id` int NOT NULL AUTO_INCREMENT,
  `category_detail` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `category_title` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`category_id`) USING BTREE,
  UNIQUE INDEX `UK_aoocyteu6o3xrboa98gll9wra`(`category_title`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES (1, 'Cotton, scissors etc.', 'Tools');
INSERT INTO `category` VALUES (2, 'Food', 'Formula');
INSERT INTO `category` VALUES (3, 'General supplies for Farm Animals', 'Farm Products');
INSERT INTO `category` VALUES (4, 'Rapid test kits for animals', 'Rapid Test Kits');

-- ----------------------------
-- Table structure for city
-- ----------------------------
DROP TABLE IF EXISTS `city`;
CREATE TABLE `city`  (
  `cid` int NOT NULL AUTO_INCREMENT,
  `cname` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`cid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 82 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of city
-- ----------------------------
INSERT INTO `city` VALUES (1, 'KHARKIV');

-- ----------------------------
-- Table structure for color_pet
-- ----------------------------
DROP TABLE IF EXISTS `color_pet`;
CREATE TABLE `color_pet`  (
  `color_id` int NOT NULL AUTO_INCREMENT,
  `color_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`color_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of color_pet
-- ----------------------------
INSERT INTO `color_pet` VALUES (1, 'Black');
INSERT INTO `color_pet` VALUES (2, 'Yellow');
INSERT INTO `color_pet` VALUES (3, 'Blue');

-- ----------------------------
-- Table structure for customer
-- ----------------------------
DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer`  (
  `cu_id` int NOT NULL AUTO_INCREMENT,
  `created_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `created_date` datetime NULL DEFAULT NULL,
  `last_modified_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `last_modified_date` datetime NULL DEFAULT NULL,
  `cu_mail` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `cu_mail_notice` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `cu_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `cu_note` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `cu_rate_of_discount` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `cu_sms_notice` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `cu_surname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `cu_taxname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `cu_tcnumber` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `cu_tel1` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `cu_tel2` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `address_id` int NULL DEFAULT NULL,
  `cu_gr_id` int NULL DEFAULT NULL,
  PRIMARY KEY (`cu_id`) USING BTREE,
  UNIQUE INDEX `UK_26xl63wwt2q0vhi57e1wbwskk`(`cu_tcnumber`) USING BTREE,
  INDEX `FKglkhkmh2vyn790ijs6hiqqpi`(`address_id`) USING BTREE,
  INDEX `FKao2bcjqe9oc9044j8cdtg0vby`(`cu_gr_id`) USING BTREE,
  CONSTRAINT `FKao2bcjqe9oc9044j8cdtg0vby` FOREIGN KEY (`cu_gr_id`) REFERENCES `customer_group` (`cu_gr_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FKglkhkmh2vyn790ijs6hiqqpi` FOREIGN KEY (`address_id`) REFERENCES `address` (`address_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of customer
-- ----------------------------
INSERT INTO `customer` VALUES (1, 'admin@mail.com', '2021-09-23 23:17:56', 'admin@mail.com', '2021-09-23 23:17:56', 'admin@mail.com', 'on', 'Admin', 'HAdmin', '99', 'on', 'Admin', 'KHARKIV', '100000000', '5555555555', NULL, 1, 1);

-- ----------------------------
-- Table structure for customer_group
-- ----------------------------
DROP TABLE IF EXISTS `customer_group`;
CREATE TABLE `customer_group`  (
  `cu_gr_id` int NOT NULL AUTO_INCREMENT,
  `created_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `created_date` datetime NULL DEFAULT NULL,
  `last_modified_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `last_modified_date` datetime NULL DEFAULT NULL,
  `cu_gr_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`cu_gr_id`) USING BTREE,
  UNIQUE INDEX `UK_kx0032jqt5x4t8qicvwcsg89r`(`cu_gr_name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of customer_group
-- ----------------------------
INSERT INTO `customer_group` VALUES (1, 'admin@mail.com', '2021-09-23 23:11:48', 'admin@mail.com', '2021-09-23 23:11:48', 'admins');

-- ----------------------------
-- Table structure for diary
-- ----------------------------
DROP TABLE IF EXISTS `diary`;
CREATE TABLE `diary`  (
  `diary_id` int NOT NULL AUTO_INCREMENT,
  `diary_date` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `diary_description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `diary_statu` bit(1) NULL DEFAULT NULL,
  `diary_time` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `diary_title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `cu_id` int NULL DEFAULT NULL,
  `us_id` int NULL DEFAULT NULL,
  PRIMARY KEY (`diary_id`) USING BTREE,
  INDEX `FK19iwlec3gc5sqmk0a7vx4w5t4`(`cu_id`) USING BTREE,
  INDEX `FKnhyqnftb1dpf5qrq43m5mog0d`(`us_id`) USING BTREE,
  CONSTRAINT `FK19iwlec3gc5sqmk0a7vx4w5t4` FOREIGN KEY (`cu_id`) REFERENCES `customer` (`cu_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FKnhyqnftb1dpf5qrq43m5mog0d` FOREIGN KEY (`us_id`) REFERENCES `user` (`us_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of diary
-- ----------------------------
INSERT INTO `diary` VALUES (1, '2000-08-09', 'Ann B-day', b'1', '09:05', 'Ann\'s birthday', 1, 3);

-- ----------------------------
-- Table structure for district
-- ----------------------------
DROP TABLE IF EXISTS `district`;
CREATE TABLE `district`  (
  `did` int NOT NULL AUTO_INCREMENT,
  `cid` int NULL DEFAULT NULL,
  `dname` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`did`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 974 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of district
-- ----------------------------
INSERT INTO `district` VALUES (1, 1, 'KHARKIVSKA');

-- ----------------------------
-- Table structure for join_pet_customer
-- ----------------------------
DROP TABLE IF EXISTS `join_pet_customer`;
CREATE TABLE `join_pet_customer`  (
  `jpt_id` int NOT NULL AUTO_INCREMENT,
  `cu_id` int NULL DEFAULT NULL,
  `pet_id` int NULL DEFAULT NULL,
  PRIMARY KEY (`jpt_id`) USING BTREE,
  INDEX `FK3uyq20458seoh35infpm0pdwp`(`cu_id`) USING BTREE,
  INDEX `FKhj4g08bd3pyhnhx1tboxsye2c`(`pet_id`) USING BTREE,
  CONSTRAINT `FK3uyq20458seoh35infpm0pdwp` FOREIGN KEY (`cu_id`) REFERENCES `customer` (`cu_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FKhj4g08bd3pyhnhx1tboxsye2c` FOREIGN KEY (`pet_id`) REFERENCES `pet` (`pet_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of join_pet_customer
-- ----------------------------
INSERT INTO `join_pet_customer` VALUES (1, 1, 1);

-- ----------------------------
-- Table structure for join_type_breed_pet
-- ----------------------------
DROP TABLE IF EXISTS `join_type_breed_pet`;
CREATE TABLE `join_type_breed_pet`  (
  `jtbp_id` int NOT NULL AUTO_INCREMENT,
  `br_id` int NULL DEFAULT NULL,
  `ty_id` int NULL DEFAULT NULL,
  PRIMARY KEY (`jtbp_id`) USING BTREE,
  INDEX `FK1r8r447jgydjxp9y2paodm33d`(`br_id`) USING BTREE,
  INDEX `FK8wmgx38jb8gt9e2h0vgy7qppx`(`ty_id`) USING BTREE,
  CONSTRAINT `FK1r8r447jgydjxp9y2paodm33d` FOREIGN KEY (`br_id`) REFERENCES `breed_pet` (`br_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK8wmgx38jb8gt9e2h0vgy7qppx` FOREIGN KEY (`ty_id`) REFERENCES `type_pet` (`ty_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of join_type_breed_pet
-- ----------------------------
INSERT INTO `join_type_breed_pet` VALUES (1, 1, 1);

-- ----------------------------
-- Table structure for lab
-- ----------------------------
DROP TABLE IF EXISTS `lab`;
CREATE TABLE `lab`  (
  `lab_id` int NOT NULL AUTO_INCREMENT,
  `created_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `created_date` datetime NULL DEFAULT NULL,
  `last_modified_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `last_modified_date` datetime NULL DEFAULT NULL,
  `lab_detail` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `lab_file` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `lab_type` int NULL DEFAULT NULL,
  `cu_id` int NULL DEFAULT NULL,
  `us_id` int NULL DEFAULT NULL,
  PRIMARY KEY (`lab_id`) USING BTREE,
  INDEX `FK3k84i0a9kktlppfgg5weo2srd`(`cu_id`) USING BTREE,
  INDEX `FK9p0ocmgbx755bkuhdhlfj6ybt`(`us_id`) USING BTREE,
  CONSTRAINT `FK3k84i0a9kktlppfgg5weo2srd` FOREIGN KEY (`cu_id`) REFERENCES `customer` (`cu_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK9p0ocmgbx755bkuhdhlfj6ybt` FOREIGN KEY (`us_id`) REFERENCES `user` (`us_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of lab
-- ----------------------------
INSERT INTO `lab` VALUES (1, 'admin@mail.com', '2021-11-08 19:24:00', 'admin@mail.com', '2021-11-08 19:24:00', 'The key was forgotten inside.', 'ef8a34e4-0fab-4580-bc03-336cd8a5cf9f.jpg', 2, 1, 3);

-- ----------------------------
-- Table structure for payment_in
-- ----------------------------
DROP TABLE IF EXISTS `payment_in`;
CREATE TABLE `payment_in`  (
  `pin_id` int NOT NULL AUTO_INCREMENT,
  `created_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `created_date` datetime NULL DEFAULT NULL,
  `last_modified_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `last_modified_date` datetime NULL DEFAULT NULL,
  `pin_detail` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `pin_operation_type` int NULL DEFAULT NULL,
  `pin_payment_type` int NULL DEFAULT NULL,
  `pin_price` int NULL DEFAULT NULL,
  `cu_id` int NULL DEFAULT NULL,
  PRIMARY KEY (`pin_id`) USING BTREE,
  INDEX `FKddur8r3p1i8caorao565mrxxa`(`cu_id`) USING BTREE,
  CONSTRAINT `FKddur8r3p1i8caorao565mrxxa` FOREIGN KEY (`cu_id`) REFERENCES `customer` (`cu_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of payment_in
-- ----------------------------
INSERT INTO `payment_in` VALUES (1, 'admin@mail.com', '2021-11-08 19:26:00', 'admin@mail.com', '2021-11-08 19:26:00', 'Payment received', 0, 3, 5, 1);

-- ----------------------------
-- Table structure for payment_out
-- ----------------------------
DROP TABLE IF EXISTS `payment_out`;
CREATE TABLE `payment_out`  (
  `pout_id` int NOT NULL AUTO_INCREMENT,
  `created_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `created_date` datetime NULL DEFAULT NULL,
  `last_modified_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `last_modified_date` datetime NULL DEFAULT NULL,
  `pout_detail` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `pout_operation_type` int NULL DEFAULT NULL,
  `pout_payment_type` int NULL DEFAULT NULL,
  `pout_price` int NULL DEFAULT NULL,
  PRIMARY KEY (`pout_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of payment_out
-- ----------------------------
INSERT INTO `payment_out` VALUES (1, 'admin@mail.com', '2021-11-08 19:28:00', 'admin@mail.com', '2021-11-08 19:28:00', 'Money deducted from the safe.', 1, 3, 4);

-- ----------------------------
-- Table structure for pet
-- ----------------------------
DROP TABLE IF EXISTS `pet`;
CREATE TABLE `pet`  (
  `pet_id` int NOT NULL AUTO_INCREMENT,
  `pet_born_date` datetime NULL DEFAULT NULL,
  `pet_chip_number` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `pet_ear_tag` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `pet_gender` bit(1) NULL DEFAULT NULL,
  `pet_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `pet_neutering` bit(1) NULL DEFAULT NULL,
  `color_pet_color_id` int NULL DEFAULT NULL,
  `join_type_breed_pet` int NULL DEFAULT NULL,
  PRIMARY KEY (`pet_id`) USING BTREE,
  INDEX `FKhl7tkyjalv2t4siraqtri15wo`(`color_pet_color_id`) USING BTREE,
  INDEX `FK5kggjwb4bwtna1kv58rlrq652`(`join_type_breed_pet`) USING BTREE,
  CONSTRAINT `FK5kggjwb4bwtna1kv58rlrq652` FOREIGN KEY (`join_type_breed_pet`) REFERENCES `join_type_breed_pet` (`jtbp_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FKhl7tkyjalv2t4siraqtri15wo` FOREIGN KEY (`color_pet_color_id`) REFERENCES `color_pet` (`color_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of pet
-- ----------------------------
INSERT INTO `pet` VALUES (1, '2021-11-08 19:28:00', '1', '1', b'0', 'Bublik', b'1', 11, 1);
INSERT INTO `pet` VALUES (2, '2021-11-08 19:28:00', '123', '123', b'0', 'Reks', b'1', 8, 2);
INSERT INTO `pet` VALUES (3, '2021-11-08 19:28:00', '1', '1', b'0', 'Jerry', b'1', 6, 3);

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product`  (
  `product_id` int NOT NULL AUTO_INCREMENT,
  `created_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `created_date` datetime NULL DEFAULT NULL,
  `last_modified_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `last_modified_date` datetime NULL DEFAULT NULL,
  `product_alis` int NULL DEFAULT NULL,
  `product_kdv` int NULL DEFAULT NULL,
  `product_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `product_satis` int NULL DEFAULT NULL,
  `product_statu` bit(1) NULL DEFAULT NULL,
  `product_stok_miktari` int NULL DEFAULT NULL,
  `product_unit` int NULL DEFAULT NULL,
  `category_id` int NULL DEFAULT NULL,
  PRIMARY KEY (`product_id`) USING BTREE,
  INDEX `FK1mtsbur82frn64de7balymq9s`(`category_id`) USING BTREE,
  CONSTRAINT `FK1mtsbur82frn64de7balymq9s` FOREIGN KEY (`category_id`) REFERENCES `category` (`category_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of product
-- ----------------------------
INSERT INTO `product` VALUES (1, 'admin@mail.com', '2021-11-08 19:30:00', 'admin@mail.com', '2021-11-08 19:31:00', 100, 2, 'Pet Collar', 150, b'1', 997, 1, 1);
INSERT INTO `product` VALUES (2, 'admin@mail.com', '2021-11-08 19:30:00', 'admin@mail.com', '2021-11-08 19:31:00', 125, 3, 'Thermomether', 170, b'0', 500, 1, 1);
INSERT INTO `product` VALUES (3, 'admin@mail.com', '2021-11-08 19:30:00', 'admin@mail.com', '2021-11-08 19:31:00', 200, 2, 'Dog food', 300, b'1', 300, 2, 2);
INSERT INTO `product` VALUES (4, 'admin@mail.com', '2021-11-08 19:30:00', 'admin@mail.com', '2021-11-08 19:31:00', 300, 3, 'Chewing Bone', 400, b'1', 750, 1, 2);
INSERT INTO `product` VALUES (5, 'admin@mail.com', '2021-11-08 19:30:00', 'admin@mail.com', '2021-11-08 19:31:00', 1000, 1, 'Detectors', 2000, b'1', 19, 1, 3);
INSERT INTO `product` VALUES (6, 'admin@mail.com', '2021-11-08 19:30:00', 'admin@mail.com', '2021-11-08 19:31:00', 150, 2, 'Nail Care Product', 250, b'1', 100, 1, 3);

-- ----------------------------
-- Table structure for purchase
-- ----------------------------
DROP TABLE IF EXISTS `purchase`;
CREATE TABLE `purchase`  (
  `purchase_id` int NOT NULL AUTO_INCREMENT,
  `created_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `created_date` datetime NULL DEFAULT NULL,
  `last_modified_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `last_modified_date` datetime NULL DEFAULT NULL,
  `purchase_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `purchase_detail` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `purchase_number` int NULL DEFAULT NULL,
  `purchase_total` int NULL DEFAULT NULL,
  `purchase_type` int NULL DEFAULT NULL,
  `product_id` int NULL DEFAULT NULL,
  `supplier_id` int NULL DEFAULT NULL,
  PRIMARY KEY (`purchase_id`) USING BTREE,
  INDEX `FK3s4jktret4nl7m8yhfc8mfrn5`(`product_id`) USING BTREE,
  INDEX `FK8omm6fki86s9oqk0o9s6w43h5`(`supplier_id`) USING BTREE,
  CONSTRAINT `FK3s4jktret4nl7m8yhfc8mfrn5` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK8omm6fki86s9oqk0o9s6w43h5` FOREIGN KEY (`supplier_id`) REFERENCES `supplier` (`supplier_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of purchase
-- ----------------------------
INSERT INTO `purchase` VALUES (1, 'admin@mail.com', '2021-11-08 19:34:00', 'admin@mail.com', '2021-11-08 19:34:00', 'b503e257-3d59-4770-b2ab-06469be80ef5', 'The thermometer was taken.', 2, 250, 2, 2, 1);

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `ro_id` int NOT NULL AUTO_INCREMENT,
  `ro_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`ro_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (1, 'ROLE_ADMIN');
INSERT INTO `role` VALUES (2, 'ROLE_DOCTOR');
INSERT INTO `role` VALUES (3, 'ROLE_SECRETARY');
INSERT INTO `role` VALUES (4, 'ROLE_BEGINNER');

-- ----------------------------
-- Table structure for sale
-- ----------------------------
DROP TABLE IF EXISTS `sale`;
CREATE TABLE `sale`  (
  `sale_id` int NOT NULL AUTO_INCREMENT,
  `created_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `created_date` datetime NULL DEFAULT NULL,
  `last_modified_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `last_modified_date` datetime NULL DEFAULT NULL,
  `sale_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sale_detail` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sale_number` int NULL DEFAULT NULL,
  `sale_total` int NULL DEFAULT NULL,
  `sale_type` int NULL DEFAULT NULL,
  `cu_id` int NULL DEFAULT NULL,
  `product_id` int NULL DEFAULT NULL,
  PRIMARY KEY (`sale_id`) USING BTREE,
  INDEX `FKg7d1nmnivxg6fayyi56vg7uxq`(`cu_id`) USING BTREE,
  INDEX `FKonrcqwf09u6spb6ty6sh11jh5`(`product_id`) USING BTREE,
  CONSTRAINT `FKg7d1nmnivxg6fayyi56vg7uxq` FOREIGN KEY (`cu_id`) REFERENCES `customer` (`cu_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FKonrcqwf09u6spb6ty6sh11jh5` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sale
-- ----------------------------
INSERT INTO `sale` VALUES (1, 'admin@mail.com', '2021-11-08 19:36:00', 'admin@mail.com', '2021-11-08 19:36:00', '4b3028bc-c559-4a87-8ec1-57ff229cb29e', 'Detector sold.', 1, 21, 1, 1, 5);
INSERT INTO `sale` VALUES (2, 'admin@mail.com', '2021-11-08 19:36:00', 'admin@mail.com', '2021-11-08 19:36:00', 'f138dea7-3e98-42aa-9586-bc72ff062b89', 'Collar sold.', 3, 5, 3, 1, 1);
INSERT INTO `sale` VALUES (3, 'admin@mail.com', '2021-11-08 19:36:00', 'admin@mail.com', '2021-11-08 19:36:00', 'd095494a-bc48-4f5b-8016-8bcef34e07d6', 'Thermometer has been sold.', 2, 4, 2, 1, 2);

-- ----------------------------
-- Table structure for schedule_calendar
-- ----------------------------
DROP TABLE IF EXISTS `schedule_calendar`;
CREATE TABLE `schedule_calendar`  (
  `sid` int NOT NULL AUTO_INCREMENT,
  `bg_color` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `border_color` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `calendar_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `category` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `color` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `drag_bg_color` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `due_date_class` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `end` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `is_all_day` bit(1) NULL DEFAULT NULL,
  `location` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `raw` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `start` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `state` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`sid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of schedule_calendar
-- ----------------------------
INSERT INTO `schedule_calendar` VALUES (1, '#4287f5', '#4287f5', '1', 'time', '#ffffff', '#4287f5', '', 'Fri Nov 19 2021 15:00:00 GMT+0300 (GMT+03:00)', 'be4c14e2-246f-55eb-848f-73a653641409', b'0', 'PC Head', 'private', 'Fri Nov 19 2021 10:00:00 GMT+0300 (GMT+03:00)', 'Busy', 'Presentation');

-- ----------------------------
-- Table structure for store
-- ----------------------------
DROP TABLE IF EXISTS `store`;
CREATE TABLE `store`  (
  `store_id` int NOT NULL AUTO_INCREMENT,
  `created_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `created_date` datetime NULL DEFAULT NULL,
  `last_modified_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `last_modified_date` datetime NULL DEFAULT NULL,
  `store_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `store_statu` bit(1) NULL DEFAULT NULL,
  `store_tel` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`store_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of store
-- ----------------------------

-- ----------------------------
-- Table structure for supplier
-- ----------------------------
DROP TABLE IF EXISTS `supplier`;
CREATE TABLE `supplier`  (
  `supplier_id` int NOT NULL AUTO_INCREMENT,
  `created_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `created_date` datetime NULL DEFAULT NULL,
  `last_modified_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `last_modified_date` datetime NULL DEFAULT NULL,
  `supplier_mail` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `supplier_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `supplier_statu` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `supplier_tel` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`supplier_id`) USING BTREE,
  UNIQUE INDEX `UK_aof5tqg9b2ya6ybg99y9ebbhy`(`supplier_mail`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of supplier
-- ----------------------------
INSERT INTO `supplier` VALUES (1, 'admin@mail.com', '2021-11-08 19:38:00', 'admin@mail.com', '2021-11-08 19:38:00', 'supplier@mail.com', 'I Don\'t Know Chains Inc.', '1', '4441444');
INSERT INTO `supplier` VALUES (2, NULL, NULL, 'admin@mail.com', '2021-11-08 19:38:00', 'admin@mail.com', 'I know Chains Inc.', '1', '5551555');

-- ----------------------------
-- Table structure for type_pet
-- ----------------------------
DROP TABLE IF EXISTS `type_pet`;
CREATE TABLE `type_pet`  (
  `ty_id` int NOT NULL AUTO_INCREMENT,
  `ty_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`ty_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of type_pet
-- ----------------------------
INSERT INTO `type_pet` VALUES (1, 'Exotic');
INSERT INTO `type_pet` VALUES (2, 'Winged');
INSERT INTO `type_pet` VALUES (3, 'Cat');
INSERT INTO `type_pet` VALUES (4, 'Dog');
INSERT INTO `type_pet` VALUES (5, 'Cattle');
INSERT INTO `type_pet` VALUES (6, 'Small cattle');
INSERT INTO `type_pet` VALUES (7, 'Tropical');
INSERT INTO `type_pet` VALUES (8, 'Wild');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `us_id` int NOT NULL AUTO_INCREMENT,
  `created_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `created_date` datetime NULL DEFAULT NULL,
  `last_modified_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `last_modified_date` datetime NULL DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `enabled` bit(1) NOT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `token_expired` bit(1) NOT NULL,
  `us_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `us_surname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `us_tel` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `user_file` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`us_id`) USING BTREE,
  UNIQUE INDEX `UK_ob8kqyqqgmefl0aco34akdtpe`(`email`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'anonymousUser', '2021-11-08 19:42:00', 'admin@mail.com', '2021-11-08 19:42:00', 'admin@mail.com', b'1', '$2a$12$5VvcRwTGfJ6aNacxCqZiSOrtgThPIIgyV8D9gDxB/l2yKh6AkxeGO', b'0', 'ADMIN', 'ADMIN', '5345907262', 'd2d9edba-53ef-4dff-8a20-68ebf493853a.png');
INSERT INTO `user` VALUES (2, 'anonymousUser', '2021-11-08 19:42:00', 'test@mail.com', '2021-11-08 19:42:00', 'test@mail.com', b'1', '$2a$12$iuuxBXX/gFkKAePXEd5rR.lm9XO8TfSaZxyoZW8PMEyvKogB13Sl6', b'0', 'TEST', 'TEST', '5050103555', '88c74aa8-9972-4795-9e8e-11b25dc1cedc.jpeg');

-- ----------------------------
-- Table structure for user_follow_in
-- ----------------------------
DROP TABLE IF EXISTS `user_follow_in`;
CREATE TABLE `user_follow_in`  (
  `user_follow_in_id` int NOT NULL AUTO_INCREMENT,
  `created_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `created_date` datetime NULL DEFAULT NULL,
  `last_modified_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `last_modified_date` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`user_follow_in_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_follow_in
-- ----------------------------
INSERT INTO `user_follow_in` VALUES (1, 'admin@mail.com', '2021-11-08 19:44:00', 'admin@mail.com', '2021-11-08 19:44:00');

-- ----------------------------
-- Table structure for users_roles
-- ----------------------------
DROP TABLE IF EXISTS `users_roles`;
CREATE TABLE `users_roles`  (
  `user_id` int NOT NULL,
  `role_id` int NOT NULL,
  INDEX `FKt4v0rrweyk393bdgt107vdx0x`(`role_id`) USING BTREE,
  INDEX `FKgd3iendaoyh04b95ykqise6qh`(`user_id`) USING BTREE,
  CONSTRAINT `FKgd3iendaoyh04b95ykqise6qh` FOREIGN KEY (`user_id`) REFERENCES `user` (`us_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FKt4v0rrweyk393bdgt107vdx0x` FOREIGN KEY (`role_id`) REFERENCES `role` (`ro_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of users_roles
-- ----------------------------
INSERT INTO `users_roles` VALUES (1, 1);
INSERT INTO `users_roles` VALUES (1, 2);
INSERT INTO `users_roles` VALUES (1, 3);
INSERT INTO `users_roles` VALUES (1, 4);
INSERT INTO `users_roles` VALUES (2, 2);
INSERT INTO `users_roles` VALUES (2, 3);

SET FOREIGN_KEY_CHECKS = 1;

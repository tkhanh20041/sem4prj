-- MySQL dump 10.13  Distrib 8.0.19, for Win64 (x86_64)
--
-- Host: localhost    Database: newtourdatabase
-- ------------------------------------------------------
-- Server version	8.0.36

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `app_roles`
--

DROP TABLE IF EXISTS `app_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `app_roles` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `app_roles`
--

LOCK TABLES `app_roles` WRITE;
/*!40000 ALTER TABLE `app_roles` DISABLE KEYS */;
INSERT INTO `app_roles` VALUES (1,'ROLE_USER'),(2,'ROLE_ADMIN');
/*!40000 ALTER TABLE `app_roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `book_details`
--

DROP TABLE IF EXISTS `book_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `book_details` (
  `book_detail_id` bigint NOT NULL AUTO_INCREMENT,
  `price` double DEFAULT NULL,
  `quantity` int NOT NULL,
  `start_date` datetime DEFAULT NULL,
  `end_date` datetime DEFAULT NULL,
  `book_id` bigint DEFAULT NULL,
  `tour_id` bigint DEFAULT NULL,
  PRIMARY KEY (`book_detail_id`),
  KEY `tour_id` (`tour_id`),
  KEY `book_id` (`book_id`),
  CONSTRAINT `book_details_ibfk_1` FOREIGN KEY (`tour_id`) REFERENCES `tours` (`tour_id`),
  CONSTRAINT `book_details_ibfk_2` FOREIGN KEY (`book_id`) REFERENCES `books` (`book_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `book_details`
--

LOCK TABLES `book_details` WRITE;
/*!40000 ALTER TABLE `book_details` DISABLE KEYS */;
INSERT INTO `book_details` VALUES (1,9000,2,'2024-11-12 00:00:00','2024-11-13 00:00:00',1,1),(2,552.5,1,'2024-11-12 00:00:00','2024-11-13 00:00:00',1,2),(3,13500,3,'2024-11-12 00:00:00','2024-11-13 00:00:00',2,1),(4,9800,2,'2024-11-12 00:00:00','2024-11-13 00:00:00',2,6),(5,6840,2,'2024-11-12 00:00:00','2024-11-13 00:00:00',3,10),(6,13120.000000000002,4,'2024-11-12 00:00:00','2024-11-13 00:00:00',3,14),(7,18000,4,'2024-11-12 00:00:00','2024-11-13 00:00:00',4,1);
/*!40000 ALTER TABLE `book_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `books`
--

DROP TABLE IF EXISTS `books`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `books` (
  `book_id` bigint NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `amount` double DEFAULT NULL,
  `book_date` datetime DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `status` int NOT NULL,
  `start_date` datetime DEFAULT NULL,
  `end_date` datetime DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`book_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `books_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `books`
--

LOCK TABLES `books` WRITE;
/*!40000 ALTER TABLE `books` DISABLE KEYS */;
INSERT INTO `books` VALUES (1,'36, Phường Kim Mã, Quận Ba Đình, Thành phố Hà Nội',9552.5,'2024-11-12 03:15:38','0912345678',0,'2024-11-12 00:00:00','2024-11-13 00:00:00',2),(2,'123, Xã Cai Kinh, Huyện Hữu Lũng, Tỉnh Lạng Sơn',23300,'2024-11-12 04:17:08','0912345678',1,'2024-11-12 00:00:00','2024-11-13 00:00:00',2),(3,'36, Phường Nhật Tân, Quận Tây Hồ, Thành phố Hà Nội',19960,'2024-11-12 04:18:29','0912345678',2,'2024-11-12 00:00:00','2024-11-13 00:00:00',2),(4,'111, Xã Bắc Phú, Huyện Sóc Sơn, Thành phố Hà Nội',18000,'2024-11-12 07:04:24','0912345678',2,'2024-11-12 00:00:00','2024-11-13 00:00:00',2);
/*!40000 ALTER TABLE `books` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cart_details`
--

DROP TABLE IF EXISTS `cart_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cart_details` (
  `cart_detail_id` bigint NOT NULL AUTO_INCREMENT,
  `price` double DEFAULT NULL,
  `quantity` int NOT NULL,
  `cart_id` bigint DEFAULT NULL,
  `tour_id` bigint DEFAULT NULL,
  `start_date` date DEFAULT NULL,
  `end_date` date DEFAULT NULL,
  PRIMARY KEY (`cart_detail_id`),
  KEY `tour_id` (`tour_id`),
  KEY `cart_id` (`cart_id`),
  CONSTRAINT `cart_details_ibfk_1` FOREIGN KEY (`tour_id`) REFERENCES `tours` (`tour_id`),
  CONSTRAINT `cart_details_ibfk_2` FOREIGN KEY (`cart_id`) REFERENCES `carts` (`cart_id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cart_details`
--

LOCK TABLES `cart_details` WRITE;
/*!40000 ALTER TABLE `cart_details` DISABLE KEYS */;
/*!40000 ALTER TABLE `cart_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `carts`
--

DROP TABLE IF EXISTS `carts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `carts` (
  `cart_id` bigint NOT NULL AUTO_INCREMENT,
  `amount` double DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`cart_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `carts_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `carts`
--

LOCK TABLES `carts` WRITE;
/*!40000 ALTER TABLE `carts` DISABLE KEYS */;
INSERT INTO `carts` VALUES (1,0,'HN','0912345678',1),(2,0,'111, Xã Bắc Phú, Huyện Sóc Sơn, Thành phố Hà Nội','0912345678',2);
/*!40000 ALTER TABLE `carts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `categories`
--

DROP TABLE IF EXISTS `categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `categories` (
  `category_id` bigint NOT NULL AUTO_INCREMENT,
  `category_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categories`
--

LOCK TABLES `categories` WRITE;
/*!40000 ALTER TABLE `categories` DISABLE KEYS */;
INSERT INTO `categories` VALUES (1,'Miền Bắc'),(2,'Miền Trung'),(3,'Miền Nam'),(4,'Du lịch biển'),(5,'Du lịch vùng núi');
/*!40000 ALTER TABLE `categories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `favorites`
--

DROP TABLE IF EXISTS `favorites`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `favorites` (
  `favorite_id` bigint NOT NULL AUTO_INCREMENT,
  `tour_id` bigint DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`favorite_id`),
  KEY `tour_id` (`tour_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `favorites_ibfk_1` FOREIGN KEY (`tour_id`) REFERENCES `tours` (`tour_id`),
  CONSTRAINT `favorites_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `favorites`
--

LOCK TABLES `favorites` WRITE;
/*!40000 ALTER TABLE `favorites` DISABLE KEYS */;
/*!40000 ALTER TABLE `favorites` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `image`
--

DROP TABLE IF EXISTS `image`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `image` (
  `image_id` bigint NOT NULL AUTO_INCREMENT,
  `tour_id` bigint NOT NULL,
  `image_url` varchar(255) NOT NULL,
  `description` varchar(500) DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`image_id`),
  KEY `tour_id` (`tour_id`),
  CONSTRAINT `image_ibfk_1` FOREIGN KEY (`tour_id`) REFERENCES `tours` (`tour_id`)
) ENGINE=InnoDB AUTO_INCREMENT=125 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `image`
--

LOCK TABLES `image` WRITE;
/*!40000 ALTER TABLE `image` DISABLE KEYS */;
INSERT INTO `image` VALUES (1,1,'https://res.cloudinary.com/shopdemo/image/upload/v1730965730/irx2p3pib6c8rtgod9ht','Beautiful view of the mountains','2024-11-06 13:49:57','2024-11-12 08:34:36'),(2,1,'https://res.cloudinary.com/shopdemo/image/upload/v1730965730/eggbzztaowmu5zkuqgcl','Sunset over the valley','2024-11-06 13:49:57','2024-11-12 08:34:36'),(3,2,'https://example.com/image3.jpg','River flowing through the forest','2024-11-06 13:49:57','2024-11-06 13:49:57'),(4,2,'https://example.com/image4.jpg','Historic city square at night','2024-11-06 13:49:57','2024-11-06 13:49:57'),(5,2,'https://example.com/image5.jpg','Trail leading into the hills','2024-11-06 13:49:57','2024-11-06 13:49:57'),(6,1,'https://res.cloudinary.com/shopdemo/image/upload/v1730965730/iyojs0bdxnjsylgmjzr2','Description: Image of Tour1','2024-11-08 14:13:00','2024-11-12 08:34:36'),(7,1,'https://res.cloudinary.com/shopdemo/image/upload/v1730965730/qu3fkk4cgszzbntcrliy','Description: Image of Tour1','2024-11-08 14:13:00','2024-11-12 08:34:36'),(8,1,'https://res.cloudinary.com/shopdemo/image/upload/v1730965730/uxigexubvphw9oi1inlq','Description: Image of Tour1','2024-11-08 14:13:00','2024-11-12 08:34:36'),(12,21,'url1','Description: Image of Tour21','2024-11-08 17:07:24','2024-11-08 17:07:24'),(13,21,'url2','Description: Image of Tour21','2024-11-08 17:07:24','2024-11-08 17:07:24'),(14,21,'url3','Description: Image of Tour21','2024-11-08 17:07:24','2024-11-08 17:07:24'),(15,1,'https://res.cloudinary.com/shopdemo/image/upload/v1730965730/lxj19tq3zghiuz0gp9on','td','2024-11-08 17:07:24','2024-11-12 09:30:14'),(16,1,'https://res.cloudinary.com/shopdemo/image/upload/v1730965730/ttcrlhegbvkvhk2chfpo','td','2024-11-08 17:07:24','2024-11-12 09:30:14'),(17,1,'https://res.cloudinary.com/shopdemo/image/upload/v1730965730/suzph25elc4iaunwvos0','td','2024-11-08 17:07:24','2024-11-12 09:30:14'),(18,1,'https://res.cloudinary.com/shopdemo/image/upload/v1730965730/hoe2shlopkecqjzvn1p3','td','2024-11-08 17:07:24','2024-11-12 09:30:14'),(19,1,'https://res.cloudinary.com/shopdemo/image/upload/v1730965730/sjvw2nvm1tuhafgyplhf','td','2024-11-08 17:07:24','2024-11-12 09:30:14'),(20,12,'https://res.cloudinary.com/shopdemo/image/upload/v1730965730/qebtedzjo6tm3jkfr6qo','pq','2024-11-12 09:29:33','2024-11-12 09:29:58'),(21,12,'https://res.cloudinary.com/shopdemo/image/upload/v1730965730/upef6x2m8rmjy3d6uwuw','pq','2024-11-12 09:29:33','2024-11-12 09:29:58'),(22,12,'https://res.cloudinary.com/shopdemo/image/upload/v1730965730/czu8de2w6kmr3yjqha2p','pq','2024-11-12 09:29:33','2024-11-12 09:29:58'),(23,12,'https://res.cloudinary.com/shopdemo/image/upload/v1730965730/iuiy3cdsgcwfgapmm5nk','pq','2024-11-12 09:29:33','2024-11-12 09:29:58'),(24,12,'https://res.cloudinary.com/shopdemo/image/upload/v1730965730/huiatgqtzmvymbsr4gp8','pq','2024-11-12 09:29:33','2024-11-12 09:29:58'),(25,12,'https://res.cloudinary.com/shopdemo/image/upload/v1730965730/ubsaw8hibklwjozcslja','pq','2024-11-12 09:29:33','2024-11-12 09:29:58'),(26,2,'https://res.cloudinary.com/shopdemo/image/upload/v1730965730/xtps9jgtn0uyrx5ejoth',NULL,'2024-11-12 09:32:33','2024-11-12 09:32:33'),(27,2,'https://res.cloudinary.com/shopdemo/image/upload/v1730965730/ux1cerjg3gcy2ovzvhru',NULL,'2024-11-12 09:32:33','2024-11-12 09:32:33'),(28,2,'https://res.cloudinary.com/shopdemo/image/upload/v1730965730/dkjkdo8shuakmjf1rnwx',NULL,'2024-11-12 09:32:33','2024-11-12 09:32:33'),(29,2,'https://res.cloudinary.com/shopdemo/image/upload/v1730965730/zsym5s66ssyzfhiab4bu',NULL,'2024-11-12 09:32:33','2024-11-12 09:32:33'),(30,2,'https://res.cloudinary.com/shopdemo/image/upload/v1730965730/rqynvkrhf4ohjpdq5uaa',NULL,'2024-11-12 09:32:33','2024-11-12 09:32:33'),(31,2,'https://res.cloudinary.com/shopdemo/image/upload/v1730965730/srs2kwbjyfrtioksvunh',NULL,'2024-11-12 09:32:33','2024-11-12 09:32:33'),(32,2,'https://res.cloudinary.com/shopdemo/image/upload/v1730965730/cbwdr3t5mzmeoyo7anse',NULL,'2024-11-12 09:32:33','2024-11-12 09:32:33'),(33,2,'https://res.cloudinary.com/shopdemo/image/upload/v1730965730/hberfysrgpbtcriy9baf',NULL,'2024-11-12 09:32:33','2024-11-12 09:32:33'),(34,2,'https://res.cloudinary.com/shopdemo/image/upload/v1730965730/l2bceki0tj9jj1pva6bw',NULL,'2024-11-12 09:32:33','2024-11-12 09:32:33'),(35,2,'https://res.cloudinary.com/shopdemo/image/upload/v1730965730/eqfwce9jazw3if2syzf7',NULL,'2024-11-12 09:32:33','2024-11-12 09:32:33'),(36,2,'https://res.cloudinary.com/shopdemo/image/upload/v1730965730/aoc6zkozdebynuysi2ol',NULL,'2024-11-12 09:32:33','2024-11-12 09:32:33'),(37,3,'https://res.cloudinary.com/shopdemo/image/upload/v1730965730/gp5owqck2i3kgekr6kic',NULL,'2024-11-12 09:34:27','2024-11-12 09:34:27'),(38,3,'https://res.cloudinary.com/shopdemo/image/upload/v1730965730/syp3siv6yevk6xrgkdxn',NULL,'2024-11-12 09:34:27','2024-11-12 09:34:27'),(39,3,'https://res.cloudinary.com/shopdemo/image/upload/v1730965730/jjimyoyzdimu2mxqqbge',NULL,'2024-11-12 09:34:27','2024-11-12 09:34:27'),(40,3,'https://res.cloudinary.com/shopdemo/image/upload/v1730965730/sbgum7lqiz9myg11ivqe',NULL,'2024-11-12 09:34:27','2024-11-12 09:34:27'),(41,3,'https://res.cloudinary.com/shopdemo/image/upload/v1730965730/ip3bhspgaedsbcwitdjn',NULL,'2024-11-12 09:34:27','2024-11-12 09:34:27'),(42,3,'https://res.cloudinary.com/shopdemo/image/upload/v1730965730/hh08qzpyznmnyngxc3re',NULL,'2024-11-12 09:34:27','2024-11-12 09:34:27'),(43,3,'https://res.cloudinary.com/shopdemo/image/upload/v1730965730/bgnwddas9kypxv4wzr7q',NULL,'2024-11-12 09:34:27','2024-11-12 09:34:27'),(44,3,'https://res.cloudinary.com/shopdemo/image/upload/v1730965730/pepywx1pfip48c3lsuth',NULL,'2024-11-12 09:34:27','2024-11-12 09:34:27'),(45,3,'https://res.cloudinary.com/shopdemo/image/upload/v1730965730/joh7pk5qaimrapurkgx3',NULL,'2024-11-12 09:34:27','2024-11-12 09:34:27'),(46,3,'https://res.cloudinary.com/shopdemo/image/upload/v1730965730/xdhezpiososx6hbqtfux',NULL,'2024-11-12 09:34:27','2024-11-12 09:34:27'),(47,4,'https://res.cloudinary.com/shopdemo/image/upload/v1730965730/lj8xijccujbwlqra3gbk',NULL,'2024-11-12 09:36:11','2024-11-12 09:36:11'),(48,4,'https://res.cloudinary.com/shopdemo/image/upload/v1730965730/kiasjryg41iinkw976mb',NULL,'2024-11-12 09:36:11','2024-11-12 09:36:11'),(49,4,'https://res.cloudinary.com/shopdemo/image/upload/v1730965730/evkaqvb113hcpcngk6la',NULL,'2024-11-12 09:36:11','2024-11-12 09:36:11'),(50,4,'https://res.cloudinary.com/shopdemo/image/upload/v1730965730/lvb6n3y6fqfgrp4n4unf',NULL,'2024-11-12 09:36:11','2024-11-12 09:36:11'),(51,4,'https://res.cloudinary.com/shopdemo/image/upload/v1730965730/ifmf7ag5d46agkxp93nu',NULL,'2024-11-12 09:36:11','2024-11-12 09:36:11'),(52,4,'https://res.cloudinary.com/shopdemo/image/upload/v1730965730/t8dlkbeutgw7hruzytbu',NULL,'2024-11-12 09:36:11','2024-11-12 09:36:11'),(53,4,'https://res.cloudinary.com/shopdemo/image/upload/v1730965730/d6tyr1vczenumjdxmt7b',NULL,'2024-11-12 09:36:11','2024-11-12 09:36:11'),(54,4,'https://res.cloudinary.com/shopdemo/image/upload/v1730965730/hk6pdlizp1g4f4huspto',NULL,'2024-11-12 09:36:11','2024-11-12 09:36:11'),(55,4,'https://res.cloudinary.com/shopdemo/image/upload/v1730965730/ay0tzbzcxjesk1nebq6n',NULL,'2024-11-12 09:36:11','2024-11-12 09:36:11'),(56,4,'https://res.cloudinary.com/shopdemo/image/upload/v1730965730/l3pmretuqza8tsnfqvw1',NULL,'2024-11-12 09:36:11','2024-11-12 09:36:11'),(57,5,'https://res.cloudinary.com/shopdemo/image/upload/v1730965730/ulf777qbzeqexqubbx9z',NULL,'2024-11-12 09:38:03','2024-11-12 09:38:03'),(58,5,'https://res.cloudinary.com/shopdemo/image/upload/v1730965730/xjns4dxpsvs9xaoomrcb',NULL,'2024-11-12 09:38:03','2024-11-12 09:38:03'),(59,5,'https://res.cloudinary.com/shopdemo/image/upload/v1730965730/ss0wus78tkpowmoabajj',NULL,'2024-11-12 09:38:03','2024-11-12 09:38:03'),(60,5,'https://res.cloudinary.com/shopdemo/image/upload/v1730965730/ysin0spc1txabcqudqd4',NULL,'2024-11-12 09:38:03','2024-11-12 09:38:03'),(61,5,'https://res.cloudinary.com/shopdemo/image/upload/v1730965730/s1naxoy9gg2sob4mzmlu',NULL,'2024-11-12 09:38:03','2024-11-12 09:38:03'),(62,5,'https://res.cloudinary.com/shopdemo/image/upload/v1730965730/j6yxq1w9fu55wozbej7o',NULL,'2024-11-12 09:38:03','2024-11-12 09:38:03'),(63,5,'https://res.cloudinary.com/shopdemo/image/upload/v1730965730/jojlzh8plvrwkkzgiu5x',NULL,'2024-11-12 09:38:03','2024-11-12 09:38:03'),(64,5,'https://res.cloudinary.com/shopdemo/image/upload/v1730965730/fezq3ngzod6bx4ga1trp',NULL,'2024-11-12 09:38:03','2024-11-12 09:38:03'),(65,5,'https://res.cloudinary.com/shopdemo/image/upload/v1730965730/x5ulyjrhrxj04npgno6k',NULL,'2024-11-12 09:38:03','2024-11-12 09:38:03'),(66,5,'https://res.cloudinary.com/shopdemo/image/upload/v1730965730/g6pnzrn3hl6hol0dqppf',NULL,'2024-11-12 09:38:03','2024-11-12 09:38:03'),(67,6,'https://res.cloudinary.com/shopdemo/image/upload/v1730965730/w29lz8ldnj7rp4t0e9in',NULL,'2024-11-12 09:40:05','2024-11-12 09:40:05'),(68,6,'https://res.cloudinary.com/shopdemo/image/upload/v1730965730/vbd6apmynfmdtswsccrr',NULL,'2024-11-12 09:40:05','2024-11-12 09:40:05'),(69,6,'https://res.cloudinary.com/shopdemo/image/upload/v1730965730/vd0spygulcg3vhbnpo2e',NULL,'2024-11-12 09:40:05','2024-11-12 09:40:05'),(70,6,'https://res.cloudinary.com/shopdemo/image/upload/v1730965730/a8ck0i0rpvuwotpvbcag',NULL,'2024-11-12 09:40:05','2024-11-12 09:40:05'),(71,6,'https://res.cloudinary.com/shopdemo/image/upload/v1730965730/iyfq5quryucd3rdpctcm',NULL,'2024-11-12 09:40:05','2024-11-12 09:40:05'),(72,6,'https://res.cloudinary.com/shopdemo/image/upload/v1730965730/qio3gxhlaumg0x2jzmlj',NULL,'2024-11-12 09:40:05','2024-11-12 09:40:05'),(73,6,'https://res.cloudinary.com/shopdemo/image/upload/v1730965730/mkfmhh058kikzovdrwgr',NULL,'2024-11-12 09:40:05','2024-11-12 09:40:05'),(74,6,'https://res.cloudinary.com/shopdemo/image/upload/v1730965730/sivbbnzxyascjfol4ud4',NULL,'2024-11-12 09:40:05','2024-11-12 09:40:05'),(75,6,'https://res.cloudinary.com/shopdemo/image/upload/v1730965730/zjd0xd4qikslyl0hqki5',NULL,'2024-11-12 09:40:05','2024-11-12 09:40:05'),(76,6,'https://res.cloudinary.com/shopdemo/image/upload/v1730965730/zujtwgta4qvsgbtblnsx',NULL,'2024-11-12 09:40:05','2024-11-12 09:40:05'),(77,6,'https://res.cloudinary.com/shopdemo/image/upload/v1730965730/a8ck0i0rpvuwotpvbcag',NULL,'2024-11-12 09:40:05','2024-11-12 09:40:05'),(78,6,'https://res.cloudinary.com/shopdemo/image/upload/v1730965730/iyfq5quryucd3rdpctcm',NULL,'2024-11-12 09:40:05','2024-11-12 09:40:05'),(79,7,'https://res.cloudinary.com/shopdemo/image/upload/v1730965730/wfjezttkszr44qhixmpu',NULL,'2024-11-12 09:41:40','2024-11-12 09:41:40'),(80,7,'https://res.cloudinary.com/shopdemo/image/upload/v1730965730/t9x3snr23r23phs9anr0',NULL,'2024-11-12 09:41:40','2024-11-12 09:41:40'),(81,7,'https://res.cloudinary.com/shopdemo/image/upload/v1730965730/aiofc89iy5w4arqai9ta',NULL,'2024-11-12 09:41:40','2024-11-12 09:41:40'),(82,7,'https://res.cloudinary.com/shopdemo/image/upload/v1730965730/kedhyhnisjuvkr4ilyzc',NULL,'2024-11-12 09:41:40','2024-11-12 09:41:40'),(83,7,'https://res.cloudinary.com/shopdemo/image/upload/v1730965730/z9bf3epakohwpmqxnyto',NULL,'2024-11-12 09:41:40','2024-11-12 09:41:40'),(84,7,'https://res.cloudinary.com/shopdemo/image/upload/v1730965730/aiofc89iy5w4arqai9ta',NULL,'2024-11-12 09:41:40','2024-11-12 09:41:40'),(85,7,'https://res.cloudinary.com/shopdemo/image/upload/v1730965730/yveaacdimqromx1wcpcg',NULL,'2024-11-12 09:41:40','2024-11-12 09:41:40'),(86,7,'https://res.cloudinary.com/shopdemo/image/upload/v1730965730/t9x3snr23r23phs9anr0',NULL,'2024-11-12 09:41:40','2024-11-12 09:41:40'),(87,8,'https://res.cloudinary.com/shopdemo/image/upload/v1730965730/gliu0wwelycmvm2ftxhm',NULL,'2024-11-12 09:43:26','2024-11-12 09:43:26'),(88,8,'https://res.cloudinary.com/shopdemo/image/upload/v1730965730/dvr9zp6on1v5b9q3dhnv',NULL,'2024-11-12 09:43:26','2024-11-12 09:43:26'),(89,8,'https://res.cloudinary.com/shopdemo/image/upload/v1730965730/evsvhrkol2ihiwjhm0bm',NULL,'2024-11-12 09:43:26','2024-11-12 09:43:26'),(90,8,'https://res.cloudinary.com/shopdemo/image/upload/v1730965730/u9owlmujzuihjjl26uhy',NULL,'2024-11-12 09:43:26','2024-11-12 09:43:26'),(91,8,'https://res.cloudinary.com/shopdemo/image/upload/v1730965730/ioqfdqk3njlbymcr4w8y',NULL,'2024-11-12 09:43:26','2024-11-12 09:43:26'),(92,8,'https://res.cloudinary.com/shopdemo/image/upload/v1730965730/r2tfjfugavisv8aszjol',NULL,'2024-11-12 09:43:26','2024-11-12 09:43:26'),(93,8,'https://res.cloudinary.com/shopdemo/image/upload/v1730965730/yndbi4dl6ytp2ngzr8la',NULL,'2024-11-12 09:43:26','2024-11-12 09:43:26'),(94,8,'https://res.cloudinary.com/shopdemo/image/upload/v1730965730/ujersafony6xi7ylikem',NULL,'2024-11-12 09:43:26','2024-11-12 09:43:26'),(95,8,'https://res.cloudinary.com/shopdemo/image/upload/v1730965730/myqdblzfgmb188ma9rtt',NULL,'2024-11-12 09:43:26','2024-11-12 09:43:26'),(96,8,'https://res.cloudinary.com/shopdemo/image/upload/v1730965730/t9x3snr23r23phs9anr0',NULL,'2024-11-12 09:43:26','2024-11-12 09:43:26'),(97,9,'https://res.cloudinary.com/shopdemo/image/upload/v1730965730/dhfyytprkvtwjvysktpt',NULL,'2024-11-12 09:45:14','2024-11-12 09:45:14'),(98,9,'https://res.cloudinary.com/shopdemo/image/upload/v1730965730/r0bf7fpudrb8lo7agumn',NULL,'2024-11-12 09:45:14','2024-11-12 09:45:14'),(99,9,'https://res.cloudinary.com/shopdemo/image/upload/v1730965730/r9dv4s7wqvrfqyxgftep',NULL,'2024-11-12 09:45:14','2024-11-12 09:45:14'),(100,9,'https://res.cloudinary.com/shopdemo/image/upload/v1730965730/mirmwhyrgzzv57okivvp',NULL,'2024-11-12 09:45:14','2024-11-12 09:45:14'),(101,9,'https://res.cloudinary.com/shopdemo/image/upload/v1730965730/siytmz44tc0mdfdmfq5x',NULL,'2024-11-12 09:45:14','2024-11-12 09:45:14'),(102,11,'https://res.cloudinary.com/shopdemo/image/upload/v1730965730/er92s1ibjglkr8fdnukp',NULL,'2024-11-12 09:47:20','2024-11-12 09:47:20'),(103,11,'https://res.cloudinary.com/shopdemo/image/upload/v1730965730/gjqeua5c9ej0s36cpj6b',NULL,'2024-11-12 09:47:20','2024-11-12 09:47:20'),(104,11,'https://res.cloudinary.com/shopdemo/image/upload/v1730965730/k3cbhg5lwvfyklebrk9a',NULL,'2024-11-12 09:47:20','2024-11-12 09:47:20'),(105,11,'https://res.cloudinary.com/shopdemo/image/upload/v1730965730/tdhu5dtu4upljdc9renj',NULL,'2024-11-12 09:47:20','2024-11-12 09:47:20'),(106,11,'https://res.cloudinary.com/shopdemo/image/upload/v1730965730/q4o4ahohbpeicggbxuro',NULL,'2024-11-12 09:47:20','2024-11-12 09:47:20'),(107,11,'https://res.cloudinary.com/shopdemo/image/upload/v1730965730/iimswwdkzjgszoncb9kf',NULL,'2024-11-12 09:47:20','2024-11-12 09:47:20'),(108,22,'https://res.cloudinary.com/shopdemo/image/upload/v1730965730/mc5vbntwujm0do3wcc0j',NULL,'2024-11-12 09:54:06','2024-11-12 09:54:06'),(109,22,'https://res.cloudinary.com/shopdemo/image/upload/v1730965730/qmgznr7tzwrsikr6xdgw',NULL,'2024-11-12 09:54:06','2024-11-12 09:54:06'),(110,22,'https://res.cloudinary.com/shopdemo/image/upload/v1730965730/mdlbzigvvyufcgi7yhxj',NULL,'2024-11-12 09:54:06','2024-11-12 09:54:06'),(111,22,'https://res.cloudinary.com/shopdemo/image/upload/v1730965730/mrspqzbfxxlrmkztjpyl',NULL,'2024-11-12 09:54:06','2024-11-12 09:54:06'),(112,22,'https://res.cloudinary.com/shopdemo/image/upload/v1730965730/xcvzxfcvfaxqgvdmo1t2',NULL,'2024-11-12 09:54:06','2024-11-12 09:54:06'),(113,22,'https://res.cloudinary.com/shopdemo/image/upload/v1730965730/gafwx1z40rlqxpv51ifs',NULL,'2024-11-12 09:54:06','2024-11-12 09:54:06'),(114,22,'https://res.cloudinary.com/shopdemo/image/upload/v1730965730/pywbfhlqmmpsicxrmgxq',NULL,'2024-11-12 09:54:06','2024-11-12 09:54:06'),(115,22,'https://res.cloudinary.com/shopdemo/image/upload/v1730965730/l9k7fyox4hhjr6vph2un',NULL,'2024-11-12 09:54:06','2024-11-12 09:54:06'),(116,22,'https://res.cloudinary.com/shopdemo/image/upload/v1730965730/btp1exep7nr4k957uuei',NULL,'2024-11-12 09:54:06','2024-11-12 09:54:06'),(117,23,'https://res.cloudinary.com/shopdemo/image/upload/v1730965730/vtlhqdbpip6dig5wvod5',NULL,'2024-11-12 09:58:34','2024-11-12 09:58:34'),(118,23,'https://res.cloudinary.com/shopdemo/image/upload/v1730965730/gwooehtiozfhxuuawc8u',NULL,'2024-11-12 09:58:34','2024-11-12 09:58:34'),(119,23,'https://res.cloudinary.com/shopdemo/image/upload/v1730965730/u6nadsu5crdgnup3gbzu',NULL,'2024-11-12 09:58:34','2024-11-12 09:58:34'),(120,23,'https://res.cloudinary.com/shopdemo/image/upload/v1730965730/kr04trs5wvi0dxwk3m0l',NULL,'2024-11-12 09:58:34','2024-11-12 09:58:34'),(121,23,'https://res.cloudinary.com/shopdemo/image/upload/v1730965730/n0ehprjfwibseuo0gs09',NULL,'2024-11-12 09:58:34','2024-11-12 09:58:34'),(122,23,'https://res.cloudinary.com/shopdemo/image/upload/v1730965730/ojgznw8yhbl80fwedz4w',NULL,'2024-11-12 09:58:34','2024-11-12 09:58:34'),(123,23,'https://res.cloudinary.com/shopdemo/image/upload/v1730965730/r5c6zin9pwatnrdeot3h',NULL,'2024-11-12 09:58:34','2024-11-12 09:58:34'),(124,23,'https://res.cloudinary.com/shopdemo/image/upload/v1730965730/eh2mp84vpspxpxok7yls',NULL,'2024-11-12 09:58:34','2024-11-12 09:58:34');
/*!40000 ALTER TABLE `image` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `location`
--

DROP TABLE IF EXISTS `location`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `location` (
  `location_id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  `description` text,
  `latitude` decimal(10,8) DEFAULT NULL,
  `longitude` decimal(11,8) DEFAULT NULL,
  `category_id` bigint DEFAULT NULL,
  `tour_id` bigint DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`location_id`),
  KEY `category_id` (`category_id`),
  KEY `tour_id` (`tour_id`),
  FULLTEXT KEY `idx_location_name` (`name`),
  CONSTRAINT `location_ibfk_1` FOREIGN KEY (`category_id`) REFERENCES `categories` (`category_id`),
  CONSTRAINT `location_ibfk_2` FOREIGN KEY (`tour_id`) REFERENCES `tours` (`tour_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1684 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `location`
--

LOCK TABLES `location` WRITE;
/*!40000 ALTER TABLE `location` DISABLE KEYS */;
INSERT INTO `location` VALUES (1,'Miền Bắc','Miền Bắc (Northern Vietnam) is known for its mountainous terrain, lush rice terraces, and vibrant cultural heritage. The region features major cities such as Hanoi and historic landmarks like Ha Long Bay.',21.02850000,105.85420000,1,NULL,'2024-11-08 11:35:32','2024-11-08 11:35:32'),(2,'Miền Trung','Miền Trung (Central Vietnam) is a coastal region known for its historical towns like Hoi An and Hue, beautiful beaches, and rich culture. The region offers a perfect blend of natural beauty and cultural significance.',15.50000000,108.00000000,2,NULL,'2024-11-08 11:35:32','2024-11-08 11:35:32'),(3,'Miền Nam','Miền Nam (Southern Vietnam) is known for its tropical climate, bustling cities like Ho Chi Minh City, and picturesque Mekong Delta. The region is a mix of modern development and traditional rural life.',10.75000000,106.00000000,3,NULL,'2024-11-08 11:35:32','2024-11-08 11:35:32'),(1006,'Long An','Long An is a province located in the Mekong Delta, known for its lush greenery, rivers, and tranquil rural lifestyle. The region offers eco-tourism and agricultural experiences.',10.24000000,106.69250000,2,NULL,'2024-11-08 11:35:32','2024-11-08 11:35:32'),(1007,'Tuyên Quang','Tuyên Quang is a northern province, home to stunning natural beauty, including forests, mountains, and rivers. It is a peaceful destination with cultural significance as the historical site of the Vietnamese revolution.',21.81300000,105.20400000,1,NULL,'2024-11-08 11:35:32','2024-11-08 11:35:32'),(1008,'Trà Vinh','Trà Vinh is located in the Mekong Delta, famous for its Khmer culture, temples, and beautiful rivers. It is an emerging destination for cultural tourism and natural landscapes.',9.93120000,106.35630000,2,NULL,'2024-11-08 11:35:32','2024-11-08 11:35:32'),(1009,'Quảng Ngãi','Quảng Ngãi is a coastal province with beautiful beaches and historical sites, including the famous Mỹ Lai massacre memorial. The region also offers a rich cultural heritage and unique cuisine.',15.10160000,108.80370000,3,NULL,'2024-11-08 11:35:32','2024-11-08 11:35:32'),(1010,'Thanh Hóa','Thanh Hóa is located in northern Vietnam, known for its ancient history, including the famous Temple of Ba Trieu and beautiful beaches along its coast. It is a cultural and natural hotspot.',19.80870000,105.77940000,1,NULL,'2024-11-08 11:35:32','2024-11-08 11:35:32'),(1011,'Hưng Yên','Hưng Yên is a province in northern Vietnam, known for its rich culture, ancient temples, and peaceful rural landscapes. It is also famous for its traditional handicrafts and fruit farming.',20.91430000,106.03810000,1,NULL,'2024-11-08 11:35:32','2024-11-08 11:35:32'),(1012,'Kon Tum','Kon Tum is a province located in the Central Highlands of Vietnam, known for its mountainous terrain, ethnic diversity, and beautiful lakes, rivers, and forests.',14.38360000,108.03200000,3,NULL,'2024-11-08 11:35:32','2024-11-08 11:35:32'),(1013,'Đắk Nông','Đắk Nông is a province in the Central Highlands, famous for its volcanic landscapes, waterfalls, and ethnic minority culture. It is a destination for eco-tourism and adventure travel.',12.96830000,107.99010000,3,NULL,'2024-11-08 11:35:32','2024-11-08 11:35:32'),(1014,'Hà Tĩnh','Hà Tĩnh is a province in central Vietnam, known for its historical sites, ancient pagodas, and stunning beaches. It is an up-and-coming destination for eco-tourism and cultural exploration.',18.33860000,105.90390000,1,NULL,'2024-11-08 11:35:32','2024-11-08 11:35:32'),(1015,'Thái Bình','Thái Bình is located in the Red River Delta, known for its agriculture, particularly rice and aquatic products. The province also features charming rural landscapes and peaceful villages.',20.44670000,106.32230000,1,NULL,'2024-11-08 11:35:32','2024-11-08 11:35:32'),(1016,'An Giang','An Giang is a province in the Mekong Delta, famous for its natural beauty, including rivers, lakes, and waterfalls. The region is rich in culture, especially its Khmer influence.',10.39430000,105.45530000,2,NULL,'2024-11-08 11:35:32','2024-11-08 11:35:32'),(1017,'Lào Cai','Lào Cai is located in northern Vietnam, known for its mountainous terrain, ethnic diversity, and the famous Sa Pa town. It is a popular destination for trekking and cultural tourism.',22.32490000,103.84890000,1,NULL,'2024-11-08 11:35:32','2024-11-08 11:35:32'),(1018,'Nam Định','Nam Định is a province in northern Vietnam, known for its temples, traditional crafts, and agricultural produce. It is famous for its historical significance and rich culture.',20.40510000,106.16270000,1,NULL,'2024-11-08 11:35:32','2024-11-08 11:35:32'),(1019,'Bình Dương','Bình Dương is an industrial hub located in southern Vietnam. It offers modern urban development and is also home to several natural parks and cultural attractions.',10.98010000,106.61470000,2,NULL,'2024-11-08 11:35:32','2024-11-08 11:35:32'),(1020,'Ninh Thuận','Ninh Thuận is a coastal province with stunning beaches, ancient temples, and vineyards. It is a destination for eco-tourism, agriculture, and history enthusiasts.',11.58810000,108.92410000,2,NULL,'2024-11-08 11:35:32','2024-11-08 11:35:32'),(1022,'Quảng Trị','Quảng Trị is a province in central Vietnam, known for its historical sites, including the Battle of Quảng Trị, as well as its rich cultural heritage and beautiful coastal landscapes.',16.77470000,106.67510000,3,NULL,'2024-11-08 11:35:32','2024-11-08 11:35:32'),(1023,'Bắc Ninh','Bắc Ninh is a province in northern Vietnam, famous for its ancient temples, traditional music, and cultural festivals. The region is rich in history and tradition.',21.18320000,106.07800000,1,NULL,'2024-11-08 11:35:32','2024-11-08 11:35:32'),(1024,'Vĩnh Long','Vĩnh Long is a province in the Mekong Delta, famous for its lush greenery, rivers, and tranquil environment. It is an ideal destination for eco-tourism and rural experiences.',10.25350000,105.97000000,2,NULL,'2024-11-08 11:35:32','2024-11-08 11:35:32'),(1025,'Yên Bái','Yên Bái is located in the northern mountains, known for its stunning landscapes, ethnic diversity, and unique culture. The region is also famous for terraced rice fields and traditional festivals.',21.71810000,104.93050000,1,NULL,'2024-11-08 11:35:32','2024-11-08 11:35:32'),(1026,'Đồng Tháp','Đồng Tháp is a province in the Mekong Delta, famous for its waterways, lotus flowers, and eco-tourism attractions. It is also known for its local cuisine and vibrant culture.',10.30470000,105.59530000,2,NULL,'2024-11-08 11:35:32','2024-11-08 11:35:32'),(1027,'Gia Lai','Gia Lai is a province in the Central Highlands, known for its ethnic minorities, coffee plantations, and rich culture. The region is famous for its waterfalls and scenic landscapes.',13.90920000,108.06410000,3,NULL,'2024-11-08 11:35:32','2024-11-08 11:35:32'),(1028,'Bắc Kạn','Bắc Kạn is a mountainous province in northern Vietnam, known for its beautiful lakes, waterfalls, and peaceful villages. The region is a paradise for nature lovers and eco-tourism.',22.15310000,105.86190000,1,NULL,'2024-11-08 11:35:32','2024-11-08 11:35:32'),(1029,'Thái Nguyên','Thái Nguyên is located in northern Vietnam, famous for its tea plantations, natural landscapes, and rich cultural heritage. The region is known for its peaceful atmosphere and historical significance.',21.59490000,105.21300000,1,NULL,'2024-11-08 11:35:32','2024-11-08 11:35:32'),(1030,'Đăk Lăk','Đăk Lăk is a province in the Central Highlands, known for its coffee, unique culture, and beautiful landscapes. It is also famous for its traditional longhouses and ethnic groups.',12.69200000,108.15000000,3,NULL,'2024-11-08 11:35:32','2024-11-08 11:35:32'),(1031,'Quảng Nam','Quảng Nam is a coastal province known for its beaches, historical sites like Hội An, and rich cultural traditions. It is a popular destination for tourists who love both nature and history.',15.98000000,108.19190000,3,NULL,'2024-11-08 11:35:32','2024-11-08 11:35:32'),(1032,'Quảng Ninh','Quảng Ninh is a province in northeastern Vietnam, famous for its UNESCO-listed Ha Long Bay, beautiful beaches, and rich history. The region is a paradise for nature lovers and travelers.',21.03330000,107.08330000,1,NULL,'2024-11-08 11:35:32','2024-11-08 11:35:32'),(1033,'Hòa Bình','Hòa Bình is a mountainous province in northern Vietnam, known for its lush landscapes, waterfalls, and rich ethnic diversity. It offers opportunities for eco-tourism and adventure travel.',20.93300000,105.35920000,1,NULL,'2024-11-08 11:35:32','2024-11-08 11:35:32'),(1034,'Vũng Tàu','Vũng Tàu is a coastal city known for its beautiful beaches, vibrant atmosphere, and proximity to Ho Chi Minh City. It is a popular weekend getaway destination for both locals and tourists.',10.38800000,107.07830000,2,NULL,'2024-11-08 11:35:32','2024-11-08 11:35:32'),(1035,'Quảng Bình','Quảng Bình is known for its stunning caves, such as Phong Nha-Kẻ Bàng National Park. It is a destination for adventure lovers and those interested in Vietnam’s natural beauty.',17.42910000,106.35630000,2,NULL,'2024-11-08 11:35:32','2024-11-08 11:35:32'),(1036,'Hậu Giang','Hậu Giang is a province in the Mekong Delta, known for its tranquil rivers, floating markets, and agricultural activities. It is a peaceful destination for eco-tourism and cultural exploration.',9.74910000,105.40910000,2,NULL,'2024-11-08 11:35:32','2024-11-08 11:35:32'),(1037,'Thừa Thiên Huế','Thừa Thiên Huế is a province in central Vietnam, known for its imperial history, ancient citadels, and beautiful rivers. It is a UNESCO World Heritage site and a cultural hub of Vietnam.',16.46770000,107.58900000,3,NULL,'2024-11-08 11:35:32','2024-11-08 11:35:32'),(1038,'Phú Thọ','Phú Thọ is known as the cradle of Vietnamese culture, famous for the Hùng Kings and their historic temple complex. The province also boasts stunning landscapes and traditional festivals.',21.16400000,105.27400000,1,NULL,'2024-11-08 11:35:32','2024-11-08 11:35:32'),(1039,'Hải Dương','Hải Dương is a province in northern Vietnam, famous for its historical sites, including the famous Côn Sơn Temple. The region is rich in cultural traditions and agricultural heritage.',20.91420000,106.32000000,1,NULL,'2024-11-08 11:35:32','2024-11-08 11:35:32'),(1040,'Ninh Bình','Ninh Bình is known for its stunning karst landscape, ancient temples, and tranquil rivers. It is home to the famous Trang An Scenic Landscape Complex and the ancient capital of Hoa Lư.',20.25120000,105.97160000,1,NULL,'2024-11-08 11:35:32','2024-11-08 11:35:32'),(1042,'Tiền Giang','Tiền Giang is a province in the Mekong Delta, known for its beautiful rivers, fruit orchards, and floating markets. It is a destination for eco-tourism and exploring traditional rural life.',10.45230000,106.34070000,2,NULL,'2024-11-08 11:35:32','2024-11-08 11:35:32'),(1043,'Cà Mau','Cà Mau is the southernmost province of Vietnam, famous for its coastal mangrove forests, wildlife sanctuaries, and tranquil rivers. It is a haven for nature lovers and eco-tourists.',9.17500000,105.15000000,2,NULL,'2024-11-08 11:35:32','2024-11-08 11:35:32'),(1044,'Hồ Chí Minh','Hồ Chí Minh City, formerly known as Saigon, is the largest city in Vietnam. It is a dynamic hub for commerce, culture, and history, blending modernity with historical landmarks.',10.82310000,106.62970000,2,NULL,'2024-11-08 11:35:32','2024-11-08 11:35:32'),(1045,'Khánh Hòa','Khánh Hòa is a coastal province famous for its beautiful beaches, warm climate, and the vibrant tourism industry. Nha Trang, the capital, is a well-known beach destination.',12.22920000,109.19670000,3,NULL,'2024-11-08 11:35:32','2024-11-08 11:35:32'),(1046,'Kiên Giang','Kiên Giang is a coastal province in southern Vietnam, home to stunning islands, pristine beaches, and rich cultural heritage. Phú Quốc Island, in particular, is a tropical paradise.',10.00890000,105.50000000,2,NULL,'2024-11-08 11:35:32','2024-11-08 11:35:32'),(1047,'Sơn La','Sơn La is a mountainous province in northern Vietnam, known for its breathtaking landscapes, ethnic diversity, and tranquil environment. It is famous for tea and fruit farming.',21.31440000,103.91250000,1,NULL,'2024-11-08 11:35:32','2024-11-08 11:35:32'),(1048,'Bình Định','Bình Định is known for its beautiful beaches, historical sites, and delicious seafood. It is a mix of natural beauty and rich cultural traditions, especially martial arts.',13.78240000,109.18750000,3,NULL,'2024-11-08 11:35:32','2024-11-08 11:35:32'),(1049,'Sóc Trăng','Sóc Trăng is located in the Mekong Delta, known for its rivers, traditional Khmer pagodas, and rich cultural festivals. It is also famous for its delicious local cuisine.',9.60420000,105.97420000,2,NULL,'2024-11-08 11:35:32','2024-11-08 11:35:32'),(1050,'Hà Nội','Hà Nội, the capital city of Vietnam, is rich in history and culture. It features ancient temples, vibrant street markets, and a blend of French colonial architecture and traditional Vietnamese charm.',21.02850000,105.85420000,1,NULL,'2024-11-08 11:35:32','2024-11-08 11:35:32'),(1051,'Lai Châu','Lai Châu is a mountainous province located in the northwest of Vietnam, known for its stunning landscapes, ethnic minority groups, and hiking trails in the Hoàng Liên Sơn mountain range.',22.38420000,103.56740000,1,NULL,'2024-11-08 11:35:32','2024-11-08 11:35:32'),(1052,'Cần Thơ','Cần Thơ is a city in the Mekong Delta, known for its lively floating markets, lush landscapes, and delicious regional cuisine. It is often called the “Western Capital” of Vietnam.',10.03000000,105.74600000,2,NULL,'2024-11-08 11:35:32','2024-11-08 11:35:32'),(1053,'Đồng Nai','Đồng Nai is an industrial province located in southern Vietnam, with a mix of natural beauty, including forests and rivers, and the modern urban environment of Bien Hoa City.',10.95430000,106.81550000,2,NULL,'2024-11-08 11:35:32','2024-11-08 11:35:32'),(1054,'Vĩnh Phúc','Vĩnh Phúc is a northern province known for its serene landscapes, historical sites, and close proximity to Hà Nội. It is an emerging tourist destination with a rich cultural heritage.',21.32340000,105.59250000,1,NULL,'2024-11-08 11:35:32','2024-11-08 11:35:32'),(1055,'Bắc Giang','Bắc Giang is located in the northern region of Vietnam and is famous for its lush forests, scenic mountains, and traditional villages. The region is known for producing high-quality lychees.',21.27190000,106.19430000,1,NULL,'2024-11-08 11:35:32','2024-11-08 11:35:32'),(1056,'Hải Phòng','Hải Phòng is a port city in northern Vietnam, offering a mix of modern development and natural beauty. It is known for its beaches, food, and the historical Cat Ba Island.',20.84490000,106.68820000,1,NULL,'2024-11-08 11:35:32','2024-11-08 11:35:32'),(1057,'Tây Ninh','Tây Ninh is a province in southern Vietnam, known for the famous Ba Na Hills, its sacred Cao Dai Temple, and natural attractions such as Nui Ba Den Mountain.',11.32250000,106.11940000,2,NULL,'2024-11-08 11:35:32','2024-11-08 11:35:32'),(1058,'Đà Nẵng','Đà Nẵng is a coastal city known for its stunning beaches, modern architecture, and vibrant nightlife. It is a gateway to the cultural heritage of the ancient town of Hội An and the imperial city of Huế.',16.04700000,108.20690000,3,NULL,'2024-11-08 11:35:32','2024-11-08 11:35:32'),(1059,'Lạng Sơn','Lạng Sơn is a border province in northern Vietnam, known for its mountainous landscapes, border markets, and rich cultural heritage from various ethnic groups.',21.86100000,106.60100000,1,NULL,'2024-11-08 11:35:32','2024-11-08 11:35:32'),(1060,'Bình Phước','Bình Phước is a southern province known for its agriculture, particularly cashews and rubber. It is also home to several ethnic minorities and has a number of natural parks and forests.',11.49850000,106.60360000,2,NULL,'2024-11-08 11:35:32','2024-11-08 11:35:32'),(1061,'Lâm Đồng','Lâm Đồng is known for its cool climate and beautiful landscapes, particularly around the city of Đà Lạt, which is famous for its flowers, lakes, and French colonial architecture.',11.93800000,108.44390000,2,NULL,'2024-11-08 11:35:32','2024-11-08 11:35:32'),(1062,'Hà Nam','Hà Nam is a province located in the Red River Delta, known for its peaceful rural landscapes, rich history, and proximity to Hà Nội. It is home to several cultural and historical sites.',20.58960000,105.93050000,1,NULL,'2024-11-08 11:35:32','2024-11-08 11:35:32'),(1063,'Bình Thuận','Bình Thuận is a coastal province in southern Vietnam, famous for its beautiful beaches, sand dunes, and fishing villages. It is also known for its fresh seafood and pleasant climate.',10.93930000,108.13500000,2,NULL,'2024-11-08 11:35:32','2024-11-08 11:35:32'),(1064,'Hà Giang','Hà Giang is a province in northern Vietnam known for its dramatic landscapes, including terraced fields, high mountains, and ethnic minority cultures. It is a must-see destination for nature lovers and adventurers.',22.79600000,104.98900000,1,NULL,'2024-11-08 11:35:32','2024-11-08 11:35:32'),(1065,'Nghệ An','Nghệ An is a province in central Vietnam, famous for its beaches, historical sites, and the homeland of the late president Hồ Chí Minh. The region is known for its traditional festivals and natural beauty.',18.68110000,105.67410000,1,NULL,'2024-11-08 11:35:32','2024-11-08 11:35:32'),(1066,'Phú Yên','Phú Yên is a coastal province in central Vietnam, known for its untouched beaches, fresh seafood, and the famous Gành Đá Dĩa (stone plate reef). The region is also gaining popularity as a tourism destination.',13.07710000,109.31980000,3,NULL,'2024-11-08 11:35:32','2024-11-08 11:35:32'),(1067,'Cao Bằng','Cao Bằng is a province in northern Vietnam, famous for its stunning natural landscapes, including waterfalls, mountains, and the famous Pac Bo Cave, linked to the revolutionary history of Vietnam.',22.66200000,106.28820000,1,NULL,'2024-11-08 11:35:32','2024-11-08 11:35:32'),(1068,'Điện Biên','Điện Biên is a province in northwestern Vietnam, known for the historical site of the Battle of Điện Biên Phủ, which ended the French colonial rule. It is also known for its ethnic diversity and beautiful mountainous landscapes.',21.50400000,103.01350000,1,NULL,'2024-11-08 11:35:32','2024-11-08 11:35:32'),(1682,'Bạc Liêu','Bạc Liêu is known for its rich history, beautiful landscapes, and being one of the largest producers of seafood in Vietnam. A peaceful place with a charming atmosphere.',9.28750000,105.71050000,2,NULL,'2024-11-08 11:35:32','2024-11-08 11:35:32'),(1683,'Bến Tre','Bến Tre is the \"Land of Coconut\" in the Mekong Delta, famous for its coconut trees, peaceful rivers, and rich local culture. It is a destination for eco-tourism and cultural exploration.',10.24310000,106.37430000,2,NULL,'2024-11-08 11:35:32','2024-11-08 11:35:32');
/*!40000 ALTER TABLE `location` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `notification`
--

DROP TABLE IF EXISTS `notification`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `notification` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `message` varchar(255) DEFAULT NULL,
  `status` bit(1) DEFAULT NULL,
  `time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notification`
--

LOCK TABLES `notification` WRITE;
/*!40000 ALTER TABLE `notification` DISABLE KEYS */;
INSERT INTO `notification` VALUES (1,'user1 đã đặt chỗ (1)',_binary '\0','2024-11-12 03:15:38'),(2,'user1 đã đặt chỗ (2)',_binary '\0','2024-11-12 04:17:08'),(3,'user1 đã đặt chỗ (3)',_binary '\0','2024-11-12 04:18:29'),(4,'user1 đã đặt chỗ (4)',_binary '\0','2024-11-12 07:04:24');
/*!40000 ALTER TABLE `notification` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rates`
--

DROP TABLE IF EXISTS `rates`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `rates` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `comment` varchar(255) DEFAULT NULL,
  `rate_date` datetime DEFAULT NULL,
  `rating` double DEFAULT NULL,
  `book_detail_id` bigint DEFAULT NULL,
  `tour_id` bigint DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `tour_id` (`tour_id`),
  KEY `user_id` (`user_id`),
  KEY `book_detail_id` (`book_detail_id`),
  CONSTRAINT `rates_ibfk_1` FOREIGN KEY (`tour_id`) REFERENCES `tours` (`tour_id`),
  CONSTRAINT `rates_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`),
  CONSTRAINT `rates_ibfk_3` FOREIGN KEY (`book_detail_id`) REFERENCES `book_details` (`book_detail_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rates`
--

LOCK TABLES `rates` WRITE;
/*!40000 ALTER TABLE `rates` DISABLE KEYS */;
INSERT INTO `rates` VALUES (1,'ok','2024-11-12 08:30:42',5,7,1,2);
/*!40000 ALTER TABLE `rates` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tour_categories`
--

DROP TABLE IF EXISTS `tour_categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tour_categories` (
  `tour_id` bigint NOT NULL,
  `category_id` bigint NOT NULL,
  PRIMARY KEY (`tour_id`,`category_id`),
  KEY `category_id` (`category_id`),
  CONSTRAINT `tour_categories_ibfk_1` FOREIGN KEY (`tour_id`) REFERENCES `tours` (`tour_id`),
  CONSTRAINT `tour_categories_ibfk_2` FOREIGN KEY (`category_id`) REFERENCES `categories` (`category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tour_categories`
--

LOCK TABLES `tour_categories` WRITE;
/*!40000 ALTER TABLE `tour_categories` DISABLE KEYS */;
INSERT INTO `tour_categories` VALUES (1,1),(2,1),(4,1),(6,1),(22,2),(12,3),(6,4),(12,4),(22,4),(1,5),(2,5),(4,5);
/*!40000 ALTER TABLE `tour_categories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tours`
--

DROP TABLE IF EXISTS `tours`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tours` (
  `tour_id` bigint NOT NULL AUTO_INCREMENT,
  `description` varchar(1000) DEFAULT NULL,
  `discount` int NOT NULL,
  `entered_date` date DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `quantity` int NOT NULL,
  `sold` int NOT NULL,
  `status` bit(1) DEFAULT NULL,
  `duration` int NOT NULL,
  `category_id` bigint DEFAULT NULL,
  `location_id` bigint DEFAULT NULL,
  `time_id` int DEFAULT NULL,
  PRIMARY KEY (`tour_id`),
  KEY `category_id` (`category_id`),
  KEY `location_id` (`location_id`),
  CONSTRAINT `tours_ibfk_1` FOREIGN KEY (`category_id`) REFERENCES `categories` (`category_id`),
  CONSTRAINT `tours_ibfk_2` FOREIGN KEY (`location_id`) REFERENCES `location` (`location_id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tours`
--

LOCK TABLES `tours` WRITE;
/*!40000 ALTER TABLE `tours` DISABLE KEYS */;
INSERT INTO `tours` VALUES (1,'Tam Đảo, located in Vĩnh Phúc Province, is a popular hill station known for its cool climate, misty mountains, and beautiful landscapes. Famous for attractions like the Tam Đảo National Park and the ancient church.',10,'2024-11-06','https://res.cloudinary.com/shopdemo/image/upload/v1731086011/prtr3ksa7dopmd8qijpr.jpg','Tam Đảo Tour',5000,26,9,_binary '',1,1,1054,20241106),(2,'Sapa, a beautiful town in Lào Cai, offers stunning views of terraced rice fields, lush valleys, and traditional ethnic villages. It is a top destination for trekking and cultural exploration.',15,'2024-11-06','https://res.cloudinary.com/shopdemo/image/upload/v1730965730/aoc6zkozdebynuysi2ol','Sapa Tour',6500,25,7,_binary '',1,1,1017,20241106),(3,'Ninh Bình, known for its breathtaking natural beauty, offers attractions like the Trang An Scenic Landscape Complex, Tam Coc, and Bai Dinh Pagoda. It is a peaceful escape with dramatic limestone landscapes.',20,'2024-11-06','https://res.cloudinary.com/shopdemo/image/upload/v1730965730/gp5owqck2i3kgekr6kic','Ninh Bình Tour',4000,40,10,_binary '',1,1,1040,20241106),(4,'Mộc Châu, a popular destination in Lào Cai Province, is known for its beautiful flower fields, lush green valleys, and traditional ethnic minority villages. It is a perfect getaway for nature lovers.',18,'2024-11-06','https://res.cloudinary.com/shopdemo/image/upload/v1730965730/ay0tzbzcxjesk1nebq6n','Mộc Châu Tour',5500,30,8,_binary '',1,1,1017,20241106),(5,'Hà Nội, the capital city of Vietnam, is known for its rich history, colonial architecture, and vibrant street life. Famous for landmarks such as the Hoan Kiem Lake, Ho Chi Minh Mausoleum, and Old Quarter.',25,'2024-11-06','https://res.cloudinary.com/shopdemo/image/upload/v1730965730/j6yxq1w9fu55wozbej7o','Hà Nội City Tour',3000,50,20,_binary '',1,1,1050,20241106),(6,'Hạ Long Bay, a UNESCO World Heritage Site, is famous for its emerald waters and thousands of towering limestone islands topped with rainforests. It is one of Vietnam\'s most popular tourist destinations.',30,'2024-11-06','https://res.cloudinary.com/shopdemo/image/upload/v1730965730/a8ck0i0rpvuwotpvbcag','Hạ Long Bay Tour',7000,30,15,_binary '',1,1,1032,20241106),(7,'Hà Giang, located in northern Vietnam, offers magnificent landscapes, including mountains, valleys, and terraced fields. Famous for its Dong Van Karst Plateau Geopark and ethnic minority villages.',22,'2024-11-06','https://res.cloudinary.com/shopdemo/image/upload/v1730965730/z9bf3epakohwpmqxnyto','Hà Giang Tour',6000,35,12,_binary '',1,1,1064,20241106),(8,'Ba Vì, located near Hà Nội, is famous for its cool climate, Ba Vì National Park, and historical sites like the old French church. It offers scenic hiking trails and peaceful nature.',12,'2024-11-06','https://res.cloudinary.com/shopdemo/image/upload/v1730965730/dvr9zp6on1v5b9q3dhnv','Ba Vì Tour',4500,20,5,_binary '',1,1,1050,20241106),(9,'Cần Thơ is known as the heart of the Mekong Delta, offering a vibrant floating market culture, lush rice paddies, and riverside charm. Highlights include the Cai Rang Floating Market and Ninh Kieu Wharf.',15,'2024-11-06','https://res.cloudinary.com/shopdemo/image/upload/v1730965730/dhfyytprkvtwjvysktpt','Cần Thơ Tour',4500,25,10,_binary '',3,3,1052,20241106),(10,'Đồng Nai, located near Ho Chi Minh City, is known for its natural parks and historical sites. Famous attractions include Cat Tien National Park and the scenic Tri An Lake.',10,'2024-11-06','https://res.cloudinary.com/shopdemo/image/upload/v1730965730/wliwbqnclbmtmpks62v7','Đồng Nai Adventure',3800,18,8,_binary '',3,3,1053,20241106),(11,'Hồ Chí Minh City, Vietnam\'s bustling metropolis, is famous for its vibrant culture, colonial architecture, and historical sites. Key attractions include the Notre-Dame Cathedral Basilica and the War Remnants Museum.',20,'2024-11-06','https://res.cloudinary.com/shopdemo/image/upload/v1730965730/tdhu5dtu4upljdc9renj','Hồ Chí Minh City Tour',6000,50,25,_binary '',3,3,1044,20241106),(12,'Phú Quốc, a tropical paradise in Kiên Giang, is known for its white sandy beaches, clear waters, and coral reefs. Popular destinations include Sao Beach, the Phú Quốc National Park, and Dinh Cau Night Market.',25,'2024-11-06','https://res.cloudinary.com/shopdemo/image/upload/v1730965730/ubsaw8hibklwjozcslja','Phú Quốc Island Tour',8000,30,12,_binary '',3,3,1046,20241106),(13,'Sóc Trăng is celebrated for its cultural diversity, especially its Khmer community and pagodas. Attractions include the Clay Pagoda and Bat Pagoda, offering a unique cultural experience.',12,'2024-11-06','https://res.cloudinary.com/shopdemo/image/upload/v1730965730/tde6pckt4eig18mrxd9v','Sóc Trăng Cultural Tour',3500,15,4,_binary '',3,3,1049,20241106),(14,'Tây Ninh, known for the Cao Dai Temple, is a spiritual center and also offers scenic views from Ba Den Mountain. It is a significant pilgrimage site with a unique religious heritage.',18,'2024-11-06','https://res.cloudinary.com/shopdemo/image/upload/v1730965730/f1z01zuld6pr43uzygg1','Tây Ninh Spiritual Tour',4000,16,12,_binary '',3,3,1057,20241106),(21,'testurl',10,'2024-11-08','https://res.cloudinary.com/shopdemo/image/upload/v1731086011/prtr3ksa7dopmd8qijpr.jpg','testurl',10000,1,0,_binary '',0,1,NULL,0),(22,'Tuyệt vời! Để tạo một description hấp dẫn cho tour du lịch Đà Nẵng, mình xin gợi ý một vài mẫu dưới đây. Bạn có thể tùy chỉnh để phù hợp với tour của mình nhé:\n\nMẫu 1: Nhấn mạnh vẻ đẹp tự nhiên\n\n\"Khám phá Đà Nẵng - Thành phố biển xinh đẹp\n\nĐắm mình trong không gian biển xanh cát trắng tại bãi biển Mỹ Khê nổi tiếng. Chiêm ngưỡng vẻ đẹp hùng vĩ của Ngũ Hành Sơn và khám phá hệ thống hang động kỳ bí. Thưởng thức không khí trong lành tại Bà Nà Hills, lạc vào thế giới cổ tích Pháp với những lâu đài tráng lệ. Đà Nẵng sẽ mang đến cho bạn những trải nghiệm khó quên!',20,'2024-11-12','https://res.cloudinary.com/shopdemo/image/upload/v1730965730/qmgznr7tzwrsikr6xdgw','Đà Nẵng',10000,100,50,_binary '',7,2,NULL,0),(23,'Nha Trang - Thiên đường biển nhiệt đới. Đắm mình trong làn nước trong xanh, cát trắng mịn màng tại những bãi biển đẹp nhất Việt Nam. Khám phá quần đảo xinh đẹp với hàng trăm hòn đảo lớn nhỏ, lặn ngắm san hô, câu cá, hoặc đơn giản là thư giãn trên những bãi cát hoang sơ. Nha Trang sẽ mang đến cho bạn những trải nghiệm biển đảo tuyệt vời nhất!',30,'2024-11-12','https://res.cloudinary.com/shopdemo/image/upload/v1730965730/vtlhqdbpip6dig5wvod5','Nha Trang',20000,100,60,_binary '',7,2,NULL,0);
/*!40000 ALTER TABLE `tours` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tourstatus`
--

DROP TABLE IF EXISTS `tourstatus`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tourstatus` (
  `status_id` bigint NOT NULL AUTO_INCREMENT,
  `tour_id` bigint NOT NULL,
  `from_date` date NOT NULL,
  `to_date` date NOT NULL,
  `status` tinyint(1) NOT NULL,
  PRIMARY KEY (`status_id`),
  KEY `tour_id` (`tour_id`),
  CONSTRAINT `tourstatus_ibfk_1` FOREIGN KEY (`tour_id`) REFERENCES `tours` (`tour_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tourstatus`
--

LOCK TABLES `tourstatus` WRITE;
/*!40000 ALTER TABLE `tourstatus` DISABLE KEYS */;
INSERT INTO `tourstatus` VALUES (1,1,'2024-11-29','2024-11-30',0);
/*!40000 ALTER TABLE `tourstatus` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_roles`
--

DROP TABLE IF EXISTS `user_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_roles` (
  `user_id` bigint NOT NULL,
  `role_id` int NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `FKihg20vygk8qb8lw0s573lqsmq` (`role_id`),
  CONSTRAINT `FKhfh9dx7w3ubf1co1vdev94g3f` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`),
  CONSTRAINT `FKihg20vygk8qb8lw0s573lqsmq` FOREIGN KEY (`role_id`) REFERENCES `app_roles` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_roles`
--

LOCK TABLES `user_roles` WRITE;
/*!40000 ALTER TABLE `user_roles` DISABLE KEYS */;
INSERT INTO `user_roles` VALUES (2,1),(1,2);
/*!40000 ALTER TABLE `user_roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `user_id` bigint NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `gender` bit(1) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `register_date` date DEFAULT NULL,
  `status` bit(1) DEFAULT NULL,
  `token` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'HN','admin@gmail.com',_binary '','https://res.cloudinary.com/veggie-shop/image/upload/v1633795994/users/mnoryxp056ohm0b4gcrj.png','phamngocdung','$2a$10$5ttfZi8Bu4Rx4P0ryq/Gc.znrmAP1MqFaeH6K8FPaU4fnzTViLtYW','0912345678','2024-11-05',_binary '','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIyMDEwLm5nb2NkdW5nQGdtYWlsLmNvbSIsInNjb3BlcyI6W3siYXV0aG9yaXR5IjoiUk9MRV9BRE1JTiJ9XSwiaXNzIjoiaHR0cDovL2RldmdsYW4uY29tIiwiaWF0IjoxNzMwNzczNjYxLCJleHAiOjE3MzA3OTE2NjF9.v6Nc26TNpKzBVBNF1JnZIXuxJgNrF7omhBb9rHjO_xk'),(2,'123 Main Street, City, Country','user1@gmail.com',_binary '','https://img.freepik.com/free-psd/3d-illustration-person-with-sunglasses_23-2149436188.jpg','user1','$2a$10$UP/eITdhlcWv9qUKAh2mle0f7fVjZPREZ0GMosn4xvoqqoYz2nxOa','09123456789','2024-11-11',_binary '','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIyMDEwLm5nb2NkdW5nQGdtYWlsLmNvbSIsInNjb3BlcyI6W3siYXV0aG9yaXR5IjoiUk9MRV9BRE1JTiJ9XSwiaXNzIjoiaHR0cDovL2RldmdsYW4uY29tIiwiaWF0IjoxNzMxMzMxNjQ2LCJleHAiOjE3MzEzNDk2NDZ9._iSW_mR8XjYYLZiCzPaIeUjJdeQWHz9KUAj5t-lhW74');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'newtourdatabase'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-11-12 17:43:11

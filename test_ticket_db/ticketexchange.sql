-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: localhost    Database: ticketexchange
-- ------------------------------------------------------
-- Server version	8.0.28

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
-- Table structure for table `accounts`
--

DROP TABLE IF EXISTS `accounts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `accounts` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `password` varchar(200) NOT NULL,
  `email` varchar(60) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `accounts`
--

LOCK TABLES `accounts` WRITE;
/*!40000 ALTER TABLE `accounts` DISABLE KEYS */;
INSERT INTO `accounts` VALUES (7,'Test2','$2a$10$21oYNk69BqRe/grUX/DYSuXpUmZx4OUXzRk9U39MHuUDArUYH0WPm','test2@gmail.com'),(8,'DemoAccount','$2a$10$p74mvy9p/pIAmzWehd4oMejiisDgLy3Gd0v6N5.sAIpVm1P/DzB2y','demo@gmail.com'),(9,'xX_AlphaChaos_Xx','$2a$10$1X6oaeB5FX71J0BB42Z2wu3PwZFQkuL.hopmA/40MLGmjjJfAouDS','chaoz@gmail.com'),(10,'Croissant','$2a$10$ggAkNSaiW1FBVRjPD5/eS.XScylCAjAZu1nuuXO7RNPCfFJNOmYWa','croissant@baguette.com'),(12,'admin','$2a$10$UAAnPkaMbLSbZa8J.nDYe.a1twN5LeZ24X4mBF.A79pP5xQQhPcma','admin@gmail.com'),(13,'rogergorissen','$2a$10$MR49zb4oF.APRQzFuJNC1.YtHha3W1jtbi0spO9Pa8MA1LGpzAtTq','roger.gorissen@home.nl'),(14,'test3','$2a$10$90tp3p3RiYUYXzI.0N.0Vu6ssVNvey.bSVVrHZBRxUqNMqYYLYhJ2','test3@gmail.com'),(18,'ebel','$2a$10$3IqYwKL4Ae7sDCnLZ9.cr.2ftwIsEOI7tvBQSlW6/.6D5WOcUXihy','ebel.gorissen@home.nl'),(19,'Ambi','$2a$10$wKrbkRrZ5MV.tMj6Sl1lIOVSXkMo.AFSSatmqb2WVVxe6QR2d1Isa','yannick.gorissen@home.nl'),(20,'IBuyManyTickets','$2a$10$pkfvqWMiMiWOj2Y2RWfeGeUrLLj90pgS5RQOsbWc9HcMnT6vVJcvW','tickets@gmail.com'),(21,'UXAccount','$2a$10$PiPqb9BnnVpJErhEdlTN9u.frQOau3Oq.wqS5LavTKIPHUUCo57cu','UXrep@gmail.com');
/*!40000 ALTER TABLE `accounts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `events`
--

DROP TABLE IF EXISTS `events`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `events` (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(50) NOT NULL,
  `location` varchar(60) NOT NULL,
  `moment` datetime NOT NULL,
  `total_tickets` int NOT NULL,
  `remaining_tickets` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `title` (`title`),
  UNIQUE KEY `timeLocationClause` (`location`,`moment`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `events`
--

LOCK TABLES `events` WRITE;
/*!40000 ALTER TABLE `events` DISABLE KEYS */;
INSERT INTO `events` VALUES (1,'Heilung concert','013 Tilburg','2023-01-12 19:00:00',100,96),(2,'Concert at Sea','Zeeland','2023-06-18 22:00:00',200,157),(3,'Sabaton concert','TivoliVredenburg Utrecht','2023-12-18 18:00:00',100,100),(4,'Motionless In White world tour.','Ziggo dome','2023-08-15 21:30:00',500,500),(5,'Wardruna northern Europe tour','Ekeberg Oslo','2023-06-24 22:00:00',200,200),(6,'Heilung','Gelsenkirchen','2023-07-30 21:00:00',200,200),(8,'Heilung Switzerland','Konzertfabrik 27, Pratteln','2023-08-20 22:00:00',200,200),(9,'Korpiklaani Suomi','Helsinki','2023-08-20 22:00:00',200,200),(10,'Bruce Springsteen','Landgraaf','2023-06-11 19:45:00',3500,3496),(11,'Skáld','TivoliVredenburg Utrecht','2023-06-11 20:00:00',150,150),(12,'Eminem','Campus Rachelsmolen 1, Eindhoven','2023-06-10 22:00:00',100,100),(13,'Iron maiden','Campus Rachelsmolen 1, Eindhoven','2023-06-11 22:00:00',100,100),(14,'Valravn','Copenhagen','2023-06-16 18:00:00',50,50),(15,'Heilung + Wardruna','Ziggo dome','2023-06-16 18:00:00',100,100),(17,'Imagine Dragons','Bristol','2023-06-24 16:30:00',3000,3000),(18,'Metallica Megafest','Moscow Red Square','2023-06-23 15:00:00',99999,99999),(19,'Bruce Springsteen world tour','Landgraaf','2023-06-11 17:00:00',3500,3500),(20,'Danheim debut','Musikkhalle Stockholm','2024-03-13 19:00:00',100,100),(21,'Sowulo','Amsterdam ArenA','2023-06-27 13:15:00',50,50),(22,'The Hu','Copenhagen','2023-06-27 13:15:00',120,120),(23,'The Weekend','Amsterdam ArenA','2023-06-23 21:00:00',55000,55000),(24,'Reggae Festival','Rotterdam Ahoy','2023-07-29 13:00:00',30000,30000),(25,'Pinkpop 2023 Friday','Landgraaf','2023-06-16 13:00:00',70000,70000),(26,'Pinkpop 2023 Saturday','Landgraaf','2023-06-17 13:00:00',70000,69996),(27,'Pinkpop 2023 Sunday','Landgraaf','2023-06-18 13:00:00',70000,69995),(28,'Castlefest','Keukenhof, Amsterdam','2023-08-03 18:00:00',2000,2000),(29,'Harper','Bristol, UK','2023-06-30 15:30:00',50,50),(30,'Iron Maiden Trooper Tour','013 Tilburg','2023-08-15 18:30:00',200,195),(31,'Wrong Direction','Stratum','2023-06-28 20:00:00',140,138);
/*!40000 ALTER TABLE `events` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `flyway_schema_history`
--

DROP TABLE IF EXISTS `flyway_schema_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `flyway_schema_history` (
  `installed_rank` int NOT NULL,
  `version` varchar(50) DEFAULT NULL,
  `description` varchar(200) NOT NULL,
  `type` varchar(20) NOT NULL,
  `script` varchar(1000) NOT NULL,
  `checksum` int DEFAULT NULL,
  `installed_by` varchar(100) NOT NULL,
  `installed_on` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `execution_time` int NOT NULL,
  `success` tinyint(1) NOT NULL,
  PRIMARY KEY (`installed_rank`),
  KEY `flyway_schema_history_s_idx` (`success`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `flyway_schema_history`
--

LOCK TABLES `flyway_schema_history` WRITE;
/*!40000 ALTER TABLE `flyway_schema_history` DISABLE KEYS */;
INSERT INTO `flyway_schema_history` VALUES (1,'1','createBasicEntityTables','SQL','V1__createBasicEntityTables.sql',-1519715541,'root','2023-05-22 09:58:09',75,1),(2,'2','UserRoles','SQL','V2__UserRoles.sql',553288924,'root','2023-05-29 14:42:25',77,1);
/*!40000 ALTER TABLE `flyway_schema_history` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tickets`
--

DROP TABLE IF EXISTS `tickets`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tickets` (
  `id` int NOT NULL AUTO_INCREMENT,
  `event` int NOT NULL,
  `account_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `account_id` (`account_id`),
  KEY `event` (`event`),
  CONSTRAINT `tickets_ibfk_1` FOREIGN KEY (`account_id`) REFERENCES `accounts` (`id`),
  CONSTRAINT `tickets_ibfk_2` FOREIGN KEY (`event`) REFERENCES `events` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=65 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tickets`
--

LOCK TABLES `tickets` WRITE;
/*!40000 ALTER TABLE `tickets` DISABLE KEYS */;
INSERT INTO `tickets` VALUES (1,1,8),(2,1,19),(3,2,12),(4,27,12),(5,27,12),(6,27,12),(7,27,12),(8,27,12),(9,30,12),(10,30,12),(11,30,12),(12,30,12),(13,30,12),(14,31,19),(15,31,19),(16,2,12),(17,2,12),(18,2,12),(19,2,12),(20,2,12),(21,2,12),(22,2,12),(23,2,12),(24,2,12),(25,2,12),(26,2,12),(27,2,12),(28,2,12),(29,2,19),(30,2,19),(31,2,12),(32,2,12),(33,2,20),(34,2,20),(35,2,20),(36,2,20),(37,2,20),(38,2,20),(39,2,20),(40,2,20),(41,2,20),(42,2,20),(43,2,20),(44,2,20),(45,2,20),(46,2,20),(47,2,20),(48,2,20),(49,2,20),(50,2,20),(51,2,20),(52,2,20),(53,2,20),(54,2,20),(55,2,20),(56,2,20),(57,10,21),(58,10,21),(59,10,21),(60,10,21),(61,26,19),(62,26,19),(63,26,19),(64,26,19);
/*!40000 ALTER TABLE `tickets` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_role` (
  `id` int NOT NULL AUTO_INCREMENT,
  `account_id` int NOT NULL,
  `role_name` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `account_id` (`account_id`,`role_name`),
  CONSTRAINT `user_role_ibfk_1` FOREIGN KEY (`account_id`) REFERENCES `accounts` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role`
--

LOCK TABLES `user_role` WRITE;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` VALUES (1,7,'USER'),(2,8,'USER'),(3,9,'USER'),(4,10,'USER'),(6,12,'ADMIN'),(7,13,'USER'),(8,14,'USER'),(10,18,'USER'),(11,19,'USER'),(12,20,'USER'),(13,21,'USER');
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-06-18 12:32:55

CREATE DATABASE  IF NOT EXISTS `bookapi` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `bookapi`;
-- MySQL dump 10.13  Distrib 8.0.27, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: bookapi
-- ------------------------------------------------------
-- Server version	8.0.27

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `book`
--

DROP TABLE IF EXISTS `book`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `book` (
  `bookid` int NOT NULL AUTO_INCREMENT,
  `bookname` varchar(90) NOT NULL,
  `ISBN` varchar(45) NOT NULL,
  `genre` varchar(45) DEFAULT NULL,
  `quantity` int NOT NULL,
  `state` tinyint NOT NULL,
  PRIMARY KEY (`bookid`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `book`
--

LOCK TABLES `book` WRITE;
/*!40000 ALTER TABLE `book` DISABLE KEYS */;
INSERT INTO `book` VALUES (1,'Frankenstein','123456789','Terror',5,1),(2,'Learning Java SpringBoot','987654321','Education',10,1),(3,'Harry Potter','123123123','Fantasy',3,1),(4,'Romeo & Juliet','234234234','Romance',5,1),(5,'Differential Calculus II','543543543','Education',3,1),(8,'Libro de prueba','123456543','prueba xd',69,1);
/*!40000 ALTER TABLE `book` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `borrowedbooks`
--

DROP TABLE IF EXISTS `borrowedbooks`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `borrowedbooks` (
  `borrowedbooksid` int NOT NULL AUTO_INCREMENT,
  `expectedreturn_date` date NOT NULL,
  `borrow_date` date NOT NULL,
  `renewal_quantity` int DEFAULT NULL,
  `bookid` int NOT NULL,
  `userid` int NOT NULL,
  `returned` tinyint NOT NULL,
  `returned_date` date DEFAULT NULL,
  PRIMARY KEY (`borrowedbooksid`),
  KEY `bookid_idx` (`bookid`),
  KEY `userid_idx` (`userid`),
  CONSTRAINT `bookid` FOREIGN KEY (`bookid`) REFERENCES `book` (`bookid`),
  CONSTRAINT `userid` FOREIGN KEY (`userid`) REFERENCES `user` (`userid`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `borrowedbooks`
--

LOCK TABLES `borrowedbooks` WRITE;
/*!40000 ALTER TABLE `borrowedbooks` DISABLE KEYS */;
INSERT INTO `borrowedbooks` VALUES (1,'2021-11-28','2021-11-25',0,1,1,0,NULL),(2,'2021-11-30','2021-11-25',0,2,1,0,NULL),(4,'2021-12-01','2021-11-25',0,1,1,1,'2021-12-02');
/*!40000 ALTER TABLE `borrowedbooks` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ticket`
--

DROP TABLE IF EXISTS `ticket`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ticket` (
  `ticketid` int NOT NULL AUTO_INCREMENT,
  `total` double NOT NULL,
  `borrowedbooksid` int NOT NULL,
  PRIMARY KEY (`ticketid`),
  KEY `borrowedbooksid_idx` (`borrowedbooksid`),
  CONSTRAINT `borrowedbooksid` FOREIGN KEY (`borrowedbooksid`) REFERENCES `borrowedbooks` (`borrowedbooksid`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ticket`
--

LOCK TABLES `ticket` WRITE;
/*!40000 ALTER TABLE `ticket` DISABLE KEYS */;
INSERT INTO `ticket` VALUES (1,0.2,4),(2,0.2,4),(3,0.2,4),(6,2,4),(7,1,4),(8,4,4),(9,10,4);
/*!40000 ALTER TABLE `ticket` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `userid` int NOT NULL AUTO_INCREMENT,
  `first_name` varchar(45) NOT NULL,
  `last_name` varchar(45) NOT NULL,
  PRIMARY KEY (`userid`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'Axel','Hernandez'),(2,'Julio','Canizales'),(3,'Jackeline','Benitez'),(4,'Cristian','Padilla'),(5,'Susie','Aguilar'),(6,'Tania','Alas');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-11-25 12:22:27

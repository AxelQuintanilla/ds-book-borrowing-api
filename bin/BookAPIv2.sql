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
  `idBook` int NOT NULL AUTO_INCREMENT,
  `bookName` varchar(90) NOT NULL,
  `ISBN` varchar(45) NOT NULL,
  `genre` varchar(45) DEFAULT NULL,
  `quantity` int NOT NULL,
  `state` tinyint NOT NULL,
  PRIMARY KEY (`idBook`),
  UNIQUE KEY `ISBN_UNIQUE` (`ISBN`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `book`
--

LOCK TABLES `book` WRITE;
/*!40000 ALTER TABLE `book` DISABLE KEYS */;
INSERT INTO `book` VALUES (1,'Frankenstein','123456789','Terror',5,1),(2,'Java Programming','987654321','Education',10,1),(3,'Harry Potter','123123123','Fantasy',3,1),(4,'Romeo & Juliet','234234234','Romance',5,1),(5,'Differential Calculus I','543543543','Education',3,1);
/*!40000 ALTER TABLE `book` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `borrowedbooks`
--

DROP TABLE IF EXISTS `borrowedbooks`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `borrowedbooks` (
  `idBorrowedBooks` int NOT NULL AUTO_INCREMENT,
  `idUserClient` int NOT NULL,
  `returnDate` date NOT NULL,
  `borrowDate` date NOT NULL,
  `renewalQuantity` int DEFAULT NULL,
  `idBook` int NOT NULL,
  PRIMARY KEY (`idBorrowedBooks`),
  KEY `idUserClient_idx` (`idUserClient`),
  KEY `idBook_idx` (`idBook`),
  CONSTRAINT `idBook` FOREIGN KEY (`idBook`) REFERENCES `book` (`idBook`) ON UPDATE CASCADE,
  CONSTRAINT `idUserClient` FOREIGN KEY (`idUserClient`) REFERENCES `user` (`idUser`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `borrowedbooks`
--

LOCK TABLES `borrowedbooks` WRITE;
/*!40000 ALTER TABLE `borrowedbooks` DISABLE KEYS */;
/*!40000 ALTER TABLE `borrowedbooks` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `returnedbooks`
--

DROP TABLE IF EXISTS `returnedbooks`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `returnedbooks` (
  `idReturnedBooks` int NOT NULL AUTO_INCREMENT,
  `idBook2` int NOT NULL,
  `idUserClient2` int NOT NULL,
  `returnedDate` date NOT NULL,
  PRIMARY KEY (`idReturnedBooks`),
  KEY `idBook2_idx` (`idBook2`),
  KEY `idUserClient2_idx` (`idUserClient2`),
  CONSTRAINT `idBook2` FOREIGN KEY (`idBook2`) REFERENCES `book` (`idBook`),
  CONSTRAINT `idUserClient2` FOREIGN KEY (`idUserClient2`) REFERENCES `user` (`idUser`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `returnedbooks`
--

LOCK TABLES `returnedbooks` WRITE;
/*!40000 ALTER TABLE `returnedbooks` DISABLE KEYS */;
/*!40000 ALTER TABLE `returnedbooks` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ticket`
--

DROP TABLE IF EXISTS `ticket`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ticket` (
  `idTicket` int NOT NULL AUTO_INCREMENT,
  `idUserClient3` int NOT NULL,
  `total` double NOT NULL,
  `idBook3` int NOT NULL,
  PRIMARY KEY (`idTicket`),
  KEY `idBook3_idx` (`idBook3`),
  KEY `idUserClient3_idx` (`idUserClient3`),
  CONSTRAINT `idBook3` FOREIGN KEY (`idBook3`) REFERENCES `book` (`idBook`),
  CONSTRAINT `idUserClient3` FOREIGN KEY (`idUserClient3`) REFERENCES `user` (`idUser`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ticket`
--

LOCK TABLES `ticket` WRITE;
/*!40000 ALTER TABLE `ticket` DISABLE KEYS */;
/*!40000 ALTER TABLE `ticket` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `idUser` int NOT NULL AUTO_INCREMENT,
  `firstName` varchar(45) NOT NULL,
  `lastName` varchar(45) NOT NULL,
  PRIMARY KEY (`idUser`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'Axel','Hernandez'),(2,'Julio','Canizales'),(3,'Jackeline','Benitez'),(4,'Cristian','Padilla'),(5,'Susie','Aguilar');
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

-- Dump completed on 2021-11-23 11:11:50

-- MySQL dump 10.13  Distrib 8.0.33, for Win64 (x86_64)
--
-- Host: localhost    Database: batalla
-- ------------------------------------------------------
-- Server version	8.0.33

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
-- Table structure for table `accion`
--

DROP TABLE IF EXISTS `accion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `accion` (
  `idCarta` int unsigned NOT NULL AUTO_INCREMENT,
  `Ataque` int NOT NULL,
  `Defensa` int NOT NULL,
  `Repeticiones` int NOT NULL,
  PRIMARY KEY (`idCarta`),
  UNIQUE KEY `idCarta_UNIQUE` (`idCarta`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `accion`
--

LOCK TABLES `accion` WRITE;
/*!40000 ALTER TABLE `accion` DISABLE KEYS */;
INSERT INTO `accion` VALUES (1,3,0,1),(2,0,3,1);
/*!40000 ALTER TABLE `accion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `accion_tiene_efecto`
--

DROP TABLE IF EXISTS `accion_tiene_efecto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `accion_tiene_efecto` (
  `idAccion` int unsigned NOT NULL,
  `idEfecto` int unsigned NOT NULL,
  `Objetivo` enum('Enemigo','Jugador','Area') NOT NULL DEFAULT 'Enemigo',
  PRIMARY KEY (`idAccion`,`idEfecto`),
  KEY `fk_Carta_has_Efecto_Efecto1_idx` (`idEfecto`),
  KEY `fk_Carta_has_Efecto_Carta_idx` (`idAccion`),
  CONSTRAINT `fk_Carta_has_Efecto_Carta0` FOREIGN KEY (`idAccion`) REFERENCES `accion` (`idCarta`),
  CONSTRAINT `fk_Carta_has_Efecto_Efecto10` FOREIGN KEY (`idEfecto`) REFERENCES `efecto` (`idEfecto`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `accion_tiene_efecto`
--

LOCK TABLES `accion_tiene_efecto` WRITE;
/*!40000 ALTER TABLE `accion_tiene_efecto` DISABLE KEYS */;
/*!40000 ALTER TABLE `accion_tiene_efecto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `carta`
--

DROP TABLE IF EXISTS `carta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `carta` (
  `idCarta` int unsigned NOT NULL AUTO_INCREMENT,
  `Ataque` int NOT NULL,
  `Defensa` int NOT NULL,
  `Repeticiones` int NOT NULL,
  `Imagen` varchar(45) NOT NULL,
  PRIMARY KEY (`idCarta`),
  UNIQUE KEY `idCarta_UNIQUE` (`idCarta`),
  UNIQUE KEY `Imagen_UNIQUE` (`Imagen`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `carta`
--

LOCK TABLES `carta` WRITE;
/*!40000 ALTER TABLE `carta` DISABLE KEYS */;
INSERT INTO `carta` VALUES (1,5,0,1,'Carta/Slash'),(2,0,5,1,'Carta/Block'),(3,3,0,2,'Carta/Double'),(4,0,0,2,'Carta/GuardUp'),(5,0,0,5,'Carta/Heal');
/*!40000 ALTER TABLE `carta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `carta_tiene_efecto`
--

DROP TABLE IF EXISTS `carta_tiene_efecto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `carta_tiene_efecto` (
  `idCarta` int unsigned NOT NULL,
  `idEfecto` int unsigned NOT NULL,
  `Objetivo` enum('Enemigo','Jugador','Area') NOT NULL DEFAULT 'Enemigo',
  PRIMARY KEY (`idCarta`,`idEfecto`),
  KEY `fk_Carta_has_Efecto_Efecto1_idx` (`idEfecto`),
  KEY `fk_Carta_has_Efecto_Carta_idx` (`idCarta`),
  CONSTRAINT `fk_Carta_has_Efecto_Carta` FOREIGN KEY (`idCarta`) REFERENCES `carta` (`idCarta`),
  CONSTRAINT `fk_Carta_has_Efecto_Efecto1` FOREIGN KEY (`idEfecto`) REFERENCES `efecto` (`idEfecto`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `carta_tiene_efecto`
--

LOCK TABLES `carta_tiene_efecto` WRITE;
/*!40000 ALTER TABLE `carta_tiene_efecto` DISABLE KEYS */;
INSERT INTO `carta_tiene_efecto` VALUES (4,1,'Jugador'),(5,2,'Jugador');
/*!40000 ALTER TABLE `carta_tiene_efecto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comportamiento`
--

DROP TABLE IF EXISTS `comportamiento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comportamiento` (
  `idPersonaje` int NOT NULL,
  `idAccion` int unsigned NOT NULL,
  `orden` int unsigned NOT NULL,
  PRIMARY KEY (`idPersonaje`,`orden`),
  KEY `fk_Personaje_has_Carta_Carta1_idx` (`idAccion`),
  KEY `fk_Personaje_has_Carta_Personaje1_idx` (`idPersonaje`),
  CONSTRAINT `fk_Personaje_has_Carta_Carta10` FOREIGN KEY (`idAccion`) REFERENCES `accion` (`idCarta`),
  CONSTRAINT `fk_Personaje_has_Carta_Personaje10` FOREIGN KEY (`idPersonaje`) REFERENCES `personaje` (`idPersonaje`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comportamiento`
--

LOCK TABLES `comportamiento` WRITE;
/*!40000 ALTER TABLE `comportamiento` DISABLE KEYS */;
INSERT INTO `comportamiento` VALUES (1,1,1),(1,2,2);
/*!40000 ALTER TABLE `comportamiento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `efecto`
--

DROP TABLE IF EXISTS `efecto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `efecto` (
  `idEfecto` int unsigned NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(45) NOT NULL,
  PRIMARY KEY (`idEfecto`),
  UNIQUE KEY `idEfecto_UNIQUE` (`idEfecto`),
  UNIQUE KEY `Nombre_UNIQUE` (`Nombre`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `efecto`
--

LOCK TABLES `efecto` WRITE;
/*!40000 ALTER TABLE `efecto` DISABLE KEYS */;
INSERT INTO `efecto` VALUES (3,'Atq'),(1,'Def'),(2,'Heal'),(4,'Regen');
/*!40000 ALTER TABLE `efecto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mazo`
--

DROP TABLE IF EXISTS `mazo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mazo` (
  `idPersonaje` int NOT NULL,
  `idCarta` int unsigned NOT NULL,
  `LugarMazo` int unsigned NOT NULL,
  PRIMARY KEY (`idPersonaje`,`LugarMazo`),
  KEY `fk_Personaje_has_Carta_Carta1_idx` (`idCarta`),
  KEY `fk_Personaje_has_Carta_Personaje1_idx` (`idPersonaje`),
  CONSTRAINT `fk_Personaje_has_Carta_Carta1` FOREIGN KEY (`idCarta`) REFERENCES `carta` (`idCarta`),
  CONSTRAINT `fk_Personaje_has_Carta_Personaje1` FOREIGN KEY (`idPersonaje`) REFERENCES `personaje` (`idPersonaje`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mazo`
--

LOCK TABLES `mazo` WRITE;
/*!40000 ALTER TABLE `mazo` DISABLE KEYS */;
INSERT INTO `mazo` VALUES (0,1,1),(0,1,2),(0,2,3),(0,2,4);
/*!40000 ALTER TABLE `mazo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `personaje`
--

DROP TABLE IF EXISTS `personaje`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `personaje` (
  `idPersonaje` int NOT NULL,
  `Vida` int NOT NULL,
  `Imagen` varchar(45) DEFAULT NULL,
  `Energia` int NOT NULL,
  `RegeneracionEnergia` int NOT NULL,
  PRIMARY KEY (`idPersonaje`),
  UNIQUE KEY `idPersonaje_UNIQUE` (`idPersonaje`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `personaje`
--

LOCK TABLES `personaje` WRITE;
/*!40000 ALTER TABLE `personaje` DISABLE KEYS */;
INSERT INTO `personaje` VALUES (0,20,NULL,0,0),(1,10,'Enemigos/Esqueleto',0,0);
/*!40000 ALTER TABLE `personaje` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-05-26  0:24:48

CREATE DATABASE  IF NOT EXISTS `bd_proyecto_lp` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `bd_proyecto_lp`;
-- MySQL dump 10.13  Distrib 8.0.22, for Win64 (x86_64)
--
-- Host: localhost    Database: bd_proyecto_lp
-- ------------------------------------------------------
-- Server version	8.0.22

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
-- Table structure for table `tb_bases_admin`
--

DROP TABLE IF EXISTS `tb_bases_admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_bases_admin` (
  `cod_bases` int NOT NULL AUTO_INCREMENT,
  `des_bases` varchar(100) DEFAULT NULL,
  `plazo` varchar(100) DEFAULT NULL,
  `cod_emp` int DEFAULT NULL,
  `fecha_bases` date DEFAULT NULL,
  `cod_est` int DEFAULT NULL,
  `cod_sol_con` int DEFAULT NULL,
  PRIMARY KEY (`cod_bases`),
  KEY `cod_emp` (`cod_emp`),
  KEY `cod_est` (`cod_est`),
  KEY `cod_sol_con` (`cod_sol_con`),
  CONSTRAINT `tb_bases_admin_ibfk_1` FOREIGN KEY (`cod_emp`) REFERENCES `tb_empleado` (`cod_emp`),
  CONSTRAINT `tb_bases_admin_ibfk_2` FOREIGN KEY (`cod_est`) REFERENCES `tb_estado` (`cod_est`),
  CONSTRAINT `tb_bases_admin_ibfk_3` FOREIGN KEY (`cod_sol_con`) REFERENCES `tb_sol_contrata` (`cod_sol_con`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_bases_admin`
--

LOCK TABLES `tb_bases_admin` WRITE;
/*!40000 ALTER TABLE `tb_bases_admin` DISABLE KEYS */;
INSERT INTO `tb_bases_admin` VALUES (18,'BASES PARA CONTRATO','90 días',1,'2021-12-02',4,22);
/*!40000 ALTER TABLE `tb_bases_admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_cargo`
--

DROP TABLE IF EXISTS `tb_cargo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_cargo` (
  `cod_car` int NOT NULL AUTO_INCREMENT,
  `nom_car` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`cod_car`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_cargo`
--

LOCK TABLES `tb_cargo` WRITE;
/*!40000 ALTER TABLE `tb_cargo` DISABLE KEYS */;
INSERT INTO `tb_cargo` VALUES (1,'secretaria'),(2,'Asistente'),(3,'Supervisor'),(4,'Representate'),(5,'mantenimiento'),(6,'Gerente');
/*!40000 ALTER TABLE `tb_cargo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_ccp`
--

DROP TABLE IF EXISTS `tb_ccp`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_ccp` (
  `cod_ccp` int NOT NULL AUTO_INCREMENT,
  `desc_ccp` varchar(70) DEFAULT NULL,
  `monto_ccp` decimal(10,2) DEFAULT NULL,
  `cod_emp` int DEFAULT NULL,
  `cod_est` int DEFAULT NULL,
  `fecha_ccp` date DEFAULT NULL,
  PRIMARY KEY (`cod_ccp`),
  KEY `CCP01` (`cod_emp`),
  KEY `EST01` (`cod_est`),
  CONSTRAINT `CCP01` FOREIGN KEY (`cod_emp`) REFERENCES `tb_empleado` (`cod_emp`),
  CONSTRAINT `EST01` FOREIGN KEY (`cod_est`) REFERENCES `tb_estado` (`cod_est`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_ccp`
--

LOCK TABLES `tb_ccp` WRITE;
/*!40000 ALTER TABLE `tb_ccp` DISABLE KEYS */;
INSERT INTO `tb_ccp` VALUES (9,'CREDITO PARA COFFE BREAK PARA GG',15000.00,1,1,'2021-12-02'),(10,'PERSONAL DE SEGURIDAD',15000.00,1,4,'2021-12-02');
/*!40000 ALTER TABLE `tb_ccp` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_contratacion`
--

DROP TABLE IF EXISTS `tb_contratacion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_contratacion` (
  `id_con` int NOT NULL AUTO_INCREMENT,
  `cod_prov` int DEFAULT NULL,
  `cod_emp` int DEFAULT NULL,
  `cod_sol` int DEFAULT NULL,
  `cod_t_sol` int DEFAULT NULL,
  PRIMARY KEY (`id_con`),
  KEY `PRO001` (`cod_prov`),
  KEY `EMP001` (`cod_emp`),
  KEY `cod_sol` (`cod_sol`),
  KEY `cod_t_sol` (`cod_t_sol`),
  CONSTRAINT `EMP001` FOREIGN KEY (`cod_emp`) REFERENCES `tb_empleado` (`cod_emp`),
  CONSTRAINT `PRO001` FOREIGN KEY (`cod_prov`) REFERENCES `tb_proveedor` (`cod_prov`),
  CONSTRAINT `tb_contratacion_ibfk_1` FOREIGN KEY (`cod_sol`) REFERENCES `tb_solicitud` (`cod_sol`),
  CONSTRAINT `tb_contratacion_ibfk_2` FOREIGN KEY (`cod_t_sol`) REFERENCES `tb_tipo_solicitud` (`cod_t_sol`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_contratacion`
--

LOCK TABLES `tb_contratacion` WRITE;
/*!40000 ALTER TABLE `tb_contratacion` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_contratacion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_contrato`
--

DROP TABLE IF EXISTS `tb_contrato`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_contrato` (
  `cod_contrato` int NOT NULL AUTO_INCREMENT,
  `cod_emp` int DEFAULT NULL,
  `cod_sol_con` int DEFAULT NULL,
  `fecha_contra` date DEFAULT NULL,
  `cod_bases` int DEFAULT NULL,
  `cod_expbp` int DEFAULT NULL,
  PRIMARY KEY (`cod_contrato`),
  KEY `cod_emp` (`cod_emp`),
  KEY `cod_sol_con` (`cod_sol_con`),
  KEY `ibk1_idx` (`cod_expbp`),
  KEY `ibk4_idx` (`cod_bases`),
  CONSTRAINT `ibk1` FOREIGN KEY (`cod_expbp`) REFERENCES `tb_expediente_buenapro` (`cod_expbp`),
  CONSTRAINT `ibk4` FOREIGN KEY (`cod_bases`) REFERENCES `tb_bases_admin` (`cod_bases`),
  CONSTRAINT `tb_contrato_ibfk_1` FOREIGN KEY (`cod_emp`) REFERENCES `tb_empleado` (`cod_emp`),
  CONSTRAINT `tb_contrato_ibfk_3` FOREIGN KEY (`cod_sol_con`) REFERENCES `tb_sol_contrata` (`cod_sol_con`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_contrato`
--

LOCK TABLES `tb_contrato` WRITE;
/*!40000 ALTER TABLE `tb_contrato` DISABLE KEYS */;
INSERT INTO `tb_contrato` VALUES (1,1,22,'2021-12-02',18,3);
/*!40000 ALTER TABLE `tb_contrato` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_detalle_solicitud`
--

DROP TABLE IF EXISTS `tb_detalle_solicitud`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_detalle_solicitud` (
  `cod_deta` int NOT NULL AUTO_INCREMENT,
  `cod_sol` int DEFAULT NULL,
  `cod_emp` int DEFAULT NULL,
  PRIMARY KEY (`cod_deta`),
  KEY `SOL001` (`cod_sol`),
  KEY `cod_emp` (`cod_emp`),
  CONSTRAINT `SOL001` FOREIGN KEY (`cod_sol`) REFERENCES `tb_solicitud` (`cod_sol`),
  CONSTRAINT `tb_detalle_solicitud_ibfk_1` FOREIGN KEY (`cod_emp`) REFERENCES `tb_empleado` (`cod_emp`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_detalle_solicitud`
--

LOCK TABLES `tb_detalle_solicitud` WRITE;
/*!40000 ALTER TABLE `tb_detalle_solicitud` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_detalle_solicitud` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_division`
--

DROP TABLE IF EXISTS `tb_division`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_division` (
  `cod_div` int NOT NULL AUTO_INCREMENT,
  `nom_div` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`cod_div`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_division`
--

LOCK TABLES `tb_division` WRITE;
/*!40000 ALTER TABLE `tb_division` DISABLE KEYS */;
INSERT INTO `tb_division` VALUES (1,'Mesa de partes'),(2,'Gerencia de Programación'),(3,'Gerencia General'),(4,'Gerencia Central de logistica'),(5,'ADHOC'),(6,'Comite especial Permanente'),(7,'Oficina central de Planificación'),(8,'Gerencia de Abastecimiento');
/*!40000 ALTER TABLE `tb_division` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_empleado`
--

DROP TABLE IF EXISTS `tb_empleado`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_empleado` (
  `cod_emp` int NOT NULL AUTO_INCREMENT,
  `nom_emp` varchar(30) DEFAULT NULL,
  `ape_emp` varchar(30) DEFAULT NULL,
  `dni_emp` int DEFAULT NULL,
  `cod_car` int DEFAULT NULL,
  `cod_div` int DEFAULT NULL,
  PRIMARY KEY (`cod_emp`),
  KEY `fk51_idx` (`cod_div`),
  KEY `aee_idx` (`cod_car`),
  CONSTRAINT `aea` FOREIGN KEY (`cod_div`) REFERENCES `tb_division` (`cod_div`),
  CONSTRAINT `aee` FOREIGN KEY (`cod_car`) REFERENCES `tb_cargo` (`cod_car`)
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_empleado`
--

LOCK TABLES `tb_empleado` WRITE;
/*!40000 ALTER TABLE `tb_empleado` DISABLE KEYS */;
INSERT INTO `tb_empleado` VALUES (1,'pepito','perez',1234567,NULL,NULL),(11,'Maria','Espinoza',15975632,1,1),(12,'José','Perez',14785236,4,2),(13,'Marco','Ñato',12345678,2,7),(14,'Juan','Ravenna',73765999,6,8),(15,'Sebastian','Toloza',45628536,4,4),(16,'Jayme','Aguirre',12542365,4,6),(17,'Johnny','Joestar',75345685,6,3),(18,'Miles','Morale',2365478,3,5),(19,'Terry','Bogard',15963254,5,3);
/*!40000 ALTER TABLE `tb_empleado` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_estado`
--

DROP TABLE IF EXISTS `tb_estado`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_estado` (
  `cod_est` int NOT NULL AUTO_INCREMENT,
  `des_est` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`cod_est`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_estado`
--

LOCK TABLES `tb_estado` WRITE;
/*!40000 ALTER TABLE `tb_estado` DISABLE KEYS */;
INSERT INTO `tb_estado` VALUES (1,'pendiente'),(2,'aprobado'),(3,'rechazado'),(4,'finalizado');
/*!40000 ALTER TABLE `tb_estado` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_estado_proveedor`
--

DROP TABLE IF EXISTS `tb_estado_proveedor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_estado_proveedor` (
  `cod_estado_prov` int NOT NULL AUTO_INCREMENT,
  `des_estado_prov` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`cod_estado_prov`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_estado_proveedor`
--

LOCK TABLES `tb_estado_proveedor` WRITE;
/*!40000 ALTER TABLE `tb_estado_proveedor` DISABLE KEYS */;
INSERT INTO `tb_estado_proveedor` VALUES (1,'Registrado'),(2,'Seleccionado'),(3,'Contratado');
/*!40000 ALTER TABLE `tb_estado_proveedor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_expediente_buenapro`
--

DROP TABLE IF EXISTS `tb_expediente_buenapro`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_expediente_buenapro` (
  `cod_expbp` int NOT NULL AUTO_INCREMENT,
  `Asunto` varchar(100) DEFAULT NULL,
  `referencia` varchar(100) DEFAULT NULL,
  `cod_sol_con` int DEFAULT NULL,
  `cod_prov` int DEFAULT NULL,
  `cod_emp` int DEFAULT NULL,
  `fecha_buneapro` date DEFAULT NULL,
  PRIMARY KEY (`cod_expbp`),
  KEY `SOL01` (`cod_sol_con`),
  KEY `cod_prov` (`cod_prov`),
  KEY `cod_emp` (`cod_emp`),
  CONSTRAINT `SOL01` FOREIGN KEY (`cod_sol_con`) REFERENCES `tb_sol_contrata` (`cod_sol_con`),
  CONSTRAINT `tb_expediente_buenapro_ibfk_2` FOREIGN KEY (`cod_prov`) REFERENCES `tb_proveedor` (`cod_prov`),
  CONSTRAINT `tb_expediente_buenapro_ibfk_3` FOREIGN KEY (`cod_emp`) REFERENCES `tb_empleado` (`cod_emp`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_expediente_buenapro`
--

LOCK TABLES `tb_expediente_buenapro` WRITE;
/*!40000 ALTER TABLE `tb_expediente_buenapro` DISABLE KEYS */;
INSERT INTO `tb_expediente_buenapro` VALUES (3,'asunto','REFERENCIA',22,3,1,'2021-12-02');
/*!40000 ALTER TABLE `tb_expediente_buenapro` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_login`
--

DROP TABLE IF EXISTS `tb_login`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_login` (
  `cod_emp` int NOT NULL AUTO_INCREMENT,
  `nom_usu` varchar(30) DEFAULT NULL,
  `contra` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`cod_emp`),
  CONSTRAINT `fk11` FOREIGN KEY (`cod_emp`) REFERENCES `tb_empleado` (`cod_emp`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_login`
--

LOCK TABLES `tb_login` WRITE;
/*!40000 ALTER TABLE `tb_login` DISABLE KEYS */;
INSERT INTO `tb_login` VALUES (1,'pepito','123'),(11,'maria','1234'),(12,'jose','456'),(13,'marco','753'),(14,'juan','159'),(15,'Sebastian','753'),(16,'jayme','123'),(17,'jony','741'),(18,'miles','123'),(19,'terry','123');
/*!40000 ALTER TABLE `tb_login` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_objeto`
--

DROP TABLE IF EXISTS `tb_objeto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_objeto` (
  `cod_obj` int NOT NULL AUTO_INCREMENT,
  `nom_objeto` varchar(100) DEFAULT NULL,
  `des_obj` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`cod_obj`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_objeto`
--

LOCK TABLES `tb_objeto` WRITE;
/*!40000 ALTER TABLE `tb_objeto` DISABLE KEYS */;
INSERT INTO `tb_objeto` VALUES (11,'SEGURIDAD','SEGURIDAD MOVIL 1');
/*!40000 ALTER TABLE `tb_objeto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_proveedor`
--

DROP TABLE IF EXISTS `tb_proveedor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_proveedor` (
  `cod_prov` int NOT NULL AUTO_INCREMENT,
  `raz_soc` varchar(100) DEFAULT NULL,
  `ruc` char(11) DEFAULT NULL,
  `dir_prov` varchar(100) DEFAULT NULL,
  `fono` int DEFAULT NULL,
  `pre_obj` decimal(12,2) DEFAULT NULL,
  `cod_obj` int DEFAULT NULL,
  `cod_estado_prov` int DEFAULT NULL,
  PRIMARY KEY (`cod_prov`),
  KEY `OBJ001` (`cod_obj`),
  KEY `cod_estado_prov` (`cod_estado_prov`),
  CONSTRAINT `OBJ001` FOREIGN KEY (`cod_obj`) REFERENCES `tb_objeto` (`cod_obj`),
  CONSTRAINT `tb_proveedor_ibfk_1` FOREIGN KEY (`cod_estado_prov`) REFERENCES `tb_estado_proveedor` (`cod_estado_prov`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_proveedor`
--

LOCK TABLES `tb_proveedor` WRITE;
/*!40000 ALTER TABLE `tb_proveedor` DISABLE KEYS */;
INSERT INTO `tb_proveedor` VALUES (3,'SEGURIDAD SAC','12345678971','calle 6c',987654632,6000.00,11,3);
/*!40000 ALTER TABLE `tb_proveedor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_sol_contrata`
--

DROP TABLE IF EXISTS `tb_sol_contrata`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_sol_contrata` (
  `cod_sol_con` int NOT NULL AUTO_INCREMENT,
  `des_sol_con` varchar(100) DEFAULT NULL,
  `cod_est` int DEFAULT NULL,
  `cod_sol` int DEFAULT NULL,
  `cod_ccp` int DEFAULT NULL,
  `fecha_sol_con` date DEFAULT NULL,
  `cod_emp` int DEFAULT NULL,
  `t_contrato` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`cod_sol_con`),
  KEY `cod_sol` (`cod_sol`),
  KEY `cod_emp` (`cod_emp`),
  KEY `cod_ccp` (`cod_ccp`),
  KEY `cod_est` (`cod_est`),
  CONSTRAINT `tb_sol_contrata_ibfk_1` FOREIGN KEY (`cod_sol`) REFERENCES `tb_solicitud` (`cod_sol`),
  CONSTRAINT `tb_sol_contrata_ibfk_2` FOREIGN KEY (`cod_emp`) REFERENCES `tb_empleado` (`cod_emp`),
  CONSTRAINT `tb_sol_contrata_ibfk_3` FOREIGN KEY (`cod_ccp`) REFERENCES `tb_ccp` (`cod_ccp`),
  CONSTRAINT `tb_sol_contrata_ibfk_4` FOREIGN KEY (`cod_est`) REFERENCES `tb_estado` (`cod_est`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_sol_contrata`
--

LOCK TABLES `tb_sol_contrata` WRITE;
/*!40000 ALTER TABLE `tb_sol_contrata` DISABLE KEYS */;
INSERT INTO `tb_sol_contrata` VALUES (21,'SE SOLICITA CONTRATAR PERSONAL PARA ESTE ABASTECIMIENTO',1,16,9,'2021-12-02',1,'Licitación'),(22,'SE REQUIERE CONTRACION PERSONAL',4,19,10,'2021-12-02',1,'Contratación directa');
/*!40000 ALTER TABLE `tb_sol_contrata` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_solicitud`
--

DROP TABLE IF EXISTS `tb_solicitud`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_solicitud` (
  `cod_sol` int NOT NULL AUTO_INCREMENT,
  `des_sol` varchar(100) DEFAULT NULL,
  `fec_sol` date DEFAULT NULL,
  `cod_est` int DEFAULT NULL,
  `cod_t_sol` int DEFAULT NULL,
  `cod_emp` int DEFAULT NULL,
  PRIMARY KEY (`cod_sol`),
  KEY `cod_est` (`cod_est`),
  KEY `cod_emp` (`cod_emp`),
  KEY `TSL001` (`cod_t_sol`),
  CONSTRAINT `tb_solicitud_ibfk_1` FOREIGN KEY (`cod_est`) REFERENCES `tb_estado` (`cod_est`),
  CONSTRAINT `tb_solicitud_ibfk_2` FOREIGN KEY (`cod_emp`) REFERENCES `tb_empleado` (`cod_emp`),
  CONSTRAINT `TSL001` FOREIGN KEY (`cod_t_sol`) REFERENCES `tb_tipo_solicitud` (`cod_t_sol`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_solicitud`
--

LOCK TABLES `tb_solicitud` WRITE;
/*!40000 ALTER TABLE `tb_solicitud` DISABLE KEYS */;
INSERT INTO `tb_solicitud` VALUES (16,'Se Solicita personal de limpieza para area Programación','2021-12-02',2,2,1),(19,'Se requiere personal de Seguridad para GG','2021-12-02',4,2,1),(20,'Se solicita un coffe break para Gerencia General','2021-12-02',1,1,1);
/*!40000 ALTER TABLE `tb_solicitud` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_tipo_solicitud`
--

DROP TABLE IF EXISTS `tb_tipo_solicitud`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_tipo_solicitud` (
  `cod_t_sol` int NOT NULL AUTO_INCREMENT,
  `tip_sol` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`cod_t_sol`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_tipo_solicitud`
--

LOCK TABLES `tb_tipo_solicitud` WRITE;
/*!40000 ALTER TABLE `tb_tipo_solicitud` DISABLE KEYS */;
INSERT INTO `tb_tipo_solicitud` VALUES (1,'Bien'),(2,'Servicio');
/*!40000 ALTER TABLE `tb_tipo_solicitud` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'bd_proyecto_lp'
--
/*!50003 DROP PROCEDURE IF EXISTS `actualizarestado_proveedor` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `actualizarestado_proveedor`(codigo int,estado int)
BEGIN
update tb_proveedor set cod_estado_prov=estado where cod_prov=codigo;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `actualizarestado_sol_contrata` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `actualizarestado_sol_contrata`(codigo int,estado int)
BEGIN
update tb_sol_contrata set cod_est=estado where cod_sol_con=codigo;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `actualizar_estado_ccp` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `actualizar_estado_ccp`(codigoccp int,estado int)
BEGIN
update tb_ccp set cod_est=estado where cod_ccp=codigoccp;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `crear_contrato` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `crear_contrato`(codigo int,empleado int,cod_sol int,fecha varchar(50),cbases int,cexpbp int)
BEGIN
insert tb_contrato values
(codigo,empleado,cod_sol,fecha,cbases,cexpbp);
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `inicio_sesion` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `inicio_sesion`(vusuario varchar(20),vclave varchar(20))
BEGIN
select e.cod_emp,e.nom_emp,e.ape_emp,e.cod_car,e.cod_div from tb_empleado e 
join tb_login u on e.cod_emp=u.cod_emp
where u.nom_usu=vusuario and u.contra=vclave;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `listar_basesadmin` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `listar_basesadmin`()
BEGIN
select b.cod_bases,b.des_bases,b.plazo,b.cod_emp,b.fecha_bases,e.des_est,b.cod_sol_con 
from tb_bases_admin b join tb_estado e on e.cod_est=b.cod_est;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `llamarBAparacontrato` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `llamarBAparacontrato`(codigo int)
BEGIN
select b.cod_bases,b.des_bases,b.plazo,b.cod_emp,b.fecha_bases,b.cod_est,b.cod_sol_con
 from tb_bases_admin b left join tb_contrato c on c.cod_bases=b.cod_bases where b.cod_est=2 and b.cod_bases=codigo;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `llamarBPparaContrato` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `llamarBPparaContrato`(codigo int)
BEGIN
select b.cod_expbp,b.Asunto,b.referencia,b.cod_sol_con,b.cod_prov,b.cod_emp,b.fecha_buneapro
 from tb_expediente_buenapro b left join tb_contrato c on c.cod_expbp=b.cod_expbp where c.cod_contrato is null and b.cod_expbp=codigo;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `llamarsolconparaContrato` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `llamarsolconparaContrato`(codigo int)
BEGIN
select b.cod_sol_con,b.des_sol_con,b.cod_est,b.cod_sol,b.cod_ccp,b.fecha_sol_con,b.cod_emp,b.t_contrato
from tb_sol_contrata b left join tb_contrato c on b.cod_sol_con=c.cod_sol_con where c.cod_contrato is null and b.cod_est=2 and b.cod_sol_con=codigo;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `llamar_ccp` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `llamar_ccp`(cod int)
BEGIN
select c.cod_ccp,c.desc_ccp,c.monto_ccp,c.cod_emp,c.cod_est,c.fecha_ccp 
from tb_ccp c left join tb_sol_contrata x on c.cod_ccp=x.cod_ccp where c.cod_est=1 and x.cod_sol_con is null and c.cod_ccp=cod;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `llamar_sol_contrata` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `llamar_sol_contrata`(cod int)
BEGIN
select b.cod_sol_con,b.des_sol_con,b.cod_est,b.cod_sol,b.cod_ccp,b.fecha_sol_con,b.cod_emp,b.t_contrato
from tb_sol_contrata b left join tb_bases_admin c on b.cod_sol_con=c.cod_sol_con where c.cod_bases is null and b.cod_est=2 and b.cod_sol_con=cod;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `llamar_sol_contrata_est_1` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `llamar_sol_contrata_est_1`(cod int)
BEGIN
select b.cod_sol_con,b.des_sol_con,b.cod_est,b.cod_sol,b.cod_ccp,b.fecha_sol_con,b.cod_emp,b.t_contrato
from tb_sol_contrata b left join tb_bases_admin c on b.cod_sol_con=c.cod_sol_con where b.cod_est=1 and b.cod_sol_con=cod;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `reporte_sol_contrata` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `reporte_sol_contrata`(tipo int,filtro varchar(100))
BEGIN
if tipo=1 then
begin
Select c.cod_sol_con,c.des_sol_con,e.des_est,c.cod_sol,c.cod_ccp,c.fecha_sol_con,c.cod_emp,c.t_contrato 
from tb_sol_contrata c join tb_estado e on c.cod_est=e.cod_est
where c.cod_sol_con=cast(filtro as UNSIGNED);
end;
end if;

if tipo=2 then
begin
Select c.cod_sol_con,c.des_sol_con,e.des_est,c.cod_sol,c.cod_ccp,c.fecha_sol_con,c.cod_emp,c.t_contrato 
from tb_sol_contrata c join tb_estado e on c.cod_est=e.cod_est
where c.cod_est=cast(filtro as unsigned);
end;
end if;

if tipo=3 then
begin
Select c.cod_sol_con,c.des_sol_con,e.des_est,c.cod_sol,c.cod_ccp,c.fecha_sol_con,c.cod_emp,c.t_contrato 
from tb_sol_contrata c join tb_estado e on c.cod_est=e.cod_est
where c.t_contrato like filtro;
end;
end if;

if tipo=4 and filtro='' then
begin
Select c.cod_sol_con,c.des_sol_con,e.des_est,c.cod_sol,c.cod_ccp,c.fecha_sol_con,c.cod_emp,c.t_contrato 
from tb_sol_contrata c join tb_estado e on c.cod_est=e.cod_est;
end;
end if;

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `sp_actualizaEstado_frmBaseAdmin` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_actualizaEstado_frmBaseAdmin`(codEst int,codBases int)
BEGIN
  update tb_bases_admin set cod_est=codEst
  where cod_bases=codBases;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `sp_actualizaEstado_frmSolicitud` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_actualizaEstado_frmSolicitud`(codEst int,codSol int)
BEGIN
update tb_solicitud set cod_est=codEst
where cod_sol=codSol;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `sp_actualizar_objeto_frmobjeto` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_actualizar_objeto_frmobjeto`(CodObj int, NomObj varchar(20), DescObj varchar(50))
BEGIN
  update tb_objeto Set
   cod_obj=CodObj, nom_objeto=NomObj, des_obj=DescObj
   where cod_obj=CodObj;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `sp_añadir_objeto_frmobjeto` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_añadir_objeto_frmobjeto`(NomObj varchar(20), DescObj varchar(50))
BEGIN
  insert into tb_objeto values (null,NomObj,DescObj);
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `sp_consulta_solicitudesAbas` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_consulta_solicitudesAbas`(Tipo int, Filtro varchar(15))
BEGIN
 if Tipo=1 then 
           begin
				select S.cod_sol, S.des_sol, S.fec_sol, E.des_est, TS.tip_sol, S.cod_emp
						from tb_estado E join tb_solicitud S 
                        on E.cod_est= S.cod_est join tb_tipo_solicitud TS
                        on S.cod_t_sol=TS.Cod_t_sol
						where E.cod_est= CAST(Filtro AS UNSIGNED);
            end;
            end if;
  if tipo=2 then 
			begin 
               select S.cod_sol, S.des_sol, S.fec_sol, E.des_est, TS.tip_sol, S.cod_emp
						from tb_estado E join tb_solicitud S 
                        on E.cod_est= S.cod_est join tb_tipo_solicitud TS
                        on S.cod_t_sol=TS.Cod_t_sol
                        where S.cod_sol= cast(Filtro as unsigned);
			end;
            end if;
	if tipo=3 then 
            begin 
            select S.cod_sol, S.des_sol, S.fec_sol, E.des_est, TS.tip_sol, S.cod_emp
						from tb_estado E join tb_solicitud S 
                        on E.cod_est= S.cod_est join tb_tipo_solicitud TS
                        on S.cod_t_sol=TS.Cod_t_sol
                        where S.fec_sol= concat(Filtro,'%');
			end;
            end if;
   if tipo=4 and Filtro='' then 
            begin 
            select S.cod_sol, S.des_sol, S.fec_sol, E.des_est, TS.tip_sol, S.cod_emp
						from tb_estado E join tb_solicitud S 
                        on E.cod_est= S.cod_est join tb_tipo_solicitud TS
                        on S.cod_t_sol=TS.Cod_t_sol;
			end;
            end if;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `sp_consulta_solicitudesBasesAdmin` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_consulta_solicitudesBasesAdmin`(Tipo int, Filtro varchar(15))
BEGIN
 if Tipo=1 then 
           begin
				select BA.cod_bases, BA.des_bases, BA.plazo, BA.cod_emp, BA.fecha_bases,E.des_est, BA.cod_sol_con
						from tb_estado E join tb_bases_admin BA 
                        on E.cod_est= BA.cod_est join tb_sol_contrata SC
                        on BA.cod_sol_con= SC.cod_sol_con
						where E.cod_est= CAST(Filtro AS UNSIGNED);
            end;
            end if;
 if Tipo=2 then 
		   begin
		   select BA.cod_bases, BA.des_bases, BA.plazo, BA.cod_emp, BA.fecha_bases,E.des_est, BA.cod_sol_con
						from tb_estado E join tb_bases_admin BA 
                        on E.cod_est= BA.cod_est join tb_sol_contrata SC
                        on BA.cod_sol_con= SC.cod_sol_con
                         where BA.cod_bases= cast(Filtro as unsigned);
             end;
            end if;
  if Tipo=3 then 
            begin 
            select BA.cod_bases, BA.des_bases, BA.plazo, BA.cod_emp, BA.fecha_bases,E.des_est, BA.cod_sol_con
						from tb_estado E join tb_bases_admin BA 
                        on E.cod_est= BA.cod_est join tb_sol_contrata SC
                        on BA.cod_sol_con= SC.cod_sol_con
                         where BA.fecha_bases= concat(Filtro,'%');
			end;
            end if;
 if tipo=4 and Filtro='' then 
            begin 
            select BA.cod_bases, BA.des_bases, BA.plazo, BA.cod_emp, BA.fecha_bases,E.des_est, BA.cod_sol_con
						from tb_estado E join tb_bases_admin BA 
                        on E.cod_est= BA.cod_est join tb_sol_contrata SC
                        on BA.cod_sol_con= SC.cod_sol_con;
			end;
            end if;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `sp_eliminar_objeto_frmobjeto` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_eliminar_objeto_frmobjeto`(CodObj int)
BEGIN
 delete from tb_objeto where cod_obj=CodObj;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `sp_Filtar_cod_frmConsultaDbaseAdmin` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_Filtar_cod_frmConsultaDbaseAdmin`(vFiltro int)
BEGIN
select BA.cod_bases, BA.des_bases, BA.plazo, BA.cod_emp, BA.fecha_bases, E.des_est, BA.cod_sol_con
					from tb_bases_admin BA join tb_estado E
					on BA.cod_est = E.cod_est
                    where BA.cod_bases like concat(vFiltro,'%') and E.cod_est=1;

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `sp_listar_por_estado` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_listar_por_estado`(Estado varchar(15))
BEGIN
if Estado='Todos' then
		begin
			select S.cod_sol, S.des_sol, S.fec_sol, E.des_est, TS.tip_sol, S.cod_emp
            from tb_estado E join tb_solicitud S
            on E.cod_est = S.cod_est join tb_tipo_solicitud TS
            on S.cod_t_sol = TS.cod_t_sol;
		end;
    else
		begin
			select S.cod_sol, S.des_sol, S.fec_sol, E.des_est, TS.tip_sol, S.cod_emp
            from tb_estado E join tb_solicitud S
            on E.cod_est = S.cod_est join tb_tipo_solicitud TS
            on S.cod_t_sol = TS.cod_t_sol
            where E.des_est=Estado;
		end;
    end if;    
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `sp_listar_solicitudes_frmSolicitud` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_listar_solicitudes_frmSolicitud`(vFiltro int)
BEGIN
 Select S.cod_sol, S.des_sol, S.fec_sol, E.des_est, TS.tip_sol, x.cod_emp
					From tb_estado E join tb_solicitud S
					On E.cod_est = S.cod_est join tb_tipo_solicitud TS
					On S.cod_t_sol = TS.cod_t_sol join tb_empleado x on x.cod_emp=S.cod_emp
                    where S.cod_sol like concat(vFiltro,'%') and E.cod_est=1;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `sp_llamar_objeto` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_llamar_objeto`(cod int)
begin
select o.cod_obj,o.nom_objeto,o.des_obj
from tb_objeto o left join  tb_proveedor p on o.cod_obj=p.cod_obj where p.cod_obj is null
and o.cod_obj=cod;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `sp_llamar_proveedor` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_llamar_proveedor`(cod int)
begin
select p.cod_prov, p.raz_soc, p.ruc,p.dir_prov, p.fono, p.pre_obj,p.cod_obj, e.des_estado_prov 
 from  tb_proveedor p join tb_estado_proveedor e on  p.cod_estado_prov = e.cod_estado_prov
 left join tb_expediente_buenapro b on p.cod_prov=b.cod_prov where b.cod_prov is null and
p.cod_prov=cod;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `sp_llamar_solbuenapro` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_llamar_solbuenapro`(cod int)
begin
select b.cod_sol_con,b.des_sol_con,b.cod_est,b.cod_sol,b.cod_ccp,b.fecha_sol_con,b.cod_emp,b.t_contrato
from tb_sol_contrata b left join tb_expediente_buenapro e on b.cod_sol_con=e.cod_sol_con where e.cod_sol_con is null
and b.cod_est=2 and b.cod_sol_con=cod;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `sp_llamar_sol_abastecimiento` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_llamar_sol_abastecimiento`(cod int)
BEGIN
select s.cod_sol,s.des_sol,s.fec_sol,s.cod_est,s.cod_t_sol,s.cod_emp from tb_solicitud s left join tb_sol_contrata c on s.cod_sol=c.cod_sol where c.cod_est is null and s.cod_est=2
and s.cod_sol=cod;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `sp_mostrar_objeto_frmobjeto` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_mostrar_objeto_frmobjeto`()
BEGIN
 Select * from tb_objeto;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `update_bases` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `update_bases`(des varchar(100),tiempo varchar(100),empleado int,fechabas date,cod_sol int,codigo int)
BEGIN
update tb_bases_admin set des_bases=des,plazo=tiempo,cod_emp=empleado,fecha_bases=fechabas,cod_sol_con=cod_sol where cod_bases=codigo;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-12-02 16:27:30

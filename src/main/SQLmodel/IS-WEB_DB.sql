-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               8.0.22 - MySQL Community Server - GPL
-- Server OS:                    Win64
-- HeidiSQL Version:             11.3.0.6295
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Dumping database structure for web_service_db
CREATE DATABASE IF NOT EXISTS `web_service_db` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `web_service_db`;

-- Dumping structure for table web_service_db.t_addresses
CREATE TABLE IF NOT EXISTS `t_addresses` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `T_PEOPLE_ID` int NOT NULL,
  `ADDR_TYPE` varchar(5) COLLATE utf8mb4_unicode_ci NOT NULL,
  `ADDR_INFO` varchar(300) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_t_addresses_t_people` (`T_PEOPLE_ID`),
  CONSTRAINT `FK_t_addresses_t_people` FOREIGN KEY (`T_PEOPLE_ID`) REFERENCES `t_people` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Dumping data for table web_service_db.t_addresses: ~5 rows (approximately)
/*!40000 ALTER TABLE `t_addresses` DISABLE KEYS */;
INSERT INTO `t_addresses` (`ID`, `T_PEOPLE_ID`, `ADDR_TYPE`, `ADDR_INFO`) VALUES
	(42, 81, 'home', 'Sofia'),
	(43, 82, 'work', 'Plovdiv'),
	(44, 83, 'work', 'London'),
	(45, 84, 'home', 'Burgas'),
	(46, 85, 'home', 'Sofia'),
	(47, 86, 'home', 'Burgas');
/*!40000 ALTER TABLE `t_addresses` ENABLE KEYS */;

-- Dumping structure for table web_service_db.t_mails
CREATE TABLE IF NOT EXISTS `t_mails` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `T_PEOPLE_ID` int NOT NULL,
  `EMAIL_TYPE` varchar(5) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
  `EMAIL` varchar(40) COLLATE utf8mb4_unicode_ci DEFAULT '',
  PRIMARY KEY (`ID`),
  KEY `FK_t_mails_t_people` (`T_PEOPLE_ID`),
  CONSTRAINT `FK_t_mails_t_people` FOREIGN KEY (`T_PEOPLE_ID`) REFERENCES `t_people` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Dumping data for table web_service_db.t_mails: ~5 rows (approximately)
/*!40000 ALTER TABLE `t_mails` DISABLE KEYS */;
INSERT INTO `t_mails` (`ID`, `T_PEOPLE_ID`, `EMAIL_TYPE`, `EMAIL`) VALUES
	(37, 81, 'home', 'ivanov@abv.bg'),
	(38, 82, 'work', 'georgiev@yahoo.com'),
	(39, 83, 'home', 'ipetrov@gmail.com'),
	(40, 84, 'work', 'nikolova@gmail.com'),
	(41, 85, 'home', 'petrova99@yahoo.com'),
	(42, 86, 'home', 'ivanov@abv.bg');
/*!40000 ALTER TABLE `t_mails` ENABLE KEYS */;

-- Dumping structure for table web_service_db.t_people
CREATE TABLE IF NOT EXISTS `t_people` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `FULL_NAME` varchar(90) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
  `PIN` varchar(10) COLLATE utf8mb4_unicode_ci DEFAULT '',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=86 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Dumping data for table web_service_db.t_people: ~5 rows (approximately)
/*!40000 ALTER TABLE `t_people` DISABLE KEYS */;
INSERT INTO `t_people` (`ID`, `FULL_NAME`, `PIN`) VALUES
	(81, 'Asen Ivanov', '1111111111'),
	(82, 'Ivan Georgiev', '6666666666'),
	(83, 'Ivan Petrov', '0987654321'),
	(84, 'Violina Nikolova', '7865748903'),
	(85, 'Maria Petrova', '8746785908'),
	(86, 'Иван Иванов', '8777766903');
/*!40000 ALTER TABLE `t_people` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;

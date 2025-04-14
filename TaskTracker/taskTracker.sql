/*
SQLyog Community v13.2.1 (64 bit)
MySQL - 10.4.24-MariaDB : Database - tasktracker
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`tasktracker` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

USE `tasktracker`;

/*Table structure for table `menadzer` */

DROP TABLE IF EXISTS `menadzer`;

CREATE TABLE `menadzer` (
  `menadzerID` bigint(20) NOT NULL AUTO_INCREMENT,
  `ime` varchar(50) NOT NULL,
  `prezime` varchar(50) NOT NULL,
  `email` varchar(100) NOT NULL,
  `lozinka` varchar(100) NOT NULL,
  PRIMARY KEY (`menadzerID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;

/*Data for the table `menadzer` */

insert  into `menadzer`(`menadzerID`,`ime`,`prezime`,`email`,`lozinka`) values 
(1,'Marko','Marković','marko.markovic@email.com','lozinka123'),
(2,'Jovana','Jovanović','jovana.jovanovic@email.com','lozinka456'),
(3,'Nikola','Nikolić','nikola.nikolic@email.com','lozinka789'),
(4,'Ana','Anić','ana.anic@email.com','lozinka101'),
(5,'Milos','Pavlovic','milos.pavlovic@email.com','password');

/*Table structure for table `posao` */

DROP TABLE IF EXISTS `posao`;

CREATE TABLE `posao` (
  `posaoID` bigint(20) NOT NULL AUTO_INCREMENT,
  `nazivPosla` varchar(100) NOT NULL,
  `opisPosla` text DEFAULT NULL,
  `datumKreiranjaPosla` date DEFAULT NULL,
  `menadzerID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`posaoID`),
  KEY `menadzerID` (`menadzerID`),
  CONSTRAINT `posao_ibfk_1` FOREIGN KEY (`menadzerID`) REFERENCES `menadzer` (`menadzerID`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4;

/*Data for the table `posao` */

insert  into `posao`(`posaoID`,`nazivPosla`,`opisPosla`,`datumKreiranjaPosla`,`menadzerID`) values 
(11,'Snimanje reklame za aplikaciju','Produkcija TV reklame za mobilnu aplikaciju','2025-04-15',5),
(12,'Snimanje podcasta','Tehnička produkcija za nedeljni podcast epizoda','2025-04-15',5),
(13,'Snimanje edukativnog kursa','Online kurs o osvetljenju i snimanju','2025-04-15',5),
(14,'Snimanje kratkog filma','Umetnički kratki film za filmski festival','2025-04-15',5),
(15,'Intervju sa sportistima','Serijal intervjua sa poznatim sportistima za YouTube','2025-04-15',5);

/*Table structure for table `pripadnostzadatkaposlu` */

DROP TABLE IF EXISTS `pripadnostzadatkaposlu`;

CREATE TABLE `pripadnostzadatkaposlu` (
  `posaoID` bigint(20) NOT NULL,
  `zadatakID` bigint(20) NOT NULL,
  `datumPocetkaPrip` date DEFAULT NULL,
  `datumZavrsetkaPrip` date DEFAULT NULL,
  PRIMARY KEY (`posaoID`,`zadatakID`),
  KEY `zadatakID` (`zadatakID`),
  CONSTRAINT `pripadnostzadatkaposlu_ibfk_1` FOREIGN KEY (`posaoID`) REFERENCES `posao` (`posaoID`),
  CONSTRAINT `pripadnostzadatkaposlu_ibfk_2` FOREIGN KEY (`zadatakID`) REFERENCES `zadatak` (`zadatakID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `pripadnostzadatkaposlu` */

insert  into `pripadnostzadatkaposlu`(`posaoID`,`zadatakID`,`datumPocetkaPrip`,`datumZavrsetkaPrip`) values 
(11,11,'2025-04-15',NULL),
(11,12,'2025-04-15',NULL),
(11,17,'2025-04-15',NULL),
(12,11,'2025-04-15',NULL),
(12,12,'2025-04-15',NULL),
(12,15,'2025-04-15',NULL),
(12,16,'2025-04-15',NULL),
(13,11,'2025-04-15',NULL),
(13,14,'2025-04-15',NULL),
(13,15,'2025-04-15',NULL),
(14,13,'2025-04-15',NULL),
(14,14,'2025-04-15',NULL),
(14,15,'2025-04-15',NULL),
(14,17,'2025-04-15',NULL),
(15,12,'2025-04-15',NULL),
(15,16,'2025-04-15',NULL);

/*Table structure for table `zadatak` */

DROP TABLE IF EXISTS `zadatak`;

CREATE TABLE `zadatak` (
  `zadatakID` bigint(20) NOT NULL AUTO_INCREMENT,
  `nazivZadatka` varchar(100) NOT NULL,
  `opisZadatka` text DEFAULT NULL,
  `datumKreiranjaZadatka` date DEFAULT NULL,
  `statusZadatka` enum('NEDOVRSEN','ZAVRSEN','U_TOKU') DEFAULT 'NEDOVRSEN',
  `menadzerID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`zadatakID`),
  KEY `menadzerID` (`menadzerID`),
  CONSTRAINT `zadatak_ibfk_1` FOREIGN KEY (`menadzerID`) REFERENCES `menadzer` (`menadzerID`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4;

/*Data for the table `zadatak` */

insert  into `zadatak`(`zadatakID`,`nazivZadatka`,`opisZadatka`,`datumKreiranjaZadatka`,`statusZadatka`,`menadzerID`) values 
(11,'Kupovina kamere Canon R5','Nabavka kamere potrebne za više produkcija','2025-04-15','NEDOVRSEN',5),
(12,'Zakup studija StudioCentar','Rezervacija prostora za snimanje','2025-04-15','NEDOVRSEN',5),
(13,'Montaža video materijala','Finalna montaža snimaka','2025-04-15','NEDOVRSEN',5),
(14,'Nabavka LED svetala','Svetlosna oprema za snimanja','2025-04-15','NEDOVRSEN',5),
(15,'Testiranje mikrofona Rode NTG3','Provera audio opreme pre snimanja','2025-04-15','NEDOVRSEN',5),
(16,'Dizajniranje univerzalnog introa','Intro sekvenca koja se koristi za više video formata','2025-04-15','NEDOVRSEN',5),
(17,'Potpisivanje ugovora sa glumcem','Angažovanje glumca koji glumi u više snimanja','2025-04-15','NEDOVRSEN',5);

/*Table structure for table `zaposleni` */

DROP TABLE IF EXISTS `zaposleni`;

CREATE TABLE `zaposleni` (
  `zaposleniID` bigint(20) NOT NULL AUTO_INCREMENT,
  `ime` varchar(50) NOT NULL,
  `prezime` varchar(50) NOT NULL,
  `telefon` varchar(20) DEFAULT NULL,
  `email` varchar(100) NOT NULL,
  `lozinka` varchar(100) NOT NULL,
  `menadzerID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`zaposleniID`),
  KEY `menadzerID` (`menadzerID`),
  CONSTRAINT `zaposleni_ibfk_1` FOREIGN KEY (`menadzerID`) REFERENCES `menadzer` (`menadzerID`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4;

/*Data for the table `zaposleni` */

insert  into `zaposleni`(`zaposleniID`,`ime`,`prezime`,`telefon`,`email`,`lozinka`,`menadzerID`) values 
(1,'Petar','Petrović','123456789','petar.petrovic@email.com','lozinka111',1),
(2,'Maja','Majić','987654321','maja.majic@email.com','lozinka222',2),
(3,'Ivan','Ivanić','112233445','ivan.ivanic@email.com','lozinka333',3),
(4,'Jelena','Jelenić','556677889','jelena.jelenic@email.com','lozinka444',4);

/*Table structure for table `zaposlenje` */

DROP TABLE IF EXISTS `zaposlenje`;

CREATE TABLE `zaposlenje` (
  `zaposleniID` bigint(20) NOT NULL,
  `posaoID` bigint(20) NOT NULL,
  `datumPocetkaZap` date DEFAULT NULL,
  `datumZavrsetkaZap` date DEFAULT NULL,
  PRIMARY KEY (`zaposleniID`,`posaoID`),
  KEY `posaoID` (`posaoID`),
  CONSTRAINT `zaposlenje_ibfk_1` FOREIGN KEY (`zaposleniID`) REFERENCES `zaposleni` (`zaposleniID`),
  CONSTRAINT `zaposlenje_ibfk_2` FOREIGN KEY (`posaoID`) REFERENCES `posao` (`posaoID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `zaposlenje` */

insert  into `zaposlenje`(`zaposleniID`,`posaoID`,`datumPocetkaZap`,`datumZavrsetkaZap`) values 
(1,13,'2025-04-15',NULL),
(1,14,'2025-04-15',NULL),
(2,11,'2025-04-15',NULL),
(2,12,'2025-04-15',NULL),
(2,13,'2025-04-15',NULL),
(3,11,'2025-04-15',NULL),
(3,12,'2025-04-15',NULL),
(3,15,'2025-04-15',NULL),
(4,12,'2025-04-15',NULL);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

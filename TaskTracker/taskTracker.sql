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
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4;

/*Data for the table `posao` */

insert  into `posao`(`posaoID`,`nazivPosla`,`opisPosla`,`datumKreiranjaPosla`,`menadzerID`) values 
(1,'Razvoj aplikacije','Razvijanje nove aplikacije za klijenta','2025-03-01',1),
(2,'Dizajn sajta','Dizajn početne stranice za projekat','2025-03-02',2),
(3,'Optimizacija sistema','Poboljšanje performansi servera','2025-03-03',3),
(4,'Planiranje projekta','Priprema planova za buduće projekte','2025-03-04',4),
(5,'Grabuljanje','Grabuljama lisce skupljam','2025-04-05',5),
(9,'Parafraziranje','Usluzivanje programa bar ja tako mislim','2025-04-05',5);

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
(1,2,'2025-04-10',NULL),
(1,6,'2025-04-10',NULL),
(2,2,'2025-03-02','2025-03-20'),
(2,4,'2025-04-10',NULL),
(3,4,'2025-04-10',NULL),
(4,3,'2025-04-10',NULL),
(4,4,'2025-03-04','2025-04-01'),
(4,6,'2025-04-10',NULL),
(4,7,'2025-04-10',NULL),
(5,3,'2025-04-10',NULL),
(5,6,'2025-04-10',NULL),
(9,1,'2025-04-10',NULL),
(9,3,'2025-04-14',NULL);

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
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4;

/*Data for the table `zadatak` */

insert  into `zadatak`(`zadatakID`,`nazivZadatka`,`opisZadatka`,`datumKreiranjaZadatka`,`statusZadatka`,`menadzerID`) values 
(1,'Dizajn početne stranice','Dizajn početne stranice za aplikaciju','2025-03-01','ZAVRSEN',1),
(2,'Optimizacija baza podataka','Optimizacija baza podataka za brži rad bravoo','2025-03-02','NEDOVRSEN',2),
(3,'Testiranje aplikacije','Testiranje novih funkcionalnosti u aplikaciji 2','2025-03-03','ZAVRSEN',3),
(4,'Priprema dokumentacije','Priprema tehničke dokumentacije za projekat','2025-03-04','U_TOKU',4),
(6,'Pranje sudova','Operi sudje','2025-04-07','ZAVRSEN',5),
(7,'Tandracak','nema','2025-04-10','NEDOVRSEN',5);

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
(4,'Jelena','Jelenić','556677889','jelena.jelenic@email.com','lozinka444',4),
(5,'Moca','Pavlovic','01555715500000','moca.pavlovic@email.com','password',5),
(6,'Zoki','Sumadinac','0677152999','zoki.sumadinac@gmail.com','password',5),
(9,'Pistac','Kerina','06515528888','pistac@mail.kor','987',5),
(10,'Brat Vasilije','Perovic','123456','b@g.s','bob',5);

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
(1,4,'2025-04-08',NULL),
(1,9,'2025-04-08',NULL),
(2,1,'2025-04-08',NULL),
(2,2,'2025-03-02','2025-06-02'),
(3,1,'2025-04-08',NULL),
(3,3,'2025-03-03','2025-06-03'),
(3,4,'2025-04-08',NULL),
(3,5,'2025-04-10',NULL),
(4,2,'2025-04-08',NULL),
(5,1,'2025-04-08',NULL),
(5,5,'2025-04-14',NULL),
(6,2,'2025-04-10',NULL),
(6,3,'2025-04-14',NULL),
(6,9,'2025-04-08',NULL),
(9,2,'2025-04-10',NULL),
(10,4,'2025-04-10',NULL),
(10,9,'2025-04-10',NULL);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

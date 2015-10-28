/*
SQLyog 企业版 - MySQL GUI v8.14 
MySQL - 5.6.10 : Database - blog
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`blog` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `blog`;

/*Table structure for table `admin` */

DROP TABLE IF EXISTS `admin`;

CREATE TABLE `admin` (
  `id` varchar(32) NOT NULL,
  `aname` varchar(20) DEFAULT NULL,
  `apassword` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `admin` */

insert  into `admin`(`id`,`aname`,`apassword`) values ('79ac562676c846349e6bad5f17db7b12','dante','202cb962ac59075b964b07152d234b70');

/*Table structure for table `article` */

DROP TABLE IF EXISTS `article`;

CREATE TABLE `article` (
  `aid` varchar(32) NOT NULL,
  `title` varchar(100) DEFAULT NULL,
  `cid` varchar(32) DEFAULT NULL,
  `adate` datetime DEFAULT NULL,
  `digest` varchar(300) DEFAULT NULL,
  `content` longtext,
  `acount` int(11) DEFAULT NULL,
  `atimestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `label` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`aid`),
  KEY `category_article_fk` (`cid`),
  CONSTRAINT `category_article_fk` FOREIGN KEY (`cid`) REFERENCES `category` (`cid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `article` */

/*Table structure for table `articlelabel` */

DROP TABLE IF EXISTS `articlelabel`;

CREATE TABLE `articlelabel` (
  `aid` varchar(255) NOT NULL,
  `lid` varchar(255) NOT NULL,
  PRIMARY KEY (`aid`,`lid`),
  KEY `FKE76B6B9E14FAB80E` (`lid`),
  KEY `FKE76B6B9E93288405` (`aid`),
  CONSTRAINT `FKE76B6B9E14FAB80E` FOREIGN KEY (`lid`) REFERENCES `label` (`id`),
  CONSTRAINT `FKE76B6B9E93288405` FOREIGN KEY (`aid`) REFERENCES `article` (`aid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `articlelabel` */

/*Table structure for table `category` */

DROP TABLE IF EXISTS `category`;

CREATE TABLE `category` (
  `cid` varchar(32) NOT NULL,
  `cname` varchar(30) DEFAULT NULL,
  `descr` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`cid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `category` */

insert  into `category`(`cid`,`cname`,`descr`) values ('72e195a10fb04943a2022df1748f0406','心情文章','描述心路历程');

/*Table structure for table `clicks` */

DROP TABLE IF EXISTS `clicks`;

CREATE TABLE `clicks` (
  `aid` varchar(32) NOT NULL DEFAULT '',
  `times` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`aid`,`times`),
  CONSTRAINT `article_clicks_fk` FOREIGN KEY (`aid`) REFERENCES `article` (`aid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `clicks` */

/*Table structure for table `comments` */

DROP TABLE IF EXISTS `comments`;

CREATE TABLE `comments` (
  `cmid` varchar(32) NOT NULL,
  `content` text,
  `cdate` datetime DEFAULT NULL,
  `cname` varchar(100) DEFAULT NULL,
  `aid` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`cmid`),
  KEY `article_comments_fk` (`aid`),
  CONSTRAINT `article_comments_fk` FOREIGN KEY (`aid`) REFERENCES `article` (`aid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `comments` */

/*Table structure for table `gallery` */

DROP TABLE IF EXISTS `gallery`;

CREATE TABLE `gallery` (
  `gid` varchar(32) NOT NULL,
  `gname` varchar(100) DEFAULT NULL,
  `gdate` date DEFAULT NULL,
  `gcover` varchar(150) DEFAULT NULL,
  PRIMARY KEY (`gid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `gallery` */

/*Table structure for table `label` */

DROP TABLE IF EXISTS `label`;

CREATE TABLE `label` (
  `id` varchar(32) NOT NULL,
  `lname` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `label` */

/*Table structure for table `photo` */

DROP TABLE IF EXISTS `photo`;

CREATE TABLE `photo` (
  `id` varchar(32) NOT NULL,
  `purl` varchar(150) DEFAULT NULL,
  `gid` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `gallery_photo_fk` (`gid`),
  CONSTRAINT `gallery_photo_fk` FOREIGN KEY (`gid`) REFERENCES `gallery` (`gid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `photo` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

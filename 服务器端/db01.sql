/*
SQLyog Community v13.1.6 (64 bit)
MySQL - 5.7.19 : Database - db01
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`db01` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci */;

USE `db01`;

/*Table structure for table `db01` */

DROP TABLE IF EXISTS `db01`;

CREATE TABLE `db01` (
  `id` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `type` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `notice` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `db01` */

insert  into `db01`(`id`,`type`,`notice`) values 
('1','小雨','雨虽小，注意保暖别感冒'),
('2','多云','阴晴之间，谨防紫外线侵扰'),
('3','晴','愿你拥有比阳光明媚的心情'),
('4','阴','不要被阴云遮挡住好心情'),
('5','中雨','记得随身携带雨伞哦');

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`,`username`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `user` */

insert  into `user`(`id`,`username`,`password`) values 
(1,'ljg','hh'),
(2,'dd','dd'),
(3,'gg','湖南'),
(4,'tom','合肥'),
(5,'哈哈','湖南'),
(6,'tt','湖南'),
(7,'ljh','xx'),
(8,'123','123'),
(9,'381','321'),
(10,'321','321'),
(12,'456','456'),
(15,'234','234'),
(16,'345','234'),
(17,'q','q');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

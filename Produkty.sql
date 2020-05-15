DROP DATABASE IF EXISTS `kcal`;
CREATE DATABASE `kcal`;

USE kcal;
DROP TABLE IF EXISTS `dailyproducts`;
CREATE TABLE `dailyproducts` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `userID` int(11) DEFAULT NULL,
  `ProductName` varchar(255) DEFAULT NULL,
  `Carbo` double DEFAULT NULL,
  `Whey` double DEFAULT NULL,
  `Fats` double DEFAULT NULL,
  `MealNo` int(11) DEFAULT NULL,
  `Weight` double DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=UTF8;

DROP TABLE IF EXISTS `products`;
CREATE TABLE `products` (
  `ProductID` int(11) NOT NULL AUTO_INCREMENT,
  `userID` INT(11) NOT NULL,
  `ProductName` varchar(255) DEFAULT NULL,
  `ProductCarbo` double DEFAULT NULL,
  `ProductWhey` double DEFAULT NULL,
  `ProductFats` double DEFAULT NULL,
  PRIMARY KEY (`ProductID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=UTF8;

DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `userID` int(11) NOT NULL AUTO_INCREMENT,
  `userName` varchar(255) NOT NULL,
  `password` VARCHAR(255) NOT NULL,
  `preferedKcal` int DEFAULT NULL,
  PRIMARY KEY (`userID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=UTF8;

DROP TABLE IF EXISTS `usersWeight`;
CREATE TABLE `usersWeight` (
  `userID` int(11) NOT NULL,
  `date` varchar(255) DEFAULT NULL,
  `bodyWeight` double DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=UTF8;

INSERT INTO `products` VALUES (1,'Karkówka wołowa',0,20.5,7);
INSERT INTO `products` VALUES (2,'Skrzydełka z KFC',9,19,22);
INSERT INTO `products` VALUES (3,'Ketchup Winiary',20,1.9,0.5);
INSERT INTO `products` VALUES (4,'Pierś z kurczaka',0,21.8,3.7);
INSERT INTO `products` VALUES (5,'Olej rzepakowy',0,0,92);
INSERT INTO `products` VALUES (6,'Śmietana 18%',3.7,2.7,19);
INSERT INTO `products` VALUES (7,'Jogurt Naturalny',4.2,4.9,3);
INSERT INTO `products` VALUES (8,'Mozzarella biała',2,18,19);
INSERT INTO `products` VALUES (9,'Jogurt proteinowy Pilos',3.3,8.8,0);
INSERT INTO `products` VALUES (10,'Musli crunchy Cronfield Orzech',62,9.3,15);
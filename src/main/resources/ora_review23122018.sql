-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Dec 23, 2018 at 04:27 PM
-- Server version: 5.7.23
-- PHP Version: 7.2.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `ora_review`
--

-- --------------------------------------------------------

--
-- Table structure for table `booking_vs_rating`
--

DROP TABLE IF EXISTS `booking_vs_rating`;
CREATE TABLE IF NOT EXISTS `booking_vs_rating` (
  `booking_rating_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` bigint(20) DEFAULT NULL,
  `created_date` varchar(255) DEFAULT NULL,
  `modified_by` bigint(20) DEFAULT NULL,
  `modified_date` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `property_id` bigint(20) DEFAULT NULL,
  `rating` varchar(255) DEFAULT NULL,
  `rating_id` bigint(20) NOT NULL,
  `user_review_id` bigint(20) NOT NULL,
  PRIMARY KEY (`booking_rating_id`),
  KEY `FKiee6xtfv1thmr9ucouwi3jm93` (`rating_id`),
  KEY `FKkevr8f4ymijf42ytodcf4fxu8` (`user_review_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `master_rating`
--

DROP TABLE IF EXISTS `master_rating`;
CREATE TABLE IF NOT EXISTS `master_rating` (
  `rating_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` bigint(20) DEFAULT NULL,
  `created_date` varchar(255) DEFAULT NULL,
  `modified_by` bigint(20) DEFAULT NULL,
  `modified_date` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `rating_name` varchar(255) DEFAULT NULL,
  `user_type_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`rating_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `master_rating`
--

INSERT INTO `master_rating` (`rating_id`, `created_by`, `created_date`, `modified_by`, `modified_date`, `status`, `rating_name`, `user_type_id`) VALUES
(1, 1, '2018-12-10 22:22:31', NULL, NULL, 1, 'Cleanliness', '2'),
(2, 1, '2018-12-10 22:22:31', NULL, NULL, 1, 'Service', '2'),
(3, 1, '2018-12-15 01:27:34', NULL, NULL, 1, 'Host Behaviour', '2'),
(4, 1, '2018-12-10 22:22:31', NULL, NULL, 1, 'Customer Behaviour', '3');

-- --------------------------------------------------------

--
-- Table structure for table `master_user_review`
--

DROP TABLE IF EXISTS `master_user_review`;
CREATE TABLE IF NOT EXISTS `master_user_review` (
  `user_review_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` bigint(20) DEFAULT NULL,
  `created_date` varchar(255) DEFAULT NULL,
  `modified_by` bigint(20) DEFAULT NULL,
  `modified_date` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `booking_id` bigint(20) DEFAULT NULL,
  `comment` varchar(255) DEFAULT NULL,
  `language_id` bigint(20) DEFAULT NULL,
  `parent_id` bigint(20) DEFAULT NULL,
  `property_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `user_type_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`user_review_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `booking_vs_rating`
--
ALTER TABLE `booking_vs_rating`
  ADD CONSTRAINT `FKiee6xtfv1thmr9ucouwi3jm93` FOREIGN KEY (`rating_id`) REFERENCES `master_rating` (`rating_id`),
  ADD CONSTRAINT `FKkevr8f4ymijf42ytodcf4fxu8` FOREIGN KEY (`user_review_id`) REFERENCES `master_user_review` (`user_review_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

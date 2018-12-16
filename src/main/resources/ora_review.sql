-- phpMyAdmin SQL Dump
-- version 4.5.2
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Dec 16, 2018 at 02:01 PM
-- Server version: 5.7.9
-- PHP Version: 5.6.16

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
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

--
-- Dumping data for table `booking_vs_rating`
--

INSERT INTO `booking_vs_rating` (`booking_rating_id`, `created_by`, `created_date`, `modified_by`, `modified_date`, `status`, `property_id`, `rating`, `rating_id`, `user_review_id`) VALUES
(1, 1, '2018-12-16 18:33:19', NULL, NULL, 1, NULL, '5', 1, 1),
(2, 1, '2018-12-16 18:33:20', NULL, NULL, 1, NULL, '4', 2, 1);

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
  PRIMARY KEY (`rating_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `master_rating`
--

INSERT INTO `master_rating` (`rating_id`, `created_by`, `created_date`, `modified_by`, `modified_date`, `status`, `rating_name`) VALUES
(1, 1, '2018-09-06 01:27:34', NULL, NULL, 1, 'Cleanliness'),
(2, 1, '2018-09-06 01:27:34', NULL, NULL, 1, 'Dining'),
(3, 1, '2018-09-06 01:27:34', NULL, NULL, 1, 'Service');

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
  PRIMARY KEY (`user_review_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `master_user_review`
--

INSERT INTO `master_user_review` (`user_review_id`, `created_by`, `created_date`, `modified_by`, `modified_date`, `status`, `booking_id`, `comment`, `language_id`, `parent_id`, `property_id`, `user_id`) VALUES
(1, 1, '2018-12-16 18:33:12', NULL, NULL, 1, 2, 'Testing', 1, NULL, 1, 1);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `booking_vs_rating`
--
ALTER TABLE `booking_vs_rating`
  ADD CONSTRAINT `FKiee6xtfv1thmr9ucouwi3jm93` FOREIGN KEY (`rating_id`) REFERENCES `master_rating` (`rating_id`),
  ADD CONSTRAINT `FKkevr8f4ymijf42ytodcf4fxu8` FOREIGN KEY (`user_review_id`) REFERENCES `master_user_review` (`user_review_id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

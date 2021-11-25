-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Dec 12, 2017 at 06:33 PM
-- Server version: 10.1.19-MariaDB
-- PHP Version: 7.0.13

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `university`
--

-- --------------------------------------------------------

--
-- Table structure for table `course`
--

CREATE TABLE `course` (
  `courseId` int(11) NOT NULL,
  `courseName` varchar(500) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `course`
--

INSERT INTO `course` (`courseId`, `courseName`) VALUES
(1, 'Physical Science'),
(2, 'Industrial Statistics'),
(3, 'Biological Science');

-- --------------------------------------------------------

--
-- Table structure for table `module`
--

CREATE TABLE `module` (
  `moduleCode` int(11) NOT NULL,
  `moduleName` varchar(500) NOT NULL,
  `credits` int(11) NOT NULL,
  `courseId` int(11) NOT NULL,
  `year` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `module`
--

INSERT INTO `module` (`moduleCode`, `moduleName`, `credits`, `courseId`, `year`) VALUES
(11001, 'Modern Physics', 2, 1, 1),
(11002, 'Thermodynamics', 1, 1, 1),
(11003, 'Differential Equations', 2, 1, 1),
(11004, 'Statistical Computing', 2, 1, 1),
(11005, 'Organic Chemistry', 3, 1, 1),
(11006, 'Calculus', 2, 1, 1),
(11007, 'Physical Laboratory ', 2, 1, 2),
(12002, 'Electromagnetic Theory', 2, 1, 2),
(12003, 'Differential Equations 2', 2, 1, 2),
(12004, 'Linear Programming', 2, 1, 2),
(12005, 'Bio Chemistry', 3, 1, 2),
(12006, 'Software Engineering', 3, 1, 2),
(13001, 'Quantum Mechanics', 3, 1, 3),
(13002, 'Nuclear Physics', 3, 1, 3),
(13003, 'Mathematical Methods', 2, 1, 3),
(13004, 'Regression Analysis', 2, 1, 3),
(13005, 'Algebra', 3, 1, 3),
(13006, 'Visual Programming', 3, 1, 3),
(14001, 'Computational Chemistry', 2, 1, 4),
(14002, 'Complex Analysis', 3, 1, 4),
(14003, 'Database Systems', 2, 1, 4),
(14004, 'Sampling Techniques', 1, 1, 4),
(14005, 'Financial Mathematics', 3, 1, 4),
(14006, 'Industrial Chemistry', 2, 1, 4),
(21001, 'Probability', 3, 2, 1),
(21002, 'Statistical Computing', 2, 2, 1),
(21003, 'Linear Programming', 3, 2, 1),
(21004, 'Survey', 2, 2, 1),
(21005, 'Calculus', 3, 2, 1),
(22001, 'Statistical Inference', 3, 2, 2),
(22002, 'Matrices', 2, 2, 2),
(22003, 'Actuarial Mathematics', 2, 2, 2),
(22004, 'Logical Analysis', 2, 2, 2),
(22005, 'Quality Control', 1, 2, 2),
(22006, 'Software Engineering', 3, 2, 2),
(22007, 'Calculus 2', 3, 2, 2),
(23001, 'Regression Analysis', 3, 2, 3),
(23002, 'Calculus 3', 2, 2, 3),
(23003, 'Numerical Methods', 2, 2, 3),
(23004, 'Sampling Techniques', 1, 2, 3),
(23005, 'Visual Programming', 3, 2, 3),
(24001, 'Game Theory', 3, 2, 4),
(24002, 'Time Series', 2, 2, 4),
(24003, 'Operational Research', 3, 2, 4),
(24004, 'Multivariate Methods', 2, 2, 4),
(31001, 'Plant Resources', 1, 3, 1),
(31002, 'Flora', 1, 3, 1),
(31003, 'Organic Chemistry', 3, 3, 1),
(31004, 'Cell Biology', 2, 3, 1),
(31005, 'Calculation Chemistry', 2, 3, 1),
(31006, 'Animal Behavior', 2, 3, 1),
(32001, 'Micro Biology', 1, 3, 2),
(32002, 'Bio Statistics', 2, 3, 2),
(32003, 'Bio Chemistry', 3, 3, 2),
(32004, 'Systems', 2, 3, 2),
(32005, 'Animal Form', 2, 3, 2),
(32006, 'Plant Development', 3, 3, 2),
(32007, 'Software Engineering', 3, 3, 2),
(33001, 'Molecular Biology', 2, 3, 3),
(33002, 'Quality Managemnet', 2, 3, 3),
(33003, 'Economic Zoology', 2, 3, 3),
(33004, 'Mammalian Biology\n', 2, 3, 3),
(33005, 'Chemical Technology\n', 1, 3, 3),
(33006, 'Pharmaceutical Chemistry', 3, 3, 3),
(34001, 'Plant Pathology', 3, 3, 4),
(34002, 'Anthropology', 3, 3, 4),
(34003, 'Horticulture', 3, 3, 4),
(34004, 'Industrial Chemistry', 2, 3, 4);

-- --------------------------------------------------------

--
-- Table structure for table `student`
--

CREATE TABLE `student` (
  `indexNo` int(11) NOT NULL,
  `regNo` int(11) NOT NULL,
  `studentName` varchar(500) NOT NULL,
  `courseId` int(11) NOT NULL,
  `gpa` double NOT NULL DEFAULT '0',
  `year` int(11) NOT NULL,
  `marksEntered` int(11) NOT NULL DEFAULT '0',
  `credits` int(11) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `student`
--

INSERT INTO `student` (`indexNo`, `regNo`, `studentName`, `courseId`, `gpa`, `year`, `marksEntered`, `credits`) VALUES
(1, 1, 'Kasun', 1, 3.6538461538461537, 3, 2, 26);

-- --------------------------------------------------------

--
-- Table structure for table `studentmodule`
--

CREATE TABLE `studentmodule` (
  `indexNo` int(11) NOT NULL,
  `moduleCode` int(11) NOT NULL,
  `moduleMarks` int(11) DEFAULT '0',
  `grade` char(1) NOT NULL DEFAULT '0',
  `gpv` double NOT NULL DEFAULT '0',
  `pass` tinyint(1) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `studentmodule`
--

INSERT INTO `studentmodule` (`indexNo`, `moduleCode`, `moduleMarks`, `grade`, `gpv`, `pass`) VALUES
(1, 11001, 88, 'A', 4, 1),
(1, 11002, 77, 'B', 3, 1),
(1, 11003, 55, 'C', 2, 1),
(1, 11004, 99, 'A', 4, 1),
(1, 11005, 87, 'A', 4, 1),
(1, 11006, 66, 'B', 3, 1),
(1, 11007, 88, 'A', 4, 1),
(1, 12002, 77, 'B', 3, 1),
(1, 12003, 88, 'A', 4, 1),
(1, 12004, 88, 'A', 4, 1),
(1, 12005, 88, 'A', 4, 1),
(1, 12006, 99, 'A', 4, 1),
(1, 13001, 0, '0', 0, 0),
(1, 13002, 0, '0', 0, 0),
(1, 13003, 0, '0', 0, 0),
(1, 13004, 0, '0', 0, 0),
(1, 13005, 0, '0', 0, 0),
(1, 13006, 0, '0', 0, 0),
(1, 14001, 0, '0', 0, 0),
(1, 14002, 0, '0', 0, 0),
(1, 14003, 0, '0', 0, 0),
(1, 14004, 0, '0', 0, 0),
(1, 14005, 0, '0', 0, 0),
(1, 14006, 0, '0', 0, 0);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `course`
--
ALTER TABLE `course`
  ADD PRIMARY KEY (`courseId`);

--
-- Indexes for table `module`
--
ALTER TABLE `module`
  ADD PRIMARY KEY (`moduleCode`),
  ADD KEY `fk_course` (`courseId`);

--
-- Indexes for table `student`
--
ALTER TABLE `student`
  ADD PRIMARY KEY (`indexNo`),
  ADD KEY `fk_courseCons` (`courseId`);

--
-- Indexes for table `studentmodule`
--
ALTER TABLE `studentmodule`
  ADD PRIMARY KEY (`indexNo`,`moduleCode`),
  ADD KEY `fk_moduleCons` (`moduleCode`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `module`
--
ALTER TABLE `module`
  ADD CONSTRAINT `fk_course` FOREIGN KEY (`courseId`) REFERENCES `course` (`courseId`);

--
-- Constraints for table `student`
--
ALTER TABLE `student`
  ADD CONSTRAINT `fk_courseCons` FOREIGN KEY (`courseId`) REFERENCES `course` (`courseId`);

--
-- Constraints for table `studentmodule`
--
ALTER TABLE `studentmodule`
  ADD CONSTRAINT `fk_moduleCons` FOREIGN KEY (`moduleCode`) REFERENCES `module` (`moduleCode`),
  ADD CONSTRAINT `fk_studentCons` FOREIGN KEY (`indexNo`) REFERENCES `student` (`indexNo`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

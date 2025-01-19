-- phpMyAdmin SQL Dump
-- version 4.9.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 20, 2020 at 04:16 AM
-- Server version: 10.4.11-MariaDB
-- PHP Version: 7.4.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `librarydb`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `Username` varchar(20) NOT NULL,
  `Password` varchar(40) DEFAULT NULL,
  `AccountCreated` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `AccountStatus` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`Username`, `Password`, `AccountCreated`, `AccountStatus`) VALUES
('abc', 'abc', '2020-01-20 02:29:12', 1),
('admin', 'admin', '2020-01-19 18:59:00', 1),
('superadmin', 'superadmin', '2020-01-19 18:59:27', 1),
('tyu', 'tyu', '2020-01-19 19:08:08', 0);

-- --------------------------------------------------------

--
-- Table structure for table `booklist`
--

CREATE TABLE `booklist` (
  `ID` int(10) NOT NULL,
  `Title` varchar(50) NOT NULL,
  `Author` varchar(50) NOT NULL,
  `Publisher` varchar(50) NOT NULL,
  `Type` varchar(20) NOT NULL,
  `Category` varchar(50) DEFAULT NULL,
  `IsAvailable` tinyint(1) DEFAULT 1,
  `Quantity` int(11) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `booklist`
--

INSERT INTO `booklist` (`ID`, `Title`, `Author`, `Publisher`, `Type`, `Category`, `IsAvailable`, `Quantity`) VALUES
(1, 'Meditations', 'Marcus Aurelius', 'Modern Library', '', NULL, 1, 1),
(2, 'Histories of Nations', 'Peter Furtado', 'James & Hudson', '', NULL, 0, 1),
(3, 'Great Cat Tales', 'Lesley O\'Mara', 'Carroll & Graff', '', NULL, 1, 1),
(4, 'Mein Kampo', 'Adolf Hitler', 'JAICO', '', NULL, 1, 1),
(5, 'Contagious', 'Jonah Berger', 'Simon & Schuster', 'Non Fiction', 'Self-help Book', 1, 1),
(6, 'Introduction to Philosophy', 'Christine Ramos', 'Rex Publishing', 'Non Fiction', 'Textbook', 1, 1),
(7, 'Jose Rizal', 'Gregorio Zaide', 'All Nations Publishing', 'Non Fiction', 'Biography/Autobiography', 0, 1),
(8, 'ERIC', 'POGI', 'POGI PUBLISHING', 'Fiction', 'Action and Adventure', 1, 1),
(10, 'Meditations', 'Marcus Aurelius', 'Modern Library', '', NULL, 1, 1),
(11, 'qqq', 'www', 'eee', 'Fiction', 'Action and Adventure', 0, 1),
(12, 'qqq', 'www', 'eee', 'Fiction', 'Action and Adventure', 1, 1),
(13, 'qqq', 'www', 'eee', 'Fiction', 'Action and Adventure', 1, 1),
(14, 'tt', 'yy', 'uu', 'Fiction', 'Drama', 1, 1),
(15, 'tt', 'yy', 'uu', 'Fiction', 'Drama', 1, 1),
(16, 'Emotional Intelligence', 'Daniel Goleman', 'Bantam Books', 'Non Fiction', 'Narrative Nonfiction', 1, 1),
(17, 'Emotional Intelligence', 'Daniel Goleman', 'Bantam Books', 'Non Fiction', 'Narrative Nonfiction', 1, 1),
(18, 'Emotional Intelligence', 'Daniel Goleman', 'Bantam Books', 'Non Fiction', 'Narrative Nonfiction', 1, 1),
(19, 'Emotional Intelligence', 'Daniel Goleman', 'Bantam Books', 'Non Fiction', 'Narrative Nonfiction', 1, 1),
(20, 'Art Of War', 'Sun Tzu', 'Delta PaperBacks', 'Non Fiction', 'Reference Books', 1, 1),
(21, 'Art Of War', 'Sun Tzu', 'Delta PaperBacks', 'Non Fiction', 'Reference Books', 1, 1),
(22, 'Art Of War', 'Sun Tzu', 'Delta PaperBacks', 'Non Fiction', 'Reference Books', 1, 1),
(23, 'Art Of War', 'Sun Tzu', 'Delta PaperBacks', 'Non Fiction', 'Reference Books', 1, 1),
(24, 'Art Of War', 'Sun Tzu', 'Delta PaperBacks', 'Non Fiction', 'Reference Books', 0, 1);

-- --------------------------------------------------------

--
-- Table structure for table `issuedbooklist`
--

CREATE TABLE `issuedbooklist` (
  `BookID` varchar(10) NOT NULL,
  `MemberID` varchar(10) NOT NULL,
  `DateIssued` datetime NOT NULL,
  `ReturnDate` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `issuedbooklist`
--

INSERT INTO `issuedbooklist` (`BookID`, `MemberID`, `DateIssued`, `ReturnDate`) VALUES
('B002', 'M001', '2020-01-09 10:27:55', '2020-01-17 00:00:00'),
('B007', 'M003', '2020-01-12 14:13:34', '2020-01-17 00:00:00'),
('24', '1', '2020-01-20 10:03:30', '2020-01-25 00:00:00'),
('11', '1', '2020-01-20 11:09:15', '2020-01-25 00:00:00');

-- --------------------------------------------------------

--
-- Table structure for table `memberlist`
--

CREATE TABLE `memberlist` (
  `ID` int(10) NOT NULL,
  `Name` char(100) DEFAULT NULL,
  `Email` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `memberlist`
--

INSERT INTO `memberlist` (`ID`, `Name`, `Email`) VALUES
(1, 'Eric dela Cruz', 'eric@mail.com'),
(2, 'Marion Dela Cruz', 'marion@mail.com');

-- --------------------------------------------------------

--
-- Table structure for table `receivedbooklist`
--

CREATE TABLE `receivedbooklist` (
  `BookID` varchar(10) NOT NULL,
  `MemberID` varchar(10) NOT NULL,
  `DateReturned` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `receivedbooklist`
--

INSERT INTO `receivedbooklist` (`BookID`, `MemberID`, `DateReturned`) VALUES
('B001', 'M001', '2020-01-09 09:46:38'),
('B001', 'M001', '2020-01-19 22:19:52'),
('24', '1', '2020-01-20 10:03:03');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`Username`);

--
-- Indexes for table `booklist`
--
ALTER TABLE `booklist`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `memberlist`
--
ALTER TABLE `memberlist`
  ADD PRIMARY KEY (`ID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `booklist`
--
ALTER TABLE `booklist`
  MODIFY `ID` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;

--
-- AUTO_INCREMENT for table `memberlist`
--
ALTER TABLE `memberlist`
  MODIFY `ID` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

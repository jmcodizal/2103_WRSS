-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 24, 2024 at 12:14 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `wrss_2103`
--

-- --------------------------------------------------------

--
-- Table structure for table `admins`
--

CREATE TABLE `admins` (
  `AdminID` int(11) NOT NULL,
  `Name` varchar(100) NOT NULL,
  `UserName` varchar(50) NOT NULL,
  `Email` varchar(100) NOT NULL,
  `Password` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `barangaydeliveries`
--

CREATE TABLE `barangaydeliveries` (
  `BarangayID` int(11) NOT NULL,
  `BarangayName` varchar(255) NOT NULL,
  `DeliveryCount` int(11) DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `containersummary`
--

CREATE TABLE `containersummary` (
  `ContainerID` int(11) NOT NULL,
  `ContainerType` varchar(50) NOT NULL,
  `TotalQuantitySold` int(11) DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `customers`
--

CREATE TABLE `customers` (
  `CustomerID` int(11) NOT NULL,
  `Name` varchar(100) NOT NULL,
  `Barangay` varchar(100) DEFAULT NULL,
  `ContactNumber` varchar(15) NOT NULL,
  `PaymentMethod` varchar(50) DEFAULT NULL,
  `DeliveryDate` date DEFAULT NULL,
  `GcashAmount` decimal(10,2) DEFAULT 0.00,
  `ContainerType` varchar(50) DEFAULT NULL,
  `Quantity` int(11) DEFAULT NULL,
  `GcashName` varchar(100) NOT NULL,
  `GcashNumber` varchar(20) NOT NULL,
  `SalesDate` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `dailysales`
--

CREATE TABLE `dailysales` (
  `SalesDate` date NOT NULL,
  `TotalSalesAmount` decimal(10,2) NOT NULL,
  `SalesByBarangay` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL CHECK (json_valid(`SalesByBarangay`))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `sales`
--

CREATE TABLE `sales` (
  `SaleID` int(11) NOT NULL,
  `CustomerID` int(11) DEFAULT NULL,
  `SaleAmount` decimal(10,2) NOT NULL,
  `SaleDate` date DEFAULT curdate(),
  `Barangay` varchar(255) NOT NULL,
  `ContainerType` varchar(50) NOT NULL,
  `Quantity` int(11) NOT NULL,
  `PaymentMethod` varchar(50) NOT NULL,
  `GcashAmount` decimal(10,2) DEFAULT 0.00
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `systemmetrics`
--

CREATE TABLE `systemmetrics` (
  `MetricID` int(11) NOT NULL,
  `TotalWalkIns` int(11) DEFAULT 0,
  `TotalDeliveries` int(11) DEFAULT 0,
  `TotalSales` decimal(10,2) DEFAULT 0.00,
  `TotalGcashPayments` decimal(10,2) DEFAULT 0.00,
  `DateRecorded` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admins`
--
ALTER TABLE `admins`
  ADD PRIMARY KEY (`AdminID`),
  ADD UNIQUE KEY `UserName` (`UserName`);

--
-- Indexes for table `barangaydeliveries`
--
ALTER TABLE `barangaydeliveries`
  ADD PRIMARY KEY (`BarangayID`);

--
-- Indexes for table `containersummary`
--
ALTER TABLE `containersummary`
  ADD PRIMARY KEY (`ContainerID`);

--
-- Indexes for table `customers`
--
ALTER TABLE `customers`
  ADD PRIMARY KEY (`CustomerID`);

--
-- Indexes for table `dailysales`
--
ALTER TABLE `dailysales`
  ADD PRIMARY KEY (`SalesDate`);

--
-- Indexes for table `sales`
--
ALTER TABLE `sales`
  ADD PRIMARY KEY (`SaleID`),
  ADD KEY `CustomerID` (`CustomerID`);

--
-- Indexes for table `systemmetrics`
--
ALTER TABLE `systemmetrics`
  ADD PRIMARY KEY (`MetricID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `admins`
--
ALTER TABLE `admins`
  MODIFY `AdminID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `barangaydeliveries`
--
ALTER TABLE `barangaydeliveries`
  MODIFY `BarangayID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `containersummary`
--
ALTER TABLE `containersummary`
  MODIFY `ContainerID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `customers`
--
ALTER TABLE `customers`
  MODIFY `CustomerID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `sales`
--
ALTER TABLE `sales`
  MODIFY `SaleID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `systemmetrics`
--
ALTER TABLE `systemmetrics`
  MODIFY `MetricID` int(11) NOT NULL AUTO_INCREMENT;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `sales`
--
ALTER TABLE `sales`
  ADD CONSTRAINT `sales_ibfk_1` FOREIGN KEY (`CustomerID`) REFERENCES `customers` (`CustomerID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

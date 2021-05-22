-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 22, 2021 at 07:22 PM
-- Server version: 10.4.19-MariaDB
-- PHP Version: 8.0.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `drinkwholesale`
--

DELIMITER $$
--
-- Procedures
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `CProduct` (IN `ProductName` VARCHAR(100), IN `Manufacturer` VARCHAR(100), IN `Abv` TINYINT, IN `CurrStock` INT, IN `MinStock` INT, IN `WarehouseID` INT)  INSERT INTO `product`(`ProductName`, `Manufacturer`, `Abv`, `CurrStock`, `MinStock`, WarehouseID) VALUES (ProductName,Manufacturer,Abv,CurrStock,MinStock, WarehouseID)$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `CWarehouse` (IN `City` INT, IN `Address` VARCHAR(250))  INSERT INTO `warehouse`( `City`, `Address`) VALUES (City,Address)$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `CWorker` (IN `FName` VARCHAR(50), IN `SName` VARCHAR(50), IN `ShiftLength` TINYINT, IN `ShiftStart` INT, IN `FWarehouseID` INT)  INSERT INTO `worker`(`FName`, `SName`, `ShiftLenght`, `ShiftStart`, `FWarehouseID`) VALUES (FName,SName,ShiftLength,ShiftStart,FWarehouseID)$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `DProduct` (IN `ProductID` INT)  update `product` SET product.Visible = 0 where product.ProductId = ProductID$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `DWarehouse` (IN `WarehouseID` INT)  Update `warehouse` set warehouse.Visible = 0 where warehouse.Id = WarehouseID$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `DWorker` (IN `WorkerID` INT)  Update worker set Visible = 0 where worker.WorkerId = WorkerID$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `getProductByABV` (IN `iAbv` INT)  SELECT * from product where product.Abv = iAbv and visible = 1$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `getProductStatus` (IN `ProductID` INT)  SELECT (product.CurrStock - product.MinStock) as Difference FROM product where product.ProductId = ProductID and product.Visible = 1$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `getWarehouseByCity` (IN `cityID` INT)  SELECT * from warehouse where warehouse.City = cityID and warehouse.Visible = 1$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `getWorkerByWarehouse` (IN `whID` INT)  select * from worker where worker.FWarehouseID = whID and visible = 1$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `RProduct` ()  SELECT * FROM `product` WHERE product.Visible = 1$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `RWarehouse` ()  SELECT * FROM `warehouse` WHERE warehouse.Visible = 1$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `RWoker` ()  SELECT * from worker where worker.Visible = 1$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `UProduct` (IN `ProductName` INT, IN `Manufacturer` INT, IN `Abv` INT, IN `CurrStock` INT, IN `MinStock` INT, IN `ProductID` INT, IN `WarehouseID` INT)  UPDATE `product` 
SET `ProductName`=ProductName,`Manufacturer`=Manufacturer,`Abv`=Abv,`CurrStock`=CurrStock,`MinStock`=MinStock,`WarehouseID`=WarehouseID WHERE ProductId = product.ProductId$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `UWarehouse` (IN `WarehouseID` INT, IN `City` INT, IN `Address` VARCHAR(250))  UPDATE `warehouse` SET `City`=City,`Address`=Address WHERE warehouse.Id = WarehouseID$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `UWorker` (IN `WorkerID` INT, IN `FName` VARCHAR(50), IN `SName` VARCHAR(50), IN `ShiftLength` TINYINT, IN `ShiftStart` INT, IN `FWarehouseID` INT)  UPDATE `worker`
SET `FName`=FName,`SName`=SName,`ShiftLenght`=ShiftLength,`ShiftStart`=ShiftStart,`FWarehouseID`=FWarehouseID
WHERE worker.WorkerId = WorkerID$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `product`
--

CREATE TABLE `product` (
  `ProductName` varchar(100) NOT NULL,
  `Manufacturer` varchar(100) NOT NULL,
  `Abv` tinyint(4) NOT NULL,
  `CurrStock` int(11) NOT NULL,
  `MinStock` int(11) NOT NULL,
  `ProductId` int(11) NOT NULL,
  `WarehouseID` int(11) NOT NULL,
  `Visible` tinyint(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `product`
--

INSERT INTO `product` (`ProductName`, `Manufacturer`, `Abv`, `CurrStock`, `MinStock`, `ProductId`, `WarehouseID`, `Visible`) VALUES
('Szalon sör', 'Pécsi sörfőzde', 47, 89, 50, 8, 1, 0),
('Hidegkomlós', 'Pécsi sörfőzde', 50, 75, 250, 10, 1, 1),
('Lager', 'Pécsi sörfőzde', 60, 100, 250, 11, 1, 1),
('Mester', 'Borsodi sörgyár', 50, 50, 300, 13, 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `warehouse`
--

CREATE TABLE `warehouse` (
  `Id` int(11) NOT NULL,
  `City` int(11) NOT NULL,
  `Address` varchar(250) NOT NULL,
  `Visible` tinyint(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `warehouse`
--

INSERT INTO `warehouse` (`Id`, `City`, `Address`, `Visible`) VALUES
(1, 7261, 'Füredi Utca 23.', 1),
(2, 7261, 'Ltp. 14', 0),
(5, 7400, 'asdasdasdasdasdasdasd', 0),
(6, 7400, 'Fő Utca 1', 1);

-- --------------------------------------------------------

--
-- Table structure for table `worker`
--

CREATE TABLE `worker` (
  `FName` varchar(50) NOT NULL,
  `SName` varchar(50) NOT NULL,
  `WorkerId` int(11) NOT NULL,
  `ShiftLenght` tinyint(4) NOT NULL,
  `ShiftStart` int(11) NOT NULL,
  `FWarehouseID` int(11) NOT NULL,
  `Visible` tinyint(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `worker`
--

INSERT INTO `worker` (`FName`, `SName`, `WorkerId`, `ShiftLenght`, `ShiftStart`, `FWarehouseID`, `Visible`) VALUES
('Marcell', 'Fehér', 2, 8, 1600, 1, 1),
('Gábor', 'Fehér', 3, 4, 1600, 1, 1),
('Bence', 'Magyar', 4, 4, 600, 1, 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`ProductId`);

--
-- Indexes for table `warehouse`
--
ALTER TABLE `warehouse`
  ADD PRIMARY KEY (`Id`);

--
-- Indexes for table `worker`
--
ALTER TABLE `worker`
  ADD PRIMARY KEY (`WorkerId`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `product`
--
ALTER TABLE `product`
  MODIFY `ProductId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT for table `warehouse`
--
ALTER TABLE `warehouse`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `worker`
--
ALTER TABLE `worker`
  MODIFY `WorkerId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

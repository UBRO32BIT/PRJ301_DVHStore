/*******************************************************************************
	Drop database if exists
********************************************************************************/
USE master;
Go
IF EXISTS (SELECT 1 FROM sys.databases WHERE [name] = N'PRJ301_DVHStore')
BEGIN
    ALTER DATABASE PRJ301_DVHStore SET SINGLE_USER WITH ROLLBACK IMMEDIATE;
    DROP DATABASE PRJ301_DVHStore;
END;
Go
/*******************************************************************************
	Create and use the database
********************************************************************************/
CREATE DATABASE PRJ301_DVHStore
Go

USE PRJ301_DVHStore
Go

-- Check and drop the tblRoles table if it exists
IF OBJECT_ID('dbo.tblRoles', 'U') IS NOT NULL 
    DROP TABLE dbo.tblRoles;

-- Check and drop the tblUsers table if it exists
IF OBJECT_ID('dbo.tblUsers', 'U') IS NOT NULL 
    DROP TABLE dbo.tblUsers;

-- Check and drop the tblProductCategories table if it exists
IF OBJECT_ID('dbo.tblProductCategories', 'U') IS NOT NULL 
    DROP TABLE dbo.tblProductCategories;

-- Check and drop the tblProducts table if it exists
IF OBJECT_ID('dbo.tblProducts', 'U') IS NOT NULL 
    DROP TABLE dbo.tblProducts;

-- Check and drop the tblOrders table if it exists
IF OBJECT_ID('dbo.tblOrders', 'U') IS NOT NULL 
    DROP TABLE dbo.tblOrders;

-- Check and drop the tblOrderDetails table if it exists
IF OBJECT_ID('dbo.tblOrderDetails', 'U') IS NOT NULL 
    DROP TABLE dbo.tblOrderDetails;

-- User Roles Table
CREATE TABLE [dbo].[tblRoles] (
	[roleID] VARCHAR(10) NOT NULL,
	[roleName] VARCHAR(50) NOT NULL,
	PRIMARY KEY(roleID),
);
--Users Table
CREATE TABLE [dbo].[tblUsers] (
	[userID] BIGINT IDENTITY(1, 1) PRIMARY KEY NOT NULL,
	[userName] VARCHAR(20) UNIQUE NOT NULL,
	[fullName] NVARCHAR(50) NOT NULL,
	[password] NVARCHAR(255),
	[address] NVARCHAR(200),
	[phone] VARCHAR(20),
	[email] VARCHAR(100) NOT NULL,
	[roleID] VARCHAR(10) NOT NULL,
	[googleID] VARCHAR(100) NULL,
	[profileImage] NVARCHAR(1000) NULL,
	isDeleted BIT DEFAULT 0,
	FOREIGN KEY(roleID) REFERENCES [dbo].[tblRoles](roleID) 
);

--Product's Categories Table
CREATE TABLE [dbo].[tblProductCategories] (
	[categoryID] INT IDENTITY(1, 1) NOT NULL,
	[name] NVARCHAR(100) NOT NULL,
	PRIMARY KEY(categoryID),
);

--Products Table
CREATE TABLE [dbo].[tblProducts] (
	[productID] BIGINT IDENTITY(1, 1) NOT NULL,
	[name] NVARCHAR(200) NOT NULL,
	[description] NVARCHAR(1000) DEFAULT '',
	[price] MONEY NOT NULL,
	[quantity] INT NOT NULL,
	[categoryID] INT NOT NULL,
	[image] NVARCHAR(100) NULL,
	PRIMARY KEY(productID),
	FOREIGN KEY(categoryID) REFERENCES [dbo].[tblProductCategories](categoryID),
);
-- Orders table
CREATE TABLE [dbo].[tblOrders] (
	[orderID] BIGINT IDENTITY(1, 1) NOT NULL,
	[orderDate] DATETIME DEFAULT GETDATE(),
	[paymentMethod] VARCHAR(20) NOT NULL,
	[status] VARCHAR(20) NOT NULL,
	[userID] BIGINT NOT NULL,
	PRIMARY KEY(orderID),
	FOREIGN KEY(userID) REFERENCES [dbo].[tblUsers](userID),
);

-- Order details table
CREATE TABLE [dbo].[tblOrderDetails] (
	[detailID] BIGINT IDENTITY(1, 1) NOT NULL,
	[productID] BIGINT NOT NULL,
	[orderID] BIGINT NOT NULL,
	[quantity] INT NOT NULL,
	[price] MONEY NOT NULL,
	PRIMARY KEY(detailID),
	FOREIGN KEY(productID) REFERENCES [dbo].[tblProducts](productID),
	FOREIGN KEY(orderID) REFERENCES [dbo].[tblOrders](orderID),
);

-- Insert records into tblRoles
INSERT INTO [dbo].[tblRoles] ([roleID], [roleName])
VALUES ('AD', 'Admin'),
       ('US', 'User');

-- Insert records into tblUsers
INSERT INTO [dbo].[tblUsers] ([userName], [fullName], [password], [address], [phone], [email], [roleID], [profileImage])
VALUES ('ubro3', 'Minh Duc', '123456', N'48 Lê Duẩn, Long Thành, Đồng Nai', '0937294024', 'ubro32bit06122003@gmail.com', 'AD', NULL),
	   ('huynhthu', 'Huynh Anh Thu', '123456', N'Long Thành, Đồng Nai', '0394817248', 'thcute03@gmail.com', 'AD', NULL),
       ('phucnt40', 'Nguyen Tan Phuc', '123456', N'34 Nam Kì Khởi Nghĩa, Quận 1, TP.HCM', '987-654-3210', 'ubro32bit06122003@gmail.com', 'US', 'https://chuotcord.32mine.net/media/images/252_received_image.png'),
	   ('trangkocoten123', 'Tran Ngoc Doan Trang', '123456', N'1 Nguyễn Công Trứ, Phường 1, TP.Bảo Lộc, Lâm Đồng', '332-182-3821', 'ubro32bit06122003@gmail.com', 'US', NULL),
	   ('69LostAmbition', 'John Xina', '123456', N'15 Thùy Vân, Phường 8, TP.Vũng Tàu, BRVT', '123-282-1283', 'ubro32bit06122003@gmail.com', 'US', NULL);


-- Insert records into tblProductCategories
INSERT INTO [dbo].[tblProductCategories] ([name])
VALUES (N'Electronics'),
       (N'Đàm Vĩnh Hưng'),
       (N'Furniture'),
	   (N'Food');

-- Insert records into tblProducts
INSERT INTO [dbo].[tblProducts] ([name], [price], [quantity], [categoryID], [image])
VALUES (N'Laptop', 21900000, 10, 1, 'https://th.bing.com/th/id/OIP.Ze7VevS47fhQi5cVO-sMPAHaEU?pid=ImgDet&rs=1'),
       (N'Can Canh Dam Vinh Hung', 549000, 50, 2, 'https://ubro32bit.github.io/MyFirstWebpage/other-site/damvinhhung.jpg'),
	   (N'Dam Vinh Hung Khoe Buoi', 799000, 50, 2, 'https://ubro32bit.github.io/MyFirstWebpage/other-site/damvinhhung.jpg'),
	   (N'Dam Vinh Hung Nghich Chim', 999000, 0, 2, 'https://image.xahoi.com.vn/resize_580x1100/news/2013/10/22/64/dvh6jpg1382413081.jpg'),
       (N'Sofa', 1250000, 5, 3, 'https://th.bing.com/th/id/OIP.ntTgXwg_XfjH73P08XAAYQHaHa?pid=ImgDet&rs=1'),
	   (N'Hot Swap Mechanical Keyboard 87 Keys', 2599000, 30, 1, 'https://file-cdn.bzfuture.com/physical/f52431b23aa81bab8482fff540b02630_thumb_big.jpeg'), 
	   (N'Gà KFC Full Topping Nhà Làm', 1799000, 10, 4, 'https://meetmeprograms.hu/wp-content/uploads/2017/06/kfc-head.jpg');

-- Insert records into tblOrders
INSERT INTO [dbo].[tblOrders] ([paymentMethod], [status], [userID])
VALUES ('ZaloPay', 'SUCCESS', 1), -- Order by Admin (User1)
	   ('ZaloPay', 'FAILED', 1), -- Order by Admin (User1)
       ('COD', 'PENDING', 2); -- Order by User (User2)

-- Insert records into tblOrderDetails
INSERT INTO [dbo].[tblOrderDetails] ([productID], [orderID], [quantity], [price])
VALUES (1, 1, 2, 1599.98), -- Order by Admin (User1)
       (2, 2, 5, 99.95);   -- Order by User (User2)
USE [master]
GO
/****** Object:  Database [MoonCake]    Script Date: 10/18/2020 2:21:55 PM ******/
CREATE DATABASE [MoonCake]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'MoonCake', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.SQLEXPRESS\MSSQL\DATA\MoonCake.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'MoonCake_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.SQLEXPRESS\MSSQL\DATA\MoonCake_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
 WITH CATALOG_COLLATION = DATABASE_DEFAULT
GO
ALTER DATABASE [MoonCake] SET COMPATIBILITY_LEVEL = 150
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [MoonCake].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [MoonCake] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [MoonCake] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [MoonCake] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [MoonCake] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [MoonCake] SET ARITHABORT OFF 
GO
ALTER DATABASE [MoonCake] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [MoonCake] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [MoonCake] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [MoonCake] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [MoonCake] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [MoonCake] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [MoonCake] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [MoonCake] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [MoonCake] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [MoonCake] SET  DISABLE_BROKER 
GO
ALTER DATABASE [MoonCake] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [MoonCake] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [MoonCake] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [MoonCake] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [MoonCake] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [MoonCake] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [MoonCake] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [MoonCake] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [MoonCake] SET  MULTI_USER 
GO
ALTER DATABASE [MoonCake] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [MoonCake] SET DB_CHAINING OFF 
GO
ALTER DATABASE [MoonCake] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [MoonCake] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [MoonCake] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [MoonCake] SET QUERY_STORE = OFF
GO
USE [MoonCake]
GO
/****** Object:  Table [dbo].[tbl_Account_1]    Script Date: 10/18/2020 2:21:55 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tbl_Account_1](
	[email] [nvarchar](100) NOT NULL,
	[password] [nvarchar](50) NULL,
	[name] [nvarchar](70) NULL,
	[avatar] [nvarchar](500) NULL,
	[role] [nvarchar](50) NULL,
	[address] [nvarchar](300) NULL,
	[phoneNo] [nvarchar](20) NULL,
	[status] [nvarchar](50) NULL,
	[dateOfCreate] [datetime] NULL,
 CONSTRAINT [PK_tbl_Account_1] PRIMARY KEY CLUSTERED 
(
	[email] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tbl_Cake]    Script Date: 10/18/2020 2:21:55 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tbl_Cake](
	[cakeID] [nvarchar](100) NOT NULL,
	[price] [float] NULL,
	[image] [nvarchar](250) NULL,
	[dateOfCreate] [datetime] NULL,
	[quantity] [int] NULL,
	[expirationDate] [datetime] NULL,
	[category] [nvarchar](50) NULL,
	[name] [nvarchar](100) NULL,
	[status] [nvarchar](50) NULL,
 CONSTRAINT [PK_tbl_Cake] PRIMARY KEY CLUSTERED 
(
	[cakeID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tbl_Category]    Script Date: 10/18/2020 2:21:55 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tbl_Category](
	[categoryID] [nvarchar](50) NOT NULL,
	[category] [nvarchar](50) NULL,
 CONSTRAINT [PK_tbl_Category] PRIMARY KEY CLUSTERED 
(
	[categoryID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tbl_Order]    Script Date: 10/18/2020 2:21:55 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tbl_Order](
	[orderID] [nvarchar](100) NOT NULL,
	[email] [nvarchar](100) NULL,
	[phoneNo] [nvarchar](100) NULL,
	[address] [nvarchar](150) NULL,
	[dateOfCreate] [datetime] NULL,
	[total] [float] NULL,
	[name] [nvarchar](50) NULL,
 CONSTRAINT [PK_tbl_Order] PRIMARY KEY CLUSTERED 
(
	[orderID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tbl_OrderDetail]    Script Date: 10/18/2020 2:21:55 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tbl_OrderDetail](
	[detailID] [nvarchar](100) NOT NULL,
	[orderID] [nvarchar](100) NOT NULL,
	[cakeID] [nvarchar](100) NOT NULL,
	[quantity] [int] NULL,
	[dateOfCreate] [datetime] NULL,
 CONSTRAINT [PK_tbl_OrderDetail] PRIMARY KEY CLUSTERED 
(
	[detailID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tbl_Payment]    Script Date: 10/18/2020 2:21:55 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tbl_Payment](
	[method] [nvarchar](50) NULL,
	[orderID] [nvarchar](100) NOT NULL,
 CONSTRAINT [PK_tbl_Payment] PRIMARY KEY CLUSTERED 
(
	[orderID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tbl_Update]    Script Date: 10/18/2020 2:21:55 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tbl_Update](
	[lastUpdateDate] [datetime] NULL,
	[email] [nvarchar](100) NULL,
	[cakeID] [nvarchar](100) NULL,
	[updateID] [nvarchar](150) NOT NULL,
 CONSTRAINT [PK_tbl_Update] PRIMARY KEY CLUSTERED 
(
	[updateID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
ALTER TABLE [dbo].[tbl_Cake]  WITH CHECK ADD  CONSTRAINT [FK_tbl_Cake_tbl_Category] FOREIGN KEY([category])
REFERENCES [dbo].[tbl_Category] ([categoryID])
GO
ALTER TABLE [dbo].[tbl_Cake] CHECK CONSTRAINT [FK_tbl_Cake_tbl_Category]
GO
ALTER TABLE [dbo].[tbl_Order]  WITH CHECK ADD  CONSTRAINT [FK_tbl_Order_tbl_Account_1] FOREIGN KEY([email])
REFERENCES [dbo].[tbl_Account_1] ([email])
GO
ALTER TABLE [dbo].[tbl_Order] CHECK CONSTRAINT [FK_tbl_Order_tbl_Account_1]
GO
ALTER TABLE [dbo].[tbl_OrderDetail]  WITH CHECK ADD  CONSTRAINT [FK_tbl_OrderDetail_tbl_Cake] FOREIGN KEY([cakeID])
REFERENCES [dbo].[tbl_Cake] ([cakeID])
GO
ALTER TABLE [dbo].[tbl_OrderDetail] CHECK CONSTRAINT [FK_tbl_OrderDetail_tbl_Cake]
GO
ALTER TABLE [dbo].[tbl_OrderDetail]  WITH CHECK ADD  CONSTRAINT [FK_tbl_OrderDetail_tbl_Order] FOREIGN KEY([orderID])
REFERENCES [dbo].[tbl_Order] ([orderID])
GO
ALTER TABLE [dbo].[tbl_OrderDetail] CHECK CONSTRAINT [FK_tbl_OrderDetail_tbl_Order]
GO
ALTER TABLE [dbo].[tbl_Payment]  WITH CHECK ADD  CONSTRAINT [FK_tbl_Payment_tbl_Order] FOREIGN KEY([orderID])
REFERENCES [dbo].[tbl_Order] ([orderID])
GO
ALTER TABLE [dbo].[tbl_Payment] CHECK CONSTRAINT [FK_tbl_Payment_tbl_Order]
GO
ALTER TABLE [dbo].[tbl_Update]  WITH CHECK ADD  CONSTRAINT [FK_tbl_Update_tbl_Account_1] FOREIGN KEY([email])
REFERENCES [dbo].[tbl_Account_1] ([email])
GO
ALTER TABLE [dbo].[tbl_Update] CHECK CONSTRAINT [FK_tbl_Update_tbl_Account_1]
GO
ALTER TABLE [dbo].[tbl_Update]  WITH CHECK ADD  CONSTRAINT [FK_tbl_Update_tbl_Cake] FOREIGN KEY([cakeID])
REFERENCES [dbo].[tbl_Cake] ([cakeID])
GO
ALTER TABLE [dbo].[tbl_Update] CHECK CONSTRAINT [FK_tbl_Update_tbl_Cake]
GO
USE [master]
GO
ALTER DATABASE [MoonCake] SET  READ_WRITE 
GO

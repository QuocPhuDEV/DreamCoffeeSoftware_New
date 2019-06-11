CREATE DATABASE coffeemanager

USE coffeemanager

-- Create table Account
CREATE TABLE ACCOUNT
(ID_Account int PRIMARY KEY,
Username varchar(10) not null,
Passwords varchar(20) not null,
Permission varchar(15) not null
)

-- Create table Customer
CREATE TABLE CUSTOMER
(ID_Customer int PRIMARY KEY,
ID_Account int,
Customername Nvarchar(50),
Birthofdate varchar(15),
sex varchar(10),
Phonenumber varchar(15),
Email varchar(20),
CusAddress Nvarchar(50),
C_coint int
)

--Create table Employee
CREATE TABLE EMPLOYEE
(ID_Employee int PRIMARY KEY,
ID_Account int,
ID_Position int,
Employeename Nvarchar(50),
Birthofdate varchar(15),
sex varchar(10),
Phonenumber varchar(15),
Email varchar(20),
CusAddress Nvarchar(50),
)

--Create table posstion
CREATE TABLE POSITION
(ID_Position int PRIMARY KEY,
Position varchar(20)
)

-- Create table Type Drinks
CREATE TABLE TYPEDRINKS
(ID_Type int PRIMARY KEY,
Typename Nvarchar(30)
)

--Create table menu
CREATE TABLE MENU
(ID_Drink int PRIMARY KEY,
ID_Type int,
Drinks Nvarchar(40),
Price int,
Inventory int
) 

--Create table Table
CREATE TABLE NUMTABLE
(ID_table int PRIMARY KEY,
Numtable int
)

-- Create table Voucher
CREATE TABLE VOUCHER
(ID_Voucher int PRIMARY KEY,
Voucher varchar(20),
NumPercent int
)

--Create table Sale
CREATE TABLE SALE
(ID_Sale int PRIMARY KEY,
Datesale varchar(20),
PercentSale int
)

-- Create table Bill
CREATE TABLE BILL
(ID_Bil int PRIMARY KEY,
ID_Customer int,
ID_Drink int,
ID_table int,
ID_voucher int,
ID_Sale int,
ID_Services int
)
DROP TABLE BILL

--Create table Details bill
CREATE TABLE DETAILSBILL
(ID_BIll int,
Date varchar(20),
Customername Nvarchar(50),
Numtable int,
Drinks Nvarchar(40),
Number int,
Price int,
Voucher int,
Servicename Nvarchar(50),
Sale int,
Total int,
Cus_give int,
Give_back int
)

--Create table Services
CREATE TABLE SERVICES
(ID_Services int,
Servicename Nvarchar(50),
Describe Nvarchar(300),
)

--Create table Services
CREATE TABLE REQUEST
(Numtable int,
Request varchar(10)
)
---------------------------------------------------
CREATE TABLE tblFCM(
id int identity(1,1) primary key,
Token nvarchar(max) null)

----------------------------
-- Insert data to ACCOUNT
INSERT INTO ACCOUNT VALUES(1,'customer','customer','customer');
INSERT INTO ACCOUNT VALUES(2,'employee','employee','employee');
INSERT INTO ACCOUNT VALUES(3,'manager','manager','manager');
INSERT INTO ACCOUNT VALUES(4,'phuhq','phuhq','manager');

-- Insert data to CUSTOMER
INSERT INTO CUSTOMER VALUES(1,1,N'Hoàng Quốc Phú','28/07/1995','Male','01648753257','phuhq95@gmail.com',N'Hóc Môn - TP.HCM',100);
INSERT INTO CUSTOMER VALUES(2,3,N'Cristinal Ronaldo','25/05/1985','Male','094617468','ronaldo@gmail.com',N'Quận 4 - TP.HCM',500);
INSERT INTO CUSTOMER VALUES(3,2,N'Lionel Messi','23/06/1989','Male','01234876744','messi@gmail.com',N'Bà Rịa - Vũng Tàu',250);

--Insert data to VOUCHER
INSERT INTO VOUCHER VALUES (1,'voucher001',20);
INSERT INTO VOUCHER VALUES (2,'voucher002',10);
INSERT INTO VOUCHER VALUES (3,'voucher003',5);

--Insert data to SALE
INSERT INTO SALE VALUES(1,'27/04/2018',10)
INSERT INTO SALE VALUES(2,'1/05/2018',15)
INSERT INTO SALE VALUES(3,'7/06/2018',20)

--Insert data to NUMTABLE
INSERT INTO NUMTABLE VALUES(1,01)
INSERT INTO NUMTABLE VALUES(2,02)
INSERT INTO NUMTABLE VALUES(3,03)

--Insert data to TYPEDRINKS
INSERT INTO TYPEDRINKS VALUES(1,'Cafe')
INSERT INTO TYPEDRINKS VALUES(2,N'Nước Ngọt')
INSERT INTO TYPEDRINKS VALUES(3,N'Sinh Tố')

--Insert data to MENU
INSERT INTO MENU VALUES(1,1,N'Cafe sữa',20000,0)
INSERT INTO MENU VALUES(2,1,N'Cafe đá',15000,0)
INSERT INTO MENU VALUES(3,2,N'String dâu',15000,150)
INSERT INTO MENU VALUES(4,1,N'Sữa Nutri',10000,3)
INSERT INTO MENU VALUES(5,1,N'Sinh tố bơ',25000,8)
INSERT INTO MENU VALUES(6,2,N'Monster',30000,100)
INSERT INTO MENU VALUES(7,1,N'Cafe sữa đá',15000,10)
INSERT INTO MENU VALUES(8,3,N'Sinh tố xoài',25000,0)
INSERT INTO MENU VALUES(9,2,N'String vàng',15000,100)
INSERT INTO MENU VALUES(10,1,N'Rau câu dừa',20000,10)
INSERT INTO MENU VALUES(11,3,N'Trái cây tô',25000,30)
INSERT INTO MENU VALUES(12,2,N'Numberone',15000,100)

--Insert data to POSITION
INSERT INTO POSITION VALUES(1,'Employee')
INSERT INTO POSITION VALUES(2,'Manager')

-- Insert data to EMPLOYEE
INSERT INTO EMPLOYEE VALUES(101,3,2,N'Hoàng Quốc Phú','28/07/1995','Male','01648753257','phuhq95@gmail.com',N'Hóc Môn - TP.HCM');
INSERT INTO EMPLOYEE VALUES(102,3,1,N'Nguyễn Hữu Vũ','2/9/1992','Male','0123894467','huuvu@gmail.com',N'Tân Bình - TP.HCM');
INSERT INTO EMPLOYEE VALUES(103,1,1,N'Cristial Ronaldo','25/05/1985','Male','0123487645','ronaldo@gmail.com',N'Củ Chi - TP.HCM');

--Insert data to BILL
INSERT INTO BILL VALUES(1,1,2,2,3,2)
INSERT INTO BILL VALUES(2,3,1,2,1,2)
INSERT INTO BILL VALUES(3,2,3,1,2,3)

--Insert data to DETAILSBILL
INSERT INTO DETAILSBILL VALUES(1,'27/04/2018',N'Hoàng Quốc Phú',2,N'Cafe đá',1,15000,0,0,15000,50000,35000);
INSERT INTO DETAILSBILL VALUES(2,'27/04/2018',N'Nguyễn Hữu Vũ',3,N'Cafe sữa',2,20000,0,0,40000,100000,60000);
INSERT INTO DETAILSBILL VALUES(3,'27/04/2018',N'Hoàng Quốc Phú',1,N'Cafe đá',1,15000,0,0,15000,50000,35000);

--Insert data to SERVICES
INSERT INTO SERVICES VALUES (1,N'Tổ chức sinh nhật',N'Quán sẽ phục vụ tổ chức tiệc sinh nhật theo yêu cầu của khách hàng, bao gồm trang trí, đồ ăn thức uống nhẹ và chụp hình lưu niệm');
INSERT INTO SERVICES VALUES (2,N'Tổ chức team work',N'Quán sẽ có không gian riêng dành cho các bạn học sinh, sinh viên hay nhân viên văn phòng có nhu cầu học và làm việc nhóm');
INSERT INTO SERVICES VALUES (3,N'Tiệc ngọt - drinks party',N'Sẽ có party nhỏ gồm các đồ uống và thức ăn nhẹ phục vụ các bạn với giá combo cực kỳ ưu đãi');
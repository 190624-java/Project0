--Add Accounts
--Passwords are same, because passwords column does not have to
--be unique. These users are assumed to be the very rare case 
--where they happed to guess passwords during their account
--creations, that were the same.
--For development purposes, the tables can be populated quicker
--for testing, by using this assumption on non-unique fields
--of other tables.

--Admin
INSERT INTO MYADMIN.users (userdriversid,PASSWORD, TYPE) VALUES (10000000, 'Admin', 1);
--employee
INSERT INTO MYADMIN.users VALUES(00000006, 'LacYCLoC', 5);
INSERT INTO MYADMIN.users VALUES(00000007, 'LacYCLoC', 5);
INSERT INTO MYADMIN.users VALUES(00000008, 'LacYCLoC', 5);
INSERT INTO MYADMIN.users VALUES(00000009, 'LacYCLoC', 5);
INSERT INTO MYADMIN.users VALUES(00000010, 'LacYCLoC', 5);
--customer
INSERT INTO MYADMIN.users VALUES(00000001, 'sIblerOt', 10);
INSERT INTO MYADMIN.users VALUES(00000002, 'sIblerOt', 10);

INSERT INTO MYADMIN.users VALUES(00000003, 'sIblerOt', 10);
INSERT INTO MYADMIN.users VALUES(00000004, 'sIblerOt', 10);
INSERT INTO MYADMIN.users VALUES(00000005, 'sIblerOt', 10);


--Populate Car Lots

--Of Dealership
INSERT INTO MYADMIN.cars VALUES(100001, 10001, 1, 1, NULL,1000);
INSERT INTO MYADMIN.cars VALUES(100002, 10002, 2, 1, NULL,1001);

--DEMO-Insert, Verify in Cars
INSERT INTO MYADMIN.cars VALUES(100003, 10003, 1, 1, NULL,1000);
INSERT INTO MYADMIN.cars VALUES(100004, 10004, 2, 1, NULL,1001);

--Of Customers


------------------------------
--      Connecting
------------------------------
--Java: getPassword(int userDriversID)
--SQL: 
SELECT password FROM MyAdmin.Users WHERE userDriversID = 10000000;


------------------------------
--      Viewing Cars
------------------------------
--Java: getUserLot()
--DAO: getAllCars()
SELECT * FROM MyAdmin.Cars;


--Java: viewDealershipLot()
--DAO: getDealerCars()
SELECT * FROM MyAdmin.Cars WHERE ownerID = 10000000;



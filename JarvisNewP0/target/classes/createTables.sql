CREATE TABLE Users (
    UserDriversID NUMBER(16),
    Password VARCHAR2(40),
    Type INT,
PRIMARY KEY (UserDriversID)
);

CREATE TABLE Cars (
    CarID NUMBER(8),
    RegID NUMBER(17),
    OwnerID NUMBER(8),
    SpaceID NUMBER(8),
    OffersID NUMBER(10),
    ContractID NUMBER(8),
PRIMARY KEY (CarID),
CONSTRAINT FK_OwnerOfCar FOREIGN KEY (OwnerID)
REFERENCES Users(UserDriversID)
);

CREATE TABLE CarReges (
    RegID NUMBER(17),
    Make VARCHAR2(40),
    Model VARCHAR2(40),
    Year INT,
    MSRP FLOAT,
    CarID NUMBER,
PRIMARY KEY (CarID),
CONSTRAINT FK_CarRegistration FOREIGN KEY (CarID)
REFERENCES Cars(CarID)
);

CREATE TABLE Contracts (
    ContractID NUMBER,
    CarID NUMBER,
    SoldOffer FLOAT,
    Balance FLOAT,
    Terms INT,
PRIMARY KEY (ContractID),
CONSTRAINT FK_CarContract FOREIGN KEY (CarID)
REFERENCES Cars(CarID)
);

CREATE TABLE Offers (
    OfferID NUMBER,
    Amount FLOAT,
    Term INT,
    APR FLOAT,
    OffereeID NUMBER,
    ProductID NUMBER,
PRIMARY KEY (OfferID,ProductID),
CONSTRAINT FK_OffereeOffer FOREIGN KEY (OffereeID)
REFERENCES Users(UserDriversID),
CONSTRAINT FK_Product FOREIGN KEY (ProductID)
REFERENCES Cars(CarID)
);

CREATE TABLE Payments (
    PaymentID NUMBER,
    ContractID NUMBER,
    Amount FLOAT,
    PayDate DATE,
PRIMARY KEY (PaymentID,ContractID),
CONSTRAINT FK_ContractPayment FOREIGN KEY (ContractID)
REFERENCES Contracts(ContractID)
);
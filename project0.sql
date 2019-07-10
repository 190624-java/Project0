-- Car Table
CREATE TABLE Car (
    car_id INTEGER NOT NULL,
    brand VARCHAR2(20),
    model VARCHAR2(20),
    color VARCHAR2(20),
    year INTEGER NOT NULL,
    price INTEGER NOT NULL,
    mileage INTEGER NOT NULL,
    has_offer CHAR(1),
    current_offer INTEGER,
    remaining_price INTEGER,
    owner VARCHAR2(20),
    
    PRIMARY KEY(car_id)
);

--Customer Table
CREATE TABLE Customer (
    user_name VARCHAR2(20),
    login_pin INTEGER,
    
    PRIMARY KEY(user_name)
);

--Offer Table
CREATE TABLE Offer (
    customer VarChar2(20),
    car_id INTEGER,
    offer_amount INTEGER,
    offer_status VARCHAR2(20),
    
    PRIMARY KEY (customer, car_id)
);
    

SELECT * FROM Car;

--SoldCars table not used
CREATE TABLE SoldCars (
    car_id INTEGER,
    brand VARCHAR2(20),
    model VARCHAR2(20),
    color VARCHAR2(20),
    year INTEGER,
    price INTEGER,
    mileage INTEGER,
    remaining_price INTEGER,
    owner VARCHAR2(20),
    
    PRIMARY KEY(car_id)
);

-- Offer Table
CREATE TABLE Offer (
    customer VARCHAR2(20),
    car_id INTEGER,
    offer_amount INTEGER,
    offer_status VARCHAR2(20)
);

CREATE TABLE Employee (
    user_name VARCHAR2(20),
    login_pin INTEGER,
    
    PRIMARY KEY (user_name)
);

INSERT INTO Employee (user_name, login_pin)
VALUES ('user', 333);

SELECT * FROM employee;

SELECT * FROM Employee WHERE user_name = 'user' AND login_pin = 333;

ALTER TABLE employee
ADD PRIMARY KEY(user_name);

SELECT * FROM Car;

SELECT * FROM Car

WHERE offer_status = 'Pending';

UPDATE Car SET owner WHERE owner = ben;

DELETE FROM Car WHERE car_id = 3; -- AND  car_id = 3;
DELETE FROM Offer WHERE car_id = 3; -- AND  car_id = 3;


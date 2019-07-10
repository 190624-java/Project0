--drop table offers;
--drop table cars;
--drop table users;



--Creating tables
Create Table Users (
    user_id Number(8) Primary Key,
    username Varchar2(30) Unique Not Null,
    user_password varchar2(30) Not Null,
    is_employ Number (1) Not Null
);
/
Create Table Cars (
    car_id Number(8) Primary Key,
    car_make Varchar2(30) Not Null,
    car_model Varchar2(30) Not Null,
    num_payments Number(2),
    monthly_payment Number(8,2),
    owner_id Number(8),
    FOREIGN Key(owner_id)  References Users (user_id)
);
/
Create Table Offers (
    customer_id Number(8),
    car_id Number(8),
    offer_amount Number(8,2) Not Null,
    Primary Key(customer_id, car_id),
    Foreign Key (customer_id) References Users(user_id),
    Foreign Key (car_id) References Cars(car_id)
);
/
--Creating ID sequence for cars and users
CREATE SEQUENCE user_id_seq
    START WITH 1
    INCREMENT BY 1
;
/
--drop sequence user_id_seq;

CREATE SEQUENCE car_id_seq
    START WITH 1
    INCREMENT BY 1
;
/
--drop sequence car_id_seq;
--create triggers to assign ids
CREATE or Replace TRIGGER user_id_trigger
BEFORE INSERT
ON Users
FOR EACH ROW
BEGIN
    SELECT user_id_seq.nextval INTO :new.user_id
    FROM dual;
END;
/
CREATE or Replace TRIGGER car_id_trigger
BEFORE INSERT
ON Cars
FOR EACH ROW
BEGIN
    SELECT car_id_seq.nextval INTO :new.car_id
    FROM dual;
END;
/
--adding the dealership owners user information

SHOW ERRORS TRIGGER user_id_trigger;


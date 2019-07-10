ALTER TABLE car_lot MODIFY initial_cost NUMBER(9,2);
ALTER TABLE cars_paying MODIFY owed NUMBER(9,2);

CREATE TABLE car_lot
(
car_id NUMBER PRIMARY KEY,
make VARCHAR2(20),
car_model VARCHAR2(32),
initial_cost NUMBER(9,2)
);

CREATE TABLE user_account
(
username VARCHAR2(50),
user_password VARCHAR2(20),
access_level VARCHAR2(8),
user_id NUMBER(33) PRIMARY KEY
);

CREATE TABLE cars_paying
(
car_id NUMBER(5) PRIMARY KEY,
user_id NUMBER(33),
selling_price NUMBER (7,2),
make VARCHAR2(20),
car_model VARCHAR2(32),
owed NUMBER(7,2),
pay_period NUMBER(2) DEFAULT -1,
date_init DATE DEFAULT sysdate,
date_pay DATE,
FOREIGN KEY(user_id) REFERENCES user_account(user_id)
);

CREATE TABLE cars_sold
(
account_id NUMBER(33) PRIMARY KEY,
selling_price NUMBER (7,2),
make VARCHAR2(20),
car_model VARCHAR2(32),
date_purchased DATE,
date_paid_off DATE,
FOREIGN KEY(account_id) REFERENCES user_account(user_id)
);

CREATE TABLE offers
(
user_id NUMBER(33),
car_id NUMBER,
offer_value NUMBER,
PRIMARY KEY(user_id, car_id),
FOREIGN KEY(user_id) REFERENCES user_account(user_id)
);


CREATE SEQUENCE car_id_seq
    START WITH 1
    INCREMENT BY 1
;

CREATE TRIGGER car_id_increment
BEFORE INSERT
ON car_lot
FOR EACH ROW
BEGIN
    SELECT car_id_seq.nextval INTO :new.car_id
    FROM dual;
END;


CREATE TRIGGER offer_synchro
BEFORE DELETE
ON car_lot
FOR EACH ROW
BEGIN
    DELETE FROM offers WHERE car_id = :OLD.car_id;
END;
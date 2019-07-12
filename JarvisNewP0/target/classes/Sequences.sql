--Project0 - DealershipSystem, table incrementers
/* 
Auto increments the car id, when one is created
*/
CREATE SEQUENCE car_it
    START WITH 1
    INCREMENT BY 1
;
CREATE OR REPLACE TRIGGER car_it
BEFORE INSERT ON Cars
FOR EACH ROW
BEGIN
    IF :new.CarID IS NULL
    THEN
        SELECT customerIT.nextval INTO :new.CarID FROM dual;
    END IF;
END;

/* 
Auto increments the Customer's UserDriversID, when one is created
*/
CREATE SEQUENCE customerIT
    START WITH 1
    INCREMENT BY 1
;
CREATE OR REPLACE TRIGGER customerIT
BEFORE INSERT ON Users
FOR EACH ROW
BEGIN
    IF :new.UserDriversID IS NULL
    THEN
        SELECT customerIT.nextval INTO :new.UserDriversID FROM dual;
    END IF;
END;